# 🖥️ Priorisation anisotrope du maillage de chunks côté client (MC 26.3)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Clause de non-responsabilité relative au code source** : La documentation de ce Wiki reflète **l'état actuel du code source dans le dépôt**, incluant d'éventuels commits récents non publiés sur CurseForge ou Modrinth.

---

## 1. Tableau technique officiel

| Parameter | Technical Details |
| :--- | :--- |
| **Subsystem Name** | Anisotropic Chunk Mesh Prioritization |
| **Minecraft Anchor** | MC 26.3 |
| **Java Implementation** | `net.vanillaoutsider.yourunfast.math.AnisotropicDistanceHelper` |
| **Client Polling Mixin** | `net.vanillaoutsider.yourunfast.client.mixin.SectionTaskDynamicQueueMixin` |
| **Vanilla Target Class** | `net.minecraft.client.renderer.chunk.SectionTaskDynamicQueue` |
| **Target Method** | `poll()` |
| **Redirect Call** | `Lnet/minecraft/core/BlockPos;distToCenterSqr(Lnet/minecraft/core/Position;)D` |
| **Controlling GameRules** | `yourunfast:enabled`, `yourunfast:forward_lead_multiplier`, `yourunfast:min_speed_threshold_pct` |
| **Default Activation Speed** | $0.20\text{ blocks/tick} = 4.0\text{ m/s}$ |
| **Maximum Lead Offset** | $256.0\text{ blocks}$ (16 Chunks ahead) |

---

## 2. Déroulement et logique d'exécution

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

## 3. Formules mathématiques et algorithme anisotrope

### Lissage de vitesse EMA :
$$\vec{v}_{\text{smooth}} = \alpha \cdot \vec{v}_{\text{eff}} + (1.0 - \alpha) \cdot \vec{v}_{\text{smooth}}, \quad \alpha = 0.65$$

### Distance au carré pondérée :
$$\text{BiasedDistSqr} = \max\left(0.0,\, \text{DistSqr} - 2.0 \cdot (\vec{d}_{\text{norm}} \cdot \Delta\vec{P}) \cdot \text{LeadOffset}\right)$$

- Les chunks situés dans la trajectoire sont compilés en premier.

---

> ☕ *Note du développeur solo*: Si vous appréciez la fluidité du rendu de terrain lors de vos déplacements rapides, soutenez mon travail sur [Ko-fi](https://ko-fi.com/dasikigaijin) !

---

## 🔗 Related Pages (MC 26.3)

- [[Chunk Generation Biasing|fr_fr-26.3-Chunk-Generation-Biasing]]
- [[Configuration & GameRules|fr_fr-26.3-Configuration-and-GameRules]]
- [[Architecture & Mixins|fr_fr-26.3-Architecture-and-Mixins]]
- Return to [[26.3 Overview Portal|fr_fr-26.3-Home]]
