#!/usr/bin/env bash
# Writes app/google-services.json from Doppler (the official file in GOOGLE_SERVICES_JSON, or one
# built from the individual Firebase keys), so the JSON never lives in the
# repo. With the file present the build applies the google-services and Crashlytics Gradle plugins
# (see build-logic AppConventionPlugin): Crashlytics then injects its build id and uploads the R8
# mapping of release builds, so crashes are readable.
#
# Usage:  doppler run -- scripts/write-google-services-json.sh
#         (CI calls it the same way; see .github/actions/build-release-apk)
#
# Every flavor/build-type applicationId gets a client entry with its own Firebase app, so any
# variant builds. Without this file the build falls back to the same keys as resValue resources
# (app/build.gradle.kts), which is enough for a local debug build.
set -euo pipefail

cd "$(dirname "$0")/.."

# Preferred: the official file from Firebase, stored in Doppler by scripts/refresh-google-services.sh
# (it also carries the Android OAuth clients derived from the registered SHAs).
if [ -n "${GOOGLE_SERVICES_JSON:-}" ]; then
  umask 077
  printf '%s' "$GOOGLE_SERVICES_JSON" > app/google-services.json
  echo "app/google-services.json written from Doppler's GOOGLE_SERVICES_JSON."
  exit 0
fi

# Fallback: build an equivalent file from the individual keys.

missing=0
for name in FIREBASE_APP_ID FIREBASE_API_KEY FIREBASE_PROJECT_ID FIREBASE_SENDER_ID \
            FIREBASE_STORAGE_BUCKET FIREBASE_DATABASE_URL GOOGLE_WEB_CLIENT_ID; do
  if [ -z "${!name:-}" ]; then echo "missing $name (run through: doppler run -- $0)" >&2; missing=1; fi
done
[ "$missing" -eq 0 ] || exit 1

BASE_ID="es.sebas1705.youknow"
# applicationId -> Doppler key holding its Firebase app id (each variant has its own app; any key
# missing falls back to FIREBASE_APP_ID, the production app).
PACKAGES=(
  "$BASE_ID:FIREBASE_APP_ID"
  "$BASE_ID.debug:FIREBASE_APP_ID_PRODUCTION_DEBUG"
  "$BASE_ID.dev:FIREBASE_APP_ID_DEVELOPMENT"
  "$BASE_ID.dev.debug:FIREBASE_APP_ID_DEVELOPMENT_DEBUG"
  "$BASE_ID.staging:FIREBASE_APP_ID_STAGING"
  "$BASE_ID.staging.debug:FIREBASE_APP_ID_STAGING_DEBUG"
)

clients=""
for entry in "${PACKAGES[@]}"; do
  pkg="${entry%%:*}"; key="${entry#*:}"; app_id="${!key:-$FIREBASE_APP_ID}"
  [ -z "$clients" ] || clients+=","
  clients+=$(cat <<EOF

    {
      "client_info": {
        "mobilesdk_app_id": "$app_id",
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
