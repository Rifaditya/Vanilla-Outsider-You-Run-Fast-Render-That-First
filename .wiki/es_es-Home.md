# 🚀 You Run Fast, Render That First — Wiki Oficial

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Aviso sobre el código fuente del repositorio**: La documentación de esta Wiki refleja el **estado actual del código fuente en el repositorio**, que puede incluir confirmaciones recientes o características en desarrollo no disponibles aún en CurseForge o Modrinth.

---

## 🧭 Bienvenido a la Documentación Oficial

**Vanilla Outsider: You Run Fast, Render That First** es un mod de optimización de ultra alto rendimiento para Fabric, diseñado para eliminar la aparición repentina de chunks (pop-in) y muros de vacío a altas velocidades. Al sincronizar las colas de mallado del cliente y los tickets de generación del servidor directamente con el vector de velocidad del jugador ($ec{v}$), el terreno hacia el que vuelas o cabalgas se compila y carga primero.

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

## 🏛️ Selecciona tu versión de Minecraft

| Minecraft Version | Version Tree Link | Engine Lifecycle | Client Queue Mixin | Minimum Java |
| :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | [[👉 Enter MC 26.3 Wiki|es_es-26.3-Home]] | **Modern Lead** | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.2** | [[👉 Enter MC 26.2 Wiki|es_es-26.2-Home]] | **Modern Predecessor** | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.1** | [[👉 Enter MC 26.1 Wiki|es_es-26.1-Home]] | **Modern Anchor (26.1.2)** | `CompileTaskDynamicQueue` | Java 25+ |

---

## ⚡ Subsistemas de ingeniería clave

- **[[Anisotropic Chunk Prioritization|es_es-26.3-Anisotropic-Prioritization]]**
- **[[Server Chunk Generation Biasing & Watchdog|es_es-26.3-Chunk-Generation-Biasing]]**
- **[[Dynamic GameRules & Brigadier Commands|es_es-26.3-Configuration-and-GameRules]]**
- **[[Zero-GC High-Frequency Hot Path|es_es-26.3-Architecture-and-Mixins]]**

---

> ☕ *Nota del desarrollador independiente*: Si disfrutas de la fluidez del terreno generándose instantáneamente al volar o galopar, ¡apoya mi desarrollo en [Ko-fi](https://ko-fi.com/dasikigaijin)!

---

## 🛠️ Documentación de desarrollo

- [[Version Compatibility Matrix|es_es-Version-Compatibility]]
- [[Developer Setup & Building|es_es-Developer-Setup-and-Building]]
