// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.yourunfast;

import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import net.vanillaoutsider.yourunfast.math.AnisotropicDistanceHelper;
import net.vanillaoutsider.yourunfast.math.VelocityCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnisotropicMathTest {

    @Test
    @DisplayName("Forward chunk section must have lower biased distance score than rear chunk section")
    void testForwardPriorityOverRear() {
        double camX = 100.0, camY = 64.0, camZ = 100.0;
        double dirX = 1.0, dirY = 0.0, dirZ = 0.0;
        double speed = 1.5; // 30 m/s (Elytra flight)
        double leadMultiplier = 1.0;
        double minSpeed = 0.20;

        double chunkAheadX = 164.0, chunkAheadY = 64.0, chunkAheadZ = 100.0;
        double chunkBehindX = 36.0, chunkBehindY = 64.0, chunkBehindZ = 100.0;

        double distAhead = AnisotropicDistanceHelper.computeRawBiasedDistanceSqr(
                chunkAheadX, chunkAheadY, chunkAheadZ,
                camX, camY, camZ,
                dirX, dirY, dirZ,
                speed, leadMultiplier, minSpeed
        );

        double distBehind = AnisotropicDistanceHelper.computeRawBiasedDistanceSqr(
                chunkBehindX, chunkBehindY, chunkBehindZ,
                camX, camY, camZ,
                dirX, dirY, dirZ,
                speed, leadMultiplier, minSpeed
        );

        assertTrue(distAhead < distBehind, "Forward chunk must score lower than rear chunk");
        assertTrue(distAhead < 4096.0, "Forward chunk distance must be biased downward");
        assertTrue(distBehind > 4096.0, "Rear chunk distance must be biased upward");
    }

    @Test
    @DisplayName("Speeds below threshold must retain standard Euclidean distance")
    void testBelowSpeedThreshold() {
        double camX = 0.0, camY = 0.0, camZ = 0.0;
        double dirX = 1.0, dirY = 0.0, dirZ = 0.0;
        double speed = 0.05;
        double leadMultiplier = 1.0;
        double minSpeed = 0.20;

        double originX = 10.0, originY = 0.0, originZ = 0.0;

        double dist = AnisotropicDistanceHelper.computeRawBiasedDistanceSqr(
                originX, originY, originZ,
                camX, camY, camZ,
                dirX, dirY, dirZ,
                speed, leadMultiplier, minSpeed
        );

        assertEquals(100.0, dist, 1e-6, "Low speed must return exact Euclidean squared distance");
    }

    @Test
    @DisplayName("VelocityCalculator smoothing and magnitude calculations")
    void testVelocityCalculator() {
        VelocityCalculator calc = new VelocityCalculator();
        assertEquals(0.0, calc.getSpeedBlocksPerTick());

        calc.update(0.0, 0.0, 0.0, 1.0, 0.0, 0.0);
        assertEquals(1.0, calc.getSpeedBlocksPerTick(), 1e-4);
        assertEquals(20.0, calc.getSpeedMetersPerSecond(), 1e-4);
        assertEquals(1.0, calc.getNormDx(), 1e-4);
        assertEquals(0.0, calc.getNormDy(), 1e-4);

        calc.update(1.0, 0.0, 0.0, 1.0, 0.0, 0.0);
        assertTrue(calc.getSpeedBlocksPerTick() > 0.9);

        calc.reset();
        assertEquals(0.0, calc.getSpeedBlocksPerTick());
        assertEquals(0.0, calc.getNormDx());
    }

    @Test
    @DisplayName("Zero-allocation primitive bit-packing and LongOpenHashSet")
    void testBitPackedChunkPositions() {
        int x = 12345;
        int z = -67890;
        long packed = ((long) x & 0xFFFFFFFFL) | (((long) z & 0xFFFFFFFFL) << 32);

        int unpackedX = (int) (packed & 0xFFFFFFFFL);
        int unpackedZ = (int) (packed >> 32);
        assertEquals(x, unpackedX);
        assertEquals(z, unpackedZ);

        LongOpenHashSet set = new LongOpenHashSet();
        assertTrue(set.add(packed));
        assertFalse(set.add(packed));
        assertTrue(set.contains(packed));
        assertEquals(1, set.size());
    }

    @Test
    @DisplayName("Continuous MSPT Watchdog taper math")
    void testMsptWatchdogFormula() {
        float normalMspt = 20.0f;
        double factorNormal = normalMspt > 25.0f ? Math.max(0.25, 1.0 - (double)(normalMspt - 25.0f) / 25.0) : 1.0;
        assertEquals(1.0, factorNormal);

        float heavyMspt = 37.5f;
        double factorHeavy = heavyMspt > 25.0f ? Math.max(0.25, 1.0 - (double)(heavyMspt - 25.0f) / 25.0) : 1.0;
        assertEquals(0.5, factorHeavy, 1e-4);

        float extremeMspt = 60.0f;
        double factorExtreme = extremeMspt > 25.0f ? Math.max(0.25, 1.0 - (double)(extremeMspt - 25.0f) / 25.0) : 1.0;
        assertEquals(0.25, factorExtreme, 1e-4);
    }

    @Test
    @DisplayName("Null safety and boundary checks")
    void testNullSafety() {
        assertEquals(Double.MAX_VALUE, AnisotropicDistanceHelper.calculateBiasedDistanceSqr(null, null));
    }
}
