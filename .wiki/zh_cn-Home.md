# 🚀 跑得飞快，先渲染那儿 (You Run Fast, Render That First) — 官方维基

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **代码仓库源码免责声明**：本维基文档反映了**仓库当前的源代码状态**，可能包含领先于 CurseForge 与 Modrinth 上公开发布版本的最新开发提交或未发布功能。

---

## 🧭 欢迎访问官方技术文档

**Vanilla Outsider: You Run Fast, Render That First** 是一款超高性能的 Fabric 客户端与服务端优化模组，旨在消除高速移动时的区块突然弹出 (pop-in) 与虚空虚化墙。通过将客户端区块渲染网格化队列和服务端世界生成票据与玩家的实时速度向量 ($ec{v}$) 直接同步，你正在飞行或疾驰朝向的前方地形将获得最高优先级并首先编译加载。

```
                  =============================================
                  VELOCITY-BIASED ANISOTROPIC CHUNK PIPELINE
                  =============================================

                                  [ PLAYER ]
                                      |
                     Velocity Vector  |  s >= 0.20 b/t
                                      v
                 +-----------------------------------------+
                 |       VelocityCalculator (EMA a=0.65)   |
                 +-----------------------------------------+
                                 /         \
                                /           \
        (Client Render Meshing)/             \(Server Chunk Tickets)
                              v               v
             +---------------------+     +--------------------------+
             | AnisotropicDistance |     | ForwardTicketManager     |
             | Helper (Dot Product)|     | (TicketType.             |
             | Directional Bias)   |     |  PLAYER_LOADING)         |
             +---------------------+     +--------------------------+
                        |                             |
                        v                             v
             Prioritized Chunk Mesh       Predictive Forward Chunks
             (Zero Pop-In Ahead)          (Ahead up to 16 Chunks)
```

---

## 🏛️ 选择你的 Minecraft 版本

| Minecraft Version | Version Tree Link | Engine Lifecycle | Client Queue Mixin | Minimum Java |
| :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | [[👉 Enter MC 26.3 Wiki|zh_cn-26.3-Home]] | **Modern Lead** | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.2** | [[👉 Enter MC 26.2 Wiki|zh_cn-26.2-Home]] | **Modern Predecessor** | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.1** | [[👉 Enter MC 26.1 Wiki|zh_cn-26.1-Home]] | **Modern Anchor (26.1.2)** | `CompileTaskDynamicQueue` | Java 25+ |

---

## ⚡ 核心工程架构子系统

- **[[Anisotropic Chunk Prioritization|zh_cn-26.3-Anisotropic-Prioritization]]**
- **[[Server Chunk Generation Biasing & Watchdog|zh_cn-26.3-Chunk-Generation-Biasing]]**
- **[[Dynamic GameRules & Brigadier Commands|zh_cn-26.3-Configuration-and-GameRules]]**
- **[[Zero-GC High-Frequency Hot Path|zh_cn-26.3-Architecture-and-Mixins]]**

---

> ☕ *独立开发者寄语*：如果你喜欢高速飞行和骑马时前方地形即时渲染的流畅体验，欢迎在 [Ko-fi](https://ko-fi.com/dasikigaijin) 上支持我的独立开发！

---

## 🛠️ 开发者与环境文档

- [[Version Compatibility Matrix|zh_cn-Version-Compatibility]]
- [[Developer Setup & Building|zh_cn-Developer-Setup-and-Building]]
