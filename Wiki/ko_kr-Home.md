# 🚀 You Run Fast, Render That First — 공식 위키

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **저장소 소스 코드 면책 조항**: 본 위키 문서는 **저장소의 현재 소스 코드 상태**를 반영하며, CurseForge 및 Modrinth의 공개 릴리스 빌드보다 앞선 미출시 커밋이나 개발 중인 기능을 포함할 수 있습니다.

---

## 🧭 공식 기술 문서에 오신 것을 환영합니다

**Vanilla Outsider: You Run Fast, Render That First**는 고속 이동 시 발생하는 청크 팝인(pop-in)과 보이드 장벽을 원천 차단하는 초고성능 패브릭 최적화 모드입니다. 클라이언트 청크 렌더 메시 큐와 서버 월드 생성 티켓을 플레이어의 실시간 속도 벡터 ($ec{v}$)와 직접 동기화하여, 플레이어가 날아가거나 질주하는 방향의 지형을 최우선으로 컴파일하고 로드합니다.

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

## 🏛️ 마인크래프트 버전 선택

| Minecraft Version | Version Tree Link | Engine Lifecycle | Client Queue Mixin | Minimum Java |
| :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | [[👉 Enter MC 26.3 Wiki|ko_kr-26.3-Home]] | **Modern Lead** | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.2** | [[👉 Enter MC 26.2 Wiki|ko_kr-26.2-Home]] | **Modern Predecessor** | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.1** | [[👉 Enter MC 26.1 Wiki|ko_kr-26.1-Home]] | **Modern Anchor (26.1.2)** | `CompileTaskDynamicQueue` | Java 25+ |

---

## ⚡ 핵심 엔지니어링 서브시스템

- **[[Anisotropic Chunk Prioritization|ko_kr-26.3-Anisotropic-Prioritization]]**
- **[[Server Chunk Generation Biasing & Watchdog|ko_kr-26.3-Chunk-Generation-Biasing]]**
- **[[Dynamic GameRules & Brigadier Commands|ko_kr-26.3-Configuration-and-GameRules]]**
- **[[Zero-GC High-Frequency Hot Path|ko_kr-26.3-Architecture-and-Mixins]]**

---

> ☕ *1인 개발자 노트*: 겉날개 활공이나 말 탑승 시 이동 방향 지형이 즉시 매끄럽게 렌더링되는 경험이 마음에 드셨다면, [Ko-fi](https://ko-fi.com/dasikigaijin)에서 개발을 응원해 주세요!

---

## 🛠️ 개발자 및 환경 문서

- [[Version Compatibility Matrix|ko_kr-Version-Compatibility]]
- [[Developer Setup & Building|ko_kr-Developer-Setup-and-Building]]
