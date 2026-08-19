# Changelog

All notable changes to **You Run Fast, Render That First** will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.3+26.3] - 2026-08-19

### Fixed
- Fixed critical startup crash (`IllegalClassLoadError`) caused by top-level mixin package definition claiming entrypoint classes; properly scoped mixin package to `net.vanillaoutsider.yourunfast.client.mixin`.

---

## [1.0.3+26.2] - 2026-08-19

### Fixed
- Fixed critical startup crash (`IllegalClassLoadError`) caused by top-level mixin package definition claiming entrypoint classes; properly scoped mixin package to `net.vanillaoutsider.yourunfast.client.mixin`.

---

## [1.0.3+26.1.2] - 2026-08-19

### Fixed
- Fixed critical startup crash (`IllegalClassLoadError`) caused by top-level mixin package definition claiming entrypoint classes; properly scoped mixin package to `net.vanillaoutsider.yourunfast.client.mixin`.

---

## [1.0.2+26.3] - 2026-08-19 [BROKEN / CRASHED ON STARTUP]

> **Post-Mortem**: Crashed on game initialization with `IllegalClassLoadError: YouRunFastMod is in a defined mixin package net.vanillaoutsider.yourunfast.*`. Superseded by `1.0.3+26.3`.

### Changed
- Integrated real code dependency on `DasikLibrary` (`net.dasik.social:dasik-library:>=1.8.0`).
- Migrated GameRule category registration and dynamic builder declarations to `DynamicGameRuleManager`.
- Standardized gamerule value getters and percentage scaling calculations via `DynamicGameRuleManager.getPct`.

---

## [1.0.2+26.2] - 2026-08-19 [BROKEN / CRASHED ON STARTUP]

> **Post-Mortem**: Crashed on game initialization with `IllegalClassLoadError: YouRunFastMod is in a defined mixin package net.vanillaoutsider.yourunfast.*`. Superseded by `1.0.3+26.2`.

### Changed
- Integrated real code dependency on `DasikLibrary` (`net.dasik.social:dasik-library:>=1.8.0`).
- Migrated GameRule category registration and dynamic builder declarations to `DynamicGameRuleManager`.
- Standardized gamerule value getters and percentage scaling calculations via `DynamicGameRuleManager.getPct`.

---

## [1.0.2+26.1.2] - 2026-08-19 [BROKEN / CRASHED ON STARTUP]

> **Post-Mortem**: Crashed on game initialization with `IllegalClassLoadError: YouRunFastMod is in a defined mixin package net.vanillaoutsider.yourunfast.*`. Superseded by `1.0.3+26.1.2`.

### Changed
- Integrated real code dependency on `DasikLibrary` (`net.dasik.social:dasik-library:>=1.8.0`).
- Migrated GameRule category registration and dynamic builder declarations to `DynamicGameRuleManager`.
- Standardized gamerule value getters and percentage scaling calculations via `DynamicGameRuleManager.getPct`.

---

## [1.0.1+26.3] - 2026-08-18

### Changed
- Refactored server-side `ForwardTicketManager` to use `LongOpenHashSet` with primitive 64-bit packed chunk coordinates (`ChunkPos.pack`), achieving zero GC allocations during flight.
- Added Automatic Dynamic MSPT Watchdog continuously tapering forward lead reach under heavy server load to maintain 20 TPS.
- Added Mach Speed Lateral Fan-Out cone expansion ($\pm 1$ chunk width at $> 16\text{ m/s}$) for smooth banked turns and flight maneuvering.
- Added Hybrid Spatial & Angular update throttling (re-evaluating on chunk crossings, turns $> 8^\circ$, or every 10 ticks).
- Pre-cached client-side vector calculation state with volatile lock-free reads in `ClientVelocityTracker`.
- Enhanced `/yourunfast status` command with full engine telemetry (MSPT, dynamic reach, packed tickets, and client bias state).
- Optimized mod icon assets with deep 128-color quantization.

---

## [1.0.1+26.2] - 2026-08-18

