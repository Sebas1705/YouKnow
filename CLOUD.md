# Cloud Context

Cloud and external service context for YouKnow.

## Services in Use

- Firebase (analytics, crashlytics, auth, firestore, realtime database, storage, messaging)
- Open Trivia DB via Retrofit (`data:retrofit`)
- Couchbase Lite for local embedded data (not cloud-hosted by default)

## Environment Notes

- No Firebase config file in the repo: the build reads it from Doppler (or `local.properties`).
- Secrets and Firebase config live in Doppler (project `youknow`); see `docs/DISTRIBUTION.md`.

## Security and Secrets

- Never commit secrets to Git.
- Keep local secret files out of source control.
- Treat API keys and service credentials as runtime environment concerns.

## Operational Workflows

- Release: `.github/workflows/release-apk.yml` (tag `vX.Y.Z` to GitHub Release + Firebase App Distribution)
- Dependency report: repository root `.github/workflows/dependency-report-monthly.yml`
- Security checks: repository root security workflows

## Risks and Controls

- Dependency drift: mitigated with monthly dependency report and Dependabot.
- Release-note quality drift: mitigated with changelog label gate.
- Debug tooling leakage into release: mitigated with variant-scoped dependencies.

