import es.sebas1705.convention.implementation

plugins {
    alias(libs.plugins.youknow.domain)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.youknow.domain.usescases.auth"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.common)
    api(projects.data.authentication)
    api(projects.domain.models)
    api(projects.domain.mappers)

    implementation(libs.firebase.auth)
}