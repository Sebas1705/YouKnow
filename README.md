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
| Build | Gradle with build-logic convention plugins |

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
- Android Studio Hedgehog or newer
- JDK 17+
- Firebase project with **Authentication** and **Storage** enabled

### Setup

1. Clone the repo:
   ```bash
   git clone https://github.com/Sebas1705/YouKnow.git
   ```

2. Add your `google-services.json` to the `app/` directory.

3. Build:
   ```powershell
   .\gradlew.bat assembleDebug    # Windows
   ```
   ```bash
   ./gradlew assembleDebug        # macOS/Linux
   ```

---

## License

MIT
