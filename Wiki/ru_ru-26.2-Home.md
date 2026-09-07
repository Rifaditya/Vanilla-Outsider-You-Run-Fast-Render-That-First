# 🚀 Minecraft 26.2 — You Run Fast, Render That First

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Отказ от ответственности относительно исходного кода**: Документация в этой вики отражает **текущее состояние исходного кода в репозитории**, которое может содержать недавние невыпущенные коммиты или разрабатываемые функции, опережающие общедоступные сборки на CurseForge и Modrinth.

---

## 📖 Обзор подпроекта: якорная версия MC 26.2

Добро пожаловать в техническую документацию **Vanilla Outsider: You Run Fast, Render That First** для **Minecraft 26.2**.

Данная версия собрана под Minecraft `26.2`, Fabric Loader `>=0.18.4` и Java 25+. Мод оптимизирует клиентскую очередь `SectionTaskDynamicQueue` и использует DasikLibrary `>=1.8.0` для синхронизации правил.

---

## 📚 Навигационная матрица подсистем

| Домен функционала | Ссылка на руководство | Назначение и механики |
| :--- | :--- | :--- |
| **Client Meshing** | [[Anisotropic Prioritization|ru_ru-26.2-Anisotropic-Prioritization]] | Directional dot-product distance bias, EMA smoothing, zero-allocation render polling |
| **Server Worldgen** | [[Chunk Generation Biasing|ru_ru-26.2-Chunk-Generation-Biasing]] | Predictive forward tickets (`TicketType.PLAYER_LOADING`), MSPT watchdog, lateral trimming |
| **Configuration** | [[Configuration & GameRules|ru_ru-26.2-Configuration-and-GameRules]] | Dynamic namespaced GameRules, `/yourunfast` command suite (`help`, `status`, `set`) |
| **Architecture** | [[Architecture & Mixins|ru_ru-26.2-Architecture-and-Mixins]] | Mixin inspection (`SectionTaskDynamicQueueMixin`), FastUtil data structures, zero-GC fast path |

---

## ⚡ Краткие технические характеристики

- **Target Game Version**: Minecraft `26.2`
- **Client Render Queue**: `SectionTaskDynamicQueue`
- **Server Ticket Type**: `TicketType.PLAYER_LOADING` with radius 1
- **Dynamic GameRules Category**: `yourunfast:main`
- **Root Brigadier Command**: `/yourunfast`
- **Mathematical Smoothing**: Exponential Moving Average with $\alpha = 0.65$

---

> ☕ *Заметка соло-разработчика*: Если вам нравится плавный рендеринг чанков на высокой скорости без пустот и лагов, поддержите разработку на [Ko-fi](https://ko-fi.com/dasikigaijin)!

---

## 🔙 Возврат в меню
- [[Back to Version Selector Portal|ru_ru-Home]]
- [[View Compatibility Matrix|ru_ru-Version-Compatibility]]
- [[Developer Setup & Building|ru_ru-Developer-Setup-and-Building]]
