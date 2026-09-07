# 🧭 版本相容性矩陣與環境技術規範

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **代碼倉庫原始碼免責聲明**：本維基文件反映了**倉庫當前的原始碼狀態**，可能包含領先於 CurseForge 與 Modrinth 上公開發布版本的最新開發提交或未發布功能。

---

## 📊 多版本支援矩陣

**Vanilla Outsider: You Run Fast, Render That First** 嚴格貫徹 **單版本單 Jar 政策 (1 Jar 1 Version Policy)**。每個目標 Minecraft 發行版均維護在獨立的子專案中，確保編譯期與執行期的絕對隔離與最高效能。

| Target Minecraft | Status | Toolchain | Loom Version | Client Queue Target | Mod Dependency |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **MC 26.3** | **Modern Lead** | Java 25+ | `net.fabricmc.fabric-loom 1.15+` | `SectionTaskDynamicQueue` | `dasik-library >= 1.8.0` |
| **MC 26.2** | **Modern Predecessor** | Java 25+ | `net.fabricmc.fabric-loom 1.15+` | `SectionTaskDynamicQueue` | `dasik-library >= 1.8.0` |
| **MC 26.1 (26.1.2)** | **Modern Anchor** | Java 25+ | `net.fabricmc.fabric-loom 1.15+` | `CompileTaskDynamicQueue` | `dasik-library >= 1.8.0` |

---

## ⚙️ 執行階段與環境依賴

| Component | Minimum Bound | Recommended Version | Technical Notes |
| :--- | :--- | :--- | :--- |
| **Fabric Loader** | `>= 0.18.4` | Latest Stable | Java 25 bytecode loading & Mixin 0.8+ |
| **Fabric API** | `*` (Matching MC) | Latest Release | Lifecycle events (`ServerTickEvents`, `ClientTickEvents`, `CommandRegistrationCallback`) |
| **Dasik Library** | `>= 1.8.0` | 1.8.0+ | Namespaced dynamic GameRule management & registry unfreezing |
| **Java Virtual Machine** | `Java 25` | JDK 25 LTS | Modern foreign memory, vector API, high-performance GC |

---

## 🎨 圖形渲染管線與光影相容性

---

> ☕ *獨立開發者寄語*：如果你喜歡高速飛行和騎馬時前方地形即時渲染的流暢體驗，歡迎在 [Ko-fi](https://ko-fi.com/dasikigaijin) 上支持我的獨立開發！

---

## 🗂️ Version Navigation

- [[👉 Explore MC 26.3 Documentation|zh_tw-26.3-Home]]
- [[👉 Explore MC 26.2 Documentation|zh_tw-26.2-Home]]
- [[👉 Explore MC 26.1 Documentation|zh_tw-26.1-Home]]
