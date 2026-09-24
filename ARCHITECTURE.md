# Architecture

High-level architecture snapshot for YouKnow.

## Layer Model

- `app`: Android application entry point and top-level wiring (Hilt application, `MainActivity`, background music).
- `core`: shared UI, common utilities, resources, and design system.
- `data`: data source implementations and repository adapters.
- `domain`: models, mappers, managers, providers, services, and use cases.
- `feature`: screen-level flows and navigation.

## Dependency Direction

- `feature` depends on `domain` and selected `core` modules.
- `domain` depends on abstractions and shared models; avoids UI details.
- `data` implements repository interfaces and data-source details.
- `app` composes features and platform-specific runtime setup.

## Navigation

Navigation 3 (`androidx.navigation3`) throughout — no `navigation-compose`.

- `feature:main` — `MainScreen` shows the splash, then the network-error screen or `AppNav`.
  `AppNav` owns the root back stack (`AppGraph` keys): guide, auth, home, game, settings, survey.
  The start destination comes from `MainViewModel` (guide on first run, home when signed in, auth otherwise).
- `feature:auth` — `AuthNav`, a nested `NavDisplay` over `AuthScreens`.
- `feature:home` — `HomeNav`, a one-entry back stack swapped by the bottom bar (`HomeScreens`).
- `feature:game` — `GameNav` picks the game by index; no back stack of its own.

Moving between root graphs (auth ↔ home, guide → auth) replaces the back stack (`pushAndFree`); leaving
settings, survey or a game pops it.

## Environment Profiles

Three product flavors — `Development`, `Staging`, `Production` (ADR-0018 in the Templetry wiki) — each
with `BuildConfig.ENVIRONMENT`, `API_BASE_URL` (from `app/secrets.properties`) and `VERBOSE_LOGGING`.
Defined once in `build-logic/.../CoreFlavor.kt`.

## Cross-Cutting Concerns

- Dependency injection: Hilt modules by feature/data boundary.
- Static analysis: Detekt shared config in `config/detekt/detekt.yml`.
- Build conventions: custom convention plugins in `build-logic/`.
- Testing and coverage: module-level tests plus `coverageUnitTestAll` aggregation.
- Firebase Gradle plugins are applied only when a `google-services.json` is present, so CI builds without one.

## Debug Diagnostics

- LeakCanary in app debug variant.

## Detailed Replication Guides

- Guardrails: `docs/ai/ARCHITECTURE_GUARDRAILS.md`
- Existing flows: `docs/ai/FLOW_CATALOG.md`
- Implementation recipes: `docs/ai/PATTERN_RECIPES.md`
