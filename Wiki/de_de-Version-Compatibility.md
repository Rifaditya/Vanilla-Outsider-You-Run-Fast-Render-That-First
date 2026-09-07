# 🧭 Versionskompatibilitäts-Matrix & Umgebungsspezifikationen

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Quellcode-Hinweis**: Die Dokumentation in diesem Wiki gibt den **aktuellen Stand des Quellcodes im Repository** wieder, einschließlich unfertiger Commits und Entwicklungsfunktionen vor der offiziellen Veröffentlichung auf CurseForge und Modrinth.

---

## 📊 Multi-Versions-Matrix

**Vanilla Outsider: You Run Fast, Render That First** folgt strikt der **1 Jar 1 Version Richtlinie**. Jede Version wird isoliert gepflegt.

| Target Minecraft | Status | Toolchain | Loom Version | Client Queue Target | Mod Dependency |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **MC 26.3** | **Modern Lead** | Java 25+ | `net.fabricmc.fabric-loom 1.15+` | `SectionTaskDynamicQueue` | `dasik-library >= 1.8.0` |
| **MC 26.2** | **Modern Predecessor** | Java 25+ | `net.fabricmc.fabric-loom 1.15+` | `SectionTaskDynamicQueue` | `dasik-library >= 1.8.0` |
| **MC 26.1 (26.1.2)** | **Modern Anchor** | Java 25+ | `net.fabricmc.fabric-loom 1.15+` | `CompileTaskDynamicQueue` | `dasik-library >= 1.8.0` |

---

## ⚙️ Laufzeit- & Umgebungsvoraussetzungen

| Component | Minimum Bound | Recommended Version | Technical Notes |
| :--- | :--- | :--- | :--- |
| **Fabric Loader** | `>= 0.18.4` | Latest Stable | Java 25 bytecode loading & Mixin 0.8+ |
| **Fabric API** | `*` (Matching MC) | Latest Release | Lifecycle events (`ServerTickEvents`, `ClientTickEvents`, `CommandRegistrationCallback`) |
| **Dasik Library** | `>= 1.8.0` | 1.8.0+ | Namespaced dynamic GameRule management & registry unfreezing |
| **Java Virtual Machine** | `Java 25` | JDK 25 LTS | Modern foreign memory, vector API, high-performance GC |

---

## 🎨 Grafik-Pipelines & Shader-Kompatibilität

---

> ☕ *Solo-Entwickler-Notiz*: Wenn dir die flüssige Chunk-Generierung bei hohen Geschwindigkeiten ohne Pop-Ins gefällt, unterstütze meine Arbeit auf [Ko-fi](https://ko-fi.com/dasikigaijin)!

---

## 🗂️ Version Navigation

- [[👉 Explore MC 26.3 Documentation|de_de-26.3-Home]]
- [[👉 Explore MC 26.2 Documentation|de_de-26.2-Home]]
- [[👉 Explore MC 26.1 Documentation|de_de-26.1-Home]]
