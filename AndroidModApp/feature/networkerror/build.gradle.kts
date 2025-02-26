plugins {
    alias(libs.plugins.youknow.feature)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.youknow.feature.networkerror"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.common)
    api(projects.core.ui)
    api(projects.core.designsystem)

    api(projects.domain.usescases.analytics)
}