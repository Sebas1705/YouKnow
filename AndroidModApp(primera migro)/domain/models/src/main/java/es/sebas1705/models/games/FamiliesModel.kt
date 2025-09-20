package es.sebas1705.models.games
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

import es.sebas1705.common.games.Category
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.Languages

/**
 * Model class to represent the families game
 *
 * @param answers [List]<[String]>: List of answers
 * @param correctAnswer [String]: Correct answer
 * @param category [Category]: Category of the question
 * @param language [Languages]: Language of the question
 * @param difficulty [Difficulty]: Difficulty of the question
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
data class FamiliesModel(
    val answers: List<String>,
    val correctAnswer: String,
    val category: Category,
    val language: Languages,
    val difficulty: Difficulty,
)