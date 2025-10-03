import es.sebas1705.convention.implementation

plugins {
    alias(libs.plugins.buildlogic.domain)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.domain.usescases.auth"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.common)

    api(projects.data.repositories)

    api(projects.domain.models)

    implementation(libs.firebase.auth)
}