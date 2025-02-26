plugins {
    alias(libs.plugins.youknow.application)
    alias(libs.plugins.youknow.firebase)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "es.sebas1705.youknow"

    defaultConfig {
        applicationId = "es.sebas1705.youknow"
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            applicationIdSuffix = ".debug"
        }
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }


    configurations.all {
        resolutionStrategy {
            force("org.jetbrains:annotations:23.0.0")
        }
    }

}

dependencies {
    api(projects.core.resources)

    api(projects.domain.services)

    api(projects.feature.main)
}