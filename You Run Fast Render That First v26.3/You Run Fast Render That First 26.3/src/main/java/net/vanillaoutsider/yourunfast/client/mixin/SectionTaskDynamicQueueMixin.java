// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.yourunfast.client.mixin;

import net.minecraft.client.renderer.chunk.SectionTaskDynamicQueue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.vanillaoutsider.yourunfast.math.AnisotropicDistanceHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SectionTaskDynamicQueue.class)
public abstract class SectionTaskDynamicQueueMixin {

    @Redirect(
            method = "poll",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/core/BlockPos;distToCenterSqr(Lnet/minecraft/world/phys/Vec3;)D"
            )
    )
    private double youRunFast$biasedDistance(BlockPos origin, Vec3 cameraPos) {
        return AnisotropicDistanceHelper.calculateBiasedDistanceSqr(origin, cameraPos);
    }
}
