# 🧭 버전 호환성 매트릭스 및 환경 기술 사양

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **저장소 소스 코드 면책 조항**: 본 위키 문서는 **저장소의 현재 소스 코드 상태**를 반영하며, CurseForge 및 Modrinth의 공개 릴리스 빌드보다 앞선 미출시 커밋이나 개발 중인 기능을 포함할 수 있습니다.

---

## 📊 마인크래프트 멀티 버전 지원 매트릭스

**Vanilla Outsider: You Run Fast, Render That First**는 **1 Jar 1 버전 정책**을 엄격히 준수합니다. 각 버전은 독립된 하위 프로젝트에서 격리 개발됩니다.

| Target Minecraft | Status | Toolchain | Loom Version | Client Queue Target | Mod Dependency |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **MC 26.3** | **Modern Lead** | Java 25+ | `net.fabricmc.fabric-loom 1.15+` | `SectionTaskDynamicQueue` | `dasik-library >= 1.8.0` |
| **MC 26.2** | **Modern Predecessor** | Java 25+ | `net.fabricmc.fabric-loom 1.15+` | `SectionTaskDynamicQueue` | `dasik-library >= 1.8.0` |
| **MC 26.1 (26.1.2)** | **Modern Anchor** | Java 25+ | `net.fabricmc.fabric-loom 1.15+` | `CompileTaskDynamicQueue` | `dasik-library >= 1.8.0` |

---

## ⚙️ 런타임 및 환경 종속성

| Component | Minimum Bound | Recommended Version | Technical Notes |
| :--- | :--- | :--- | :--- |
| **Fabric Loader** | `>= 0.18.4` | Latest Stable | Java 25 bytecode loading & Mixin 0.8+ |
| **Fabric API** | `*` (Matching MC) | Latest Release | Lifecycle events (`ServerTickEvents`, `ClientTickEvents`, `CommandRegistrationCallback`) |
| **Dasik Library** | `>= 1.8.0` | 1.8.0+ | Namespaced dynamic GameRule management & registry unfreezing |
| **Java Virtual Machine** | `Java 25` | JDK 25 LTS | Modern foreign memory, vector API, high-performance GC |

---

## 🎨 그래픽 파이프라인 및 셰이더 호환성

---

> ☕ *1인 개발자 노트*: 겉날개 활공이나 말 탑승 시 이동 방향 지형이 즉시 매끄럽게 렌더링되는 경험이 마음에 드셨다면, [Ko-fi](https://ko-fi.com/dasikigaijin)에서 개발을 응원해 주세요!

---

## 🗂️ Version Navigation

- [[👉 Explore MC 26.3 Documentation|ko_kr-26.3-Home]]
- [[👉 Explore MC 26.2 Documentation|ko_kr-26.2-Home]]
- [[👉 Explore MC 26.1 Documentation|ko_kr-26.1-Home]]
