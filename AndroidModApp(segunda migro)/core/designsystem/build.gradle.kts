plugins {
    alias(libs.plugins.buildlogic.core)
}

android {
    buildFeatures {
        buildConfig = true
    }

    namespace = "es.sebas1705.core.designsystem"
}

dependencies {
    api(projects.core.common)
    api(projects.core.ui)

    api(projects.domain.models)
    api(projects.domain.providers)

    implementation(libs.coil.compose)
    implementation(libs.lottie.compose)
}