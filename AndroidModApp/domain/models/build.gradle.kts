import es.sebas1705.convention.implementation

plugins {
    alias(libs.plugins.youknow.domain)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.youknow.domain.models"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.common)

    implementation(libs.firebase.auth)
}
