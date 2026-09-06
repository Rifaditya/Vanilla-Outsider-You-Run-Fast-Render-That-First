# 🚀 Du rennst schnell, rendere das zuerst (You Run Fast, Render That First)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 📖 Geschwindigkeitsvektor-basierte anisotrope Chunk-Rendering- und Vorhersageoptimierung

You Run Fast, Render That First ist eine Hochleistungs-Fabric-Optimierungsmodifikation, die Chunk-Pop-in bei hohen Geschwindigkeiten vollständig eliminiert.

---

## ⚡ Du rennst schnell, rendere das zuerst (You Run Fast, Render That First) — 핵심 기능 / 主要特性

- **Anisotrope Chunk-Priorisierung**: Ersetzt euklidische Sortierung durch direktionales Skalarprodukt.
- **Server-Chunk-Vorhersage**: Weist vorauseilende PLAYER_LOADING-Tickets bis zu 16 Chunks im Voraus zu.
- **Dynamische GameRules & Befehle**: Umfassende Steuerung über yourunfast:* und /yourunfast.
- **Zero-Allocation GC Architektur**: Lock-free Cache mit primitiven Datentypen.

---

## 🏛️ Minecraft-Versionsauswahl

| Minecraft Version | Documentation Link | Client Queue Mixin | Minimum Java |
| :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | [[👉 Enter MC 26.3 Wiki|26.3-Home]] | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.2** | [[👉 Enter MC 26.2 Wiki|26.2-Home]] | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.1** | [[👉 Enter MC 26.1 Wiki|26.1-Home]] | `CompileTaskDynamicQueue` | Java 25+ |

---

## 🔗 Technische Übersicht
- [[Technische Übersicht|de_de-Overview]]
- [[English Documentation Portal|Home]]
