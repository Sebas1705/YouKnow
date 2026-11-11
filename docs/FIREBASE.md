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
| Firestore | `firebase/firestore.rules` | `users/{uid}` read by signed-in users, so it holds nothing private (the email stays in Firebase Auth); written only by the owner, every field type checked; `news` read-only; `surveys/{uid}` one per author; `dailyChallenges/{date}` created once by a verified account for the current UTC day (±1 for clock skew), full shape checked, at most 5 questions of 2 to 4 answers, immutable |
| Realtime Database | `firebase/database.rules.json` | `chat-global-youknow`: messages keyed `<uid>-<time>`, created only by their author (text ≤ 50 chars), deletable by any signed-in client (the app trims to 100); `groups-youknow`: created/deleted by the leader (`<name>-<leaderUid>`), members list rewritten by joiners |
| Storage | `firebase/storage.rules` | `profile_photos/{uid}`: read by signed-in users, written (images under 5 MB) and deleted only by the owner; everything else closed |

Everything else is denied, and nothing is readable without signing in.

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

Profiles written before 1.5.0 still hold an `email` that every signed-in user can read. The rules
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

The dry run writes nothing. Both also list documents the app can't use, which are left for you to
review in the console: `users/` ids are uids, so keep that output private. Running it again is safe.

Indexes (`firebase/firestore.indexes.json`) are deployed with `--only firestore:indexes`; the app
only runs single-field queries, which need no composite index.

## Account deletion

Settings → **Delete account** deletes the signed-in user's account and what the app stores about them
(`DeleteCurrentUser`, `docs/ai/FLOW_CATALOG.md` Flow 4). The user confirms, then re-authenticates
(password, or the Google account picker; Firebase refuses to delete a stale sign-in), then the steps
run in this order and stop at the first failure (every one tolerates being repeated, so retrying is
safe):

| # | Step | Needs from the rules |
|---|---|---|
| 1 | Leave the group in `groups-youknow/<name>-<leaderUid>`: remove the uid from `members`; delete the group only when no member is left | `members` is writable by any signed-in user; the group is deletable by its leader or once it has no members |
| 2 | Delete the user's chat messages in `chat-global-youknow` (key range query on the `<uid>-` prefix, one multi-path update) | any signed-in client may delete a message |
| 3 | Delete `profile_photos/<uid>` in Storage (a user with no photo is not an error) | owner may delete |
| 4 | Delete the usability survey cached on the device (Room `survey_table`) | none |
| 5 | Delete `users/<uid>` in Firestore | owner may delete |
| 6 | Delete the Firebase Authentication user | none (the rules need the session in 1 to 5, so it goes last) |

The group id is read from `users/<uid>` (step 1), which is why that document goes after it. If step 6
fails after 1 to 5 (offline, or Firebase still wants a recent sign-in), the data is gone and the
account is not: the dialog says so and confirming again re-authenticates and finishes.

What is **kept**, and why (also stated in `docs/account-deletion.html`):

- **`surveys/<uid>`** (nickname, age, profession, ratings): the rules say `allow delete: if false`, so
  a client cannot delete it. Removing it needs a console/admin action, on request. Allowing the owner
  to delete it is a one-line rules change (`allow delete: if isOwner(uid)`) plus a delete call in
  `DeleteCurrentUser`.
- **A leader's uid in the group id**: if the leader deletes the account while others remain, the group
  stays (a group is never dissolved under its members) and its key keeps the uid, a random Firebase
  id, until the last member leaves and the empty group is deleted.
- **Analytics and Crashlytics** data, kept under Google's retention; and the device preferences
  (volume, contrast, language) and downloaded questions, which are not part of the account.

Rollout: deploy the rules before the app release as usual. This feature adds no rule changes: it only
uses deletes the current rules already allow. It does depend on the owner being able to delete
`users/<uid>`; if that rule changes, step 5 must keep working.

Without the app, deletion is requested by email (contact in `SECURITY.md`), as written on
`docs/account-deletion.html`. That page is plain static HTML in `docs/`; it becomes public when
GitHub Pages is enabled for the repository (source: branch `main`, folder `/docs`), at
`https://sebas1705.github.io/YouKnow/account-deletion.html`. Pages is not enabled today, and
`firebase.json` hosting is unused, so until then the page is only readable in the repository.

## Other services

- **Authentication**: email/password and Google (web client `875884945428-k0hdf…`); authorized domains are the defaults.
- **App Distribution**: group `testers`; the release pipeline uploads there.
- **Hosting** (`youknow-tfg.web.app`), **Remote Config**, **Cloud Functions**: unused.
