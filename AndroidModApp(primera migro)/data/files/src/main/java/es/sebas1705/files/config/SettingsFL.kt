package es.sebas1705.files.config
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
 * Object to store the settings of the files
 *
 * @property FAMILIES_DEFAULT_BD_JSON [String]: Default file name for the families database
 * @property QUESTION_DEFAULT_BD_JSON [String]: Default file name for the question database
 * @property WORD_DEFAULT_BD_JSON [String]: Default file name for the word database
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
object SettingsFL {
    const val FAMILIES_DEFAULT_BD_JSON = "defaultFamiliesbd.json"
    const val QUESTION_DEFAULT_BD_JSON = "defaultQuestionbd.json"
    const val WORD_DEFAULT_BD_JSON = "defaultWordbd.json"
}