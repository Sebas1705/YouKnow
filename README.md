# YouKnow

Android trivia and games app built with **Kotlin** and **Jetpack Compose** on a multi-module Clean Architecture. Play quizzes powered by [OpenTriviaDB](https://opentdb.com/) and three more games, take the shared daily challenge, climb the global ranking, chat with everyone and join groups, backed by Firebase. The UI is available in English and Spanish.

---

## Features

- **Games** — four game types, each with several modes (survival, time attack, a quick random round, custom):
  - **Quiz**: OpenTriviaDB questions by category, difficulty and type (multiple choice or true/false)
  - **Mystery Number**: guess the hidden number before you run out of lives
  - **Word-Pass**: answer the word of each letter, up to the full letter wheel
  - **Families**: pick the word that doesn't belong to the family
- **Daily challenge** — one short round shared by every player on a given UTC day, published once to Firestore by whichever device asks first, with a bonus of points and credits
- **Points, credits and ranking** — every round earns points and credits; the global ranking lists players by points
- **Global chat** — a public chat (messages up to 50 characters) on the Realtime Database
- **Groups** — create a group (it costs credits), join one or leave it; a group has a leader and up to 50 members
- **News** — announcements published from the Firebase console
- **Profile** — nickname and profile photo (Photo Picker, stored in Cloud Storage); sign in with email and password or Google
- **Survey** — a usability survey published to Firestore from Settings
- **Guide / onboarding**, **settings** (sound, music, game language, ...) and a thin offline banner: saved questions and games keep working without a connection
- **Audio feedback** — sound effects and background music

---

## Tech Stack

| Layer | Technologies |
|---|---|
| UI | Jetpack Compose · Material Design 3 |
| Architecture | Multi-module Clean Architecture (core / data / domain / feature), MVI view models |
| Data | OpenTriviaDB (Retrofit) · Room (questions, words, families, survey) · DataStore (settings) |
| Firebase | Authentication (email and Google) · Cloud Firestore (users, news, surveys, daily challenges) · Realtime Database (chat, groups) · Cloud Storage (profile photos) · Crashlytics and Analytics |
| Navigation | Navigation 3 (nested `NavDisplay` per feature) |
| Build | Gradle 9 with build-logic convention plugins · Kotlin 2.3 · Hilt · KSP · `minSdk` 31 |
| Tests | JUnit, Robolectric, Mockito, Turbine; security rules tests on the Firebase emulators |

---

## Architecture

Dependencies point inwards: `feature` → `domain` → `data` → `core`. View models only talk to
use cases; use cases talk to repositories; each data source lives in its own module. Details and
the working rules: [ARCHITECTURE.md](ARCHITECTURE.md), [MODULE_MAP.md](MODULE_MAP.md), [AGENTS.md](AGENTS.md).

```
app/           ← application, flavors, Firebase wiring
core/          ← common utilities, design system, resources, shared UI
data/          ← one module per source: retrofit, room, datastore, firestore, realtime, storage,
                 authentication, analytics, files, couchbase; repositories on top
domain/        ← models, mappers, managers, providers, services and use cases per area
                 (quiz, daily challenge, mystery number, word pass, families, chat, groups, user, ...)
feature/
├── auth/         ← sign in and sign up
├── game/         ← quiz, mystery number, word-pass, families and the daily challenge
├── guide/        ← onboarding guide
├── home/         ← main (ranking, news), play, chat, groups, profile
├── main/         ← root navigation host
├── networkerror/ ← offline banner
├── settings/     ← app preferences
├── splash/       ← launch screen
└── survey/       ← usability survey
```

Firestore and Realtime Database are protected by security rules (`firebase/`) that are tested against
the emulators; what they enforce, and what they cannot, is in [docs/FIREBASE.md](docs/FIREBASE.md).

---

## Getting Started

### Prerequisites
- JDK 21 (Android Studio's bundled JDK works)
- Android SDK 36 (the app targets 36 and runs on Android 12 / API 31 and later)
- Firebase project with **Authentication**, **Firestore**, **Realtime Database** and **Storage** enabled (optional for building — see below)

### Setup

1. Clone the repo:
   ```bash
   git clone https://github.com/Sebas1705/YouKnow.git
   ```

2. Secrets and the Firebase config come from [Doppler](https://www.doppler.com) (project `youknow`, config `dev`) — no
   `google-services.json` or secrets file in the repo:
   ```bash
   doppler setup                                  # once
   doppler run -- ./gradlew assembleDevelopmentDebug
   scripts/doppler-sync-local-properties.sh       # for Android Studio: local.properties + app/google-services.json
   ```
   Without the Firebase keys the project still builds (CI's validation relies on this), but
   Firebase features will not work at runtime.

3. Test:
   ```bash
   ./gradlew testDebugUnitTest detekt
   cd firebase/rules-test && npm ci && npm test   # security rules, on the emulators (needs Java 21)
   ```

### Environment profiles

| Flavor | `applicationId` | `BuildConfig.ENVIRONMENT` | Verbose logging |
|---|---|---|---|
| `Development` | `es.sebas1705.youknow.dev` | `development` | on |
| `Staging` | `es.sebas1705.youknow.staging` | `staging` | on |
| `Production` | `es.sebas1705.youknow` | `production` | off |

Debug builds add `.debug`.

---

## Distribution

A `vX.Y.Z` tag builds the signed `Production` release, publishes it as a GitHub Release and
uploads it to Firebase App Distribution; GitHub only needs `DOPPLER_TOKEN`:

```bash
git tag v1.1.0
git push origin v1.1.0
```

Secrets, one-time setup and details: **[docs/DISTRIBUTION.md](docs/DISTRIBUTION.md)**.
Firebase apps, security rules and how to test and deploy them: **[docs/FIREBASE.md](docs/FIREBASE.md)**.

---

## Template

YouKnow is aligned with the [Templetry](https://github.com/Templetry) form
[`android/modular-features`](https://github.com/Templetry/android/tree/main/modular-features).
`.templetry-answers.yml` records the template and commit it tracks, so improvements to the template can be
pulled in with a three-way merge that keeps YouKnow's own changes:

```bash
templetry update          # preview
templetry update --apply  # merge
```

Do not edit `.templetry-answers.yml` by hand. See `AGENTS.md` and `AI_INDEX.md` for the working docs.

---

## License

MIT
