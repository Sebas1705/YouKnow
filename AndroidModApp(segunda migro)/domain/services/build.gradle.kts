
plugins {
    alias(libs.plugins.buildlogic.domain)
    alias(libs.plugins.buildlogic.firebase)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.domain.services"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.common)
}