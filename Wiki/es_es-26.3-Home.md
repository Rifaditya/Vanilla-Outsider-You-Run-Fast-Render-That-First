# 🚀 Minecraft 26.3 — You Run Fast, Render That First

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Aviso sobre el código fuente del repositorio**: La documentación de esta Wiki refleja el **estado actual del código fuente en el repositorio**, que puede incluir confirmaciones recientes o características en desarrollo no disponibles aún en CurseForge o Modrinth.

---

## 📖 Descripción del subproyecto: Ancla MC 26.3

Bienvenido a la documentación técnica de **Vanilla Outsider: You Run Fast, Render That First** para **Minecraft 26.3**.

Compilado para Minecraft `26.3`, Fabric Loader `>=0.18.4` y Java 25+. Optimiza la cola `SectionTaskDynamicQueue` y sincroniza GameRules con DasikLibrary `>=1.8.0`.

---

## 📚 Matriz de navegación de subsistemas

| Dominio de funciones | Guía Wiki | Mecánicas principales |
| :--- | :--- | :--- |
| **Client Meshing** | [[Anisotropic Prioritization|es_es-26.3-Anisotropic-Prioritization]] | Directional dot-product distance bias, EMA smoothing, zero-allocation render polling |
| **Server Worldgen** | [[Chunk Generation Biasing|es_es-26.3-Chunk-Generation-Biasing]] | Predictive forward tickets (`TicketType.PLAYER_LOADING`), MSPT watchdog, lateral trimming |
| **Configuration** | [[Configuration & GameRules|es_es-26.3-Configuration-and-GameRules]] | Dynamic namespaced GameRules, `/yourunfast` command suite (`help`, `status`, `set`) |
| **Architecture** | [[Architecture & Mixins|es_es-26.3-Architecture-and-Mixins]] | Mixin inspection (`SectionTaskDynamicQueueMixin`), FastUtil data structures, zero-GC fast path |

---

## ⚡ Especificaciones técnicas

- **Target Game Version**: Minecraft `26.3`
- **Client Render Queue**: `SectionTaskDynamicQueue`
- **Server Ticket Type**: `TicketType.PLAYER_LOADING` with radius 1
- **Dynamic GameRules Category**: `yourunfast:main`
- **Root Brigadier Command**: `/yourunfast`
- **Mathematical Smoothing**: Exponential Moving Average with $\alpha = 0.65$

---

> ☕ *Nota del desarrollador independiente*: Si disfrutas de la fluidez del terreno generándose instantáneamente al volar o galopar, ¡apoya mi desarrollo en [Ko-fi](https://ko-fi.com/dasikigaijin)!

---

## 🔙 Navegación de retorno
- [[Back to Version Selector Portal|es_es-Home]]
- [[View Compatibility Matrix|es_es-Version-Compatibility]]
- [[Developer Setup & Building|es_es-Developer-Setup-and-Building]]
