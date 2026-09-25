# Flow Catalog

Catalog of existing end-to-end flows to replicate safely.

## Flow 1: App Startup Navigation (MVI)

- Entry: `feature/main/.../MainScreen.kt` → `feature/main/.../AppNav.kt`
- ViewModel: `feature/main/.../MainViewModel.kt` (settings + auth state → `startDestination`)
- Base pattern: `core/common/.../MVIBaseViewModel.kt`

Replication notes:

1. Model events as intents.
2. Keep navigation trigger in feature state transitions.
3. Avoid direct side effects from composables.

## Flow 2: Trivia Questions (OpenTriviaDB)

- Entry: `feature/game/.../quiz/viewmodel/QuizViewModel.kt`
- Domain: `domain/usescases/opendb/...`
- Repository: `OpendbRepository`
- Data source: `data/retrofit/.../OpendbApiDataSource.kt`

Replication notes:

1. Add/extend use case in `domain`.
2. Reuse repository interface when possible.
3. Keep network specifics in `data` only.
4. Return UI-ready state from ViewModel, not from use case.

## Flow 3: Settings (DataStore)

- Entry: `feature/settings/.../viewmodel/SettingsViewModel.kt`
- Domain: `domain/usescases/settings/...`, mapping in `domain/mappers/.../SettingsMapper.kt`
- Data source: `data/datastore/...`

Replication notes:

1. Map between `SettingsData` and `SettingsModel` in `domain:mappers` only.
2. Do not bypass use cases from `feature` to `data`.
