# 📚 Tu cours vite, génère ça d'abord (You Run Fast, Render That First) — Vue d'ensemble

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## ⚡ 1. Priorisation anisotrope du maillage
Remplace le tri radial par un produit scalaire directionnel.

### 数学公式 / Formulas:
$$\vec{v}_{\text{smooth}} = \alpha \cdot \vec{v}_{\text{eff}} + (1.0 - \alpha) \cdot \vec{v}_{\text{smooth}}, \quad \alpha = 0.65$$
$$\text{BiasedDistSqr} = \max\left(0.0,\, \text{DistSqr} - 2.0 \cdot (\vec{d}_{\text{norm}} \cdot \Delta\vec{P}) \cdot \text{LeadOffset}\right)$$

---

## 🌐 2. Génération prédictive côté serveur
Allocation de tickets PLAYER_LOADING jusqu'à 16 chunks vers l'avant.

### Continuous MSPT Watchdog:
$$\text{msptFactor} = \begin{cases} 1.0 & \text{if } \text{mspt} \le 25.0 \\ \max\left(0.25,\, 1.0 - \frac{\text{mspt} - 25.0}{25.0}\right) & \text{if } \text{mspt} > 25.0 \end{cases}$$

---

## ⌨️ 3. GameRules dynamiques et commandes
Contrôle via yourunfast:* et la suite /yourunfast.

### 指令列表 / Commands:
- `/yourunfast help`
- `/yourunfast status`
- `/yourunfast get <rule>`
- `/yourunfast set <rule> <value>`
- `/yourunfast reset`
- `/yourunfast reload`

---

## 🏛️ 4. Zéro allocation d'objets (Zero GC)
Structure ultra-optimisée sur types primitifs.

---

## 🔙 Navigation
- [[Accueil|fr_fr-Home]]
- [[English Main Portal|Home]]
