# 🎛️ Master Release Queue: Vanilla Outsider — You Run Fast, Render That First

> **Mod Project Master Ground-Truth Document**  
> *Last Synchronized: 2026-09-01*  
> **Modrinth ID**: `yBO3F5aQ` | **CurseForge ID**: `you-run-fast-render-that-first` | **Lead SemVer**: `1.0.6`

---

## 📊 Multi-Version Release Matrix & Queue Status

| Target MC | Generational Era | Live on Platforms | Next Queued Version | Status & Cadence Action | Feature Highlights / Notes |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **MC 26.3** | Modern Lead | *(Unreleased)* | `1.0.6+26.3` | 🛠️ **Local Development** | Initial workspace build ready for first public deployment. |
| **MC 26.2** | Modern Predecessor | *(Unreleased)* | `N/A` | 🛠️ **Local Development** | Initial workspace build ready for first public deployment. |
| **MC 26.2** | Modern Predecessor | *(Unreleased)* | `1.0.6+26.2` | 🛠️ **Local Development** | Initial workspace build ready for first public deployment. |
| **MC 26.1** | Modern Predecessor | *(Unreleased)* | `1.0.6+26.1.2` | 🛠️ **Local Development** | Initial workspace build ready for first public deployment. |

---

## 🏛️ Project Operating Rules & Architectural Invariants

1. **🔢 Universal Direct SemVer Inheritance**:
   - Modern subprojects share unified SemVer milestone lineage targeting `1.0.6`.
   - Each Minecraft version anchor manages its own organic progression to ensure 100% clean, verified parity.

2. **📅 Daily Update Guard**:
   - Strict maximum of 1 release per day per targeted Minecraft version anchor across Modrinth and CurseForge.

---

## 🛠️ CLI Publisher Commands for You Run Fast, Render That First

```powershell
# 1. Check current status across all targeted Minecraft versions
python ".agents/skills/platform-publisher/scripts/platform_publisher.py" --mod "You Run Fast, Render That First" --status

# 2. Publish next sequential batch across all active versions
python ".agents/skills/platform-publisher/scripts/platform_publisher.py" --mod "You Run Fast, Render That First" --publish-next --yes

# 3. Publish for a specific version anchor only (e.g. MC 26.3)
python ".agents/skills/platform-publisher/scripts/platform_publisher.py" --mod "You Run Fast, Render That First" --mc 26.3 --publish-next --yes
```
