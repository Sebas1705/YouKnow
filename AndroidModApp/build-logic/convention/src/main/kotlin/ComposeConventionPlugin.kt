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

class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply{
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            dependencies {
                // Compose dependencies with libs implementation
                implementation(libs.findLibrary("compose-bom").get())
                implementation(libs.findLibrary("ui").get())
                implementation(libs.findLibrary("ui-graphics").get())
                implementation(libs.findLibrary("ui-tooling").get())
                implementation(libs.findLibrary("ui-tooling-preview").get())
                implementation(libs.findLibrary("ui-text-google-fonts").get())
                implementation(libs.findLibrary("navigation").get())
                implementation(libs.findLibrary("hilt-navigation").get())
                implementation(libs.findLibrary("activity").get())
                implementation(libs.findLibrary("fragment").get())
                implementation(libs.findLibrary("constraintlayout-compose").get())
                implementation(libs.findLibrary("material3").get())
                implementation(libs.findLibrary("material-icons-extended").get())
            }
        }
    }
}