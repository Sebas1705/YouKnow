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

class TestConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.dependencies.apply {
            implementation(project.libs.findLibrary("junit").get())
            implementation(project.libs.findLibrary("truth").get())
            implementation(project.libs.findLibrary("ui-test-manifest").get())
            implementation(project.libs.findLibrary("ui-test-junit4").get())
            implementation(project.libs.findLibrary("test-ext-junit").get())
            implementation(project.libs.findLibrary("test-core").get())
            implementation(project.libs.findLibrary("test-runner").get())
        }
    }
}