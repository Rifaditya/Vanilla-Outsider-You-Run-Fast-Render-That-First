# 🏛️ 技术架构与 Mixin 引擎 (MC 26.3)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **代码仓库源码免责声明**：本维基文档反映了**仓库当前的源代码状态**，可能包含领先于 CurseForge 与 Modrinth 上公开发布版本的最新开发提交或未发布功能。

---

## 1. 官方技术信息框

| Parameter | Technical Details |
| :--- | :--- |
| **Subsystem Name** | Technical Architecture & Mixin Engine |
| **Minecraft Anchor** | MC 26.3 |
| **Mod ID** | `you-run-fast-render-that-first` |
| **Root Package** | `net.vanillaoutsider.yourunfast` |
| **Client Mixin Class** | `net.vanillaoutsider.yourunfast.client.mixin.SectionTaskDynamicQueueMixin` |
| **Target Minecraft Class** | `net.minecraft.client.renderer.chunk.SectionTaskDynamicQueue` |
| **Mixin Configuration** | `you-run-fast-render-that-first.mixins.json` |
| **Compatibility Level** | `JAVA_25` |
| **Dependencies** | Fabric Loader `>=0.18.4`, Fabric API `*`, DasikLibrary `>=1.8.0` |

---

## 2. 执行流程与架构流水线

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
                  [ SectionTaskDynamicQueue.poll() ]
                                       |
                                       v
               [ AnisotropicDistanceHelper.calculateBiasedDistSqr() ]
```

---

## 3. 核心 Mixin 注入代码剖析

```java
@Mixin(SectionTaskDynamicQueue.class)
public class SectionTaskDynamicQueueMixin {
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

> ☕ *独立开发者寄语*：如果你喜欢高速飞行和骑马时前方地形即时渲染的流畅体验，欢迎在 [Ko-fi](https://ko-fi.com/dasikigaijin) 上支持我的独立开发！

---

## 🔗 Related Pages (MC 26.3)

- [[Anisotropic Prioritization|zh_cn-26.3-Anisotropic-Prioritization]]
- [[Chunk Generation Biasing|zh_cn-26.3-Chunk-Generation-Biasing]]
- [[Configuration & GameRules|zh_cn-26.3-Configuration-and-GameRules]]
- Return to [[26.3 Overview Portal|zh_cn-26.3-Home]]
