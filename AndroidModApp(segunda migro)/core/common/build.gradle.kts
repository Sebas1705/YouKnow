plugins {
    alias(libs.plugins.buildlogic.core)
    alias(libs.plugins.buildlogic.retrofit)
    alias(libs.plugins.buildlogic.credential)
    alias(libs.plugins.buildlogic.unit.test)
}

android {
    buildFeatures {
        buildConfig = true
    }

    namespace = "es.sebas1705.common"
}

dependencies {
    api(projects.core.resources)
    api(libs.material3.windowsizeclass)
}