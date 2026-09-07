# 🚀 Minecraft 26.1 — 跑得飛快，先渲染那兒 (You Run Fast, Render That First)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **代碼倉庫原始碼免責聲明**：本維基文件反映了**倉庫當前的原始碼狀態**，可能包含領先於 CurseForge 與 Modrinth 上公開發布版本的最新開發提交或未發布功能。

---

## 📖 子專案概覽：MC 26.1 獨立版本錨點

歡迎查閱 **Vanilla Outsider: You Run Fast, Render That First** 在 **Minecraft 26.1** 上的技術文件。

本版本針對 Minecraft `26.1.2`、Fabric Loader `>=0.18.4` 和 Java 25+ 建置。客戶端以 `CompileTaskDynamicQueue` 為目標加速區塊網格化，並依託 DasikLibrary `>=1.8.0` 實現命名空間遊戲規則的動態同步。

---

## 📚 功能與子系統導航矩陣

| 特性領域 | 維基指南連結 | 核心機制與職責 |
| :--- | :--- | :--- |
| **Client Meshing** | [[Anisotropic Prioritization|zh_tw-26.1-Anisotropic-Prioritization]] | Directional dot-product distance bias, EMA smoothing, zero-allocation render polling |
| **Server Worldgen** | [[Chunk Generation Biasing|zh_tw-26.1-Chunk-Generation-Biasing]] | Predictive forward tickets (`TicketType.PLAYER_LOADING`), MSPT watchdog, lateral trimming |
| **Configuration** | [[Configuration & GameRules|zh_tw-26.1-Configuration-and-GameRules]] | Dynamic namespaced GameRules, `/yourunfast` command suite (`help`, `status`, `set`) |
| **Architecture** | [[Architecture & Mixins|zh_tw-26.1-Architecture-and-Mixins]] | Mixin inspection (`CompileTaskDynamicQueueMixin`), FastUtil data structures, zero-GC fast path |

---

## ⚡ 核心技術規格

- **Target Game Version**: Minecraft `26.1.2`
- **Client Render Queue**: `CompileTaskDynamicQueue`
- **Server Ticket Type**: `TicketType.PLAYER_LOADING` with radius 1
- **Dynamic GameRules Category**: `yourunfast:main`
- **Root Brigadier Command**: `/yourunfast`
- **Mathematical Smoothing**: Exponential Moving Average with $\alpha = 0.65$

---

> ☕ *獨立開發者寄語*：如果你喜歡高速飛行和騎馬時前方地形即時渲染的流暢體驗，歡迎在 [Ko-fi](https://ko-fi.com/dasikigaijin) 上支持我的獨立開發！

---

## 🔙 返回導航
- [[Back to Version Selector Portal|zh_tw-Home]]
- [[View Compatibility Matrix|zh_tw-Version-Compatibility]]
- [[Developer Setup & Building|zh_tw-Developer-Setup-and-Building]]
