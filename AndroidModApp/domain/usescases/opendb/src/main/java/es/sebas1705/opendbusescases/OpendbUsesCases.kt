package es.sebas1705.opendbusescases
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

import es.sebas1705.opendbusescases.usescases.GetTriviaTenQuestions

/**
 * Data class to group all the use cases related to opendb
 *
 * @property getTriviaTenQuestions [GetTriviaTenQuestions]: use case to get 10 random questions
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class OpendbUsesCases(
    val getTriviaTenQuestions: GetTriviaTenQuestions
)