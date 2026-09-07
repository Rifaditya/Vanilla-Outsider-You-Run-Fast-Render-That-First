# 🏛️ 技術アーキテクチャとMixinエンジン (MC 26.1)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **リポジトリソースコードに関する免責事項**: 本Wikiドキュメントは**リポジトリの最新ソースコード状態**を反映しており、CurseForgeやModrinthで公開されているリリース版に含まれない開発中機能を含む場合があります。

---

## 1. 公式技術インフォボックス

| Parameter | Technical Details |
| :--- | :--- |
| **Subsystem Name** | Technical Architecture & Mixin Engine |
| **Minecraft Anchor** | MC 26.1 |
| **Mod ID** | `you-run-fast-render-that-first` |
| **Root Package** | `net.vanillaoutsider.yourunfast` |
| **Client Mixin Class** | `net.vanillaoutsider.yourunfast.client.mixin.CompileTaskDynamicQueueMixin` |
| **Target Minecraft Class** | `net.minecraft.client.renderer.chunk.CompileTaskDynamicQueue` |
| **Mixin Configuration** | `you-run-fast-render-that-first.mixins.json` |
| **Compatibility Level** | `JAVA_25` |
| **Dependencies** | Fabric Loader `>=0.18.4`, Fabric API `*`, DasikLibrary `>=1.8.0` |

---

## 2. 実行パイプラインとアーキテクチャ

```
+=============================================================================+
|                          MOD INITIALIZATION PIPELINE                        |
+=============================================================================+
                                       |
                   [ YouRunFastMod.onInitialize() ]
                                       |
        +------------------------------+------------------------------+
        |                              |                              |
        v                              v                              v
[ ModVersionGuard ]          [ YouRunFastGameRules ]       [ YouRunFastCommand ]
(Verify GameRules class)     (Register via DasikLib)       (Register Brigadier)
        |                              |                              |
        +------------------------------+------------------------------+
                                       |
        +------------------------------+------------------------------+
        |                                                             |
        v                                                             v
[ ServerTickEvents.END_SERVER_TICK ]         [ ServerPlayConnectionEvents.DISCONNECT ]
(ForwardTicketManager.tickPlayer)            (ForwardTicketManager.onPlayerDisconnect)

+=============================================================================+
|                          CLIENT RENDERING PIPELINE                          |
+=============================================================================+

                  [ ClientTickEvents.END_CLIENT_TICK ]
                                       |
                                       v
                     [ ClientVelocityTracker.clientTick() ]
                                       |
                                       v
               Updates Volatile Cache: activeBias, cachedNorm, lead
                                       |
                                       v
                  [ CompileTaskDynamicQueue.poll() ]
                                       |
                                       v
               [ AnisotropicDistanceHelper.calculateBiasedDistSqr() ]
```

---

## 3. コアMixinインジェクション詳細

```java
@Mixin(CompileTaskDynamicQueue.class)
public class CompileTaskDynamicQueueMixin {
    @Redirect(
        method = "poll",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/core/BlockPos;distToCenterSqr(Lnet/minecraft/core/Position;)D"
        )
    )
    private double yourunfast$applyAnisotropicDistance(BlockPos pos, Position playerPos) {
        return AnisotropicDistanceHelper.calculateBiasedDistSqr(
            pos.getX(), pos.getY(), pos.getZ(),
            playerPos.x(), playerPos.y(), playerPos.z()
        );
    }
}
```

---

> ☕ *個人開発者ノート*: エリトラ滑空や騎乗移動時のスムーズな前方チャンク即時描画を気に入っていただけたら、ぜひ [Ko-fi](https://ko-fi.com/dasikigaijin) でのご支援をお願いします！

---

## 🔗 Related Pages (MC 26.1)

- [[Anisotropic Prioritization|ja_jp-26.1-Anisotropic-Prioritization]]
- [[Chunk Generation Biasing|ja_jp-26.1-Chunk-Generation-Biasing]]
- [[Configuration & GameRules|ja_jp-26.1-Configuration-and-GameRules]]
- Return to [[26.1 Overview Portal|ja_jp-26.1-Home]]
