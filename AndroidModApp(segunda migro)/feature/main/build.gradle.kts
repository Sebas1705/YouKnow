plugins {
    alias(libs.plugins.buildlogic.feature)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.feature.main"
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
    api(projects.domain.usescases.settings)
    api(projects.domain.usescases.analytics)

    api(projects.feature.guide)
    api(projects.feature.splash)
    api(projects.feature.settings)
    api(projects.feature.networkerror)
    api(projects.feature.survey)
    api(projects.feature.auth)
    api(projects.feature.home)
    api(projects.feature.game)
}
