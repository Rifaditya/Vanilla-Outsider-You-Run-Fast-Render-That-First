# 🚀 Minecraft 26.2 — You Run Fast, Render That First

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Aviso sobre o Código-Fonte do Repositório**: A documentação desta Wiki reflete o **estado atual do código-fonte no repositório**, podendo incluir commits recentes ou recursos em desenvolvimento não lançados no CurseForge ou Modrinth.

---

## 📖 Visão Geral do Subprojeto: Âncora MC 26.2

Bem-vindo à documentação técnica de **Vanilla Outsider: You Run Fast, Render That First** no **Minecraft 26.2**.

Compilado para Minecraft `26.2`, Fabric Loader `>=0.18.4` e Java 25+. Otimiza a fila `SectionTaskDynamicQueue` e utiliza DasikLibrary `>=1.8.0`.

---

## 📚 Matriz de Navegação dos Subsistemas

| Domínio de Recurso | Guia Wiki | Responsabilidades Principais |
| :--- | :--- | :--- |
| **Client Meshing** | [[Anisotropic Prioritization|pt_br-26.2-Anisotropic-Prioritization]] | Directional dot-product distance bias, EMA smoothing, zero-allocation render polling |
| **Server Worldgen** | [[Chunk Generation Biasing|pt_br-26.2-Chunk-Generation-Biasing]] | Predictive forward tickets (`TicketType.PLAYER_LOADING`), MSPT watchdog, lateral trimming |
| **Configuration** | [[Configuration & GameRules|pt_br-26.2-Configuration-and-GameRules]] | Dynamic namespaced GameRules, `/yourunfast` command suite (`help`, `status`, `set`) |
| **Architecture** | [[Architecture & Mixins|pt_br-26.2-Architecture-and-Mixins]] | Mixin inspection (`SectionTaskDynamicQueueMixin`), FastUtil data structures, zero-GC fast path |

---

## ⚡ Especificações Técnicas Rápidas

- **Target Game Version**: Minecraft `26.2`
- **Client Render Queue**: `SectionTaskDynamicQueue`
- **Server Ticket Type**: `TicketType.PLAYER_LOADING` with radius 1
- **Dynamic GameRules Category**: `yourunfast:main`
- **Root Brigadier Command**: `/yourunfast`
- **Mathematical Smoothing**: Exponential Moving Average with $\alpha = 0.65$

---

> ☕ *Nota do desenvolvedor independente*: Se você aprecia o carregamento suave e prioritário de chunks em alta velocidade, apoie meu trabalho solo no [Ko-fi](https://ko-fi.com/dasikigaijin)!

---

## 🔙 Navegação de Retorno
- [[Back to Version Selector Portal|pt_br-Home]]
- [[View Compatibility Matrix|pt_br-Version-Compatibility]]
- [[Developer Setup & Building|pt_br-Developer-Setup-and-Building]]
