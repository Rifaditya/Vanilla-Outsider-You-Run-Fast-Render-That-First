# 🖥️ クライアント側異方性チャンクメッシュ優先度制御 (MC 26.1)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **リポジトリソースコードに関する免責事項**: 本Wikiドキュメントは**リポジトリの最新ソースコード状態**を反映しており、CurseForgeやModrinthで公開されているリリース版に含まれない開発中機能を含む場合があります。

---

## 1. 公式技術インフォボックス

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

## 2. プレイヤーワークフローおよび実行ロジック

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

## 3. 数式および異方性バイアスアルゴリズム

### EMA（指数移動平均）速度スムージング:
$$\vec{v}_{\text{smooth}} = \alpha \cdot \vec{v}_{\text{eff}} + (1.0 - \alpha) \cdot \vec{v}_{\text{smooth}}, \quad \alpha = 0.65$$

### バイアス付き距離平方の算出式:
$$\text{BiasedDistSqr} = \max\left(0.0,\, \text{DistSqr} - 2.0 \cdot (\vec{d}_{\text{norm}} \cdot \Delta\vec{P}) \cdot \text{LeadOffset}\right)$$

- 前方のチャンクはスコアが引き下げられ、最優先で描画されます。

---

> ☕ *個人開発者ノート*: エリトラ滑空や騎乗移動時のスムーズな前方チャンク即時描画を気に入っていただけたら、ぜひ [Ko-fi](https://ko-fi.com/dasikigaijin) でのご支援をお願いします！

---

## 🔗 Related Pages (MC 26.1)

- [[Chunk Generation Biasing|ja_jp-26.1-Chunk-Generation-Biasing]]
- [[Configuration & GameRules|ja_jp-26.1-Configuration-and-GameRules]]
- [[Architecture & Mixins|ja_jp-26.1-Architecture-and-Mixins]]
- Return to [[26.1 Overview Portal|ja_jp-26.1-Home]]
