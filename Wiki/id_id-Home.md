# 🚀 You Run Fast, Render That First — Wiki Resmi

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Pernyataan Sumber Repositori**: Dokumentasi di Wiki ini mencerminkan **kondisi kode sumber terkini di repositori**, yang mungkin memuat komit atau fitur pengembangan yang belum dirilis di CurseForge atau Modrinth.

---

## 🧭 Selamat Datang di Dokumentasi Resmi

**Vanilla Outsider: You Run Fast, Render That First** adalah mod optimasi Fabric performa ultra-tinggi yang dirancang untuk mengeliminasi pop-in chunk dan dinding hampa saat bergerak dalam kecepatan tinggi. Dengan menyinkronkan antrean meshing render client dan tiket pembangkitan dunia server langsung dengan vektor kecepatan instan pemain ($ec{v}$), medan yang Anda tuju saat terbang atau berkuda dikompilasi dan dimuat terlebih dahulu.

```
                  =============================================
                  VELOCITY-BIASED ANISOTROPIC CHUNK PIPELINE
                  =============================================

                                  [ PLAYER ]
                                      |
                     Velocity Vector  |  s >= 0.20 b/t
                                      v
                 +-----------------------------------------+
                 |       VelocityCalculator (EMA a=0.65)   |
                 +-----------------------------------------+
                                 /         \
                                /           \
        (Client Render Meshing)/             \(Server Chunk Tickets)
                              v               v
             +---------------------+     +--------------------------+
             | AnisotropicDistance |     | ForwardTicketManager     |
             | Helper (Dot Product)|     | (TicketType.             |
             | Directional Bias)   |     |  PLAYER_LOADING)         |
             +---------------------+     +--------------------------+
                        |                             |
                        v                             v
             Prioritized Chunk Mesh       Predictive Forward Chunks
             (Zero Pop-In Ahead)          (Ahead up to 16 Chunks)
```

---

## 🏛️ Pilih Versi Minecraft Anda

| Minecraft Version | Version Tree Link | Engine Lifecycle | Client Queue Mixin | Minimum Java |
| :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | [[👉 Enter MC 26.3 Wiki|id_id-26.3-Home]] | **Modern Lead** | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.2** | [[👉 Enter MC 26.2 Wiki|id_id-26.2-Home]] | **Modern Predecessor** | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.1** | [[👉 Enter MC 26.1 Wiki|id_id-26.1-Home]] | **Modern Anchor (26.1.2)** | `CompileTaskDynamicQueue` | Java 25+ |

---

## ⚡ Subsistem Rekayasa Utama

- **[[Anisotropic Chunk Prioritization|id_id-26.3-Anisotropic-Prioritization]]**
- **[[Server Chunk Generation Biasing & Watchdog|id_id-26.3-Chunk-Generation-Biasing]]**
- **[[Dynamic GameRules & Brigadier Commands|id_id-26.3-Configuration-and-GameRules]]**
- **[[Zero-GC High-Frequency Hot Path|id_id-26.3-Architecture-and-Mixins]]**

---

> ☕ *Catatan Pengembang Solo*: Jika Anda menyukai kelancaran render chunk instan saat terbang atau menunggang kuda berkecepatan tinggi, dukung pengembangan mandiri saya di [Ko-fi](https://ko-fi.com/dasikigaijin)!

---

## 🛠️ Dokumentasi Pengembang

- [[Version Compatibility Matrix|id_id-Version-Compatibility]]
- [[Developer Setup & Building|id_id-Developer-Setup-and-Building]]
