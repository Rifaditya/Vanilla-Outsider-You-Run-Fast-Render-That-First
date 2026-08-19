// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.yourunfast.registry;

import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class YouRunFastGameRules {
    private static final Logger LOGGER = LoggerFactory.getLogger(YouRunFastGameRules.class);

    public static final GameRuleCategory CATEGORY = DynamicGameRuleManager.registerCategory(
            Identifier.fromNamespaceAndPath("you-run-fast-render-that-first", "main")
    );

    public static GameRule<Boolean> ENABLED;
    public static GameRule<Integer> FORWARD_LEAD_MULTIPLIER;
    public static GameRule<Boolean> BUDGET_CONSERVATION;
    public static GameRule<Integer> MIN_SPEED_THRESHOLD_PCT;
    public static GameRule<Boolean> DEBUG_MODE;

    private YouRunFastGameRules() {
    }

    public static void register() {
        LOGGER.info("[YouRunFast] Registering dynamic namespaced GameRules via DasikLibrary");

        ENABLED = DynamicGameRuleManager.booleanRule("yourunfast:enabled", CATEGORY, true)
                .name("Enabled")
                .description("Toggle anisotropic forward chunk loading priority")
                .register();

        FORWARD_LEAD_MULTIPLIER = DynamicGameRuleManager.integerRule("yourunfast:forward_lead_multiplier", CATEGORY, 100)
                .range(0, 300)
                .name("Forward Lead Multiplier")
                .description("Scaling percentage of forward lookahead reach (0% - 300%)")
                .register();

        BUDGET_CONSERVATION = DynamicGameRuleManager.booleanRule("yourunfast:budget_conservation", CATEGORY, true)
                .name("Budget Conservation")
                .description("Trim rear and lateral non-simulation tickets at high speed")
                .register();

        MIN_SPEED_THRESHOLD_PCT = DynamicGameRuleManager.integerRule("yourunfast:min_speed_threshold_pct", CATEGORY, 20)
                .range(1, 200)
                .name("Min Speed Threshold")
                .description("Minimum speed percentage required to activate forward prioritization (20 = 0.20 b/t)")
                .register();

        DEBUG_MODE = DynamicGameRuleManager.booleanRule("yourunfast:debug_mode", CATEGORY, false)
                .name("Debug Diagnostics")
                .description("Print real-time speed and trajectory diagnostics to logs")
                .register();
    }

    public static boolean isEnabled(Level level) {
        return DynamicGameRuleManager.getBoolean(level, ENABLED);
    }

    public static int getLeadMultiplierPct(Level level) {
        return DynamicGameRuleManager.getInt(level, FORWARD_LEAD_MULTIPLIER);
    }

    public static boolean isBudgetConservationEnabled(Level level) {
        return DynamicGameRuleManager.getBoolean(level, BUDGET_CONSERVATION);
    }

    public static double getMinSpeedThreshold(Level level) {
        return DynamicGameRuleManager.getPct(level, MIN_SPEED_THRESHOLD_PCT);
    }

    public static boolean isDebugMode(Level level) {
        return DynamicGameRuleManager.getBoolean(level, DEBUG_MODE);
    }
}
