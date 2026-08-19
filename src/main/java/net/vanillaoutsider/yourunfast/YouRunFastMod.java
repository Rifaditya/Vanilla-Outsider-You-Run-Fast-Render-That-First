// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.yourunfast;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.level.ServerPlayer;
import net.vanillaoutsider.yourunfast.command.YouRunFastCommand;
import net.vanillaoutsider.yourunfast.registry.YouRunFastGameRules;
import net.vanillaoutsider.yourunfast.server.ForwardTicketManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class YouRunFastMod implements ModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(YouRunFastMod.class);
    public static final String MOD_ID = "you-run-fast-render-that-first";
    public static final String MOD_NAME = "You Run Fast, Render That First";

    @Override
    public void onInitialize() {
        LOGGER.info("[YouRunFast] Initializing {}", MOD_NAME);

        // ModVersionGuard check
        ModVersionGuard.checkClass(MOD_NAME, "net.minecraft.world.level.gamerules.GameRules");

        // Register GameRules
        YouRunFastGameRules.register();

        // Register Brigadier commands
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            YouRunFastCommand.register(dispatcher);
        });

        // Register Server Tick Event for predictive forward chunk ticket tracking
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                ForwardTicketManager.tickPlayer(player);
            }
        });

        // Register Disconnect Cleanup Listener
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            ForwardTicketManager.onPlayerDisconnect(handler.getPlayer());
        });

        LOGGER.info("[YouRunFast] Initialization complete.");
    }
}
