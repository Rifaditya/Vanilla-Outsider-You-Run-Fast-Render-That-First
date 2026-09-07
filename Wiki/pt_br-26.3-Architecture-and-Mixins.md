# 🏛️ Arquitetura Técnica e Motor de Mixins (MC 26.3)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Aviso sobre o Código-Fonte do Repositório**: A documentação desta Wiki reflete o **estado atual do código-fonte no repositório**, podendo incluir commits recentes ou recursos em desenvolvimento não lançados no CurseForge ou Modrinth.

---

## 1. Tabela Técnica Oficial

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

## 2. Pipeline de Execução e Arquitetura

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

## 3. Análise de Injeções Mixin

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

> ☕ *Nota do desenvolvedor independente*: Se você aprecia o carregamento suave e prioritário de chunks em alta velocidade, apoie meu trabalho solo no [Ko-fi](https://ko-fi.com/dasikigaijin)!

---

## 🔗 Related Pages (MC 26.3)

- [[Anisotropic Prioritization|pt_br-26.3-Anisotropic-Prioritization]]
- [[Chunk Generation Biasing|pt_br-26.3-Chunk-Generation-Biasing]]
- [[Configuration & GameRules|pt_br-26.3-Configuration-and-GameRules]]
- Return to [[26.3 Overview Portal|pt_br-26.3-Home]]
