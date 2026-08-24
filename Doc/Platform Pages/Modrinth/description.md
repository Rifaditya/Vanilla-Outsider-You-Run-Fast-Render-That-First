<p align="center">
    <a href="https://modrinth.com/mod/fabric-api"><img src="https://img.shields.io/badge/Requires-Fabric_API-blue?style=for-the-badge&logo=fabric" alt="Requires Fabric API"></a>
    <img src="https://img.shields.io/badge/Language-Java_25-orange?style=for-the-badge&logo=java" alt="Java 25">
    <img src="https://img.shields.io/badge/License-GPLv3-green?style=for-the-badge" alt="License">
    <img src="https://img.shields.io/badge/Minecraft-26.1.2%20|%2026.2%20|%2026.3-brightgreen?style=for-the-badge" alt="Minecraft 26.x">
</p>

# ⚡ You Run Fast, Render That First

> **"Render what's ahead, not what's behind."**

**You Run Fast, Render That First** is a lightweight, zero-overhead Fabric mod for Minecraft **26.1.2**, **26.2**, and **26.3** that synchronizes chunk world generation and client chunk meshing with real-time player velocity, prioritizing terrain ahead of fast-traveling players to completely eliminate high-speed void pop-in and chunk loading walls.

Inspired by velocity-based open-world streaming engines, this mod dynamically focuses Minecraft's generation and rendering power along your forward travel trajectory—whether you are soaring with Elytra, racing boats on blue ice, sprinting under Speed effects, or galloping on swift horses.

Part of the **Vanilla Outsider Collection** — mods that refine the vanilla experience with modern standards.

---

## ✨ Features (Version 1.0.0)

### 🏎️ Client-Side Velocity-Biased Chunk Meshing
Stop waiting for forward chunks to appear while the game spends rendering power on terrain behind you:
- **Vanilla Bottleneck**: Vanilla Minecraft sorts chunk compile tasks (`SectionTaskDynamicQueue` / `CompileTaskDynamicQueue`) using purely radial Euclidean distance to the camera, spending equal thread time rendering terrain behind you that you cannot see.
- **Anisotropic Prioritization**: Calculates your instantaneous movement vector and computes a directional dot-product distance bias. Sections directly in front of your flight cone receive higher meshing priority, compiling forward terrain first while seamlessly deferring rear sections without visual disruption.

### 🌐 Server-Side Predictive Chunk Generation
Smooth exploration across uncharted lands:
- **Predictive Loading Cones**: On dedicated servers and integrated singleplayer servers, the mod tracks player velocity and issues lightweight loading tickets (`TicketType.PLAYER_LOADING`) up to 16 chunks ahead along the movement vector.
- **Lag-Free Exploration**: Terrain is generated and loaded into memory *before* you reach it, preventing the dreaded Elytra void stall and rubberbanding.

### ⚖️ Adaptive Budget Conservation
High speed without sacrificing server performance:
- **Constant Chunk Equilibrium**: When traveling at high speeds, the server dynamically narrows the lateral and rear non-simulation radius.
- **Rock-Solid 20 TPS**: Total active chunk count per player remains steady, ensuring server tick rates stay butter-smooth even across uncharted biomes.

### 🚀 Universal Multi-Mode Kinematics
Automatically detects and scales with all locomotion styles:
- 🏃 **Sprinting & Potions**: Speed I/II/III buffs.
- 🐎 **Mounted Riding**: Swift horses, camels, and minecarts.
- 🧊 **Ice Boating**: Blue ice hyper-speed transport.
- 🪽 **Elytra Gliding**: Rocket boosts and steep diving.
- 💨 **Wind Charges & Modded Vehicles**: Seamless physics integration.

### 🌫️ 100% Vanilla Fog, Shaders & Aesthetics Preservation
- **Uncompromised Visuals**: Modifies zero shaders, lighting models, rendering pipelines, or atmospheric fog distances.
- **Universal Shaders Compatibility**: Because the mod purely optimizes CPU task ordering without touching graphical shaders or vertex formats, it is 100% compatible with shaderpacks (**Iris**, **Oculus**, **Canvas**), performance engines (**Sodium**, **ImmediatelyFast**, **Lithium**, **Nvidium**, **Distant Horizons**, **Bobby**), and custom resource packs.
- **Seamless Immersion**: All optimizations happen silently beneath the surface—your visual aesthetic and atmosphere remain 100% vanilla.

