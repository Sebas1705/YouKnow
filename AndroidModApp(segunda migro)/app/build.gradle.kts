import java.io.FileInputStream
import java.io.FileNotFoundException
import java.util.Properties

plugins {
    alias(libs.plugins.buildlogic.application)
    alias(libs.plugins.buildlogic.firebase)
    alias(libs.plugins.kotlin.android)
}

val secretsPropertiesFile = rootProject.file("./app/secrets.properties")
val secretProperties = Properties()
if (secretsPropertiesFile.exists())
    secretProperties.load(FileInputStream(secretsPropertiesFile))
else throw FileNotFoundException("Secrets file not found. Please create a secrets.properties file in the app directory.")

android {
    namespace = "es.sebas1705.youknow"

    defaultConfig {
        applicationId = "es.sebas1705.youknow"
        versionCode = 1
        versionName = "0.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file("keystore.jks")
            storePassword = secretProperties["signing_keystore_password"] as String?
            keyAlias = secretProperties["signing_key_alias_release"] as String?
            keyPassword = secretProperties["signing_keystore_password"] as String?
        }
        getByName("debug") {
            storeFile = file("keystore.jks")
            storePassword = secretProperties["signing_keystore_password"] as String?
            keyAlias = secretProperties["signing_key_alias_debug"] as String?
            keyPassword = secretProperties["signing_keystore_password"] as String?
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            signingConfig = signingConfigs.getByName("debug")
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

    applicationVariants.all {
        val variant = name
        val vCode = versionCode
        val vName = versionName
        outputs.all {
            val projectName = project.name
            val outputImpl = this as com.android.build.gradle.internal.api.BaseVariantOutputImpl
            outputImpl.outputFileName = "$projectName-$variant(c.$vCode)-$vName.apk"
        }
    }

    packaging {
        resources {
            resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

}

dependencies {
    api(projects.core.resources)
    api(projects.domain.services)
    api(projects.feature.main)
}