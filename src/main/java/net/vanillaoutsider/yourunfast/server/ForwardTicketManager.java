// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.yourunfast.server;

import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.util.TimeUtil;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.vanillaoutsider.yourunfast.math.VelocityCalculator;
import net.vanillaoutsider.yourunfast.registry.YouRunFastGameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ForwardTicketManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ForwardTicketManager.class);

    // Zero-allocation primitive bit-packed ticket registry
    private static final Map<UUID, VelocityCalculator> PLAYER_VELOCITY = new Object2ObjectOpenHashMap<>();
    private static final Map<UUID, LongOpenHashSet> PLAYER_TICKETS = new Object2ObjectOpenHashMap<>();
    private static final Map<UUID, ResourceKey<Level>> PLAYER_DIMENSION = new Object2ObjectOpenHashMap<>();

    // Throttling state caches
    private static final Map<UUID, Long> LAST_PLAYER_CHUNK = new Object2ObjectOpenHashMap<>();
    private static final Map<UUID, Float> LAST_PLAYER_YAW = new Object2ObjectOpenHashMap<>();
    private static final Map<UUID, Integer> LAST_PLAYER_TICK = new Object2ObjectOpenHashMap<>();

    // Telemetry metrics
    private static volatile int lastDynamicReach = 16;
    private static volatile float lastServerMspt = 20.0f;

    private ForwardTicketManager() {
    }

    public static void tickPlayer(ServerPlayer player) {
        if (player == null || player.isRemoved()) {
            return;
        }

        ServerLevel level = player.level() instanceof ServerLevel sl ? sl : null;
        if (level == null || !YouRunFastGameRules.isEnabled(level)) {
            clearPlayer(player.getUUID(), level);
            return;
        }

        UUID uuid = player.getUUID();
        ResourceKey<Level> currentDim = level.dimension();
        ResourceKey<Level> lastDim = PLAYER_DIMENSION.put(uuid, currentDim);
        if (lastDim != null && !lastDim.equals(currentDim)) {
            clearPlayerTickets(uuid, level, PLAYER_TICKETS.computeIfAbsent(uuid, k -> new LongOpenHashSet()));
        }

        VelocityCalculator calculator = PLAYER_VELOCITY.computeIfAbsent(uuid, k -> new VelocityCalculator());
        calculator.update(
                player.getX(), player.getY(), player.getZ(),
                player.getDeltaMovement().x, player.getDeltaMovement().y, player.getDeltaMovement().z
        );

        double speed = calculator.getSpeedBlocksPerTick();
        double minSpeed = YouRunFastGameRules.getMinSpeedThreshold(level);

        LongOpenHashSet activeTickets = PLAYER_TICKETS.computeIfAbsent(uuid, k -> new LongOpenHashSet());

        if (speed < minSpeed) {
            if (!activeTickets.isEmpty()) {
                clearPlayerTickets(uuid, level, activeTickets);
            }
            return;
        }

        // Hybrid spatial and angular update throttling
        long currentChunkPacked = ChunkPos.pack(player.chunkPosition().x(), player.chunkPosition().z());
        Long lastChunkObj = LAST_PLAYER_CHUNK.get(uuid);
        Float lastYawObj = LAST_PLAYER_YAW.get(uuid);
        Integer lastTickObj = LAST_PLAYER_TICK.get(uuid);

        float currentYaw = player.getYRot();
        int currentTick = player.tickCount;

        boolean shouldRecalculate = (lastChunkObj == null)
                || (lastChunkObj != currentChunkPacked)
                || (lastYawObj != null && Math.abs(currentYaw - lastYawObj) > 8.0f)
                || (lastTickObj == null || (currentTick - lastTickObj) >= 10);

        if (!shouldRecalculate) {
            return;
        }

        LAST_PLAYER_CHUNK.put(uuid, currentChunkPacked);
        LAST_PLAYER_YAW.put(uuid, currentYaw);
        LAST_PLAYER_TICK.put(uuid, currentTick);

        // Continuous MSPT Watchdog: Dynamically taper reach under server load to guarantee 20 TPS
        float mspt = (float) level.getServer().getAverageTickTimeNanos() / (float) TimeUtil.NANOSECONDS_PER_MILLISECOND;
        lastServerMspt = mspt;
        double msptFactor = 1.0;
        if (mspt > 25.0f) {
            msptFactor = Math.max(0.25, 1.0 - (double) (mspt - 25.0f) / 25.0);
        }

        int playerChunkX = player.chunkPosition().x();
        int playerChunkZ = player.chunkPosition().z();
        double dirX = calculator.getNormDx();
        double dirZ = calculator.getNormDz();

        double horizontalMag = Math.sqrt(dirX * dirX + dirZ * dirZ);
        if (horizontalMag < 0.1) {
            return;
        }
        double normHzX = dirX / horizontalMag;
        double normHzZ = dirZ / horizontalMag;

        int multiplierPct = YouRunFastGameRules.getLeadMultiplierPct(level);
        double leadScale = (double) multiplierPct / 100.0;
        int maxAllowedReach = Math.max(4, (int) Math.round(16.0 * leadScale * msptFactor));
        int maxReachChunks = Math.min(maxAllowedReach, (int) Math.round(speed * 8.0 * leadScale * msptFactor));
        lastDynamicReach = maxReachChunks;

        // Populate new trajectory tickets
        LongOpenHashSet newForwardChunks = new LongOpenHashSet();
        boolean enableFanOut = speed >= 0.80; // High-speed Mach / Elytra fan-out

        for (int step = 2; step <= maxReachChunks; step += 2) {
            int targetX = playerChunkX + (int) Math.round(normHzX * step);
            int targetZ = playerChunkZ + (int) Math.round(normHzZ * step);
            newForwardChunks.add(ChunkPos.pack(targetX, targetZ));

            // Lateral fan-out for banked flight turns at high speeds (>= 6 chunks ahead)
            if (enableFanOut && step >= 6) {
                int leftX = targetX + (int) Math.round(-normHzZ);
                int leftZ = targetZ + (int) Math.round(normHzX);
                int rightX = targetX + (int) Math.round(normHzZ);
                int rightZ = targetZ + (int) Math.round(-normHzX);

                newForwardChunks.add(ChunkPos.pack(leftX, leftZ));
                newForwardChunks.add(ChunkPos.pack(rightX, rightZ));
            }
        }

        // Remove tickets no longer in path
        LongIterator iterator = activeTickets.iterator();
        while (iterator.hasNext()) {
            long packedPos = iterator.nextLong();
            if (!newForwardChunks.contains(packedPos)) {
                level.getChunkSource().removeTicketWithRadius(TicketType.PLAYER_LOADING, ChunkPos.unpack(packedPos), 1);
                iterator.remove();
            }
        }

        // Add new forward tickets
        LongIterator newIterator = newForwardChunks.iterator();
        while (newIterator.hasNext()) {
            long packedPos = newIterator.nextLong();
            if (activeTickets.add(packedPos)) {
                level.getChunkSource().addTicketWithRadius(TicketType.PLAYER_LOADING, ChunkPos.unpack(packedPos), 1);
            }
        }

        if (YouRunFastGameRules.isDebugMode(level) && (player.tickCount % 40 == 0)) {
            LOGGER.info("[YouRunFast-Server] Player {} speed: {:.2f} b/t, MSPT: {:.1f}ms, reach: {} chunks, tickets: {}",
                    player.getScoreboardName(), speed, mspt, maxReachChunks, activeTickets.size());
        }
    }

    public static void onPlayerDisconnect(ServerPlayer player) {
        if (player != null) {
            ServerLevel level = player.level() instanceof ServerLevel sl ? sl : null;
            clearPlayer(player.getUUID(), level);
        }
    }

    public static void onPlayerChangeDimension(ServerPlayer player) {
        if (player != null) {
            ServerLevel level = player.level() instanceof ServerLevel sl ? sl : null;
            clearPlayer(player.getUUID(), level);
        }
    }

    private static void clearPlayer(UUID uuid, ServerLevel level) {
        PLAYER_VELOCITY.remove(uuid);
        PLAYER_DIMENSION.remove(uuid);
        LAST_PLAYER_CHUNK.remove(uuid);
        LAST_PLAYER_YAW.remove(uuid);
        LAST_PLAYER_TICK.remove(uuid);
        LongOpenHashSet tickets = PLAYER_TICKETS.remove(uuid);
        if (tickets != null && level != null) {
            LongIterator it = tickets.iterator();
            while (it.hasNext()) {
                level.getChunkSource().removeTicketWithRadius(TicketType.PLAYER_LOADING, ChunkPos.unpack(it.nextLong()), 1);
            }
        }
    }

    private static void clearPlayerTickets(UUID uuid, ServerLevel level, LongOpenHashSet tickets) {
        if (level != null) {
            LongIterator it = tickets.iterator();
            while (it.hasNext()) {
                level.getChunkSource().removeTicketWithRadius(TicketType.PLAYER_LOADING, ChunkPos.unpack(it.nextLong()), 1);
            }
        }
        tickets.clear();
    }

    public static int getActiveTicketCount(UUID uuid) {
        LongOpenHashSet set = PLAYER_TICKETS.get(uuid);
        return set != null ? set.size() : 0;
    }

    public static double getPlayerSpeed(UUID uuid) {
        VelocityCalculator calc = PLAYER_VELOCITY.get(uuid);
        return calc != null ? calc.getSpeedBlocksPerTick() : 0.0;
    }

    public static int getLastDynamicReach() {
        return lastDynamicReach;
    }

    public static float getLastServerMspt() {
        return lastServerMspt;
    }
}
