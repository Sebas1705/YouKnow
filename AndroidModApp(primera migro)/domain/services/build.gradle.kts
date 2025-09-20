import es.sebas1705.convention.implementation

plugins {
    alias(libs.plugins.youknow.domain)
    alias(libs.plugins.youknow.firebase)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.youknow.domain.services"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.common)
}