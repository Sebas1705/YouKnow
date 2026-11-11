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


## Flow 4: Account Deletion (Settings → Firebase)

- Entry: `feature/settings/.../SettingsScreen.kt` ("Delete account"), state machine in
  `feature/settings/.../viewmodel/SettingsViewModel.kt` (`DeleteAccountStep`, `DeleteAccountError`)
- Dialogs: `DeleteAccountDialog` and `PasswordConfirmDialog` in `core/designsystem`
- Domain: `domain/usescases/auth/...` (`GetAccountProvider`, `ReauthenticateWithPassword`,
  `ReauthenticateWithGoogle`, `ReauthResult`) and `domain/usescases/user/.../DeleteCurrentUser.kt`
  (`DeleteAccountResult`)
- Data: `UserAuthDataSource.deleteCurrentUser`, `EmailAuthDataSource.reauthenticate`,
  `GoogleAuthDataSource.reauthenticateWithGoogle`, `GroupRealtimeDataSource.getGroup`,
  `MessageRealtimeDataSource.removeMessagesOf`, `ProfilePhotoStorageDataSource.deleteProfilePhoto`,
  `UserDocumentFirestoreDataSource.getUserGroupId`, `DatabaseRepository.deleteSurvey`
- What is deleted and kept, and why: `docs/FIREBASE.md` (Account deletion) and `docs/account-deletion.html`

Replication notes:

1. Re-authenticate right before deleting (password or Google): Firebase refuses to delete a stale
   sign-in, and the Auth user must go last because the rules need it to delete the data.
2. `DeleteCurrentUser` returns a result instead of throwing: `RecentLoginRequired` and
   `Failed(dataDeleted = true)` mean the data is gone and only the account is left, so the screen
   keeps the dialog open and the same confirm button retries; every step tolerates having been done.
3. Order matters: the group is found through `users/{uid}`, so that document is deleted after the
   group step and before the Auth user.
4. Navigation is a state flag (`accountDeleted`) consumed by the screen, never a callback from the
   ViewModel.
