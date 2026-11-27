# Firebase

Project **`youknow-tfg`** (display name YouKnow). Everything that can be versioned lives in this
repo and is deployed with the Firebase CLI; secrets and ids live in Doppler (`docs/DISTRIBUTION.md`).

## Apps

One Android app per variant, so each reports its own crashes and analytics:

| Variant | applicationId | Firebase app id | Doppler key |
|---|---|---|---|
| productionRelease | `es.sebas1705.youknow` | `…:c7f9dca343eebda5a64701` | `FIREBASE_APP_ID` |
| productionDebug | `es.sebas1705.youknow.debug` | `…:eecebcdfdb57be54a64701` | `FIREBASE_APP_ID_PRODUCTION_DEBUG` |
| developmentRelease | `es.sebas1705.youknow.dev` | `…:6a60144c788d2edfa64701` | `FIREBASE_APP_ID_DEVELOPMENT` |
| developmentDebug | `es.sebas1705.youknow.dev.debug` | `…:0fb0838124c4d91ea64701` | `FIREBASE_APP_ID_DEVELOPMENT_DEBUG` |
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

After adding or removing an app or a SHA, run `scripts/refresh-google-services.sh` so the
`google-services.json` stored in Doppler (used locally and in CI) picks it up.

## Security rules

| Service | File | Model |
|---|---|---|
| Firestore | `firebase/firestore.rules` | `users/{uid}` read by signed-in users, so it holds nothing private (the email stays in Firebase Auth); written only by the owner, every field type checked, created at 0 points and 1000 credits, points and credits changed only through a round event (see below); `news` read-only; `surveys/{uid}` one per author; `dailyChallenges/{date}` created once by a verified account for the current UTC day (±1 for clock skew), full shape checked, at most 5 questions of 2 to 4 answers, immutable |
| Realtime Database | `firebase/database.rules.json` | `chat-global-youknow`: messages keyed `<uid>-<time>`, created and deleted only by their author (text ≤ 50 chars); `groups-youknow`: created/deleted by the leader (`<name>-<leaderUid>`), `members` rewritten by the leader, or by a member joining (appended) or leaving (only their uid removed), at most 50 |
| Storage | `firebase/storage.rules` | closed: the app stores no files |

Everything else is denied, and nothing is readable without signing in.

### Points, credits and the daily challenge

There is no server to compute scores (no Cloud Functions), so the rules bound what a client may write
to `users/{uid}`:

| Rule | Where |
|---|---|
| A profile is created with 0 points and 1000 credits | `users/{uid}` create |
| Points never decrease; credits only decrease on their own (the app spends 2000 on a group) | `users/{uid}` update |
| Points or credits go up only in the same batch as an event `users/{uid}/events/{total}`, `{total}` being the points after the round | `users/{uid}` update, `events` create |
| The event states what was earned (`kind`, `points`, `credits`, `at` = server time) and the profile gains exactly that; events can't be edited or deleted, so each total is reached once | `events` |
| At most 10000 points per round; credits at most a tenth of the points; totals under 10 000 000 points and 1 000 000 credits | `events` |
| Rounds at least 10 seconds apart (the previous event, `events/{points before}`, must be older) | `users/{uid}` update |
| A daily round (`kind: daily`) is at most 100 points, for today or yesterday (UTC), for a published `dailyChallenges/{date}`, and creates `users/{uid}/dailyCompletions/{date}` in the same batch; that document is created once, so a day pays once | `events`, `dailyCompletions` |

The app writes all of it with one batch per round, `FirestoreRepository.addRoundRewards`. Old app
versions update points directly and are refused once these rules are deployed.

**What the rules cannot prevent.** They can't tell a round that was played from one that was made up:
a modified client can still claim up to 10000 points every 10 seconds (and the daily bonus once a day),
create as many accounts as it likes and start each at 1000 credits, or read the whole ranking. The
caps make cheating slow and visible (every gain has a timestamped event the owner can audit), not
impossible. Real authority needs the scoring to run on a server (a Cloud Function or Cloud Run
service that receives the answers and writes the score) and the rules then closed to client writes.
Events stay in `users/{uid}/events` when a profile is deleted (a client can't delete them, that is
what stops replays): they hold points, credits and a time, nothing personal, and can be removed with
the Admin SDK.

