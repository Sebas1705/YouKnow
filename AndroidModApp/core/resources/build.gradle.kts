plugins {
    alias(libs.plugins.youknow.library)
}

android {
    buildFeatures {
        buildConfig = true
    }

    namespace = "es.iberext.youknow.core.resources"
}

dependencies {
}