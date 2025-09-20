plugins {
    alias(libs.plugins.buildlogic.core)
}

android {
    buildFeatures {
        buildConfig = true
    }

    namespace = "es.sebas1705.core.ui"
}

dependencies {
    api(projects.core.common)
}