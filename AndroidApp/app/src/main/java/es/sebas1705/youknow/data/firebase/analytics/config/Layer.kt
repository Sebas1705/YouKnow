package es.sebas1705.youknow.data.firebase.analytics.config
/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

/**
 * Sealed class that represents the layers of the application.
 *
 * @param tag [String]: the tag of the layer.
 *
 * @property Data [Layer]: the data layer.
 * @property Domain [Layer]: the domain layer.
 * @property Presentation [Layer]: the presentation layer.
 * @property Core [Layer]: the core layer.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed class Layer(val tag: String) {
    data object Data : Layer("Data")
    data object Domain : Layer("Domain")
    data object Presentation : Layer("Presentation")
    data object Core : Layer("Core")
}