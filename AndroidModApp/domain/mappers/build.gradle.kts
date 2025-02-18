plugins {
    alias(libs.plugins.youknow.domain)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.youknow.domain.mappers"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.common)
    api(projects.domain.models)
    api(projects.data.datastore)
    api(projects.data.files)
    api(projects.data.firestore)
    api(projects.data.realtime)
    api(projects.data.retrofit)
    api(projects.data.room)
}