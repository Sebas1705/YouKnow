#!/usr/bin/env bash
# Writes app/google-services.json from the Firebase keys in Doppler, so the JSON never lives in the
# repo. With the file present the build applies the google-services and Crashlytics Gradle plugins
# (see build-logic AppConventionPlugin): Crashlytics then injects its build id and uploads the R8
# mapping of release builds, so crashes are readable.
#
# Usage:  doppler run -- scripts/write-google-services-json.sh
#         (CI calls it the same way; see .github/actions/build-release-apk)
#
# Every flavor/build-type applicationId gets a client entry pointing at the same Firebase app, so any
# variant builds. Without this file the build falls back to the same keys as resValue resources
# (app/build.gradle.kts), which is enough for a local debug build.
set -euo pipefail

cd "$(dirname "$0")/.."

missing=0
for name in FIREBASE_APP_ID FIREBASE_API_KEY FIREBASE_PROJECT_ID FIREBASE_SENDER_ID \
            FIREBASE_STORAGE_BUCKET FIREBASE_DATABASE_URL GOOGLE_WEB_CLIENT_ID; do
  if [ -z "${!name:-}" ]; then echo "missing $name (run through: doppler run -- $0)" >&2; missing=1; fi
done
[ "$missing" -eq 0 ] || exit 1

BASE_ID="es.sebas1705.youknow"
PACKAGES=(
  "$BASE_ID" "$BASE_ID.debug"
  "$BASE_ID.dev" "$BASE_ID.dev.debug"
  "$BASE_ID.staging" "$BASE_ID.staging.debug"
)

clients=""
for pkg in "${PACKAGES[@]}"; do
  [ -z "$clients" ] || clients+=","
  clients+=$(cat <<EOF

    {
      "client_info": {
        "mobilesdk_app_id": "$FIREBASE_APP_ID",
        "android_client_info": { "package_name": "$pkg" }
      },
      "oauth_client": [ { "client_id": "$GOOGLE_WEB_CLIENT_ID", "client_type": 3 } ],
      "api_key": [ { "current_key": "$FIREBASE_API_KEY" } ],
      "services": {
        "appinvite_service": {
          "other_platform_oauth_client": [ { "client_id": "$GOOGLE_WEB_CLIENT_ID", "client_type": 3 } ]
        }
      }
    }
EOF
)
done

umask 077
cat > app/google-services.json <<EOF
{
  "project_info": {
    "project_number": "$FIREBASE_SENDER_ID",
    "firebase_url": "$FIREBASE_DATABASE_URL",
    "project_id": "$FIREBASE_PROJECT_ID",
    "storage_bucket": "$FIREBASE_STORAGE_BUCKET"
  },
  "client": [$clients
  ],
  "configuration_version": "1"
}
EOF
echo "app/google-services.json written for ${#PACKAGES[@]} application ids (project $FIREBASE_PROJECT_ID)."