**Chat retention.** The app used to trim the chat by letting every client delete the oldest message.
Now only an author can delete a message, so that trim only works for the author's own messages and the
chat can grow past 100. A scheduled clean-up (console or Admin SDK) is the way to bound it.

**Groups.** The members list is rewritten whole, so the rules compare old and new by index, up to 50
(`ComposableConstants.MAX_GROUP`); they can't loop. The leader may rewrite it freely. Kicking a member
also updates that member's own `users/{uid}.groupId`, which only they can write, so kicks from the
app already failed before these rules.

### Test and deploy

The rules are tested against the emulators (Java 21 on the PATH):

```bash
cd firebase/rules-test
npm install
npm test
```

**CI** (`.github/workflows/firebase.yml`): every pull request touching `firebase/` runs these
tests; a push to `main` runs them again and, if green, deploys the rules. The deploy uses its own
service account, separate from the App Distribution one and limited to rules:

| | |
|---|---|
| Doppler secret (`prd`) | `FIREBASE_DEPLOY_SERVICE_ACCOUNT_JSON` |
| Roles on `youknow-tfg` | Firebase Rules Admin (Firestore + Storage rules), Firebase Realtime Database Admin (RTDB rules), Service Usage Consumer |

There are two configs, because the two tools want different shapes of `storage`:

| File | Used by | `storage` |
|---|---|---|
| `firebase.json` | `firebase deploy` | array naming the bucket, `youknow-tfg.firebasestorage.app` |
| `firebase.emulators.json` | the rules tests (`npm test`) | plain `{ "rules": ... }`, plus the emulator ports |

With the bucket named the CLI skips its default-bucket lookup (`firebasestorage.defaultBucket.get`),
which failed twice: with a 403 for the deploy account, and, once that was granted, with "Firebase
Storage has not been set up" because the lookup finds no registered default bucket. The Storage
emulator, in turn, can't load the array form (it asks for a `target`, and on a demo project it then
ignores the rules and runs open ones, so the tests would pass while testing nothing). Keep both files
in step: a rules file added to one belongs in the other. The bucket name also lives in Doppler
(`FIREBASE_STORAGE_BUCKET`), where the app reads it.

By hand, when needed:

```bash
npx firebase-tools deploy --only firestore:rules,database,storage --project youknow-tfg
```

Rules are limited to 1000 evaluated expressions per request and deny anything over it, so a size
limit in `firestore.rules` can't grow freely: the rules tests write the largest allowed round.

### Removing emails from existing profiles

Profiles written before 1.5.0 may still hold an `email` that every signed-in user can read. **The owner has to run this by hand; no CI job or deploy does.** The rules
stop new ones and let old ones stay unchanged, so that those documents can still be updated. To
remove them, run this **after** the rules are deployed and the new app is out: older versions show an
empty email and can't send the password reset once it's gone.

```bash
cd firebase/rules-test
npm ci
gcloud auth application-default login
node migrations/remove-user-emails.mjs --project youknow-tfg           # dry run
node migrations/remove-user-emails.mjs --project youknow-tfg --apply
```

The dry run is the default and writes nothing: it says how many profiles have an email and that nothing changed. Only `--apply` removes them. Both also list documents the app can't use, which are left for you to
review in the console: `users/` ids are uids, so keep that output private. Running it again is safe.

Indexes (`firebase/firestore.indexes.json`) are deployed with `--only firestore:indexes`; the app
only runs single-field queries, which need no composite index.

## Other services

- **Authentication**: email/password and Google (web client `875884945428-k0hdf…`); authorized domains are the defaults.
- **App Distribution**: group `testers`; the release pipeline uploads there.
- **Hosting** (`youknow-tfg.web.app`), **Remote Config**, **Cloud Functions**: unused.
