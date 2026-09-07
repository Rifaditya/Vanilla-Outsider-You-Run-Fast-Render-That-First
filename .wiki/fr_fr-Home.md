# 🚀 You Run Fast, Render That First — Wiki Officiel

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Clause de non-responsabilité relative au code source** : La documentation de ce Wiki reflète **l'état actuel du code source dans le dépôt**, incluant d'éventuels commits récents non publiés sur CurseForge ou Modrinth.

---

## 🧭 Bienvenue sur la documentation officielle

**Vanilla Outsider: You Run Fast, Render That First** est un mod d'optimisation Fabric haute performance conçu pour éliminer l'apparition brutale de chunks (pop-in) et les murs de vide à haute vitesse. En synchronisant les files de maillage client et les tickets de génération serveur avec le vecteur vitesse en temps réel ($ec{v}$) du joueur, le terrain vers lequel vous foncez est compilé et chargé en premier.

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

## 🏛️ Sélectionnez votre version de Minecraft

| Minecraft Version | Version Tree Link | Engine Lifecycle | Client Queue Mixin | Minimum Java |
| :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | [[👉 Enter MC 26.3 Wiki|fr_fr-26.3-Home]] | **Modern Lead** | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.2** | [[👉 Enter MC 26.2 Wiki|fr_fr-26.2-Home]] | **Modern Predecessor** | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.1** | [[👉 Enter MC 26.1 Wiki|fr_fr-26.1-Home]] | **Modern Anchor (26.1.2)** | `CompileTaskDynamicQueue` | Java 25+ |

---

## ⚡ Piliers d'ingénierie clés

- **[[Anisotropic Chunk Prioritization|fr_fr-26.3-Anisotropic-Prioritization]]**
- **[[Server Chunk Generation Biasing & Watchdog|fr_fr-26.3-Chunk-Generation-Biasing]]**
- **[[Dynamic GameRules & Brigadier Commands|fr_fr-26.3-Configuration-and-GameRules]]**
- **[[Zero-GC High-Frequency Hot Path|fr_fr-26.3-Architecture-and-Mixins]]**

---

> ☕ *Note du développeur solo*: Si vous appréciez la fluidité du rendu de terrain lors de vos déplacements rapides, soutenez mon travail sur [Ko-fi](https://ko-fi.com/dasikigaijin) !

---

## 🛠️ Guides développeurs

- [[Version Compatibility Matrix|fr_fr-Version-Compatibility]]
- [[Developer Setup & Building|fr_fr-Developer-Setup-and-Building]]
