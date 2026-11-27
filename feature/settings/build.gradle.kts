plugins {
    alias(libs.plugins.buildlogic.feature)
    alias(libs.plugins.buildlogic.unit.test)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.feature.settings"
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
    api(projects.domain.usescases.settings)
    api(projects.domain.usescases.auth)
    api(projects.domain.usescases.user)
}