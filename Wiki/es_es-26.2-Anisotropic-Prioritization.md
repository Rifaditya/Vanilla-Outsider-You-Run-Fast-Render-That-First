# 🖥️ Priorización anisotrópica del mallado de chunks en cliente (MC 26.2)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Aviso sobre el código fuente del repositorio**: La documentación de esta Wiki refleja el **estado actual del código fuente en el repositorio**, que puede incluir confirmaciones recientes o características en desarrollo no disponibles aún en CurseForge o Modrinth.

---

## 1. Ficha técnica oficial

| Parameter | Technical Details |
| :--- | :--- |
| **Subsystem Name** | Anisotropic Chunk Mesh Prioritization |
| **Minecraft Anchor** | MC 26.2 |
| **Java Implementation** | `net.vanillaoutsider.yourunfast.math.AnisotropicDistanceHelper` |
| **Client Polling Mixin** | `net.vanillaoutsider.yourunfast.client.mixin.SectionTaskDynamicQueueMixin` |
| **Vanilla Target Class** | `net.minecraft.client.renderer.chunk.SectionTaskDynamicQueue` |
| **Target Method** | `poll()` |
| **Redirect Call** | `Lnet/minecraft/core/BlockPos;distToCenterSqr(Lnet/minecraft/core/Position;)D` |
| **Controlling GameRules** | `yourunfast:enabled`, `yourunfast:forward_lead_multiplier`, `yourunfast:min_speed_threshold_pct` |
| **Default Activation Speed** | $0.20\text{ blocks/tick} = 4.0\text{ m/s}$ |
| **Maximum Lead Offset** | $256.0\text{ blocks}$ (16 Chunks ahead) |

---

## 2. Flujo de trabajo y lógica de ejecución

```
               +----------------------------------------+
               | Player Traversal (Walking / Running /  |
               | Riding Horse / Elytra Flight)          |
               +----------------------------------------+
                                   |
                                   v
             [ ClientTickEvents.END_CLIENT_TICK ]
                                   |
                                   v
             [ ClientVelocityTracker.clientTick() ]
           /                                        \
  (Speed < 0.20 b/t)                       (Speed >= 0.20 b/t)
          |                                          |
          v                                          v
+-----------------------+                 +-----------------------------+
| activeBias = false    |                 | activeBias = true           |
| cachedLeadOffset = 0  |                 | Compute normalized dx,dy,dz |
| Vanilla Radial Sphere |                 | cachedLeadOffset = v * 16   |
+-----------------------+                 +-----------------------------+
          |                                          |
          +--------------------+---------------------+
                               |
                               v
               [ SectionTaskDynamicQueue.poll() ]
                               |
                               v
            [ calculateBiasedDistanceSqr() ]
                               |
               Forward terrain scored lower!
             Compiled and rendered FIRST on screen!
```

---

## 3. Fórmulas matemáticas y algoritmo de sesgo anisotrópico

### Suavizado de velocidad EMA:
$$\vec{v}_{\text{smooth}} = \alpha \cdot \vec{v}_{\text{eff}} + (1.0 - \alpha) \cdot \vec{v}_{\text{smooth}}, \quad \alpha = 0.65$$

### Cálculo de distancia al cuadrado con sesgo:
$$\text{BiasedDistSqr} = \max\left(0.0,\, \text{DistSqr} - 2.0 \cdot (\vec{d}_{\text{norm}} \cdot \Delta\vec{P}) \cdot \text{LeadOffset}\right)$$

- Los chunks enfrente reciben reducción de distancia y prioridad de render.
- Los chunks traseros conservan su distancia euclidiana natural.

---

> ☕ *Nota del desarrollador independiente*: Si disfrutas de la fluidez del terreno generándose instantáneamente al volar o galopar, ¡apoya mi desarrollo en [Ko-fi](https://ko-fi.com/dasikigaijin)!

---

## 🔗 Related Pages (MC 26.2)

- [[Chunk Generation Biasing|es_es-26.2-Chunk-Generation-Biasing]]
- [[Configuration & GameRules|es_es-26.2-Configuration-and-GameRules]]
- [[Architecture & Mixins|es_es-26.2-Architecture-and-Mixins]]
- Return to [[26.2 Overview Portal|es_es-26.2-Home]]
