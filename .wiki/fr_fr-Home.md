# 🚀 Tu cours vite, génère ça d'abord (You Run Fast, Render That First)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 📖 Priorisation anisotrope du rendu et génération prédictive de chunks selon la vélocité

You Run Fast, Render That First est un mod d'optimisation pour Fabric conçu pour éliminer l'apparition soudaine de chunks à grande vitesse.

---

## ⚡ Tu cours vite, génère ça d'abord (You Run Fast, Render That First) — 핵심 기능 / 主要特性

- **Priorisation anisotrope du maillage**: Remplace le tri radial par un produit scalaire directionnel.
- **Génération prédictive côté serveur**: Allocation de tickets PLAYER_LOADING jusqu'à 16 chunks vers l'avant.
- **GameRules dynamiques et commandes**: Contrôle via yourunfast:* et la suite /yourunfast.
- **Zéro allocation d'objets (Zero GC)**: Structure ultra-optimisée sur types primitifs.

---

## 🏛️ Portail des versions Minecraft

| Minecraft Version | Documentation Link | Client Queue Mixin | Minimum Java |
| :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | [[👉 Enter MC 26.3 Wiki|26.3-Home]] | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.2** | [[👉 Enter MC 26.2 Wiki|26.2-Home]] | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.1** | [[👉 Enter MC 26.1 Wiki|26.1-Home]] | `CompileTaskDynamicQueue` | Java 25+ |

---

## 🔗 Vue d'ensemble
- [[Vue d'ensemble|fr_fr-Overview]]
- [[English Documentation Portal|Home]]
