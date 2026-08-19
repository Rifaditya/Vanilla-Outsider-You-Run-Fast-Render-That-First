// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.yourunfast.server;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.vanillaoutsider.yourunfast.registry.YouRunFastGameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ServerBudgetManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerBudgetManager.class);
    private static final double HIGH_SPEED_THRESHOLD = 0.60; // 12 m/s

    private ServerBudgetManager() {
    }

    public static boolean shouldTrimRearTickets(ServerPlayer player, double speed) {
        if (player == null || !(player.level() instanceof ServerLevel level)) {
            return false;
        }

        if (!YouRunFastGameRules.isEnabled(level) || !YouRunFastGameRules.isBudgetConservationEnabled(level)) {
            return false;
        }

        return speed >= HIGH_SPEED_THRESHOLD;
    }
}
