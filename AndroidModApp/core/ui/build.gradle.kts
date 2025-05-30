plugins {
    alias(libs.plugins.youknow.hilt)
    alias(libs.plugins.youknow.library)
    alias(libs.plugins.youknow.compose)
}

android {
    buildFeatures {
        buildConfig = true
    }

    namespace = "es.iberext.youknow.core.ui"
}

dependencies {
    api(projects.core.common)
}