### Changed
- Refactored server-side `ForwardTicketManager` to use `LongOpenHashSet` with primitive 64-bit packed chunk coordinates (`ChunkPos.pack`), achieving zero GC allocations during flight.
- Added Automatic Dynamic MSPT Watchdog continuously tapering forward lead reach under heavy server load to maintain 20 TPS.
- Added Mach Speed Lateral Fan-Out cone expansion ($\pm 1$ chunk width at $> 16\text{ m/s}$) for smooth banked turns and flight maneuvering.
- Added Hybrid Spatial & Angular update throttling (re-evaluating on chunk crossings, turns $> 8^\circ$, or every 10 ticks).
- Pre-cached client-side vector calculation state with volatile lock-free reads in `ClientVelocityTracker`.
- Enhanced `/yourunfast status` command with full engine telemetry (MSPT, dynamic reach, packed tickets, and client bias state).
- Optimized mod icon assets with deep 128-color quantization.

---

## [1.0.1+26.1.2] - 2026-08-18

### Changed
- Refactored server-side `ForwardTicketManager` to use `LongOpenHashSet` with primitive 64-bit packed chunk coordinates (`ChunkPos.pack`), achieving zero GC allocations during flight.
- Added Automatic Dynamic MSPT Watchdog continuously tapering forward lead reach under heavy server load to maintain 20 TPS.
- Added Mach Speed Lateral Fan-Out cone expansion ($\pm 1$ chunk width at $> 16\text{ m/s}$) for smooth banked turns and flight maneuvering.
- Added Hybrid Spatial & Angular update throttling (re-evaluating on chunk crossings, turns $> 8^\circ$, or every 10 ticks).
- Pre-cached client-side vector calculation state with volatile lock-free reads in `ClientVelocityTracker`.
- Enhanced `/yourunfast status` command with full engine telemetry (MSPT, dynamic reach, packed tickets, and client bias state).
- Optimized mod icon assets with deep 128-color quantization.

---

## [1.0.0+26.3] - 2026-08-18

### Added
- Core implementation of Velocity-Biased Anisotropic Chunk Prioritization for Minecraft 26.3.
- Client-side `SectionTaskDynamicQueueMixin` hook with directional dot-product distance calculation.
- Server-side predictive chunk generation ticket manager (`ForwardTicketManager`) using non-ticking `TicketType.FLAG_LOADING`.
- Adaptive budget manager (`ServerBudgetManager`) for lateral/rear chunk trimming at high speeds.
- Full Brigadier command suite (`/yourunfast help`, `status`, `get`, `set`, `reset`, `reload`).
- Namespaced GameRules: `yourunfast:enabled`, `yourunfast:forward_lead_multiplier`, `yourunfast:budget_conservation`, `yourunfast:min_speed_threshold_pct`, `yourunfast:debug_mode`.
- Zero-dependency `ModVersionGuard` with Knot ClassLoader resolution.

---

## [1.0.0+26.2] - 2026-08-18

### Added
- Core implementation of Velocity-Biased Anisotropic Chunk Prioritization for Minecraft 26.2.
- Client-side `SectionTaskDynamicQueueMixin` hook with directional dot-product distance calculation.
- Server-side predictive chunk generation ticket manager (`ForwardTicketManager`) using non-ticking `TicketType.FLAG_LOADING`.
- Adaptive budget manager (`ServerBudgetManager`) for lateral/rear chunk trimming at high speeds.
- Full Brigadier command suite (`/yourunfast help`, `status`, `get`, `set`, `reset`, `reload`).
- Namespaced GameRules: `yourunfast:enabled`, `yourunfast:forward_lead_multiplier`, `yourunfast:budget_conservation`, `yourunfast:min_speed_threshold_pct`, `yourunfast:debug_mode`.
- Zero-dependency `ModVersionGuard` with Knot ClassLoader resolution.

---

## [1.0.0+26.1.2] - 2026-08-18

### Added
- Core implementation of Velocity-Biased Anisotropic Chunk Prioritization for Minecraft 26.1.2.
- Client-side `CompileTaskDynamicQueueMixin` hook with directional dot-product distance calculation.
- Server-side predictive chunk generation ticket manager (`ForwardTicketManager`) using non-ticking `TicketType.FLAG_LOADING`.
- Adaptive budget manager (`ServerBudgetManager`) for lateral/rear chunk trimming at high speeds.
- Full Brigadier command suite (`/yourunfast help`, `status`, `get`, `set`, `reset`, `reload`).
- Namespaced GameRules: `yourunfast:enabled`, `yourunfast:forward_lead_multiplier`, `yourunfast:budget_conservation`, `yourunfast:min_speed_threshold_pct`, `yourunfast:debug_mode`.
- Zero-dependency `ModVersionGuard` with Knot ClassLoader resolution.
