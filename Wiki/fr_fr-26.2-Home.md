# 🚀 Minecraft 26.2 — You Run Fast, Render That First

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Clause de non-responsabilité relative au code source** : La documentation de ce Wiki reflète **l'état actuel du code source dans le dépôt**, incluant d'éventuels commits récents non publiés sur CurseForge ou Modrinth.

---

## 📖 Vue d'ensemble du sous-projet : Ancre MC 26.2

Bienvenue sur la documentation technique de **Vanilla Outsider: You Run Fast, Render That First** pour **Minecraft 26.2**.

Compilé pour Minecraft `26.2`, Fabric Loader `>=0.18.4` et Java 25+. Cible `SectionTaskDynamicQueue` et DasikLibrary `>=1.8.0`.

---

## 📚 Matrice de navigation des sous-systèmes

| Domaine technique | Guide Wiki | Rôles et mécaniques |
| :--- | :--- | :--- |
| **Client Meshing** | [[Anisotropic Prioritization|fr_fr-26.2-Anisotropic-Prioritization]] | Directional dot-product distance bias, EMA smoothing, zero-allocation render polling |
| **Server Worldgen** | [[Chunk Generation Biasing|fr_fr-26.2-Chunk-Generation-Biasing]] | Predictive forward tickets (`TicketType.PLAYER_LOADING`), MSPT watchdog, lateral trimming |
| **Configuration** | [[Configuration & GameRules|fr_fr-26.2-Configuration-and-GameRules]] | Dynamic namespaced GameRules, `/yourunfast` command suite (`help`, `status`, `set`) |
| **Architecture** | [[Architecture & Mixins|fr_fr-26.2-Architecture-and-Mixins]] | Mixin inspection (`SectionTaskDynamicQueueMixin`), FastUtil data structures, zero-GC fast path |

---

## ⚡ Spécifications techniques

- **Target Game Version**: Minecraft `26.2`
- **Client Render Queue**: `SectionTaskDynamicQueue`
- **Server Ticket Type**: `TicketType.PLAYER_LOADING` with radius 1
- **Dynamic GameRules Category**: `yourunfast:main`
- **Root Brigadier Command**: `/yourunfast`
- **Mathematical Smoothing**: Exponential Moving Average with $\alpha = 0.65$

---

> ☕ *Note du développeur solo*: Si vous appréciez la fluidité du rendu de terrain lors de vos déplacements rapides, soutenez mon travail sur [Ko-fi](https://ko-fi.com/dasikigaijin) !

---

## 🔙 Retour
- [[Back to Version Selector Portal|fr_fr-Home]]
- [[View Compatibility Matrix|fr_fr-Version-Compatibility]]
- [[Developer Setup & Building|fr_fr-Developer-Setup-and-Building]]
