plugins {
    alias(libs.plugins.buildlogic.feature)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.feature.auth"
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

    api(projects.domain.usescases.auth)
    api(projects.domain.usescases.user)
    api(projects.domain.usescases.analytics)
}
