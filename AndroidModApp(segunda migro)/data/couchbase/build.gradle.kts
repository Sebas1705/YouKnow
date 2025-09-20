plugins {
    alias(libs.plugins.buildlogic.data)
    alias(libs.plugins.buildlogic.couchbase)
    alias(libs.plugins.buildlogic.unit.test)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.data.couchbase"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.common)
    api(projects.data.analytics)
}