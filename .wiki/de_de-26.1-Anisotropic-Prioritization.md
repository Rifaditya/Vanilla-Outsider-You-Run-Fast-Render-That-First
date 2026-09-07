# 🖥️ Clientseitige anisotrope Chunk-Meshing-Priorisierung (MC 26.1)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Quellcode-Hinweis**: Die Dokumentation in diesem Wiki gibt den **aktuellen Stand des Quellcodes im Repository** wieder, einschließlich unfertiger Commits und Entwicklungsfunktionen vor der offiziellen Veröffentlichung auf CurseForge und Modrinth.

---

## 1. Offizielle Infobox

| Parameter | Technical Details |
| :--- | :--- |
| **Subsystem Name** | Anisotropic Chunk Mesh Prioritization |
| **Minecraft Anchor** | MC 26.1 |
| **Java Implementation** | `net.vanillaoutsider.yourunfast.math.AnisotropicDistanceHelper` |
| **Client Polling Mixin** | `net.vanillaoutsider.yourunfast.client.mixin.CompileTaskDynamicQueueMixin` |
| **Vanilla Target Class** | `net.minecraft.client.renderer.chunk.CompileTaskDynamicQueue` |
| **Target Method** | `poll()` |
| **Redirect Call** | `Lnet/minecraft/core/BlockPos;distToCenterSqr(Lnet/minecraft/core/Position;)D` |
| **Controlling GameRules** | `yourunfast:enabled`, `yourunfast:forward_lead_multiplier`, `yourunfast:min_speed_threshold_pct` |
| **Default Activation Speed** | $0.20\text{ blocks/tick} = 4.0\text{ m/s}$ |
| **Maximum Lead Offset** | $256.0\text{ blocks}$ (16 Chunks ahead) |

---

## 2. Ablauf und Ausführungslogik

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
               [ CompileTaskDynamicQueue.poll() ]
                               |
                               v
            [ calculateBiasedDistanceSqr() ]
                               |
               Forward terrain scored lower!
             Compiled and rendered FIRST on screen!
```

---

## 3. Mathematische Formeln & anisotroper Algorithmus

### EMA-Geschwindigkeitsglättung:
$$\vec{v}_{\text{smooth}} = \alpha \cdot \vec{v}_{\text{eff}} + (1.0 - \alpha) \cdot \vec{v}_{\text{smooth}}, \quad \alpha = 0.65$$

### Formel für verzerrte Abstandsquadrate:
$$\text{BiasedDistSqr} = \max\left(0.0,\, \text{DistSqr} - 2.0 \cdot (\vec{d}_{\text{norm}} \cdot \Delta\vec{P}) \cdot \text{LeadOffset}\right)$$

- Vorausliegende Chunks erhalten minimale Abstände und werden sofort berechnet.
- Hinter dem Spieler liegende Chunks behalten normale Werte.

---

> ☕ *Solo-Entwickler-Notiz*: Wenn dir die flüssige Chunk-Generierung bei hohen Geschwindigkeiten ohne Pop-Ins gefällt, unterstütze meine Arbeit auf [Ko-fi](https://ko-fi.com/dasikigaijin)!

---

## 🔗 Related Pages (MC 26.1)

- [[Chunk Generation Biasing|de_de-26.1-Chunk-Generation-Biasing]]
- [[Configuration & GameRules|de_de-26.1-Configuration-and-GameRules]]
- [[Architecture & Mixins|de_de-26.1-Architecture-and-Mixins]]
- Return to [[26.1 Overview Portal|de_de-26.1-Home]]
