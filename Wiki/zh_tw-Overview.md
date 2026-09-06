# 📚 你跑得快，先渲染那兒 (You Run Fast, Render That First) — 技術概覽

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## ⚡ 1. 各向異性區塊網格化優先級
採用方向點積距離計算取代原版徑向排序，前進方向的區塊優先獲得編譯。

### 数学公式 / Formulas:
$$\vec{v}_{\text{smooth}} = \alpha \cdot \vec{v}_{\text{eff}} + (1.0 - \alpha) \cdot \vec{v}_{\text{smooth}}, \quad \alpha = 0.65$$
$$\text{BiasedDistSqr} = \max\left(0.0,\, \text{DistSqr} - 2.0 \cdot (\vec{d}_{\text{norm}} \cdot \Delta\vec{P}) \cdot \text{LeadOffset}\right)$$

---

## 🌐 2. 伺服端預測性區塊生成錐
沿運動方向預分配 PLAYER_LOADING 票據（最遠達 16 區塊），並配合 MSPT 監控保護 20 TPS。

### Continuous MSPT Watchdog:
$$\text{msptFactor} = \begin{cases} 1.0 & \text{if } \text{mspt} \le 25.0 \\ \max\left(0.25,\, 1.0 - \frac{\text{mspt} - 25.0}{25.0}\right) & \text{if } \text{mspt} > 25.0 \end{cases}$$

---

## ⌨️ 3. 動態遊戲規則與指令系統
提供完整的 yourunfast:* 遊戲規則與 /yourunfast 指令體系。

### 指令列表 / Commands:
- `/yourunfast help`
- `/yourunfast status`
- `/yourunfast get <rule>`
- `/yourunfast set <rule> <value>`
- `/yourunfast reset`
- `/yourunfast reload`

---

## 🏛️ 4. 零 GC 分配超高效能
工作線程完全使用 volatile 原始型別快取，保證高效能零開銷。

---

## 🔙 Navigation
- [[主頁|zh_tw-Home]]
- [[English Main Portal|Home]]
