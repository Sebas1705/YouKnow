package es.sebas1705.youknow.data.firebase.firestore.documents
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
 * Data class to represent a new document
 *
 * @property title_es [String]: Title in Spanish
 * @property title_en [String]: Title in English
 * @property body_es [String]: Body in Spanish
 * @property body_en [String]: Body in English
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
data class NewDocument(
    val title_es: String = "",
    val title_en: String = "",
    val body_es: String = "",
    val body_en: String = "",
)