---

## 🛠️ In-Game Brigadier Commands

The mod includes a full in-game command suite under `/yourunfast`:

| Command | Description |
| :--- | :--- |
| `/yourunfast status` | Displays real-time velocity vector, speed ($m/s$ & $b/t$), forward lead distance, active tickets, and meshing queue size. |
| `/yourunfast get <gamerule>` | Queries the current value of a configuration GameRule. |
| `/yourunfast set <gamerule> <value>` | Updates settings in real time. |
| `/yourunfast reset` | Restores all settings to vanilla-friendly defaults. |
| `/yourunfast reload` | Reloads active configuration. |

---

## ⚙️ Configuration (Native Game Rules)

> [!IMPORTANT]
> **Config vs. In-Game GameRules:**
> The global configuration file only defines **default values for new worlds** at creation time.
> If you have **already created/opened a world**, changing the config file will have no effect. You must change the settings in-game using the **Edit Game Rules** UI screen or the `/gamerule` / `/yourunfast` command.

Customize behavior per-world with standard gamerules:

- `yourunfast:enabled`: Master toggle for the entire prioritization system. (Default: `true`)
- `yourunfast:forward_lead_multiplier`: Scales forward lead distance (0% to 300%). (Default: `100`)
- `yourunfast:budget_conservation`: Enables adaptive lateral and rear ticket trimming. (Default: `true`)
- `yourunfast:min_speed_threshold_pct`: Minimum speed in hundredths of a block/tick (0.20 b/t) to activate directional bias. (Default: `20`)
- `yourunfast:debug_mode`: Enables developer diagnostic logging and actionbar stats. (Default: `false`)

---

## 🔌 Compatibility & Installation

- **🎨 Shaders & Rendering Engines**: 100% compatible with Iris, Sodium, ImmediatelyFast, Lithium, Distant Horizons, Bobby, and custom shaderpacks.
- **🏠 Singleplayer**: Full synergy (client meshing priority + internal server predictive generation).
- **💻 Client-Only on Vanilla Servers**: Meshes received chunks in your forward velocity cone with top priority.
- **🖥️ Server-Only with Vanilla Clients**: Predictively generates and loads chunks ahead of fast players on the server, eliminating void walls for all connecting players.
- **⚡ Both Server & Client**: Maximum performance across world generation and client rendering pipelines.

---

## ☕ Support

If you enjoy the **Vanilla Outsider** collection, consider supporting the next update!

<p align="center">
    <a href="https://ko-fi.com/dasikigaijin/tip"><img src="https://img.shields.io/badge/Ko--fi-Support%20Me-FF5E5B?style=for-the-badge&logo=ko-fi&logoColor=white" alt="Ko-fi"></a>
    <a href="https://sociabuzz.com/dasikigaijin/tribe"><img src="https://img.shields.io/badge/SocioBuzz-Local_Support-7BB32E?style=for-the-badge" alt="SocioBuzz"></a>
    <a href="https://saweria.co/DasikIgaijinn"><img src="https://img.shields.io/badge/Saweria-Local_Support-FFA500?style=for-the-badge" alt="Saweria"></a>
</p>

> [!NOTE]
> **Indonesian Users:** SocioBuzz and Saweria support local payment methods (Gopay, OVO, Dana, etc.) if you want to support me without using PayPal/Ko-fi!

---

## 📦 Modpack Permissions

> **Modpack Distribution Policy:**
> You are free to include this mod in any modpack, provided that the modpack is hosted on the same platform where you obtained this mod (e.g. CurseForge modpacks on CurseForge, Modrinth modpacks on Modrinth). Cross-platform redistribution is strictly prohibited to support the creator and ensure legitimate downloads.

---

## 📜 Credits

| Role | Author |
| :--- | :--- |
| **Creator** | **Dasik** (Rifaditya) |
| **Collection** | Vanilla Outsider |
| **License** | GPLv3 |

---

<div align="center">

**Made with ❤️ for the Minecraft community**

*Part of the Vanilla Outsider Collection*

</div>
