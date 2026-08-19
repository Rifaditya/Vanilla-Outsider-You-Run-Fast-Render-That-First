// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3
package net.vanillaoutsider.yourunfast.math;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class VelocityCalculator {
    private static final Logger LOGGER = LoggerFactory.getLogger(VelocityCalculator.class);
    private static final double EMA_ALPHA = 0.65;

    private double lastX;
    private double lastY;
    private double lastZ;
    private boolean initialized;

    private double smoothVx;
    private double smoothVy;
    private double smoothVz;
    private double speedBlocksPerTick;
    private double normDx;
    private double normDy;
    private double normDz;

    public VelocityCalculator() {
    }

    public synchronized void update(double currentX, double currentY, double currentZ, double rawVx, double rawVy, double rawVz) {
        if (!this.initialized) {
            this.lastX = currentX;
            this.lastY = currentY;
            this.lastZ = currentZ;
            this.smoothVx = rawVx;
            this.smoothVy = rawVy;
            this.smoothVz = rawVz;
            this.initialized = true;
            this.recalculateSpeedAndNorm();
            return;
        }

        double deltaX = currentX - this.lastX;
        double deltaY = currentY - this.lastY;
        double deltaZ = currentZ - this.lastZ;
        this.lastX = currentX;
        this.lastY = currentY;
        this.lastZ = currentZ;

        // Blend delta movement and physics velocity
        double effectiveVx = Math.abs(deltaX) > 0.001 ? deltaX : rawVx;
        double effectiveVy = Math.abs(deltaY) > 0.001 ? deltaY : rawVy;
        double effectiveVz = Math.abs(deltaZ) > 0.001 ? deltaZ : rawVz;

        // Exponential moving average for jitter-free tracking
        this.smoothVx = EMA_ALPHA * effectiveVx + (1.0 - EMA_ALPHA) * this.smoothVx;
        this.smoothVy = EMA_ALPHA * effectiveVy + (1.0 - EMA_ALPHA) * this.smoothVy;
        this.smoothVz = EMA_ALPHA * effectiveVz + (1.0 - EMA_ALPHA) * this.smoothVz;

        this.recalculateSpeedAndNorm();
    }

    private void recalculateSpeedAndNorm() {
        this.speedBlocksPerTick = Math.sqrt(this.smoothVx * this.smoothVx + this.smoothVy * this.smoothVy + this.smoothVz * this.smoothVz);
        if (this.speedBlocksPerTick > 1e-4) {
            this.normDx = this.smoothVx / this.speedBlocksPerTick;
            this.normDy = this.smoothVy / this.speedBlocksPerTick;
            this.normDz = this.smoothVz / this.speedBlocksPerTick;
        } else {
            this.normDx = 0.0;
            this.normDy = 0.0;
            this.normDz = 0.0;
        }
    }

    public synchronized void reset() {
        this.initialized = false;
        this.smoothVx = 0.0;
        this.smoothVy = 0.0;
        this.smoothVz = 0.0;
        this.speedBlocksPerTick = 0.0;
        this.normDx = 0.0;
        this.normDy = 0.0;
        this.normDz = 0.0;
    }

    public double getSpeedBlocksPerTick() {
        return this.speedBlocksPerTick;
    }

    public double getSpeedMetersPerSecond() {
        return this.speedBlocksPerTick * 20.0;
    }

    public double getNormDx() {
        return this.normDx;
    }

    public double getNormDy() {
        return this.normDy;
    }

    public double getNormDz() {
        return this.normDz;
    }

    public double getSmoothVx() {
        return this.smoothVx;
    }

    public double getSmoothVy() {
        return this.smoothVy;
    }

    public double getSmoothVz() {
        return this.smoothVz;
    }
}
