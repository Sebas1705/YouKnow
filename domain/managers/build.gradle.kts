plugins {
    alias(libs.plugins.buildlogic.domain)
    alias(libs.plugins.buildlogic.unit.test)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.domain.managers"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.resources)
}