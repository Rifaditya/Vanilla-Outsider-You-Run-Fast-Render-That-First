# Architecture & Symbol Index: You Run Fast, Render That First

## 1. Mod Metadata & Entrypoint
- **Mod ID**: `you-run-fast-render-that-first`
- **Main Entrypoint**: `net.vanillaoutsider.yourunfast.YouRunFastMod` (`net.fabricmc.api.ModInitializer`)
- **Client Entrypoint**: `net.vanillaoutsider.yourunfast.client.YouRunFastClient`

## 2. Bytecode Mixin Target Registry
| Target Vanilla Class | Mixin Class | Purpose |
| :--- | :--- | :--- |
| `Minecraft` | `Mixin` | Core bytecode hook |

## 3. Core Mechanics & Subsystems
- **Source Root**: `src/main/java/`
- **Resource Root**: `src/main/resources/`

## 4. Dynamic GameRules & Commands
- **GameRules / Commands**: Configured dynamically via namespaced keys (`you-run-fast-render-that-first:*`).

## 5. Configuration & Sidedness Isolation
- **Sidedness**: Server-safe logic in main, client isolated in `src/client/java` or client entrypoint.
