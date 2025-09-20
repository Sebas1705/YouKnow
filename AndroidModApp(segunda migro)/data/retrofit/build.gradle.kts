plugins {
    alias(libs.plugins.buildlogic.data)
    alias(libs.plugins.buildlogic.retrofit)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.data.retrofit"
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