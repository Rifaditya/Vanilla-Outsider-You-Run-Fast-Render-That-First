# 💻 개발자 환경 설정 및 소스 코드 빌드 가이드

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **저장소 소스 코드 면책 조항**: 본 위키 문서는 **저장소의 현재 소스 코드 상태**를 반영하며, CurseForge 및 Modrinth의 공개 릴리스 빌드보다 앞선 미출시 커밋이나 개발 중인 기능을 포함할 수 있습니다.

---

## 🛠️ 개발 환경 설정

본 프로젝트는 Java 25+를 대상으로 하는 Fabric Loom 기반 최신 Gradle 빌드 시스템을 사용합니다. 각 버전은 독립된 하위 프로젝트로 관리됩니다.

### 1. 사전 요구사항
- **JDK**: Java 25 이상.
- **Git**: 2.40+
- **IDE**: IntelliJ IDEA 2024.3+ 또는 Eclipse.

```bash
git clone https://github.com/Rifaditya/Vanilla-Outsider-You-Run-Fast-Render-That-First.git
cd "Vanilla-Outsider-You-Run-Fast-Render-That-First"
```

---

## 🏗️ Multi-Version Directory Layout

```
Vanilla-Outsider-You-Run-Fast-Render-That-First/
├── You Run Fast Render That First v26.1/
│   └── You Run Fast Render That First 26.1/
│       ├── build.gradle
│       ├── gradle.properties
│       └── src/
├── You Run Fast Render That First v26.2/
│   └── You Run Fast Render That First 26.2/
│       ├── build.gradle
│       ├── gradle.properties
│       └── src/
├── You Run Fast Render That First v26.3/
│   └── You Run Fast Render That First 26.3/
│       ├── build.gradle
│       ├── gradle.properties
│       └── src/
├── Wiki/
└── README.md
```

---

## 🚀 빌드 및 테스트 명령어

특정 버전 하위 프로젝트 디렉터리로 이동하여 명령어를 실행하십시오:

### MC 26.3 Build & Test
```powershell
cd "You Run Fast Render That First v26.3\You Run Fast Render That First 26.3"
./gradlew check --no-daemon
./gradlew build --no-daemon
```

### MC 26.2 Build & Test
```powershell
cd "You Run Fast Render That First v26.2\You Run Fast Render That First 26.2"
./gradlew check --no-daemon
./gradlew build --no-daemon
```

### MC 26.1 Build & Test
```powershell
cd "You Run Fast Render That First v26.1\You Run Fast Render That First 26.1"
./gradlew check --no-daemon
./gradlew build --no-daemon
```

---

> ☕ *1인 개발자 노트*: 겉날개 활공이나 말 탑승 시 이동 방향 지형이 즉시 매끄럽게 렌더링되는 경험이 마음에 드셨다면, [Ko-fi](https://ko-fi.com/dasikigaijin)에서 개발을 응원해 주세요!

---

## 🔙 Return Navigation

- [[Back to Version Selector Portal|ko_kr-Home]]
- [[View Compatibility Matrix|ko_kr-Version-Compatibility]]
