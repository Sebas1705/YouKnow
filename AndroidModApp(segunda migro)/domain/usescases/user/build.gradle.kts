plugins {
    alias(libs.plugins.buildlogic.domain)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.domain.usescases.user"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.common)
    api(projects.data.firestore)
    api(projects.data.realtime)
    api(projects.domain.models)
    api(projects.domain.mappers)

    implementation(libs.firebase.auth)
}