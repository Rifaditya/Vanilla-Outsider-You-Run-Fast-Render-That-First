# You Run Fast, Render That First

**You Run Fast, Render That First** is a lightweight, high-performance Fabric mod for Minecraft **26.1.2**, **26.2**, and **26.3** that synchronizes chunk world generation and client chunk meshing with real-time player velocity, prioritizing terrain ahead of fast-moving players to completely eliminate high-speed void pop-in and chunk loading walls.

Inspired by velocity-based open-world streaming engines (like GTA V), this mod ensures that whether you are flying with Elytra, racing boats on blue ice, riding swift horses, or sprinting under Speed effects, the game dynamically focuses its generation and rendering power along your forward flight trajectory.

---

## Key Features

- **Directional Velocity-Biased Chunk Meshing**: Dynamically calculates player velocity vectors and re-prioritizes client chunk compile tasks (`SectionTaskDynamicQueue` / `CompileTaskDynamicQueue`), meshing terrain ahead of you before compiling rear sections.
- **Server-Side Predictive Chunk Generation**: On dedicated servers and singleplayer internal servers, allocates lightweight forward loading tickets (`FLAG_LOADING` without simulation load) up to 16 chunks ahead along the movement vector.
- **Adaptive Budget Conservation**: Automatically compresses rear/lateral chunk loading radius while traveling at high speeds to keep total active chunk counts constant, preserving a solid 20 TPS.
- **Universal Multi-Mode Kinematics**: Automatically scales with all player movement (walking, sprinting, horses, minecarts, ice boats, Elytra gliding, wind charge blasts, and modded vehicles).
- **100% Vanilla Fog Preservation**: Preserves vanilla atmospheric fog aesthetics; all optimizations happen invisibly behind the scenes.
- **Universal Hybrid Compatibility**: Works 100% client-side when joining unmodded vanilla multiplayer servers, and unlocks server-side predictive chunk generation when installed on servers.
- **In-Game Brigadier Commands**:
  - `/yourunfast status` — Displays real-time velocity, speed, forward lead distance, active tickets, and meshing queue size.
  - `/yourunfast get <gamerule>` — View current settings.
  - `/yourunfast set <gamerule> <value>` — Live tuning.
  - `/yourunfast reset` — Reset to defaults.
  - `/yourunfast reload` — Reload configuration.

---

## Supported Versions

- **Minecraft 26.1.2** (`+26.1.2`)
- **Minecraft 26.2** (`+26.2`)
- **Minecraft 26.3** (`+26.3`)

---

## License

This project is licensed under the **GNU General Public License v3.0 (GPLv3)**.
