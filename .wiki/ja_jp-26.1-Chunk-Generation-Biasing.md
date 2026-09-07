# 🌐 サーバー側予測チャンク生成とチケットコーン (MC 26.1)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **リポジトリソースコードに関する免責事項**: 本Wikiドキュメントは**リポジトリの最新ソースコード状態**を反映しており、CurseForgeやModrinthで公開されているリリース版に含まれない開発中機能を含む場合があります。

---

## 1. 公式技術インフォボックス

| Parameter | Technical Details |
| :--- | :--- |
| **Subsystem Name** | Predictive Chunk Generation & Ticket Cones |
| **Minecraft Anchor** | MC 26.1 |
| **Java Implementation** | `net.vanillaoutsider.yourunfast.server.ForwardTicketManager` |
| **Budget Manager** | `net.vanillaoutsider.yourunfast.server.ServerBudgetManager` |
| **Ticket Type** | `TicketType.PLAYER_LOADING` |
| **Ticket Load Radius** | Radius $1$ (Centered on projected chunk) |
| **Storage Structure** | FastUtil `LongOpenHashSet` (Zero-allocation bit-packed coordinates) |
| **Watchdog Metric** | Server MSPT via `ServerLevel.getServer().getAverageTickTimeNanos()` |
| **Controlling GameRules** | `yourunfast:enabled`, `yourunfast:budget_conservation`, `yourunfast:forward_lead_multiplier` |

---

## 2. サーバーライフサイクルおよびプレイヤーフロー

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

## 3. MSPT動的調整およびウェイポイント予測式

$$\text{msptFactor} = \begin{cases} 1.0 & \text{if } \text{mspt} \le 25.0 \\ \max\left(0.25,\, 1.0 - \frac{\text{mspt} - 25.0}{25.0}\right) & \text{if } \text{mspt} > 25.0 \end{cases}$$

$$\text{TargetReach} = \text{clamp}\left(\text{round}\left(16.0 \cdot \frac{s}{1.5} \cdot \text{leadMult} \cdot \text{msptFactor}\right),\, 4,\, 16\right)$$

---

> ☕ *個人開発者ノート*: エリトラ滑空や騎乗移動時のスムーズな前方チャンク即時描画を気に入っていただけたら、ぜひ [Ko-fi](https://ko-fi.com/dasikigaijin) でのご支援をお願いします！

---

## 🔗 Related Pages (MC 26.1)

- [[Anisotropic Prioritization|ja_jp-26.1-Anisotropic-Prioritization]]
- [[Configuration & GameRules|ja_jp-26.1-Configuration-and-GameRules]]
- [[Architecture & Mixins|ja_jp-26.1-Architecture-and-Mixins]]
- Return to [[26.1 Overview Portal|ja_jp-26.1-Home]]
