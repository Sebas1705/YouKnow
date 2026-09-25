# Release Policy

YouKnow ships as an APK, versioned with semantic versioning and distributed from tags.

## Cadence

- Weekly: triage issues and pull requests.
- Biweekly: dependency updates and plugin upgrades.
- Monthly: generate dependency update report with `dependencyUpdates`.
- Scheduled workflow: repository root `.github/workflows/dependency-report-monthly.yml`.

## Branching

- `main`: stable branch for releases.
- Feature work should use short-lived branches and pull requests.

## Versioning

Tags are `vX.Y.Z` (`vX.Y.Z-suffix` for a pre-release, e.g. `v1.2.0-beta.1`).

- `MAJOR`: breaking changes for users (data reset, removed features).
- `MINOR`: new features.
- `PATCH`: fixes and dependency refreshes.

`versionName` is the tag's version; `versionCode` is the GitHub run number, so every distributed
build installs over the previous one.

## Release Automation

- Workflow: `.github/workflows/release-apk.yml` (build: `.github/actions/build-release-apk`).
- Trigger: pushing a `vX.Y.Z` tag, or *Run workflow* with a version (creates the tag on that commit).
- Result: signed `Production` release APK attached to a GitHub Release (with its `.sha256` and
  generated notes) and uploaded to Firebase App Distribution.
- Label gate workflow: `.github/workflows/changelog-label-gate.yml`; release notes categories:
  `.github/release.yml`.
- Secrets: all in Doppler; see `docs/DISTRIBUTION.md`.

```bash
git tag v1.2.0
git push origin v1.2.0
```

## Release Checklist

1. Validation workflows green (`build + lint`, `detekt`, `unit tests + coverage`, `security`, `changelog label gate`).
2. Pull requests since the previous release have correct changelog labels (`bug`, `enhancement`, `documentation`, `ci`, `security`, `breaking-change`, or `skip-changelog`).
3. `CHANGELOG.md` updated for the version.
4. Tag pushed after merge to `main`.
5. Dependency update report reviewed (`./gradlew dependencyUpdates --no-parallel`).
6. AI context docs reviewed when release includes architectural or operational changes (`AGENTS.md`, `CLOUD.md`, `ARCHITECTURE.md`, `MODULE_MAP.md`, `AI_INDEX.md`).
