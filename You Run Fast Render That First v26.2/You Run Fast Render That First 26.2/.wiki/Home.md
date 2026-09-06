# 🚀 Minecraft 26.2 (26.2) — You Run Fast, Render That First

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 📖 Subproject Overview: MC 26.2 Sovereign Anchor

Welcome to the sovereign technical documentation for **Vanilla Outsider: You Run Fast, Render That First** on **Minecraft 26.2**.

This version compiles against Minecraft `26.2` on Fabric Loader `>=0.18.4` with Java 25+. It targets `SectionTaskDynamicQueue` for client-side meshing acceleration and leverages DasikLibrary `>=1.8.0` for dynamic namespaced GameRule synchronization.

---

## 📚 Complete Feature & Subsystem Matrix

Navigate through the isolated documentation pages for MC 26.2:

| Feature Domain | Wiki Guide Link | Core Responsibilities & Mechanics |
| :--- | :--- | :--- |
| **Client Meshing** | [[Anisotropic Prioritization|Anisotropic-Prioritization]] | Directional dot-product distance bias, EMA smoothing, zero-allocation render polling |
| **Server Worldgen** | [[Chunk Generation Biasing|Chunk-Generation-Biasing]] | Predictive forward tickets (`TicketType.PLAYER_LOADING`), MSPT watchdog, lateral trimming |
| **Configuration** | [[Configuration & GameRules|Configuration-and-GameRules]] | Dynamic namespaced GameRules, `/yourunfast` Brigadier command suite (`help`, `status`, `set`) |
| **Architecture** | [[Architecture & Mixins|Architecture-and-Mixins]] | Detailed Mixin inspection (`SectionTaskDynamicQueueMixin`), FastUtil data structures, memory model |

---

## ⚡ Quick Technical Specifications

- **Target Game Version**: Minecraft `26.2`
- **Client Render Queue**: `SectionTaskDynamicQueue`
- **Client Mixin Target**: `net.minecraft.client.renderer.chunk.SectionTaskDynamicQueue`
- **Server Ticket Type**: `TicketType.PLAYER_LOADING` with radius 1
- **Dynamic GameRules Category**: `yourunfast:main`
- **Root Brigadier Command**: `/yourunfast` (Permission Level: 2 for admin mutators)
- **Mathematical Smoothing**: Exponential Moving Average with $\alpha = 0.65$

---

## 🔙 Return Navigation
- [[Back to Home|Home]]
- [[View Compatibility Matrix|Version-Compatibility]]
- [[Developer Setup & Building|Developer-Setup-and-Building]]
