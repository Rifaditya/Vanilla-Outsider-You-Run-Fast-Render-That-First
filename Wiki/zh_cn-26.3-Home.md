# 🚀 Minecraft 26.3 — 跑得飞快，先渲染那儿 (You Run Fast, Render That First)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **代码仓库源码免责声明**：本维基文档反映了**仓库当前的源代码状态**，可能包含领先于 CurseForge 与 Modrinth 上公开发布版本的最新开发提交或未发布功能。

---

## 📖 子项目概览：MC 26.3 独立版本锚点

欢迎查阅 **Vanilla Outsider: You Run Fast, Render That First** 在 **Minecraft 26.3** 上的技术文档。

本版本针对 Minecraft `26.3`、Fabric Loader `>=0.18.4` 和 Java 25+ 构建。客户端以 `SectionTaskDynamicQueue` 为目标加速区块网格化，并依托 DasikLibrary `>=1.8.0` 实现命名空间游戏规则的动态同步。

---

## 📚 功能与子系统导航矩阵

| 特性领域 | 维基指南链接 | 核心机制与职责 |
| :--- | :--- | :--- |
| **Client Meshing** | [[Anisotropic Prioritization|zh_cn-26.3-Anisotropic-Prioritization]] | Directional dot-product distance bias, EMA smoothing, zero-allocation render polling |
| **Server Worldgen** | [[Chunk Generation Biasing|zh_cn-26.3-Chunk-Generation-Biasing]] | Predictive forward tickets (`TicketType.PLAYER_LOADING`), MSPT watchdog, lateral trimming |
| **Configuration** | [[Configuration & GameRules|zh_cn-26.3-Configuration-and-GameRules]] | Dynamic namespaced GameRules, `/yourunfast` command suite (`help`, `status`, `set`) |
| **Architecture** | [[Architecture & Mixins|zh_cn-26.3-Architecture-and-Mixins]] | Mixin inspection (`SectionTaskDynamicQueueMixin`), FastUtil data structures, zero-GC fast path |

---

## ⚡ 核心技术规格

- **Target Game Version**: Minecraft `26.3`
- **Client Render Queue**: `SectionTaskDynamicQueue`
- **Server Ticket Type**: `TicketType.PLAYER_LOADING` with radius 1
- **Dynamic GameRules Category**: `yourunfast:main`
- **Root Brigadier Command**: `/yourunfast`
- **Mathematical Smoothing**: Exponential Moving Average with $\alpha = 0.65$

---

> ☕ *独立开发者寄语*：如果你喜欢高速飞行和骑马时前方地形即时渲染的流畅体验，欢迎在 [Ko-fi](https://ko-fi.com/dasikigaijin) 上支持我的独立开发！

---

## 🔙 返回导航
- [[Back to Version Selector Portal|zh_cn-Home]]
- [[View Compatibility Matrix|zh_cn-Version-Compatibility]]
- [[Developer Setup & Building|zh_cn-Developer-Setup-and-Building]]
