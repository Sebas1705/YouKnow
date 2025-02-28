/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "es.sebas1705.youknow.build_logic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
}

gradlePlugin {
    plugins {
        ///////////////MODULES////////////////
        //Register the plugin that applies common configuration for Android applications
        register("androidApplication") {
            id = "youknow.android.application"
            implementationClass = "AppConventionPlugin"
        }
        //Register the plugin that applies common configuration for Android feature modules
        register("androidFeature") {
            id = "youknow.android.feature"
            implementationClass = "FeatureConventionPlugin"
        }
        //Register the plugin that applies common configuration for Android core modules
        register("androidCore") {
            id = "youknow.android.core"
            implementationClass = "CoreConventionPlugin"
        }
        //Register the plugin that applies common configuration for Android data modules
        register("androidData") {
            id = "youknow.android.data"
            implementationClass = "DataConventionPlugin"
        }
        //Register the plugin that applies common configuration for Android domain modules
        register("androidDomain") {
            id = "youknow.android.domain"
            implementationClass = "DomainConventionPlugin"
        }

        //////////////LIBRARIES////////////////
        register("androidLibrary") {
            id = "youknow.android.library"
            implementationClass = "LibraryConventionPlugin"
        }
        register("androidHilt") {
            id = "youknow.android.hilt"
            implementationClass = "HiltConventionPlugin"
        }
        register("androidFirebase") {
            id = "youknow.android.firebase"
            implementationClass = "FirebaseConventionPlugin"
        }
        register("androidRetrofit") {
            id = "youknow.android.retrofit"
            implementationClass = "RetrofitConventionPlugin"
        }
        register("androidCompose") {
            id = "youknow.android.compose"
            implementationClass = "ComposeConventionPlugin"
        }
        register("androidCredential") {
            id = "youknow.android.credential"
            implementationClass = "CredentialConventionPlugin"
        }
        register("androidDatastore") {
            id = "youknow.android.datastore"
            implementationClass = "DatastoreConventionPlugin"
        }
        register("androidLifecycle") {
            id = "youknow.android.lifecycle"
            implementationClass = "LifecycleConventionPlugin"
        }
        register("androidTest") {
            id = "youknow.android.test"
            implementationClass = "TestConventionPlugin"
        }

        //////////////FLAVORS////////////////
        register("androidFlavors") {
            id = "youknow.android.flavors"
            implementationClass = "AndroidFlavorsConventionPlugin"
        }
    }
}