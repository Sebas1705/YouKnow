plugins {
    alias(libs.plugins.buildlogic.data)
    alias(libs.plugins.buildlogic.firebase)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.data.realtime"
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