import es.sebas1705.convention.implementation

plugins {
    alias(libs.plugins.youknow.hilt)
    alias(libs.plugins.youknow.library)
    alias(libs.plugins.youknow.compose)
}

android {
    buildFeatures {
        buildConfig = true
    }

    namespace = "es.iberext.youknow.core.designsystem"
}

dependencies {
    api(projects.core.common)
    api(projects.core.ui)

    api(projects.domain.models)

    implementation(libs.coil.compose)
    implementation(libs.lottie.compose)
}