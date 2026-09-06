# 🚀 빠르게 달린다면, 그곳을 먼저 렌더링하라 (You Run Fast, Render That First)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 📖 속도 벡터 기반 이방성 청크 렌더링 우선순위 및 서버 예측 생성 최적화

You Run Fast, Render That First 는 고속 이동 시 발생하는 청크 팝인과 허공의 벽을 제거하기 위해 설계된 고성능 Fabric 최적화 모드입니다.

---

## ⚡ 빠르게 달린다면, 그곳을 먼저 렌더링하라 (You Run Fast, Render That First) — 핵심 기능 / 主要特性

- **이방성 청크 메시 우선순위화**: 방향성 내적 거리 계산으로 진행 경로 앞쪽의 청크를 최우선 컴파일합니다.
- **서버 예측 청크 생성 콘**: 진행 방향으로 최대 16청크 앞까지 PLAYER_LOADING 티켓을 사전 할당합니다.
- **동적 게임 규칙 및 명령어**: yourunfast:* 게임 규칙 및 /yourunfast 명령어로 실시간 제어가 가능합니다.
- **GC 할당 제로 고속 경로**: 렌더링 스레드에서 100% 힙 할당 없는 원시 타입 캐시를 사용합니다.

---

## 🏛️ 마인크래프트 버전 선택 포털

| Minecraft Version | Documentation Link | Client Queue Mixin | Minimum Java |
| :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | [[👉 Enter MC 26.3 Wiki|26.3-Home]] | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.2** | [[👉 Enter MC 26.2 Wiki|26.2-Home]] | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.1** | [[👉 Enter MC 26.1 Wiki|26.1-Home]] | `CompileTaskDynamicQueue` | Java 25+ |

---

## 🔗 기술 개요
- [[기술 개요|ko_kr-Overview]]
- [[English Documentation Portal|Home]]
