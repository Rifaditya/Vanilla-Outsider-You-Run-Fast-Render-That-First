# 💻 Guia de Configuração e Compilação para Desenvolvedores

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Aviso sobre o Código-Fonte do Repositório**: A documentação desta Wiki reflete o **estado atual do código-fonte no repositório**, podendo incluir commits recentes ou recursos em desenvolvimento não lançados no CurseForge ou Modrinth.

---

## 🛠️ Configuração do Ambiente de Desenvolvimento

O projeto utiliza Gradle moderno com Fabric Loom visando Java 25+. Cada versão do Minecraft é mantida como um subprojeto independente.

### 1. Pré-requisitos
- **JDK**: Java 25 ou superior.
- **Git**: 2.40+
- **IDE**: IntelliJ IDEA 2024.3+ ou Eclipse.

```bash
git clone https://github.com/Rifaditya/Vanilla-Outsider-You-Run-Fast-Render-That-First.git
cd "Vanilla-Outsider-You-Run-Fast-Render-That-First"
```

---

## 🏗️ Multi-Version Directory Layout

```
Vanilla-Outsider-You-Run-Fast-Render-That-First/
├── You Run Fast Render That First v26.1/
│   └── You Run Fast Render That First 26.1/
│       ├── build.gradle
│       ├── gradle.properties
│       └── src/
├── You Run Fast Render That First v26.2/
│   └── You Run Fast Render That First 26.2/
│       ├── build.gradle
│       ├── gradle.properties
│       └── src/
├── You Run Fast Render That First v26.3/
│   └── You Run Fast Render That First 26.3/
│       ├── build.gradle
│       ├── gradle.properties
│       └── src/
├── Wiki/
└── README.md
```

---

## 🚀 Comandos de Compilação e Testes

Navegue até a pasta do subprojeto correspondente:

### MC 26.3 Build & Test
```powershell
cd "You Run Fast Render That First v26.3\You Run Fast Render That First 26.3"
./gradlew check --no-daemon
./gradlew build --no-daemon
```

### MC 26.2 Build & Test
```powershell
cd "You Run Fast Render That First v26.2\You Run Fast Render That First 26.2"
./gradlew check --no-daemon
./gradlew build --no-daemon
```

### MC 26.1 Build & Test
```powershell
cd "You Run Fast Render That First v26.1\You Run Fast Render That First 26.1"
./gradlew check --no-daemon
./gradlew build --no-daemon
```

---

> ☕ *Nota do desenvolvedor independente*: Se você aprecia o carregamento suave e prioritário de chunks em alta velocidade, apoie meu trabalho solo no [Ko-fi](https://ko-fi.com/dasikigaijin)!

---

## 🔙 Return Navigation

- [[Back to Version Selector Portal|pt_br-Home]]
- [[View Compatibility Matrix|pt_br-Version-Compatibility]]
