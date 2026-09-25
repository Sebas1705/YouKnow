# Firebase

Project **`youknow-tfg`** (display name YouKnow). Everything that can be versioned lives in this
repo and is deployed with the Firebase CLI; secrets and ids live in Doppler (`docs/DISTRIBUTION.md`).

## Apps

One Android app per variant, so each reports its own crashes and analytics:

| Variant | applicationId | Firebase app id | Doppler key |
|---|---|---|---|
| productionRelease | `es.sebas1705.youknow` | `…:6700b706425121bba64701` | `FIREBASE_APP_ID` |
| productionDebug | `es.sebas1705.youknow.debug` | `…:36e1a66ecde24d4da64701` | `FIREBASE_APP_ID_PRODUCTION_DEBUG` |
| developmentRelease | `es.sebas1705.youknow.dev` | `…:1e16db34cb50c3b2a64701` | `FIREBASE_APP_ID_DEVELOPMENT` |
| developmentDebug | `es.sebas1705.youknow.dev.debug` | `…:9787518c51d60a30a64701` | `FIREBASE_APP_ID_DEVELOPMENT_DEBUG` |
| stagingRelease | `es.sebas1705.youknow.staging` | `…:ab9df8c915544da3a64701` | `FIREBASE_APP_ID_STAGING` |
| stagingDebug | `es.sebas1705.youknow.staging.debug` | `…:55681f6974ff0b24a64701` | `FIREBASE_APP_ID_STAGING_DEBUG` |

`app/build.gradle.kts` picks the id per variant (falling back to `FIREBASE_APP_ID`), and
`scripts/write-google-services-json.sh` writes one client per applicationId with its own id.

**SHA certificates** (needed for Google Sign-In): the release keystore on the production app
(plus the pre-2026 release key, for 1.0.0 installs); each developer's `~/.android/debug.keystore`
on the debug apps and on the dev/staging release apps (signed with the debug key locally):

```bash
npx firebase-tools apps:android:sha:create <appId> <sha1-or-sha256> --project youknow-tfg
```

## Security rules

| Service | File | Model |
|---|---|---|
| Firestore | `firebase/firestore.rules` | `users/{uid}` read by signed-in users, written only by the owner (schema validated, no negative credits); `news` read-only; `surveys/{uid}` one per author |
| Realtime Database | `firebase/database.rules.json` | `chat-global-youknow`: messages keyed `<uid>-<time>`, created only by their author (text ≤ 50 chars), deletable by any signed-in client (the app trims to 100); `groups-youknow`: created/deleted by the leader (`<name>-<leaderUid>`), members list rewritten by joiners |
| Storage | `firebase/storage.rules` | closed: the app stores no files |

Everything else is denied, and nothing is readable without signing in.

### Test and deploy

The rules are tested against the emulators (Java 21 on the PATH):

```bash
cd firebase/rules-test
npm install
npm test
```

Deploy after the tests pass:

```bash
npx firebase-tools deploy --only firestore:rules,database,storage --project youknow-tfg
```

Indexes (`firebase/firestore.indexes.json`) are deployed with `--only firestore:indexes`; the app
only runs single-field queries, which need no composite index.

## Other services

- **App Distribution**: group `testers`; the release pipeline uploads there.
- **Hosting** (`youknow-tfg.web.app`), **Remote Config**, **Cloud Functions**: unused.
