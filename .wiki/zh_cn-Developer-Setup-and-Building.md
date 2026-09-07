# 💻 开发者配置与源码构建指南

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **代码仓库源码免责声明**：本维基文档反映了**仓库当前的源代码状态**，可能包含领先于 CurseForge 与 Modrinth 上公开发布版本的最新开发提交或未发布功能。

---

## 🛠️ 开发环境要求

本项目采用搭载 Fabric Loom 的现代 Gradle 构建系统，目标运行环境为 Java 25+。每个受支持的 Minecraft 版本均作为独立子项目进行维护，拥有专有的 `build.gradle` 与 `gradle.properties`。

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

## 🚀 构建与测试指令

若要编译并验证特定的 Minecraft 版本锚点，请进入其专属的子项目目录：

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

> ☕ *独立开发者寄语*：如果你喜欢高速飞行和骑马时前方地形即时渲染的流畅体验，欢迎在 [Ko-fi](https://ko-fi.com/dasikigaijin) 上支持我的独立开发！

---

## 🔙 Return Navigation

- [[Back to Version Selector Portal|zh_cn-Home]]
- [[View Compatibility Matrix|zh_cn-Version-Compatibility]]
