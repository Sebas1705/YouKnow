plugins {
    alias(libs.plugins.buildlogic.domain)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.domain.usescases.quiz"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.common)
    api(projects.data.room)
    api(projects.domain.models)
    api(projects.domain.mappers)
}