plugins {
    alias(libs.plugins.youknow.feature)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.youknow.feature.games"
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
    api(projects.domain.usescases.wordPass)
    api(projects.domain.usescases.quiz)
    api(projects.domain.usescases.families)
    api(projects.domain.usescases.mysteryNumber)
    api(projects.domain.usescases.user)
    api(projects.domain.usescases.auth)
    api(projects.domain.usescases.settings)

}