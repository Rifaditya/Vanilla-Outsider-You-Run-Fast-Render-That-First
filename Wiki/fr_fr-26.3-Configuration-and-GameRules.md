# ⌨️ Configuration & GameRules dynamiques (MC 26.3)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> 📌 **Clause de non-responsabilité relative au code source** : La documentation de ce Wiki reflète **l'état actuel du code source dans le dépôt**, incluant d'éventuels commits récents non publiés sur CurseForge ou Modrinth.

---

## 1. Tableau technique officiel

| Parameter | Technical Details |
| :--- | :--- |
| **Subsystem Name** | Dynamic GameRules & Brigadier Command Suite |
| **Minecraft Anchor** | MC 26.3 |
| **Java Implementation** | `net.vanillaoutsider.yourunfast.registry.YouRunFastGameRules` |
| **Command Implementation** | `net.vanillaoutsider.yourunfast.command.YouRunFastCommand` |
| **Root Command** | `/yourunfast` |
| **GameRule Category** | `yourunfast:main` ("You Run Fast, Render That First") |
| **Permission Level (Get/Status)** | Level 0 (All players) |
| **Permission Level (Set/Reset/Reload)** | Level 2 (Server Operators) |
| **Integration Framework** | `DynamicGameRuleManager` via DasikLibrary |

---

## 2. Utilisation des commandes

### 1. Telemetry Inspection (`/yourunfast status`)
```
/yourunfast status
```

**Example Output**:
```
§6[You Run Fast, Render That First — Engine Telemetry]§r
 §7• §fStatus: §aACTIVE§r
 §7• §fYour Velocity: §a1.45 b/t (29.0 m/s)§r
 §7• §fDynamic Forward Reach: §e14 Chunks§r
 §7• §fActive Packed Tickets: §b18 (LongOpenHashSet Zero-Alloc)§r
 §7• §fServer MSPT Load: §f18.4 ms §7(§a20.0 TPS§7)§r
 §7• §fClient Mesh Bias: §aCOMPILING FORWARD§r
 §7• §fForward Lead Multiplier: §e100%§r
 §7• §fMin Speed Threshold: §b0.20 b/t (4.0 m/s)§r
 §7• §fBudget Conservation: §aON§r
 §7• §fDebug Logging: §7OFF
```

### 2. Querying GameRules (`/yourunfast get <rule>`)
```
/yourunfast get lead_multiplier
/yourunfast get min_speed
```

### 3. Modifying Settings (`/yourunfast set <rule> <value>`)
```
/yourunfast set lead_multiplier 150
/yourunfast set budget_conservation true
```

---

## 3. Table des GameRules dynamiques

| GameRule Key | Type | Default | Valid Bounds | Description |
| :--- | :--- | :--- | :--- | :--- |
| `yourunfast:enabled` | Boolean | `true` | `true` / `false` | Master toggle for both client bias and server generation tickets |
| `yourunfast:forward_lead_multiplier` | Integer | `100` | `0` to `500` | Multiplier for projected lead distance percentage ($100 = 1.0\text{x}$) |
| `yourunfast:min_speed_threshold_pct` | Integer | `20` | `5` to `100` | Minimum player traversal speed required to trigger biasing ($20 = 0.20\text{ b/t}$) |
| `yourunfast:budget_conservation` | Boolean | `true` | `true` / `false` | Enables real-time MSPT watchdog server ticket throttling |
| `yourunfast:debug_logging` | Boolean | `false` | `true` / `false` | Enables verbose diagnostic SLF4J logging in server and client logs |

---

> ☕ *Note du développeur solo*: Si vous appréciez la fluidité du rendu de terrain lors de vos déplacements rapides, soutenez mon travail sur [Ko-fi](https://ko-fi.com/dasikigaijin) !

---

## 🔗 Related Pages (MC 26.3)

- [[Anisotropic Prioritization|fr_fr-26.3-Anisotropic-Prioritization]]
- [[Chunk Generation Biasing|fr_fr-26.3-Chunk-Generation-Biasing]]
- [[Architecture & Mixins|fr_fr-26.3-Architecture-and-Mixins]]
- Return to [[26.3 Overview Portal|fr_fr-26.3-Home]]
