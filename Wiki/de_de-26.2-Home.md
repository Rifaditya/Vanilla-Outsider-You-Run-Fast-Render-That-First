# 🚀 Minecraft 26.2 — You Run Fast, Render That First

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Quellcode-Hinweis**: Die Dokumentation in diesem Wiki gibt den **aktuellen Stand des Quellcodes im Repository** wieder, einschließlich unfertiger Commits und Entwicklungsfunktionen vor der offiziellen Veröffentlichung auf CurseForge und Modrinth.

---

## 📖 Unterprojekt-Übersicht: Anker MC 26.2

Willkommen zur Dokumentation von **Vanilla Outsider: You Run Fast, Render That First** für **Minecraft 26.2**.

Kompiliert für Minecraft `26.2`, Fabric Loader `>=0.18.4` und Java 25+. Nutzt `SectionTaskDynamicQueue` für Client-Beschleunigung und DasikLibrary `>=1.8.0`.

---

## 📚 Navigationsmatrix der Subsysteme

| Funktionsbereich | Wiki-Leitfaden | Kernaufgaben & Mechaniken |
| :--- | :--- | :--- |
| **Client Meshing** | [[Anisotropic Prioritization|de_de-26.2-Anisotropic-Prioritization]] | Directional dot-product distance bias, EMA smoothing, zero-allocation render polling |
| **Server Worldgen** | [[Chunk Generation Biasing|de_de-26.2-Chunk-Generation-Biasing]] | Predictive forward tickets (`TicketType.PLAYER_LOADING`), MSPT watchdog, lateral trimming |
| **Configuration** | [[Configuration & GameRules|de_de-26.2-Configuration-and-GameRules]] | Dynamic namespaced GameRules, `/yourunfast` command suite (`help`, `status`, `set`) |
| **Architecture** | [[Architecture & Mixins|de_de-26.2-Architecture-and-Mixins]] | Mixin inspection (`SectionTaskDynamicQueueMixin`), FastUtil data structures, zero-GC fast path |

---

## ⚡ Technische Spezifikationen

- **Target Game Version**: Minecraft `26.2`
- **Client Render Queue**: `SectionTaskDynamicQueue`
- **Server Ticket Type**: `TicketType.PLAYER_LOADING` with radius 1
- **Dynamic GameRules Category**: `yourunfast:main`
- **Root Brigadier Command**: `/yourunfast`
- **Mathematical Smoothing**: Exponential Moving Average with $\alpha = 0.65$

---

> ☕ *Solo-Entwickler-Notiz*: Wenn dir die flüssige Chunk-Generierung bei hohen Geschwindigkeiten ohne Pop-Ins gefällt, unterstütze meine Arbeit auf [Ko-fi](https://ko-fi.com/dasikigaijin)!

---

## 🔙 Rückkehr
- [[Back to Version Selector Portal|de_de-Home]]
- [[View Compatibility Matrix|de_de-Version-Compatibility]]
- [[Developer Setup & Building|de_de-Developer-Setup-and-Building]]
