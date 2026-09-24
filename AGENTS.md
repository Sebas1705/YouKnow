# AGENTS

Operating contract for AI agents and automation helpers working in this project.

## Mission

- Keep YouKnow stable, maintainable, and well-documented.
- Prefer incremental changes with verifiable outputs.

## Core Rules

- Respect module boundaries (`app`, `core`, `data`, `domain`, `feature`).
- Prefer convention plugins and version catalog over ad-hoc Gradle configuration.
- Keep debug-only tooling in debug variants.
- Update docs in the same change when behavior or process changes.

## Required Checks Before Finishing

- Compile affected modules.
- Run detekt on affected modules.
- Run lint for affected Android modules (for example, `:app:lint`).
- If dependencies or build logic changed, run `dependencyUpdates --no-parallel`.

```sh templetry:checks
./gradlew assembleDevelopmentDebug
./gradlew testDebugUnitTest detekt
```

Library modules have no flavors, so their unit tests run under `testDebugUnitTest`; only `:app` carries
the `Development`/`Staging`/`Production` flavors.

## Project Conventions

- **UI**: Jetpack Compose only, no XML layouts. Stateless reusable components go in `:core:designsystem`.
- **State**: every feature ViewModel extends `MVIBaseViewModel<State, Intent>`, is `@HiltViewModel` with
  constructor injection, and exposes `StateFlow` collected with `collectAsStateWithLifecycle()`.
- **Navigation**: Navigation 3 (`NavDisplay` + `NavKey` routes). Each feature owns its nested graph
  (`AuthNav`, `HomeNav`, `GameNav`); `feature:main` owns the root (`AppNav`). Do not add `navigation-compose`.
- **Domain** stays free of `android.*` where possible; use cases are the only entry point from ViewModels.
- **Data**: one module per data source (`:data:room`, `:data:retrofit`, …); Kotlinx Serialization for JSON;
  DataStore for preferences.
- **Async**: coroutines and `Flow` only.
- **Build**: apply the matching convention plugin to new modules and declare dependencies only through
  `gradle/libs.versions.toml`.
- **KDoc** on public classes and functions, with the project tags `@since <version>` and
  `@author <Name> <dd/mm/yyyy>`.

## Safe Change Workflow

1. Read context from `AI_INDEX.md` and the relevant docs.
2. Apply the minimum necessary edits.
3. Validate with targeted Gradle tasks.
4. Summarize what changed, what was verified, and any known constraints.

## Architecture and Flow Replication Docs

- Playbook: `docs/ai/AGENT_PLAYBOOK.md`
- Guardrails: `docs/ai/ARCHITECTURE_GUARDRAILS.md`
- Existing flows: `docs/ai/FLOW_CATALOG.md`
- Recipes: `docs/ai/PATTERN_RECIPES.md`

## Do Not

- Do not commit secrets or credentials. Every secret lives in Doppler (`docs/DISTRIBUTION.md`); the build reads them with `secret("KEY", default)` from the root `build.gradle.kts`. Never commit `app/google-services.json`, `local.properties` or a keystore.
- Do not change release/governance rules silently.
- Do not move files between repository root and module directories (`app/`, `core/`, `data/`, `domain/`, `feature/`) without explicit intent and docs updates.

## This project came from a template

Four facts you cannot infer from the code in front of you:

- **Never hand-edit `.templetry-answers.yml`.** It records what generated this project. Editing it makes the next update merge against a state that never existed.
- **Before writing a capability by hand, run `templetry pieces`.** Auth, RBAC, audit trails, API keys and whole CRUD resources may already exist as pieces for this template. Adopting one is `templetry add <name>`, and it brings its own tests.
- **`templetry update` pulls improvements from the template** through a three-way merge that keeps your edits. Use it instead of copying files from the template by hand.
- **Directives like `tpl:if` belong to the template, not here.** If you find one in this project, it is a rendering bug worth reporting — do not try to interpret it.
