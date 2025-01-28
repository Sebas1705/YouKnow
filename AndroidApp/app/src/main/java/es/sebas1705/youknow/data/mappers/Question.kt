package es.sebas1705.youknow.data.mappers
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

import es.sebas1705.youknow.core.classes.enums.games.Category
import es.sebas1705.youknow.core.classes.enums.games.Difficulty
import es.sebas1705.youknow.core.classes.enums.games.Languages
import es.sebas1705.youknow.core.classes.enums.games.quiz.QuizType
import es.sebas1705.youknow.data.apis.opendb.dtos.QuestionOpendbDto
import es.sebas1705.youknow.data.local.database.entities.QuestionEntity
import es.sebas1705.youknow.data.local.files.json.QuestionJson
import es.sebas1705.youknow.domain.model.games.QuestionModel

//ENTITY

/**
 * Mapper function
 *
 * @receiver [QuestionEntity]: The object to convert
 *
 * @return [QuestionModel]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun QuestionEntity.toQuestionModel() = QuestionModel(
    question,
    answers,
    correctAnswer,
    category,
    language,
    difficulty,
    quizType
)

/**
 * Mapper function
 *
 * @receiver [QuestionEntity]: The object to convert
 *
 * @return [QuestionJson]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun QuestionEntity.toQuestionJson() = QuestionJson(
    question,
    answers,
    correctAnswer,
    category.ordinal,
    language.ordinal,
    difficulty.ordinal,
    quizType.ordinal
)

//JSON

/**
 * Mapper function
 *
 * @receiver [QuestionJson]: The object to convert
 *
 * @return [QuestionEntity]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun QuestionJson.toQuestionEntity() = QuestionEntity(
    question,
    answers,
    correctAnswer,
    Category.entries[category],
    Languages.entries[language],
    Difficulty.entries[difficulty],
    QuizType.entries[quizType]
)

/**
 * Mapper function
 *
 * @receiver [QuestionJson]: The object to convert
 *
 * @return [QuestionModel]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun QuestionJson.toQuestionModel() = QuestionModel(
    question,
    answers,
    correctAnswer,
    Category.entries[category],
    Languages.entries[language],
    Difficulty.entries[difficulty],
    QuizType.entries[quizType]
)

//MODEL

/**
 * Mapper function
 *
 * @receiver [QuestionModel]: The object to convert
 *
 * @return [QuestionEntity]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun QuestionModel.toQuestionEntity() = QuestionEntity(
    question,
    answers,
    correctAnswer,
    category,
    language,
    difficulty,
    quizType
)

/**
 * Mapper function
 *
 * @receiver [QuestionModel]: The object to convert
 *
 * @return [QuestionJson]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun QuestionModel.toQuestionJson() = QuestionJson(
    question,
    answers,
    correctAnswer,
    category.ordinal,
    language.ordinal,
    difficulty.ordinal,
    quizType.ordinal
)

//DTO

/**
 * Mapper function
 *
 * @receiver [QuestionOpendbDto]: The object to convert
 *
 * @return [QuestionModel]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun QuestionOpendbDto.toQuestionModel() = QuestionModel(
    question = question,
    answers = incorrectAnswers + correctAnswer,
    correctAnswer = correctAnswer,
    category = Category.getCategory(category.toInt()),
    language = Languages.EN,
    difficulty = Difficulty.getDifficulty(difficulty),
    quizType = QuizType.getType(type)
)