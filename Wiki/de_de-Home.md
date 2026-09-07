# 🚀 You Run Fast, Render That First — Offizielles Wiki

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Quellcode-Hinweis**: Die Dokumentation in diesem Wiki gibt den **aktuellen Stand des Quellcodes im Repository** wieder, einschließlich unfertiger Commits und Entwicklungsfunktionen vor der offiziellen Veröffentlichung auf CurseForge und Modrinth.

---

## 🧭 Willkommen zur offiziellen Dokumentation

**Vanilla Outsider: You Run Fast, Render That First** ist eine extrem performante Fabric-Optimierungsmod, die Pop-Ins und Lade-Löcher bei hohen Reisegeschwindigkeiten eliminiert. Durch die direkte Synchronisation von Client-Render-Meshing und Server-Generierungs-Tickets mit dem Echtzeit-Geschwindigkeitsvektor ($ec{v}$) des Spielers wird Terrain in Flug- oder Reitrichtung priorisiert gerendert.

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

## 🏛️ Minecraft-Version auswählen

| Minecraft Version | Version Tree Link | Engine Lifecycle | Client Queue Mixin | Minimum Java |
| :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | [[👉 Enter MC 26.3 Wiki|de_de-26.3-Home]] | **Modern Lead** | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.2** | [[👉 Enter MC 26.2 Wiki|de_de-26.2-Home]] | **Modern Predecessor** | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.1** | [[👉 Enter MC 26.1 Wiki|de_de-26.1-Home]] | **Modern Anchor (26.1.2)** | `CompileTaskDynamicQueue` | Java 25+ |

---

## ⚡ Zentrale Architektursysteme

- **[[Anisotropic Chunk Prioritization|de_de-26.3-Anisotropic-Prioritization]]**
- **[[Server Chunk Generation Biasing & Watchdog|de_de-26.3-Chunk-Generation-Biasing]]**
- **[[Dynamic GameRules & Brigadier Commands|de_de-26.3-Configuration-and-GameRules]]**
- **[[Zero-GC High-Frequency Hot Path|de_de-26.3-Architecture-and-Mixins]]**

---

> ☕ *Solo-Entwickler-Notiz*: Wenn dir die flüssige Chunk-Generierung bei hohen Geschwindigkeiten ohne Pop-Ins gefällt, unterstütze meine Arbeit auf [Ko-fi](https://ko-fi.com/dasikigaijin)!

---

## 🛠️ Entwickler-Dokumentation

- [[Version Compatibility Matrix|de_de-Version-Compatibility]]
- [[Developer Setup & Building|de_de-Developer-Setup-and-Building]]
