plugins {
    alias(libs.plugins.youknow.data)
    alias(libs.plugins.youknow.retrofit)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.youknow.data.retrofit"
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