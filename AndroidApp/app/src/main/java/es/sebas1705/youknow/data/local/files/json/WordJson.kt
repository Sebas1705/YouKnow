package es.sebas1705.youknow.data.local.files.json
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

import kotlinx.serialization.Serializable

/**
 * Data class to represent the word data and use as entity in the database
 *
 * @property word [String]: Word
 * @property definitions [List]<[String]>: List of definitions
 * @property letter [Int]: Letter of the word
 * @property language [Int]: Language of the word
 * @property difficulty [Int]: Difficulty of the word
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Serializable
data class WordJson(
    val word: String,
    val definitions: List<String>,
    val letter: Int,
    val language: Int,
    val difficulty: Int
)