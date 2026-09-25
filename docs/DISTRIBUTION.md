# Distribution and secrets

Same scheme as nanodemo: every secret lives in Doppler, GitHub only holds `DOPPLER_TOKEN`, and a
`vX.Y.Z` tag is the only way a build reaches testers.

## Secrets in Doppler

Doppler project **`youknow`**, configs **`dev`** (local) and **`prd`** (CI / distributed builds).

| Secret | Used for |
|---|---|
| `FIREBASE_APP_ID`, `FIREBASE_API_KEY`, `FIREBASE_PROJECT_ID`, `FIREBASE_SENDER_ID`, `FIREBASE_STORAGE_BUCKET`, `FIREBASE_DATABASE_URL` | Firebase config compiled into the APK (replaces `google-services.json`); `FIREBASE_APP_ID` is also the App Distribution target |
| `GOOGLE_WEB_CLIENT_ID` | Google Sign-In server client id (web OAuth client of the Firebase project) |
| `API_BASE_URL_DEVELOPMENT`, `API_BASE_URL_STAGING`, `API_BASE_URL_PRODUCTION` (optional) | `BuildConfig.API_BASE_URL` per flavor |
| `SIGNING_KEYSTORE_BASE64`, `SIGNING_STORE_PASSWORD`, `SIGNING_KEY_ALIAS`, `SIGNING_KEY_PASSWORD` | Release signing (`prd` only) |
| `FIREBASE_SERVICE_ACCOUNT_JSON` | Firebase App Distribution upload (`prd` only) |

The build reads each key from an **environment variable** first (what `doppler run` injects), then
from `local.properties`, else it stays empty (`secret` in the root `build.gradle.kts`). With empty
Firebase keys the app still builds, but Firebase is not initialized.

### Local use

- Command line: `doppler setup` once (project `youknow`, config `dev`), then
  `doppler run -- ./gradlew assembleDevelopmentDebug`.
- Android Studio (does not inherit Doppler's environment):
  `scripts/doppler-sync-local-properties.sh` writes the keys into `local.properties`, leaving
  `sdk.dir` and anything else alone. Run it again when a secret changes.

### How Firebase config reaches the APK

- **Local build**, no `google-services.json`: `app/build.gradle.kts` turns the keys into the same
  resources the google-services plugin would generate (`google_app_id`, `default_web_client_id`,
  `firebase_database_url`, ...). `FirebaseInitProvider` reads them, so every Firebase product works.
  The resource `com.google.firebase.crashlytics.RequireBuildId=false` keeps Crashlytics running
  without its Gradle plugin.
- **CI release**: `scripts/write-google-services-json.sh` writes `app/google-services.json` from the
  same keys, so the google-services and Crashlytics plugins run. Crashlytics injects its build id and
  uploads the R8 mapping, so release crash reports come out deobfuscated. The file is gitignored and
  deleted from the runner at the end.

`doppler run -- scripts/write-google-services-json.sh` also works locally if you want the plugin
path (for example to test a minified release).

## Pipeline

| | |
|---|---|
| Workflow | `.github/workflows/release-apk.yml` (build in `.github/actions/build-release-apk`) |
| Trigger | tag `vX.Y.Z` / manual run with a version |
| Variant | `productionRelease`, `applicationId` `es.sebas1705.youknow` |
| Result | GitHub Release with `YouKnow-vX.Y.Z.apk`, its `.sha256` and generated notes; the same APK in Firebase App Distribution |
| `versionName` / `versionCode` | tag version / GitHub run number |

The composite action checks that Doppler has every secret before building and fails naming the
missing one.

## One-time setup

1. **Doppler**: project `youknow` with configs `dev` and `prd`, holding the secrets above. Create a
   read-only service token for `prd` and store it in GitHub as `DOPPLER_TOKEN`
   (Settings > Secrets and variables > Actions).
2. **Fixed release keystore**. Without it every runner would sign with a different key and testers
   could not update without uninstalling:

   ```powershell
   & "$env:USERPROFILE\.jdks\jdk-21.0.12.1+1\bin\keytool.exe" -genkeypair -v -keystore youknow-release.jks -alias youknow -keyalg RSA -keysize 2048 -validity 10000
   [Convert]::ToBase64String([IO.File]::ReadAllBytes("youknow-release.jks")) | doppler secrets set SIGNING_KEYSTORE_BASE64 -p youknow -c prd
   ```

   Keep a copy of the `.jks` **outside the repo** (`*.jks` is gitignored): a lost keystore cannot be
   regenerated. When piping passwords in PowerShell 5.1 a trailing CRLF is appended; check the
   stored length.
3. **Firebase App Distribution**: enable it in the `youknow-tfg` console and create a tester group
   with alias `testers` (or set the repo variable `FIREBASE_TESTER_GROUP`). Create a Google Cloud
   service account in that project with only the **Firebase App Distribution Admin** role and
   store its JSON key as `FIREBASE_SERVICE_ACCOUNT_JSON` in Doppler `prd`.
4. **Google Sign-In**: register the SHA-1 of the release keystore (and of each developer's debug
   key) on the Android app in the Firebase console, or Credential Manager rejects the sign-in.

## Secrets inside the APK

The Firebase keys and the web client id end up in the APK, exactly as they would with
`google-services.json`: they identify the project, they do not grant access by themselves. Access
is governed by Firebase Security Rules and API key restrictions, so keep those tight. Doppler
protects where secrets live, not what ends up inside the APK.
