# 🏛️ 技術架構與 Mixin 引擎 (MC 26.3)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **代碼倉庫原始碼免責聲明**：本維基文件反映了**倉庫當前的原始碼狀態**，可能包含領先於 CurseForge 與 Modrinth 上公開發布版本的最新開發提交或未發布功能。

---

## 1. 官方技術資訊框

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

## 2. 執行流程與架構流水線

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

## 3. 核心 Mixin 注入代碼剖析

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

> ☕ *獨立開發者寄語*：如果你喜歡高速飛行和騎馬時前方地形即時渲染的流暢體驗，歡迎在 [Ko-fi](https://ko-fi.com/dasikigaijin) 上支持我的獨立開發！

---

## 🔗 Related Pages (MC 26.3)

- [[Anisotropic Prioritization|zh_tw-26.3-Anisotropic-Prioritization]]
- [[Chunk Generation Biasing|zh_tw-26.3-Chunk-Generation-Biasing]]
- [[Configuration & GameRules|zh_tw-26.3-Configuration-and-GameRules]]
- Return to [[26.3 Overview Portal|zh_tw-26.3-Home]]
