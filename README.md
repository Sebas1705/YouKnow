# YouKnow

Android trivia and quiz app built with **Kotlin** and **Jetpack Compose** on a multi-module Clean Architecture. Test your knowledge across categories powered by [OpenTriviaDB](https://opentdb.com/), with audio feedback, Firebase integration, and a survey system.

---

## Features

- **Quiz game** — questions fetched from OpenTriviaDB across multiple categories
- **Audio feedback** — sound effects tied to correct/incorrect answers
- **Firebase Auth** — user authentication and profile photo upload
- **Survey system** — collect user input within the app
- **Guide / onboarding** — built-in guide for new players
- **Settings** — customizable app preferences
- **Network error handling** — dedicated screen for offline/error states

---

## Tech Stack

| Layer | Technologies |
|---|---|
| UI | Jetpack Compose · Material Design |
| Architecture | Multi-module Clean Architecture (core / data / domain / feature) |
| Data | OpenTriviaDB API · Firebase Auth · Firebase Storage |
| Navigation | Navigation 3 (nested `NavDisplay` per feature) |
| Build | Gradle 9 with build-logic convention plugins · Kotlin 2.3 · Hilt · KSP |

---

## Module Structure

```
core/          ← shared utilities, UI components, resources
data/          ← network, Firebase, repository implementations
domain/        ← models, use cases, services
feature/
├── auth/      ← login and authentication
├── game/      ← quiz game flow
├── guide/     ← onboarding guide
├── home/      ← dashboard
├── main/      ← navigation host
├── networkerror/ ← offline/error screen
├── settings/  ← app preferences
├── splash/    ← launch screen
└── survey/    ← user surveys
```

---

## Getting Started

### Prerequisites
- JDK 21 (Android Studio's bundled JDK works)
- Android SDK 36
- Firebase project with **Authentication**, **Firestore**, **Realtime Database** and **Storage** enabled (optional for building — see below)

### Setup

1. Clone the repo:
   ```bash
   git clone https://github.com/Sebas1705/YouKnow.git
   ```

2. Secrets come from [Doppler](https://www.doppler.com) (project `youknow`, config `dev`) — no
   `google-services.json` or secrets file in the repo:
   ```bash
   doppler setup                                  # once
   doppler run -- ./gradlew assembleDevelopmentDebug
   scripts/doppler-sync-local-properties.sh       # for Android Studio: writes local.properties
   ```
   Without the Firebase keys the project still builds (CI's validation relies on this), but
   Firebase features will not work at runtime.

3. Test:
   ```bash
   ./gradlew testDebugUnitTest detekt
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
