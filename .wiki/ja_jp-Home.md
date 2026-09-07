# 🚀 You Run Fast, Render That First — 公式技術Wiki

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **リポジトリソースコードに関する免責事項**: 本Wikiドキュメントは**リポジトリの最新ソースコード状態**を反映しており、CurseForgeやModrinthで公開されているリリース版に含まれない開発中機能を含む場合があります。

---

## 🧭 公式ドキュメントへようこそ

**Vanilla Outsider: You Run Fast, Render That First** は、高速移動時（エリトラ滑空や高速騎乗）のチャンク描画遅延（ポップイン）と虚空の壁を根絶するために設計された超高性能Fabric最適化MODです。クライアント側のチャンクメッシュ構築キューとサーバー側のワールド生成チケットをプレイヤーのリアルタイム速度ベクトル ($ec{v}$) に同期させ、移動先の地形を最優先でコンパイル・ロードします。

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

## 🏛️ Minecraftバージョンの選択

| Minecraft Version | Version Tree Link | Engine Lifecycle | Client Queue Mixin | Minimum Java |
| :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | [[👉 Enter MC 26.3 Wiki|ja_jp-26.3-Home]] | **Modern Lead** | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.2** | [[👉 Enter MC 26.2 Wiki|ja_jp-26.2-Home]] | **Modern Predecessor** | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.1** | [[👉 Enter MC 26.1 Wiki|ja_jp-26.1-Home]] | **Modern Anchor (26.1.2)** | `CompileTaskDynamicQueue` | Java 25+ |

---

## ⚡ 主要アーキテクチャサブシステム

- **[[Anisotropic Chunk Prioritization|ja_jp-26.3-Anisotropic-Prioritization]]**
- **[[Server Chunk Generation Biasing & Watchdog|ja_jp-26.3-Chunk-Generation-Biasing]]**
- **[[Dynamic GameRules & Brigadier Commands|ja_jp-26.3-Configuration-and-GameRules]]**
- **[[Zero-GC High-Frequency Hot Path|ja_jp-26.3-Architecture-and-Mixins]]**

---

> ☕ *個人開発者ノート*: エリトラ滑空や騎乗移動時のスムーズな前方チャンク即時描画を気に入っていただけたら、ぜひ [Ko-fi](https://ko-fi.com/dasikigaijin) でのご支援をお願いします！

---

## 🛠️ 開発者向けリファレンス

- [[Version Compatibility Matrix|ja_jp-Version-Compatibility]]
- [[Developer Setup & Building|ja_jp-Developer-Setup-and-Building]]
