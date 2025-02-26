plugins {
    alias(libs.plugins.youknow.domain)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.youknow.domain.usescases.trivia"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.common)
    api(projects.data.retrofit)
    api(projects.domain.models)
    api(projects.domain.mappers)
}