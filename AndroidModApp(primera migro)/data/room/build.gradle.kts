import es.sebas1705.convention.implementation

plugins {
    alias(libs.plugins.youknow.data)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "es.sebas1705.youknow.data.room"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    defaultConfig {
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
}

dependencies {
    api(projects.core.common)
    api(projects.data.analytics)
    implementation(libs.retrofit.gson)

    api(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
}
