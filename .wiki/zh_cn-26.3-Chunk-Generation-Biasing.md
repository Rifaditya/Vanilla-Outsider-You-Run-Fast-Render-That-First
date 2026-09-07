# 🌐 服务端预测性区块生成与加载票据锥 (MC 26.3)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **代码仓库源码免责声明**：本维基文档反映了**仓库当前的源代码状态**，可能包含领先于 CurseForge 与 Modrinth 上公开发布版本的最新开发提交或未发布功能。

---

## 1. 官方技术信息框

| Parameter | Technical Details |
| :--- | :--- |
| **Subsystem Name** | Predictive Chunk Generation & Ticket Cones |
| **Minecraft Anchor** | MC 26.3 |
| **Java Implementation** | `net.vanillaoutsider.yourunfast.server.ForwardTicketManager` |
| **Budget Manager** | `net.vanillaoutsider.yourunfast.server.ServerBudgetManager` |
| **Ticket Type** | `TicketType.PLAYER_LOADING` |
| **Ticket Load Radius** | Radius $1$ (Centered on projected chunk) |
| **Storage Structure** | FastUtil `LongOpenHashSet` (Zero-allocation bit-packed coordinates) |
| **Watchdog Metric** | Server MSPT via `ServerLevel.getServer().getAverageTickTimeNanos()` |
| **Controlling GameRules** | `yourunfast:enabled`, `yourunfast:budget_conservation`, `yourunfast:forward_lead_multiplier` |

---

## 2. 服务端生命周期与玩家工作流程

```
       [ ServerTickEvents.END_SERVER_TICK ]
                        |
                        v
     [ ForwardTicketManager.tickPlayer(player) ]
                        |
   +--------------------+--------------------+
   |                                         |
(Speed < 0.20 b/t)                 (Speed >= 0.20 b/t)
   |                                         |
   v                                         v
Clear existing tickets             Check throttling triggers:
and return                         - Did player cross chunk boundary?
                                   - Did player yaw turn > 8 degrees?
                                   - Have 10 server ticks elapsed?
                                             |
                                    [ Trigger Met ]
                                             |
                                             v
                                  Evaluate Server MSPT
                                  Compute Dynamic Reach (4 - 16 chunks)
                                             |
                                             v
                                  Project Trajectory Waypoints
                                  (Add lateral fan-out if speed >= 0.80)
                                             |
                                             v
                                  Diff against activeTickets
                                  - Remove stale tickets
                                  - Add new PLAYER_LOADING tickets
```

---

## 3. MSPT 动态节流与航点投影公式

$$\text{msptFactor} = \begin{cases} 1.0 & \text{if } \text{mspt} \le 25.0 \\ \max\left(0.25,\, 1.0 - \frac{\text{mspt} - 25.0}{25.0}\right) & \text{if } \text{mspt} > 25.0 \end{cases}$$

$$\text{TargetReach} = \text{clamp}\left(\text{round}\left(16.0 \cdot \frac{s}{1.5} \cdot \text{leadMult} \cdot \text{msptFactor}\right),\, 4,\, 16\right)$$

---

> ☕ *独立开发者寄语*：如果你喜欢高速飞行和骑马时前方地形即时渲染的流畅体验，欢迎在 [Ko-fi](https://ko-fi.com/dasikigaijin) 上支持我的独立开发！

---

## 🔗 Related Pages (MC 26.3)

- [[Anisotropic Prioritization|zh_cn-26.3-Anisotropic-Prioritization]]
- [[Configuration & GameRules|zh_cn-26.3-Configuration-and-GameRules]]
- [[Architecture & Mixins|zh_cn-26.3-Architecture-and-Mixins]]
- Return to [[26.3 Overview Portal|zh_cn-26.3-Home]]
