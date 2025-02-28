import es.sebas1705.convention.implementation

plugins {
    alias(libs.plugins.youknow.data)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.youknow.data.files"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.common)
    api(projects.data.analytics)

    implementation(libs.kotlin.serialization.json)
}