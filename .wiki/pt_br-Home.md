# 🚀 You Run Fast, Render That First — Wiki Oficial

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Aviso sobre o Código-Fonte do Repositório**: A documentação desta Wiki reflete o **estado atual do código-fonte no repositório**, podendo incluir commits recentes ou recursos em desenvolvimento não lançados no CurseForge ou Modrinth.

---

## 🧭 Bem-vindo à Documentação Técnica Oficial

**Vanilla Outsider: You Run Fast, Render That First** é um mod de otimização de altíssimo desempenho para Fabric projetado para eliminar o pop-in súbito de chunks e paredes de vazio em alta velocidade. Ao sincronizar as filas de malha de renderização do cliente e os tickets de geração do servidor diretamente com o vetor de velocidade em tempo real do jogador ($ec{v}$), o terreno para onde você está voando ou galopando é compilado e carregado primeiro.

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

## 🏛️ Selecione sua versão do Minecraft

| Minecraft Version | Version Tree Link | Engine Lifecycle | Client Queue Mixin | Minimum Java |
| :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | [[👉 Enter MC 26.3 Wiki|pt_br-26.3-Home]] | **Modern Lead** | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.2** | [[👉 Enter MC 26.2 Wiki|pt_br-26.2-Home]] | **Modern Predecessor** | `SectionTaskDynamicQueue` | Java 25+ |
| **Minecraft 26.1** | [[👉 Enter MC 26.1 Wiki|pt_br-26.1-Home]] | **Modern Anchor (26.1.2)** | `CompileTaskDynamicQueue` | Java 25+ |

---

## ⚡ Principais Subsistemas de Engenharia

- **[[Anisotropic Chunk Prioritization|pt_br-26.3-Anisotropic-Prioritization]]**
- **[[Server Chunk Generation Biasing & Watchdog|pt_br-26.3-Chunk-Generation-Biasing]]**
- **[[Dynamic GameRules & Brigadier Commands|pt_br-26.3-Configuration-and-GameRules]]**
- **[[Zero-GC High-Frequency Hot Path|pt_br-26.3-Architecture-and-Mixins]]**

---

> ☕ *Nota do desenvolvedor independente*: Se você aprecia o carregamento suave e prioritário de chunks em alta velocidade, apoie meu trabalho solo no [Ko-fi](https://ko-fi.com/dasikigaijin)!

---

## 🛠️ Documentação para Desenvolvedores

- [[Version Compatibility Matrix|pt_br-Version-Compatibility]]
- [[Developer Setup & Building|pt_br-Developer-Setup-and-Building]]
