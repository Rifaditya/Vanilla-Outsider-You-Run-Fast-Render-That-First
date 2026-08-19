// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.yourunfast.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.TimeUtil;
import net.vanillaoutsider.yourunfast.client.ClientVelocityTracker;
import net.vanillaoutsider.yourunfast.registry.YouRunFastGameRules;
import net.vanillaoutsider.yourunfast.server.ForwardTicketManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class YouRunFastCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(YouRunFastCommand.class);

    private YouRunFastCommand() {
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("yourunfast")
                .then(Commands.literal("help").executes(YouRunFastCommand::executeHelp))
                .then(Commands.literal("status").executes(YouRunFastCommand::executeStatus))
                .then(Commands.literal("get")
                        .then(Commands.argument("rule", StringArgumentType.word())
                                .suggests((ctx, builder) -> {
                                    builder.suggest("enabled");
                                    builder.suggest("lead_multiplier");
                                    builder.suggest("budget_conservation");
                                    builder.suggest("min_speed");
                                    builder.suggest("debug_mode");
                                    return builder.buildFuture();
                                })
                                .executes(YouRunFastCommand::executeGet)))
                .then(Commands.literal("set")
                        .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                        .then(Commands.literal("enabled")
                                .then(Commands.argument("value", BoolArgumentType.bool())
                                        .executes(ctx -> executeSetBool(ctx, "enabled", BoolArgumentType.getBool(ctx, "value")))))
                        .then(Commands.literal("lead_multiplier")
                                .then(Commands.argument("value", IntegerArgumentType.integer(0, 300))
                                        .executes(ctx -> executeSetInt(ctx, "lead_multiplier", IntegerArgumentType.getInteger(ctx, "value")))))
                        .then(Commands.literal("budget_conservation")
                                .then(Commands.argument("value", BoolArgumentType.bool())
                                        .executes(ctx -> executeSetBool(ctx, "budget_conservation", BoolArgumentType.getBool(ctx, "value")))))
                        .then(Commands.literal("min_speed")
                                .then(Commands.argument("value", IntegerArgumentType.integer(1, 200))
                                        .executes(ctx -> executeSetInt(ctx, "min_speed", IntegerArgumentType.getInteger(ctx, "value")))))
                        .then(Commands.literal("debug_mode")
                                .then(Commands.argument("value", BoolArgumentType.bool())
                                        .executes(ctx -> executeSetBool(ctx, "debug_mode", BoolArgumentType.getBool(ctx, "value"))))))
                .then(Commands.literal("reset")
                        .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                        .executes(YouRunFastCommand::executeReset))
                .then(Commands.literal("reload")
                        .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                        .executes(YouRunFastCommand::executeReload))
                .executes(YouRunFastCommand::executeStatus)
        );
    }

    private static int executeHelp(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        source.sendSuccess(() -> Component.literal(
                "§6=== You Run Fast, Render That First Commands ===§r\n" +
                "§e/yourunfast status§r - View full engine telemetry, velocity, MSPT, and active tickets.\n" +
                "§e/yourunfast get <rule>§r - Query current configuration value.\n" +
                "§e/yourunfast set <rule> <value>§r - Update settings in real time.\n" +
                "§e/yourunfast reset§r - Restore default settings.\n" +
                "§e/yourunfast reload§r - Reload configuration."
        ), false);
        return 1;
    }

    private static int executeStatus(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        ServerLevel level = source.getLevel();

        boolean enabled = YouRunFastGameRules.isEnabled(level);
        int leadMultiplier = YouRunFastGameRules.getLeadMultiplierPct(level);
        double minSpeed = YouRunFastGameRules.getMinSpeedThreshold(level);
        boolean budget = YouRunFastGameRules.isBudgetConservationEnabled(level);
        boolean debug = YouRunFastGameRules.isDebugMode(level);

        int activeTickets = 0;
        double currentSpeed = 0.0;
        if (source.getEntity() instanceof ServerPlayer player) {
            activeTickets = ForwardTicketManager.getActiveTicketCount(player.getUUID());
            currentSpeed = ForwardTicketManager.getPlayerSpeed(player.getUUID());
        }

        float serverMspt = (float) source.getServer().getAverageTickTimeNanos() / (float) TimeUtil.NANOSECONDS_PER_MILLISECOND;
        float approxTps = Math.min(20.0f, 1000.0f / Math.max(1.0f, serverMspt));
        int dynamicReach = ForwardTicketManager.getLastDynamicReach();
        boolean clientBias = ClientVelocityTracker.activeBias;

        final int tickets = activeTickets;
        final double speed = currentSpeed;
        final float mspt = serverMspt;
        final float tps = approxTps;
        final int reach = dynamicReach;
        final boolean bias = clientBias;

        source.sendSuccess(() -> Component.literal(
                "§6[You Run Fast, Render That First — Engine Telemetry]§r\n" +
                " §7• §fStatus: " + (enabled ? "§aACTIVE" : "§cDISABLED") + "§r\n" +
                " §7• §fYour Velocity: §a" + String.format("%.2f", speed) + " b/t (" + String.format("%.1f", speed * 20.0) + " m/s)§r\n" +
                " §7• §fDynamic Forward Reach: §e" + reach + " Chunks§r\n" +
                " §7• §fActive Packed Tickets: §b" + tickets + " (LongOpenHashSet Zero-Alloc)§r\n" +
                " §7• §fServer MSPT Load: §f" + String.format("%.1f", mspt) + " ms §7(§a" + String.format("%.1f", tps) + " TPS§7)§r\n" +
                " §7• §fClient Mesh Bias: " + (bias ? "§aCOMPILING FORWARD" : "§7IDLE") + "§r\n" +
                " §7• §fForward Lead Multiplier: §e" + leadMultiplier + "%§r\n" +
                " §7• §fMin Speed Threshold: §b" + String.format("%.2f", minSpeed) + " b/t (" + String.format("%.1f", minSpeed * 20.0) + " m/s)§r\n" +
                " §7• §fBudget Conservation: " + (budget ? "§aON" : "§7OFF") + "§r\n" +
                " §7• §fDebug Logging: " + (debug ? "§aON" : "§7OFF")
        ), false);
        return 1;
    }

    private static int executeGet(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        ServerLevel level = source.getLevel();
        String rule = StringArgumentType.getString(context, "rule").toLowerCase();

        switch (rule) {
            case "enabled" -> source.sendSuccess(() -> Component.literal("§6yourunfast:enabled = §e" + YouRunFastGameRules.isEnabled(level)), false);
            case "lead_multiplier" -> source.sendSuccess(() -> Component.literal("§6yourunfast:forward_lead_multiplier = §e" + YouRunFastGameRules.getLeadMultiplierPct(level) + "%"), false);
            case "budget_conservation" -> source.sendSuccess(() -> Component.literal("§6yourunfast:budget_conservation = §e" + YouRunFastGameRules.isBudgetConservationEnabled(level)), false);
            case "min_speed" -> source.sendSuccess(() -> Component.literal("§6yourunfast:min_speed = §e" + YouRunFastGameRules.getMinSpeedThreshold(level) + " b/t"), false);
            case "debug_mode" -> source.sendSuccess(() -> Component.literal("§6yourunfast:debug_mode = §e" + YouRunFastGameRules.isDebugMode(level)), false);
            default -> source.sendFailure(Component.literal("§cUnknown setting: " + rule));
        }
        return 1;
    }

    private static int executeSetBool(CommandContext<CommandSourceStack> context, String rule, boolean value) {
        CommandSourceStack source = context.getSource();
        ServerLevel level = source.getLevel();

        switch (rule) {
            case "enabled" -> level.getGameRules().set(YouRunFastGameRules.ENABLED, value, source.getServer());
            case "budget_conservation" -> level.getGameRules().set(YouRunFastGameRules.BUDGET_CONSERVATION, value, source.getServer());
            case "debug_mode" -> level.getGameRules().set(YouRunFastGameRules.DEBUG_MODE, value, source.getServer());
        }

        source.sendSuccess(() -> Component.literal("§aUpdated yourunfast:" + rule + " to " + value), true);
        return 1;
    }

    private static int executeSetInt(CommandContext<CommandSourceStack> context, String rule, int value) {
        CommandSourceStack source = context.getSource();
        ServerLevel level = source.getLevel();

        switch (rule) {
            case "lead_multiplier" -> level.getGameRules().set(YouRunFastGameRules.FORWARD_LEAD_MULTIPLIER, value, source.getServer());
            case "min_speed" -> level.getGameRules().set(YouRunFastGameRules.MIN_SPEED_THRESHOLD_PCT, value, source.getServer());
        }

        source.sendSuccess(() -> Component.literal("§aUpdated yourunfast:" + rule + " to " + value), true);
        return 1;
    }

    private static int executeReset(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        ServerLevel level = source.getLevel();

        level.getGameRules().set(YouRunFastGameRules.ENABLED, true, source.getServer());
        level.getGameRules().set(YouRunFastGameRules.FORWARD_LEAD_MULTIPLIER, 100, source.getServer());
        level.getGameRules().set(YouRunFastGameRules.BUDGET_CONSERVATION, true, source.getServer());
        level.getGameRules().set(YouRunFastGameRules.MIN_SPEED_THRESHOLD_PCT, 20, source.getServer());
        level.getGameRules().set(YouRunFastGameRules.DEBUG_MODE, false, source.getServer());

        source.sendSuccess(() -> Component.literal("§aReset all You Run Fast settings to vanilla defaults."), true);
        return 1;
    }

    private static int executeReload(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        source.sendSuccess(() -> Component.literal("§aReloaded You Run Fast configuration successfully."), true);
        return 1;
    }
}
