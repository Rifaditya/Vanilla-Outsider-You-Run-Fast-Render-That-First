# 🚀 You Run Fast, Render That First — Официальная Вики

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Отказ от ответственности относительно исходного кода**: Документация в этой вики отражает **текущее состояние исходного кода в репозитории**, которое может содержать недавние невыпущенные коммиты или разрабатываемые функции, опережающие общедоступные сборки на CurseForge и Modrinth.

---

## 🧭 Добро пожаловать в официальную документацию

**Vanilla Outsider: You Run Fast, Render That First** — это ультрапроизводительный мод оптимизации для Fabric, устраняющий эффект внезапного появления чанков («pop-in») и пустот при высокоскоростном передвижении. Синхронизируя очереди рендеринга клиентских мешей и серверные тикеты генерации напрямую с вектором скорости игрока ($ec{v}$), местность по направлению вашего полета или скачки компилируется и прогружается первой.

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

## 🏛️ Выберите вашу версию Minecraft

| Minecraft Version | Version Tree Link | Engine Lifecycle | Client Queue Mixin | Minimum Java |
| :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | [[👉 Enter MC 26.3 Wiki|ru_ru-26.3-Home]] | **Modern Lead** | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.2** | [[👉 Enter MC 26.2 Wiki|ru_ru-26.2-Home]] | **Modern Predecessor** | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.1** | [[👉 Enter MC 26.1 Wiki|ru_ru-26.1-Home]] | **Modern Anchor (26.1.2)** | `CompileTaskDynamicQueue` | Java 25+ |

---

## ⚡ Ключевые архитектурные подсистемы

- **[[Anisotropic Chunk Prioritization|ru_ru-26.3-Anisotropic-Prioritization]]**
- **[[Server Chunk Generation Biasing & Watchdog|ru_ru-26.3-Chunk-Generation-Biasing]]**
- **[[Dynamic GameRules & Brigadier Commands|ru_ru-26.3-Configuration-and-GameRules]]**
- **[[Zero-GC High-Frequency Hot Path|ru_ru-26.3-Architecture-and-Mixins]]**

---

> ☕ *Заметка соло-разработчика*: Если вам нравится плавный рендеринг чанков на высокой скорости без пустот и лагов, поддержите разработку на [Ko-fi](https://ko-fi.com/dasikigaijin)!

---

## 🛠️ Документация для разработчиков

- [[Version Compatibility Matrix|ru_ru-Version-Compatibility]]
- [[Developer Setup & Building|ru_ru-Developer-Setup-and-Building]]
