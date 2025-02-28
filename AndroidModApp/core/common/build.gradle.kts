plugins {
    alias(libs.plugins.youknow.hilt)
    alias(libs.plugins.youknow.library)
    alias(libs.plugins.youknow.compose)
    alias(libs.plugins.youknow.retrofit)
    alias(libs.plugins.youknow.credential)
}

android {
    buildFeatures {
        buildConfig = true
    }

    namespace = "es.iberext.youknow.core.common"
}

dependencies {
    api(projects.core.resources)
}