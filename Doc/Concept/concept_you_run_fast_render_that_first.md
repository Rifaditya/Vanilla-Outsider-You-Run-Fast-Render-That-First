# 🚀 Vanilla Outsider: You Run Fast, Render That First — Master Concept & Behavioral Specification

**Mod ID**: `you-run-fast-render-that-first`  
**Archives Base Name**: `vanilla-outsider-you-run-fast-render-that-first`  
**Design Philosophy**: **Vanilla Outsider (VO)** — Enhancing the vanilla travel experience by dynamically synchronizing chunk generation and chunk render meshing priorities with the player's real-time velocity vector, completely eliminating high-speed chunk pop-in and void walls while maintaining 100% vanilla aesthetics, balance, and atmospheric fog.

---

## 🧭 Multi-Era Target Matrix

| Technical Area | MC 26.1.2 Sovereign | MC 26.2 Sovereign | MC 26.3 Sovereign |
| :--- | :--- | :--- | :--- |
| **Java Release Level** | `Java 25+` (`release = 25`) | `Java 25+` (`release = 25`) | `Java 25+` (`release = 25`) |
| **Loom Plugin** | `net.fabricmc.fabric-loom 1.15+` | `net.fabricmc.fabric-loom 1.15+` | `net.fabricmc.fabric-loom 1.15+` |
| **Mappings Block** | *Omitted (Non-obfuscated)* | *Omitted (Non-obfuscated)* | *Omitted (Non-obfuscated)* |
| **Client Task Queue Class** | `CompileTaskDynamicQueue` | `SectionTaskDynamicQueue` | `SectionTaskDynamicQueue` |
| **Task Inner Class** | `RenderSection.CompileTask` | `RenderSection.SectionTask` | `RenderSection.SectionTask` |
| **Ticket Storage** | `TicketStorage` / `TicketType` | `TicketStorage` / `TicketType` | `TicketStorage` / `TicketType` |
| **Resource Locations** | `Identifier.fromNamespaceAndPath` | `Identifier.fromNamespaceAndPath` | `Identifier.fromNamespaceAndPath` |
| **GameRule Category** | `GameRuleCategory.register` | `GameRuleCategory.register` | `GameRuleCategory.register` |

---

# 📚 Exhaustive Mechanics & Feature Catalog

```
═════════════════════════════════════════════════════════════════════════════════
          COMPLETE BEHAVIORAL & ARCHITECTURAL SPECIFICATION
═════════════════════════════════════════════════════════════════════════════════
```

---

## ⚡ Domain 1: Dynamic Velocity & Trajectory Kinematics

### 1. Velocity Tracking & Smoothing Engine (`VelocityCalculator`)
* **Tracking**: Samples player delta coordinates per tick ($\Delta x, \Delta y, \Delta z$) to compute the instantaneous velocity vector $\vec{v}$.
* **Smoothing**: Applies exponential moving average (EMA) with $\alpha = 0.65$ to eliminate jitter caused by minor camera turns while maintaining instantaneous reaction to sprint bursts, Elytra diving, and vehicle launches.
* **Speed Magnitude**:
  $$v = \sqrt{v_x^2 + v_y^2 + v_z^2}$$
* **Threshold Profiles**:
  * *Walking / Idle* ($v < 0.15\text{ blocks/tick}$): Vanilla baseline radial sorting.
  * *Sprinting / Horse Riding* ($0.15 \le v < 0.50\text{ blocks/tick}$): Moderate forward bias ($\text{reach} \approx 4 - 8$ chunks).
  * *Elytra / Ice Boating / Hyper Speed* ($v \ge 0.50\text{ blocks/tick}$): High-velocity aerodynamic cone ($\text{reach} \approx 8 - 16$ chunks ahead).

---

## 🖥️ Domain 2: Client-Side Anisotropic Chunk Mesh Priority

### 2. Directional Dot-Product Distance Scoring (`AnisotropicDistanceHelper`)
* **Vanilla Problem**: `SectionTaskDynamicQueue` (or `CompileTaskDynamicQueue` in 26.1.2) polls the nearest compile task using purely radial Euclidean distance:
  $$\text{dist} = \text{task.getRenderOrigin().distToCenterSqr(cameraPos)}$$
* **Anisotropic Solution**: When player speed exceeds `min_speed_threshold`, computes directional bias:
  $$\vec{d}_{\text{norm}} = \frac{\vec{v}}{\|\vec{v}\|}$$
  $$\Delta\vec{P} = \vec{P}_{\text{section}} - \vec{P}_{\text{cam}}$$
  $$\text{Dot} = \vec{d}_{\text{norm}} \cdot \Delta\vec{P}$$
  $$\text{BiasedDistSqr} = \|\Delta\vec{P}\|^2 - 2.0 \cdot \text{Dot} \cdot \min(v \times \text{biasMultiplier},\, \text{MaxLeadOffset})$$
* **Result**: Sections directly in front of the player's movement path receive dramatically lower distance scores, causing worker compile threads to prioritize meshing forward terrain first. Chunks behind the player are deferred without visual disruption.

---

## 🌐 Domain 3: Server-Side Predictive Chunk Generation & Ticket Cones

### 3. Forward Ticket Pre-Allocation (`ForwardTicketManager`)
* **Target Environment**: Dedicated Server and Singleplayer Integrated Server.
* **Ticket Type**: `TicketType.register("forward_prediction", 60L, TicketType.FLAG_LOADING)`.
  * Timeout of 60 ticks (3 seconds) ensures tickets automatically expire if player halts or turns.
  * Uses `FLAG_LOADING` without `FLAG_SIMULATION`, generating and loading world blocks for visual transmission and collision while avoiding entity/mob ticking overhead.
* **Trajectory Raycast**: Projects 4–8 sampling waypoints along the forward velocity vector $\vec{v}$ up to 16 chunks ahead, registering temporary loading tickets on `ServerChunkCache` / `TicketStorage`.

### 4. Adaptive Budget Conservation (`ServerBudgetManager`)
* **Balancing**: When a player reaches high speed and forward tickets are allocated, the server dynamically narrows the lateral and rear non-simulation view radius by up to $30\%$.
* **Performance Guarantee**: The total active chunk count per player remains stable, ensuring 20 TPS is preserved even when speeding across uncharted biomes.

---

## ⌨️ Domain 4: Commands, Diagnostics & GameRules

### 5. In-Game Brigadier Command Suite (`/yourunfast`)
* `/yourunfast help` — Displays the command guide.
* `/yourunfast status` — Displays real-time speed (m/s & b/t), forward lead distance, active tickets, and chunk compile queue stats.
* `/yourunfast get <gamerule>` — Queries active configuration.
* `/yourunfast set <gamerule> <value>` — Updates settings in real time.
* `/yourunfast reset` — Restores default settings.
* `/yourunfast reload` — Reloads configuration.

### 6. Namespaced GameRules (`YouRunFastGameRules`)
* `yourunfast:enabled` (Boolean, default `true`) — Master toggle for the entire prioritization system.
* `yourunfast:forward_lead_multiplier` (Integer %, default `100`) — Scales forward lead distance ($0\% - 300\%$).
* `yourunfast:budget_conservation` (Boolean, default `true`) — Enables adaptive lateral/rear ticket trimming.
* `yourunfast:min_speed_threshold_pct` (Integer %, default `20`) — Minimum speed in hundredths of a block/tick ($0.20\text{ b/t}$) to activate directional bias.
* `yourunfast:debug_mode` (Boolean, default `false`) — Gated diagnostic logging and actionbar stats.
