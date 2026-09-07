# 🚀 Minecraft 26.3 — You Run Fast, Render That First

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **リポジトリソースコードに関する免責事項**: 本Wikiドキュメントは**リポジトリの最新ソースコード状態**を反映しており、CurseForgeやModrinthで公開されているリリース版に含まれない開発中機能を含む場合があります。

---

## 📖 サブプロジェクト概要: MC 26.3 独立アンカー

**Minecraft 26.3** 版 **Vanilla Outsider: You Run Fast, Render That First** の技術ドキュメントへようこそ。

Minecraft `26.3`, Fabric Loader `>=0.18.4`, Java 25+ を対象に構築され、クライアント側 `SectionTaskDynamicQueue` の高速化および DasikLibrary `>=1.8.0` による動的ルール同期を行います。

---

## 📚 機能・サブシステムナビゲーションマトリクス

| 機能領域 | Wikiガイドリンク | 主な責務とメカニクス |
| :--- | :--- | :--- |
| **Client Meshing** | [[Anisotropic Prioritization|ja_jp-26.3-Anisotropic-Prioritization]] | Directional dot-product distance bias, EMA smoothing, zero-allocation render polling |
| **Server Worldgen** | [[Chunk Generation Biasing|ja_jp-26.3-Chunk-Generation-Biasing]] | Predictive forward tickets (`TicketType.PLAYER_LOADING`), MSPT watchdog, lateral trimming |
| **Configuration** | [[Configuration & GameRules|ja_jp-26.3-Configuration-and-GameRules]] | Dynamic namespaced GameRules, `/yourunfast` command suite (`help`, `status`, `set`) |
| **Architecture** | [[Architecture & Mixins|ja_jp-26.3-Architecture-and-Mixins]] | Mixin inspection (`SectionTaskDynamicQueueMixin`), FastUtil data structures, zero-GC fast path |

---

## ⚡ 主な技術仕様

- **Target Game Version**: Minecraft `26.3`
- **Client Render Queue**: `SectionTaskDynamicQueue`
- **Server Ticket Type**: `TicketType.PLAYER_LOADING` with radius 1
- **Dynamic GameRules Category**: `yourunfast:main`
- **Root Brigadier Command**: `/yourunfast`
- **Mathematical Smoothing**: Exponential Moving Average with $\alpha = 0.65$

---

> ☕ *個人開発者ノート*: エリトラ滑空や騎乗移動時のスムーズな前方チャンク即時描画を気に入っていただけたら、ぜひ [Ko-fi](https://ko-fi.com/dasikigaijin) でのご支援をお願いします！

---

## 🔙 ナビゲーション
- [[Back to Version Selector Portal|ja_jp-Home]]
- [[View Compatibility Matrix|ja_jp-Version-Compatibility]]
- [[Developer Setup & Building|ja_jp-Developer-Setup-and-Building]]
