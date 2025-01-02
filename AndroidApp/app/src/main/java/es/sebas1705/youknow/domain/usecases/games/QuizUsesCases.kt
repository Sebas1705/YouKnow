package es.sebas1705.youknow.domain.usecases.games
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

import es.sebas1705.youknow.core.classes.enums.Category
import es.sebas1705.youknow.core.classes.enums.Difficulty
import es.sebas1705.youknow.core.classes.enums.Languages
import es.sebas1705.youknow.core.classes.enums.QuizType
import es.sebas1705.youknow.domain.model.games.QuestionModel

class GenerateQuestionList(

) {
    operator fun invoke(
        numberQuestions: Int,
        category: Category,
        difficulty: Difficulty,
        quizType: QuizType,
        onLoading: () -> Unit = {},
        onSuccess: (List<QuestionModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        onLoading()
        val list =
            listOf(listOf("Option A", "Option B", "Option C", "Option D"), listOf("True", "False"))
        onSuccess(
            (1..numberQuestions).map {
                val index =
                    if (quizType == QuizType.ANY) (0..1).random() else if (quizType == QuizType.MULTIPLE) 0 else 1
                QuestionModel(
                    "Question $it",
                    list[index],
                    list[index][0],
                    if (category == Category.ANY) Category.entries.random() else category,
                    Languages.EN,
                    if (difficulty == Difficulty.ANY) Difficulty.entries.random() else difficulty,
                    if (index == 0) QuizType.MULTIPLE else QuizType.BOOLEAN
                )
            }
        )
    }
}


data class QuizUsesCases(
    val generateQuestionList: GenerateQuestionList
)