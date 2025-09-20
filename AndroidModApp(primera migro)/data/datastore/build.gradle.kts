plugins {
    alias(libs.plugins.youknow.data)
    alias(libs.plugins.youknow.datastore)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.youknow.data.datastore"
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