# 🧭 Matrice de compatibilité et spécifications d'environnement

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Clause de non-responsabilité relative au code source** : La documentation de ce Wiki reflète **l'état actuel du code source dans le dépôt**, incluant d'éventuels commits récents non publiés sur CurseForge ou Modrinth.

---

## 📊 Matrice multi-versions de Minecraft

**Vanilla Outsider: You Run Fast, Render That First** applique rigoureusement la **politique 1 Jar 1 Version**. Chaque version dispose de son sous-projet dédié.

| Target Minecraft | Status | Toolchain | Loom Version | Client Queue Target | Mod Dependency |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **MC 26.3** | **Modern Lead** | Java 25+ | `net.fabricmc.fabric-loom 1.15+` | `SectionTaskDynamicQueue` | `dasik-library >= 1.8.0` |
| **MC 26.2** | **Modern Predecessor** | Java 25+ | `net.fabricmc.fabric-loom 1.15+` | `SectionTaskDynamicQueue` | `dasik-library >= 1.8.0` |
| **MC 26.1 (26.1.2)** | **Modern Anchor** | Java 25+ | `net.fabricmc.fabric-loom 1.15+` | `CompileTaskDynamicQueue` | `dasik-library >= 1.8.0` |

---

## ⚙️ Dépendances d'exécution et environnement

| Component | Minimum Bound | Recommended Version | Technical Notes |
| :--- | :--- | :--- | :--- |
| **Fabric Loader** | `>= 0.18.4` | Latest Stable | Java 25 bytecode loading & Mixin 0.8+ |
| **Fabric API** | `*` (Matching MC) | Latest Release | Lifecycle events (`ServerTickEvents`, `ClientTickEvents`, `CommandRegistrationCallback`) |
| **Dasik Library** | `>= 1.8.0` | 1.8.0+ | Namespaced dynamic GameRule management & registry unfreezing |
| **Java Virtual Machine** | `Java 25` | JDK 25 LTS | Modern foreign memory, vector API, high-performance GC |

---

## 🎨 Pipeline graphique et compatibilité shaders

---

> ☕ *Note du développeur solo*: Si vous appréciez la fluidité du rendu de terrain lors de vos déplacements rapides, soutenez mon travail sur [Ko-fi](https://ko-fi.com/dasikigaijin) !

---

## 🗂️ Version Navigation

- [[👉 Explore MC 26.3 Documentation|fr_fr-26.3-Home]]
- [[👉 Explore MC 26.2 Documentation|fr_fr-26.2-Home]]
- [[👉 Explore MC 26.1 Documentation|fr_fr-26.1-Home]]
