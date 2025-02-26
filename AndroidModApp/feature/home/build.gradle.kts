import es.sebas1705.convention.implementation

plugins {
    alias(libs.plugins.youknow.feature)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.youknow.feature.home"
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
    api(projects.domain.usescases.auth)
    api(projects.domain.usescases.chat)
    api(projects.domain.usescases.groups)
    api(projects.domain.usescases.user)
    api(projects.domain.usescases.fill)
    api(projects.domain.usescases.news)

    implementation(libs.coil.compose)
}