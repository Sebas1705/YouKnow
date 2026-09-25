#!/usr/bin/env bash
# Downloads the official google-services.json of youknow-tfg with the Firebase CLI and stores it in
# Doppler as GOOGLE_SERVICES_JSON (configs dev, stg and prd). Run it again whenever an Android app
# or a SHA certificate changes in Firebase: the file carries one client per app, and the Android
# OAuth clients Firebase derives from the registered SHAs (what Google Sign-In checks).
#
# Usage:  scripts/refresh-google-services.sh [appId]
#         (needs `npx firebase-tools login` with access to youknow-tfg and the Doppler CLI)
#
# Builds then get the file from Doppler: CI through .github/actions/build-release-apk, locally
# through scripts/doppler-sync-local-properties.sh or scripts/write-google-services-json.sh.
set -euo pipefail

PROJECT_ID="youknow-tfg"
# Any Android app of the project returns the whole project config; default to production.
APP_ID="${1:-$(doppler secrets get FIREBASE_APP_ID --plain -p youknow -c prd)}"
APP_ID="${APP_ID%$'\r'}"

cd "$(dirname "$0")/.."
command -v doppler >/dev/null || { echo "Doppler CLI missing: winget install doppler.doppler" >&2; exit 1; }

tmp="$(mktemp -d)"
trap 'rm -rf "$tmp"' EXIT

npx --yes firebase-tools apps:sdkconfig ANDROID "$APP_ID" --project "$PROJECT_ID" --out "$tmp/google-services.json" >/dev/null

# Refuse to store anything that is not a google-services.json of this project.
node -e '
  const j = require(process.argv[1]);
  if (j.project_info?.project_id !== process.argv[2] || !Array.isArray(j.client) || j.client.length === 0) {
    console.error("unexpected sdkconfig output"); process.exit(1);
  }
  for (const c of j.client) console.log("  " + c.client_info.android_client_info.package_name);
' "$tmp/google-services.json" "$PROJECT_ID"

for config in dev stg prd; do
  doppler secrets set GOOGLE_SERVICES_JSON -p youknow -c "$config" --silent < "$tmp/google-services.json"
done
echo "GOOGLE_SERVICES_JSON updated in Doppler youknow (dev, stg, prd)."
