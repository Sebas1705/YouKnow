plugins {
    alias(libs.plugins.buildlogic.data)
    alias(libs.plugins.buildlogic.firebase)
    alias(libs.plugins.buildlogic.credential)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.data.authentication"
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
