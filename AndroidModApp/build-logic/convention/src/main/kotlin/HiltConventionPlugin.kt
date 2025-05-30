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

import es.sebas1705.convention.implementation
import es.sebas1705.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")

            dependencies{
                add("ksp", libs.findLibrary("hilt-compiler").get())
            }

            //This ensures that the plugin is applied after the android base plugin
            pluginManager.withPlugin("com.android.base") {
                //plugin necessary to integrate Hilt in the Android project.
                pluginManager.apply("dagger.hilt.android.plugin")
                dependencies {
                    //Dependency that provides the core functionality of Hilt for DI.
                    implementation(libs.findLibrary("hilt-android").get())
                }
            }
        }
    }
}