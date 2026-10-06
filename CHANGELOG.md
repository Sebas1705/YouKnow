# Changelog
All notable changes to this repository are documented in this file.
The format follows Keep a Changelog principles and semantic versioning (tags `vX.Y.Z`).
## [Unreleased]

## [1.4.1] - 2026-10-07
### Changed
- Losing the connection no longer replaces the whole app with a "Connection lost" screen: a thin offline banner is shown on top and the saved questions and games keep working.

## [1.4.0] - 2026-10-07
### Added
- Profile photo upload: pick an image with the system Photo Picker and it is stored in Cloud Storage (`profile_photos/{uid}`, signed-in read, owner-only write, under 5 MB, images only), replacing the paste-a-URL dialog.
- Usability survey screen (Settings -> "Give feedback"): five pages covering the full survey model, star ratings with optional comments, published to Firestore; question labels are English-only for now.
- "Download more questions" on Home fetches fresh questions from OpenTDB into the local database.
### Fixed
- Submitting the survey with an expired session left the spinner running forever; it now gives up after 20 seconds with an error message.
- Background music kept playing or resumed while the app was in the background.
### Changed
- Updated `play-services-auth` to 22.0.0.

## [1.3.0] - 2026-09-30
### Added
- Daily Challenge: a short quiz round shared by every player on a given day, published once by whichever device asks first and read by everyone else afterwards, wired into a new card on Home.
- A streak flame in the HUD once an answer streak reaches 2 in a row, and confetti on a flawless (3-star) result.
- Haptic feedback alongside the existing win/lose sound on every answer reveal and result screen.
### Changed
- Custom mode (Quiz, Families, Mystery Number) redesigned to match the rest of the game UI: sticker-styled setup cards, pill chips for type/difficulty, and a slider with +/- steppers; question count and lives can no longer be set to 0.
- The games' sticker card/tag look moved to `:core:designsystem` and is now used on Home's info cards too, instead of being exclusive to Play.
- Win/lose sounds now also play on Word-Pass and Mystery Number rounds.
### Fixed
- Dark mode showed the light-mode game icons on Play and mode selection.
- Custom mode (and Word-Pass's letter wheel) failed outright when opentdb/Room couldn't fill the exact requested question, family or word count; it now plays with whatever was found instead of erroring on ordinary settings.
- A revealed answer's correct/wrong outcome, and the lives remaining, were conveyed by colour and icons alone, unlabelled for screen readers.

## [1.2.0] - 2026-09-26
### Changed
- The four games get a visual refresh in the app's hand-drawn style (same backgrounds, palette and fonts): sticker-like cards and buttons, a top HUD with points, progress, hearts and a countdown bar, mode cards with the game's illustration and what each mode is and pays, and a result screen with stars and stats.
- Quiz and Families show the right answer in green and a wrong one in red before moving on.
- Mystery Number shows the higher/lower hint and the guess history on screen (it was a toast), and its keypad only offers steps that fit the range.
- Word-Pass draws a real letter wheel (guessed, missed, current).
### Fixed
- Time attack in Quiz and Families counted 15 seconds for the whole game instead of per question.

## [1.1.1] - 2026-09-25
### Fixed
- The guide stopped with an "Empty words" toast: the default games data (words, questions, families) had been lost when the project moved to the repo root (the `*.json` ignore rule hid them), their names did not match the code, and they were decoded as a single object instead of a list.
- The guide came back on every launch and every setting was reset at start: the defaults were rewritten on each launch, and finishing the guide stored the wrong value.
- The game language chosen in Settings was never saved.
- Home crashed on open: the navigation and game button sounds had been replaced by resource id 0.
- Families showed "Survival" for all four modes.

## [1.1.0] - 2026-09-25
### Changed
- Aligned with the Templetry `android/modular-features` template (commit `cc60aeb`): build-logic, version catalog (Kotlin 2.3, Compose 1.11, Navigation 3 1.0), Gradle 9 wrapper, CI and docs.
- Environment profiles `Development`/`Staging`/`Production` replace the `Dev`/`Demo`/`Beta`/`Alpha`/`Pro` flavors; `QUIZ_API_URL` becomes `API_BASE_URL`.
- Navigation migrated to Navigation 3; the root graph (`AppNav`) is restored, so the app navigates again.
- Secrets move to Doppler (project `youknow`): Firebase config, Google Sign-In client id, signing and API URLs are read from the environment or `local.properties`; no `google-services.json` or `secrets.properties` in the repo.
- Release pipeline: a `vX.Y.Z` tag builds the signed Production APK, publishes a GitHub Release and uploads it to Firebase App Distribution (`release-apk.yml`), replacing the template's date-tag release flow.
- Production keeps the `es.sebas1705.youknow` application id (no `.pro` suffix).
- Firebase moves to the BoM (34.19.0) with the main artifacts; the discontinued `-ktx` ones are gone.
- Protobuf aligned on 3.25.9 (runtime, Kotlin lite and protoc) instead of forcing 3.19.4.
- The official `google-services.json` lives in Doppler (`GOOGLE_SERVICES_JSON`, refreshed by `scripts/refresh-google-services.sh`); the sync script writes it for local development and CI uses it for release builds.
- Firebase project organized: one app per variant (Staging apps added, each variant reports to its own app id), SHA certificates registered, `testers` group created. Security rules, indexes and their emulator tests are versioned under `firebase/` (`docs/FIREBASE.md`).
### Fixed
- Unit tests no longer depend on the machine locale; stale settings mapper test updated.
- Hardcoded Google Sign-In client ids removed (one belonged to another Google project).
- Compiler warnings: deprecated `MenuAnchorType`, redundant conversion and `when` branch.
- Security: Firestore and Realtime Database were open to anyone (read and write); new rules require sign-in and ownership.
- Release builds: R8 obfuscated the Firestore, Realtime Database and Gson models, so a release APK would read empty data and write obfuscated field names; the data modules now ship keep rules.
- News were always empty: Firestore stores `title_es`/`body_en`…, now mapped with `@PropertyName`.
## [2026.05.09] - 2026-05-09
### Added
- CI and security automation in repository root workflows.
- Governance, release policy, triage, KPI and 30-60-90 maintenance docs.
- Issue forms, pull request template, CODEOWNERS, and label sync workflow.
### Changed
- Detekt configuration migration and validation pipeline hardening for `YouKnow`.
