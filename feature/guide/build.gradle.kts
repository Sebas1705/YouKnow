plugins {
    alias(libs.plugins.buildlogic.feature)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.feature.guide"
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
    api(projects.domain.usescases.fill)
    api(projects.domain.usescases.settings)
}