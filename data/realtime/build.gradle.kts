plugins {
    alias(libs.plugins.buildlogic.data)
    alias(libs.plugins.buildlogic.firebase)
}

android {
    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
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