plugins {
    alias(libs.plugins.buildlogic.data)
    alias(libs.plugins.buildlogic.firebase)
    alias(libs.plugins.buildlogic.credential)
}

// Web OAuth client of the Firebase project (Google Sign-In's server client id), from Doppler or
// local.properties — see `secret` in the root build.gradle.kts.
@Suppress("UNCHECKED_CAST")
val secret = rootProject.extra["secret"] as (String, String) -> String

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.data.authentication"
    defaultConfig {
        buildConfigField("String", "GOOGLE_WEB_CLIENT_ID", "\"${secret("GOOGLE_WEB_CLIENT_ID", "")}\"")
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.common)
    api(projects.data.analytics)
}
