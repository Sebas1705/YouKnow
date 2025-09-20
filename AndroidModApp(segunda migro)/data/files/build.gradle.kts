
plugins {
    alias(libs.plugins.buildlogic.data)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.data.files"
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