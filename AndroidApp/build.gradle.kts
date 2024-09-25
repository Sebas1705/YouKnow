// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {

    // Core:
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.ksp) apply false

    // Compose:
    alias(libs.plugins.compose.compiler) apply false

    // Hilt:
    alias(libs.plugins.hilt.gradle) apply false

    // Firebase:
    alias(libs.plugins.googleServices) apply false
    alias(libs.plugins.crashlytics) apply false
}