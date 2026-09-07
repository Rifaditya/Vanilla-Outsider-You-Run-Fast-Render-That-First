# 🌐 Serverseitige prädiktive Chunk-Generierung & Ticket-Kegel (MC 26.3)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Quellcode-Hinweis**: Die Dokumentation in diesem Wiki gibt den **aktuellen Stand des Quellcodes im Repository** wieder, einschließlich unfertiger Commits und Entwicklungsfunktionen vor der offiziellen Veröffentlichung auf CurseForge und Modrinth.

---

## 1. Offizielle Infobox

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

## 2. Server-Lebenszyklus und Arbeitsablauf

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

## 3. Dynamische MSPT-Drosselung & Wegpunkt-Projektion

$$\text{msptFactor} = \begin{cases} 1.0 & \text{if } \text{mspt} \le 25.0 \\ \max\left(0.25,\, 1.0 - \frac{\text{mspt} - 25.0}{25.0}\right) & \text{if } \text{mspt} > 25.0 \end{cases}$$

$$\text{TargetReach} = \text{clamp}\left(\text{round}\left(16.0 \cdot \frac{s}{1.5} \cdot \text{leadMult} \cdot \text{msptFactor}\right),\, 4,\, 16\right)$$

---

> ☕ *Solo-Entwickler-Notiz*: Wenn dir die flüssige Chunk-Generierung bei hohen Geschwindigkeiten ohne Pop-Ins gefällt, unterstütze meine Arbeit auf [Ko-fi](https://ko-fi.com/dasikigaijin)!

---

## 🔗 Related Pages (MC 26.3)

- [[Anisotropic Prioritization|de_de-26.3-Anisotropic-Prioritization]]
- [[Configuration & GameRules|de_de-26.3-Configuration-and-GameRules]]
- [[Architecture & Mixins|de_de-26.3-Architecture-and-Mixins]]
- Return to [[26.3 Overview Portal|de_de-26.3-Home]]
