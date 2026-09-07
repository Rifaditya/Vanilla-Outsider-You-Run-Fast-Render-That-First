# 🌐 서버 측 예측 청크 생성 및 티켓 콘 (MC 26.2)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **저장소 소스 코드 면책 조항**: 본 위키 문서는 **저장소의 현재 소스 코드 상태**를 반영하며, CurseForge 및 Modrinth의 공개 릴리스 빌드보다 앞선 미출시 커밋이나 개발 중인 기능을 포함할 수 있습니다.

---

## 1. 공식 기술 인포박스

| Parameter | Technical Details |
| :--- | :--- |
| **Subsystem Name** | Predictive Chunk Generation & Ticket Cones |
| **Minecraft Anchor** | MC 26.2 |
| **Java Implementation** | `net.vanillaoutsider.yourunfast.server.ForwardTicketManager` |
| **Budget Manager** | `net.vanillaoutsider.yourunfast.server.ServerBudgetManager` |
| **Ticket Type** | `TicketType.PLAYER_LOADING` |
| **Ticket Load Radius** | Radius $1$ (Centered on projected chunk) |
| **Storage Structure** | FastUtil `LongOpenHashSet` (Zero-allocation bit-packed coordinates) |
| **Watchdog Metric** | Server MSPT via `ServerLevel.getServer().getAverageTickTimeNanos()` |
| **Controlling GameRules** | `yourunfast:enabled`, `yourunfast:budget_conservation`, `yourunfast:forward_lead_multiplier` |

---

## 2. 서버 수명 주기 및 워크플로

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

## 3. MSPT 동적 조절 및 웨이포인트 예측 공식

$$\text{msptFactor} = \begin{cases} 1.0 & \text{if } \text{mspt} \le 25.0 \\ \max\left(0.25,\, 1.0 - \frac{\text{mspt} - 25.0}{25.0}\right) & \text{if } \text{mspt} > 25.0 \end{cases}$$

$$\text{TargetReach} = \text{clamp}\left(\text{round}\left(16.0 \cdot \frac{s}{1.5} \cdot \text{leadMult} \cdot \text{msptFactor}\right),\, 4,\, 16\right)$$

---

> ☕ *1인 개발자 노트*: 겉날개 활공이나 말 탑승 시 이동 방향 지형이 즉시 매끄럽게 렌더링되는 경험이 마음에 드셨다면, [Ko-fi](https://ko-fi.com/dasikigaijin)에서 개발을 응원해 주세요!

---

## 🔗 Related Pages (MC 26.2)

- [[Anisotropic Prioritization|ko_kr-26.2-Anisotropic-Prioritization]]
- [[Configuration & GameRules|ko_kr-26.2-Configuration-and-GameRules]]
- [[Architecture & Mixins|ko_kr-26.2-Architecture-and-Mixins]]
- Return to [[26.2 Overview Portal|ko_kr-26.2-Home]]
