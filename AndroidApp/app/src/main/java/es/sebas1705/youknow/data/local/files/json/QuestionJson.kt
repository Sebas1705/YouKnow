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
 * Data class to represent the question data and use as entity in the database
 *
 * @property question [String]: Question
 * @property answers [List]<[String]>: List of answers
 * @property correctAnswer [String]: Correct answer
 * @property category [Int]: Category of the question
 * @property language [Int]: Language of the question
 * @property difficulty [Int]: Difficulty of the question
 * @property quizType [Int]: Type of the quiz
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Serializable
data class QuestionJson(
    val question: String,
    val answers: List<String>,
    val correctAnswer: String,
    val category: Int,
    val language: Int,
    val difficulty: Int,
    val quizType: Int
)