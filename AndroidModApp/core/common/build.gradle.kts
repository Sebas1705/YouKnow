plugins {
    alias(libs.plugins.youknow.hilt)
    alias(libs.plugins.youknow.library)
    alias(libs.plugins.youknow.compose)
    alias(libs.plugins.youknow.retrofit)
}

android {
    buildFeatures {
        buildConfig = true
    }

    namespace = "es.iberext.youknow.core.common"
}

dependencies {

    implementation(libs.play.services.tasks)

}