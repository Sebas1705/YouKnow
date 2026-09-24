plugins {
    alias(libs.plugins.buildlogic.application)
    alias(libs.plugins.buildlogic.firebase)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

// CI (.github/actions/build-release-apk) injects these; locally they do not exist and the build
// behaves as always (versionCode 1, release signed with the debug key).
//  - VERSION_CODE: the GitHub run number, so every distributed build installs over the last one.
//  - VERSION_NAME: the tag's version (vX.Y.Z -> X.Y.Z).
//  - SIGNING_*: the FIXED release keystore, decoded from Doppler. Signing CI builds with a debug key
//    would give every runner a different key and testers could not update without uninstalling.
val ciVersionCode: Int? = System.getenv("VERSION_CODE")?.toIntOrNull()
val ciVersionName: String? = System.getenv("VERSION_NAME")?.takeIf { it.isNotBlank() }
val ciKeystorePath: String? = System.getenv("SIGNING_KEYSTORE_PATH")?.takeIf { it.isNotBlank() }

// Doppler, then local.properties — see `secret` in the root build.gradle.kts.
@Suppress("UNCHECKED_CAST")
val secret = rootProject.extra["secret"] as (String, String) -> String

android {
    namespace = "es.sebas1705.youknow"

    defaultConfig {
        applicationId = "es.sebas1705.youknow"
        versionCode = ciVersionCode ?: 1
        versionName = ciVersionName ?: "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        // Firebase config, always from Doppler. Two ways it reaches the APK:
        //  - app/google-services.json present (CI writes it with scripts/write-google-services-json.sh):
        //    the google-services + Crashlytics plugins generate these resources, and Crashlytics
        //    uploads the R8 mapping of release builds.
        //  - no JSON (usual local build): the same values become resValue resources here, which is
        //    what FirebaseInitProvider reads, so Auth, Firestore, Realtime Database, Storage,
        //    Messaging and Analytics work the same. Empty values = Firebase not initialized.
        if (!file("google-services.json").exists()) {
            resValue("string", "google_app_id", secret("FIREBASE_APP_ID", ""))
            resValue("string", "google_api_key", secret("FIREBASE_API_KEY", ""))
            resValue("string", "google_crash_reporting_api_key", secret("FIREBASE_API_KEY", ""))
            resValue("string", "project_id", secret("FIREBASE_PROJECT_ID", ""))
            resValue("string", "gcm_defaultSenderId", secret("FIREBASE_SENDER_ID", ""))
            resValue("string", "google_storage_bucket", secret("FIREBASE_STORAGE_BUCKET", ""))
            resValue("string", "firebase_database_url", secret("FIREBASE_DATABASE_URL", ""))
            resValue("string", "default_web_client_id", secret("GOOGLE_WEB_CLIENT_ID", ""))
        }
    }

    signingConfigs {
        if (ciKeystorePath != null) {
            create("ci") {
                storeFile = file(ciKeystorePath)
                storePassword = System.getenv("SIGNING_STORE_PASSWORD")
                keyAlias = System.getenv("SIGNING_KEY_ALIAS")
                keyPassword = System.getenv("SIGNING_KEY_PASSWORD")
            }
        }
    }

    buildTypes {
        release {
            // Without the CI keystore (local build) release is signed with the debug key, so
            // `assembleProductionRelease` works on any machine without configuring anything.
            signingConfig = signingConfigs.getByName(if (ciKeystorePath != null) "ci" else "debug")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            applicationIdSuffix = ".debug"
        }
    }

    buildFeatures {
        buildConfig = true
        compose = true
        // Resources generated with resValue are off by default since AGP 8.
        resValues = true
    }

    configurations.all {
        resolutionStrategy {
            force("org.jetbrains:annotations:23.0.0")
        }
    }

    packaging {
        resources {
            resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            resources.excludes.add("META-INF/versions/9/OSGI-INF/MANIFEST.MF")
        }
    }
}

dependencies {
    api(projects.core.resources)
    api(projects.domain.services)
    api(projects.feature.main)
    implementation(projects.domain.managers)

    debugImplementation(libs.leakcanary.android)
}
