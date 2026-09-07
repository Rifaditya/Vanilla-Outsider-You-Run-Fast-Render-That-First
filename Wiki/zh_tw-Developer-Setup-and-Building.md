# 💻 開發者配置與原始碼建置指南

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **代碼倉庫原始碼免責聲明**：本維基文件反映了**倉庫當前的原始碼狀態**，可能包含領先於 CurseForge 與 Modrinth 上公開發布版本的最新開發提交或未發布功能。

---

## 🛠️ 開發環境要求

本專案採用搭載 Fabric Loom 的現代 Gradle 建置系統，目標執行環境為 Java 25+。每個受支援的 Minecraft 版本均作為獨立子專案進行維護，擁有專有的 `build.gradle` 與 `gradle.properties`。

### 1. 前置需求
- **JDK**: Java 25 或更高版本（Eclipse Temurin、Microsoft OpenJDK 或 Oracle GraalVM）。
- **Git**: 2.40+
- **IDE**: IntelliJ IDEA 2024.3+ 或配置了 Gradle Buildship 的 Eclipse。

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

## 🚀 建置與測試指令

若要編譯並驗證特定的 Minecraft 版本錨點，請進入其專屬的子專案目錄：

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

> ☕ *獨立開發者寄語*：如果你喜歡高速飛行和騎馬時前方地形即時渲染的流暢體驗，歡迎在 [Ko-fi](https://ko-fi.com/dasikigaijin) 上支持我的獨立開發！

---

## 🔙 Return Navigation

- [[Back to Version Selector Portal|zh_tw-Home]]
- [[View Compatibility Matrix|zh_tw-Version-Compatibility]]
