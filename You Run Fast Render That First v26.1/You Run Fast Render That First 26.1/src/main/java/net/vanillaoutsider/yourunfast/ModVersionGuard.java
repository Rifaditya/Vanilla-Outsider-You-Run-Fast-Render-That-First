// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.yourunfast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ModVersionGuard {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModVersionGuard.class);

    private ModVersionGuard() {
    }

    public static void checkClass(String modName, String requiredClassName) {
        try {
            Class.forName(requiredClassName, false, Thread.currentThread().getContextClassLoader());
            LOGGER.debug("[{}] Successfully verified compatibility class: {}", modName, requiredClassName);
        } catch (ClassNotFoundException e) {
            LOGGER.error("[{}] Minecraft API Mismatch! Missing class: {}", modName, requiredClassName);
            throw new RuntimeException("\n" +
                "=====================================================================\n" +
                " [" + modName + "] Minecraft API Mismatch!\n" +
                " A required Minecraft class or API was not found in your game version.\n" +
                " Try updating your Minecraft version one drop at a time until it works, or\n" +
                " download a matching JAR for your Minecraft release from Modrinth or CurseForge.\n" +
                "=====================================================================");
        }
    }
}
