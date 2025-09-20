buildscript {
    repositories {
        google()
        mavenCentral()
    }
}


// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    kotlin("jvm") version "2.2.0-RC3"

    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.firebase.gms) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.benmanes.versions) apply false
    alias(libs.plugins.dependency.analyze) apply false
}

allprojects {
    configurations.all {
        resolutionStrategy {
            force("com.google.protobuf:protobuf-javalite:3.19.4")
        }
    }
}