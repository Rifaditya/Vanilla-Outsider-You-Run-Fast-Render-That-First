# 📚 Kamu Lari Cepat, Render Itu Dulu (You Run Fast, Render That First) — Ikhtisar Teknis

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## ⚡ 1. Prioritas Meshing Chunk Anisotropik
Menggantikan pengurutan radial vanilla dengan perhitungan dot product terarah.

### 数学公式 / Formulas:
$$\vec{v}_{\text{smooth}} = \alpha \cdot \vec{v}_{\text{eff}} + (1.0 - \alpha) \cdot \vec{v}_{\text{smooth}}, \quad \alpha = 0.65$$
$$\text{BiasedDistSqr} = \max\left(0.0,\, \text{DistSqr} - 2.0 \cdot (\vec{d}_{\text{norm}} \cdot \Delta\vec{P}) \cdot \text{LeadOffset}\right)$$

---

## 🌐 2. Generasi Chunk Prediktif Server
Mengalokasikan tiket PLAYER_LOADING sementara hingga 16 chunk di depan arah perjalanan.

### Continuous MSPT Watchdog:
$$\text{msptFactor} = \begin{cases} 1.0 & \text{if } \text{mspt} \le 25.0 \\ \max\left(0.25,\, 1.0 - \frac{\text{mspt} - 25.0}{25.0}\right) & \text{if } \text{mspt} > 25.0 \end{cases}$$

---

## ⌨️ 3. GameRules Dinamis & Perintah
Dukungan penuh GameRules yourunfast:* dan perintah Brigadier /yourunfast.

### 指令列表 / Commands:
- `/yourunfast help`
- `/yourunfast status`
- `/yourunfast get <rule>`
- `/yourunfast set <rule> <value>`
- `/yourunfast reset`
- `/yourunfast reload`

---

## 🏛️ 4. Nol Alokasi Memori GC
Eksekusi lock-free berbasis tipe data primitif tanpa beban Garbage Collector.

---

## 🔙 Navigation
- [[Beranda|id_id-Home]]
- [[English Main Portal|Home]]
