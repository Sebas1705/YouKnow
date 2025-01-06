package es.sebas1705.youknow.domain.model
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
 * Model to represent a page
 *
 * @property title [String]: Title of the page
 * @property introduction [String]: Introduction of the page
 * @property imagesAndDescription [List<Pair<Int, String>]: List of images and descriptions
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class PageModel(
    val title: String,
    val introduction: String,
    val imagesAndDescription: List<Pair<Int, String>>
)