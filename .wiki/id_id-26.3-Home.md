# 🚀 Minecraft 26.3 — You Run Fast, Render That First

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Pernyataan Sumber Repositori**: Dokumentasi di Wiki ini mencerminkan **kondisi kode sumber terkini di repositori**, yang mungkin memuat komit atau fitur pengembangan yang belum dirilis di CurseForge atau Modrinth.

---

## 📖 Ringkasan Subproyek: Jangkar MC 26.3

Selamat datang di dokumentasi teknis **Vanilla Outsider: You Run Fast, Render That First** untuk **Minecraft 26.3**.

Dikompilasi untuk Minecraft `26.3`, Fabric Loader `>=0.18.4`, dan Java 25+. Mengoptimalkan `SectionTaskDynamicQueue` dan sinkronisasi aturan via DasikLibrary `>=1.8.0`.

---

## 📚 Matriks Navigasi Subsistem

| Domain Fitur | Tautan Panduan Wiki | Mekanisme & Tanggung Jawab Inti |
| :--- | :--- | :--- |
| **Client Meshing** | [[Anisotropic Prioritization|id_id-26.3-Anisotropic-Prioritization]] | Directional dot-product distance bias, EMA smoothing, zero-allocation render polling |
| **Server Worldgen** | [[Chunk Generation Biasing|id_id-26.3-Chunk-Generation-Biasing]] | Predictive forward tickets (`TicketType.PLAYER_LOADING`), MSPT watchdog, lateral trimming |
| **Configuration** | [[Configuration & GameRules|id_id-26.3-Configuration-and-GameRules]] | Dynamic namespaced GameRules, `/yourunfast` command suite (`help`, `status`, `set`) |
| **Architecture** | [[Architecture & Mixins|id_id-26.3-Architecture-and-Mixins]] | Mixin inspection (`SectionTaskDynamicQueueMixin`), FastUtil data structures, zero-GC fast path |

---

## ⚡ Spesifikasi Teknis Cepat

- **Target Game Version**: Minecraft `26.3`
- **Client Render Queue**: `SectionTaskDynamicQueue`
- **Server Ticket Type**: `TicketType.PLAYER_LOADING` with radius 1
- **Dynamic GameRules Category**: `yourunfast:main`
- **Root Brigadier Command**: `/yourunfast`
- **Mathematical Smoothing**: Exponential Moving Average with $\alpha = 0.65$

---

> ☕ *Catatan Pengembang Solo*: Jika Anda menyukai kelancaran render chunk instan saat terbang atau menunggang kuda berkecepatan tinggi, dukung pengembangan mandiri saya di [Ko-fi](https://ko-fi.com/dasikigaijin)!

---

## 🔙 Navigasi Kembali
- [[Back to Version Selector Portal|id_id-Home]]
- [[View Compatibility Matrix|id_id-Version-Compatibility]]
- [[Developer Setup & Building|id_id-Developer-Setup-and-Building]]
