// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.yourunfast.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class YouRunFastClient implements ClientModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(YouRunFastClient.class);

    @Override
    public void onInitializeClient() {
        LOGGER.info("[YouRunFast-Client] Initializing client-side anisotropic chunk render prioritization");

        ClientTickEvents.END_CLIENT_TICK.register(ClientVelocityTracker::clientTick);
    }
}
