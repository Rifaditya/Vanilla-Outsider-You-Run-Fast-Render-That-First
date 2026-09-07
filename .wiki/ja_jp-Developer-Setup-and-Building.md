# 💻 開発環境構築およびソースコードビルドガイド

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **リポジトリソースコードに関する免責事項**: 本Wikiドキュメントは**リポジトリの最新ソースコード状態**を反映しており、CurseForgeやModrinthで公開されているリリース版に含まれない開発中機能を含む場合があります。

---

## 🛠️ 開発環境のセットアップ

本プロジェクトはJava 25+を対象としたFabric Loom環境のGradleを使用しています。各バージョンは独立したサブプロジェクトとして管理されています。

### 1. 前提条件
- **JDK**: Java 25以上。
- **Git**: 2.40+
- **IDE**: IntelliJ IDEA 2024.3+ または Eclipse。

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

## 🚀 ビルドおよびテストコマンド

目的のバージョンのサブプロジェクトディレクトリに移動して実行します:

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

> ☕ *個人開発者ノート*: エリトラ滑空や騎乗移動時のスムーズな前方チャンク即時描画を気に入っていただけたら、ぜひ [Ko-fi](https://ko-fi.com/dasikigaijin) でのご支援をお願いします！

---

## 🔙 Return Navigation

- [[Back to Version Selector Portal|ja_jp-Home]]
- [[View Compatibility Matrix|ja_jp-Version-Compatibility]]
