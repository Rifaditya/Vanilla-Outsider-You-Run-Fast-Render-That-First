# 🚀 Minecraft 26.3 — You Run Fast, Render That First

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **저장소 소스 코드 면책 조항**: 본 위키 문서는 **저장소의 현재 소스 코드 상태**를 반영하며, CurseForge 및 Modrinth의 공개 릴리스 빌드보다 앞선 미출시 커밋이나 개발 중인 기능을 포함할 수 있습니다.

---

## 📖 하위 프로젝트 개요: MC 26.3 독립 앵커

**Minecraft 26.3** 환경의 **Vanilla Outsider: You Run Fast, Render That First** 기술 문서에 오신 것을 환영합니다.

Minecraft `26.3`, Fabric Loader `>=0.18.4`, Java 25+를 대상으로 빌드되었습니다. 클라이언트 `SectionTaskDynamicQueue` 큐를 최적화하고 DasikLibrary `>=1.8.0`을 통해 게임룰을 동기화합니다.

---

## 📚 기능 및 서브시스템 내비게이션 매트릭스

| 기능 도메인 | 위키 가이드 링크 | 핵심 역할 및 메커니즘 |
| :--- | :--- | :--- |
| **Client Meshing** | [[Anisotropic Prioritization|ko_kr-26.3-Anisotropic-Prioritization]] | Directional dot-product distance bias, EMA smoothing, zero-allocation render polling |
| **Server Worldgen** | [[Chunk Generation Biasing|ko_kr-26.3-Chunk-Generation-Biasing]] | Predictive forward tickets (`TicketType.PLAYER_LOADING`), MSPT watchdog, lateral trimming |
| **Configuration** | [[Configuration & GameRules|ko_kr-26.3-Configuration-and-GameRules]] | Dynamic namespaced GameRules, `/yourunfast` command suite (`help`, `status`, `set`) |
| **Architecture** | [[Architecture & Mixins|ko_kr-26.3-Architecture-and-Mixins]] | Mixin inspection (`SectionTaskDynamicQueueMixin`), FastUtil data structures, zero-GC fast path |

---

## ⚡ 핵심 기술 사양

- **Target Game Version**: Minecraft `26.3`
- **Client Render Queue**: `SectionTaskDynamicQueue`
- **Server Ticket Type**: `TicketType.PLAYER_LOADING` with radius 1
- **Dynamic GameRules Category**: `yourunfast:main`
- **Root Brigadier Command**: `/yourunfast`
- **Mathematical Smoothing**: Exponential Moving Average with $\alpha = 0.65$

---

> ☕ *1인 개발자 노트*: 겉날개 활공이나 말 탑승 시 이동 방향 지형이 즉시 매끄럽게 렌더링되는 경험이 마음에 드셨다면, [Ko-fi](https://ko-fi.com/dasikigaijin)에서 개발을 응원해 주세요!

---

## 🔙 돌아가기
- [[Back to Version Selector Portal|ko_kr-Home]]
- [[View Compatibility Matrix|ko_kr-Version-Compatibility]]
- [[Developer Setup & Building|ko_kr-Developer-Setup-and-Building]]
