import es.sebas1705.convention.implementation

plugins {
    alias(libs.plugins.youknow.data)
    alias(libs.plugins.youknow.room)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.youknow.data.room"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.common)
    api(projects.data.analytics)
    implementation(libs.retrofit.gson)
}
