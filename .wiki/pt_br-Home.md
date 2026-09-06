# 🚀 Você corre rápido, renderize isso primeiro (You Run Fast, Render That First)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 📖 Priorização anisotrópica de malha de chunks e geração preditiva baseada em velocidade

You Run Fast, Render That First é um mod de alta performance para Fabric que elimina o pop-in de chunks e paredes de vazio durante viagens rápidas.

---

## ⚡ Você corre rápido, renderize isso primeiro (You Run Fast, Render That First) — 핵심 기능 / 主要特性

- **Priorização anisotrópica de renderização**: Substitui a ordenação radial pelo produto escalar direcional.
- **Geração preditiva no servidor**: Alocação de tickets PLAYER_LOADING até 16 chunks à frente.
- **GameRules dinâmicas e comandos**: Controle completo através de yourunfast:* e /yourunfast.
- **Zero alocação de memória GC**: Execução ultrarrápida lock-free sem custos de Garbage Collection.

---

## 🏛️ Seletor de versão do Minecraft

| Minecraft Version | Documentation Link | Client Queue Mixin | Minimum Java |
| :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | [[👉 Enter MC 26.3 Wiki|26.3-Home]] | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.2** | [[👉 Enter MC 26.2 Wiki|26.2-Home]] | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.1** | [[👉 Enter MC 26.1 Wiki|26.1-Home]] | `CompileTaskDynamicQueue` | Java 25+ |

---

## 🔗 Visão Geral
- [[Visão Geral|pt_br-Overview]]
- [[English Documentation Portal|Home]]
