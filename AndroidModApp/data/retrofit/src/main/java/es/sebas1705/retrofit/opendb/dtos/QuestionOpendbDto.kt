package es.sebas1705.retrofit.opendb.dtos
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

import com.google.gson.annotations.SerializedName

/**
 * DTO to represent a question from the Opentbd API
 *
 * @property category [String]: Category of the question
 * @property correctAnswer [String]: Correct answer of the question
 * @property difficulty [String]: Difficulty of the question
 * @property incorrectAnswers [List]<[String]>: List of incorrect answers
 * @property question [String]: Question
 * @property type [String]: Type of the question
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class QuestionOpendbDto(
    val category: String,
    @SerializedName("correct_answer") val correctAnswer: String,
    val difficulty: String,
    @SerializedName("incorrect_answers") val incorrectAnswers: List<String>,
    val question: String,
    val type: String
)