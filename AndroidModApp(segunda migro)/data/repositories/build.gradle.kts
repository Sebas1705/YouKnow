plugins {
    alias(libs.plugins.buildlogic.data)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.data.repositories"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.common)
    api(projects.data.analytics)
    api(projects.data.authentication)
    api(projects.data.couchbase)
    api(projects.data.datastore)
    api(projects.data.files)
    api(projects.data.firestore)
    api(projects.data.realtime)
    api(projects.data.retrofit)
    api(projects.data.room)
}