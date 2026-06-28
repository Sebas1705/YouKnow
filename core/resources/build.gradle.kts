plugins {
    alias(libs.plugins.buildlogic.library)
    alias(libs.plugins.buildlogic.compose)
    alias(libs.plugins.buildlogic.material)
}

android {
    buildFeatures {
        buildConfig = true
    }

    namespace = "es.sebas1705.core.resources"
}

dependencies {
}