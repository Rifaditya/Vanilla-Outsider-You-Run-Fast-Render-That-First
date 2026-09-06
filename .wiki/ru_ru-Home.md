# 🚀 Ты бежишь быстро, рендери это первым (You Run Fast, Render That First)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 📖 Анизотропная оптимизация рендеринга и предсказательная генерация чанков по вектору скорости

You Run Fast, Render That First — это высокопроизводительный оптимизационный мод для Fabric, устраняющий эффект внезапного появления чанков и пустоты при высокой скорости передвижения.

---

## ⚡ Ты бежишь быстро, рендери это первым (You Run Fast, Render That First) — 핵심 기능 / 主要特性

- **Анизотропный приоритет рендеринга**: Заменяет евклидову радиальную сортировку направленным скалярным произведением.
- **Серверная генерация чанков**: Упреждающее выделение тикетов PLAYER_LOADING по направлению движения на расстояние до 16 чанков.
- **Динамические GameRules и команды**: Полная поддержка правил yourunfast:* и команд /yourunfast.
- **Нулевое выделение памяти (Zero GC)**: Lock-free алгоритмы на примитивных типах данных.

---

## 🏛️ Выбор версии Minecraft

| Minecraft Version | Documentation Link | Client Queue Mixin | Minimum Java |
| :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | [[👉 Enter MC 26.3 Wiki|26.3-Home]] | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.2** | [[👉 Enter MC 26.2 Wiki|26.2-Home]] | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.1** | [[👉 Enter MC 26.1 Wiki|26.1-Home]] | `CompileTaskDynamicQueue` | Java 25+ |

---

## 🔗 Технический обзор
- [[Технический обзор|ru_ru-Overview]]
- [[English Documentation Portal|Home]]
