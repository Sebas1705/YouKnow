plugins {
    alias(libs.plugins.youknow.data)
    alias(libs.plugins.youknow.firebase)
    alias(libs.plugins.youknow.credential)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.youknow.data.authentication"
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
