# 🏛️ Arsitektur Teknis & Mesin Mixin (MC 26.1)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Pernyataan Sumber Repositori**: Dokumentasi di Wiki ini mencerminkan **kondisi kode sumber terkini di repositori**, yang mungkin memuat komit atau fitur pengembangan yang belum dirilis di CurseForge atau Modrinth.

---

## 1. Tabel Infobox Teknis Resmi

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

## 2. Alur Eksekusi & Arsitektur Pipeline

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

## 3. Analisis Injeksi Mixin

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

> ☕ *Catatan Pengembang Solo*: Jika Anda menyukai kelancaran render chunk instan saat terbang atau menunggang kuda berkecepatan tinggi, dukung pengembangan mandiri saya di [Ko-fi](https://ko-fi.com/dasikigaijin)!

---

## 🔗 Related Pages (MC 26.1)

- [[Anisotropic Prioritization|id_id-26.1-Anisotropic-Prioritization]]
- [[Chunk Generation Biasing|id_id-26.1-Chunk-Generation-Biasing]]
- [[Configuration & GameRules|id_id-26.1-Configuration-and-GameRules]]
- Return to [[26.1 Overview Portal|id_id-26.1-Home]]
