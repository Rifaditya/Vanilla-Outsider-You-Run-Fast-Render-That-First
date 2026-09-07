# 🖥️ 客戶端各向異性區塊網格化優先級 (MC 26.2)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **代碼倉庫原始碼免責聲明**：本維基文件反映了**倉庫當前的原始碼狀態**，可能包含領先於 CurseForge 與 Modrinth 上公開發布版本的最新開發提交或未發布功能。

---

## 1. 官方技術資訊框

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

## 2. 玩家流程與執行邏輯

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

## 3. 數學公式與各向異性偏差演算法

### 指數移動平均 (EMA) 速度平滑：
$$\vec{v}_{\text{smooth}} = \alpha \cdot \vec{v}_{\text{eff}} + (1.0 - \alpha) \cdot \vec{v}_{\text{smooth}}, \quad \alpha = 0.65$$

### 有偏距離平方計算公式：
$$\text{BiasedDistSqr} = \max\left(0.0,\, \text{DistSqr} - 2.0 \cdot (\vec{d}_{\text{norm}} \cdot \Delta\vec{P}) \cdot \text{LeadOffset}\right)$$

- 當區塊恰好位於玩家正前方時，$\vec{d}_{\text{norm}} \cdot \Delta\vec{P} \approx 1.0$，距離分值大幅縮減，優先網格化。
- 當區塊位於玩家後方時，點積為負，保留原版歐幾里得距離，避免飢餓。

---

> ☕ *獨立開發者寄語*：如果你喜歡高速飛行和騎馬時前方地形即時渲染的流暢體驗，歡迎在 [Ko-fi](https://ko-fi.com/dasikigaijin) 上支持我的獨立開發！

---

## 🔗 Related Pages (MC 26.2)

- [[Chunk Generation Biasing|zh_tw-26.2-Chunk-Generation-Biasing]]
- [[Configuration & GameRules|zh_tw-26.2-Configuration-and-GameRules]]
- [[Architecture & Mixins|zh_tw-26.2-Architecture-and-Mixins]]
- Return to [[26.2 Overview Portal|zh_tw-26.2-Home]]
