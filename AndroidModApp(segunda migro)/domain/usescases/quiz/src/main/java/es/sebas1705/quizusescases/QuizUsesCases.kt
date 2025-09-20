package es.sebas1705.quizusescases


import es.sebas1705.quizusescases.usescases.GenerateQuestionList
import es.sebas1705.quizusescases.usescases.InsertQuestionList

/**
 * Use cases for the quiz game
 *
 * @property generateQuestionList [GenerateQuestionList]: Use case to generate a list of questions
 * @property insertQuestionList [InsertQuestionList]: Use case to insert a list of questions
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
data class QuizUsesCases(
    val generateQuestionList: GenerateQuestionList,
    val insertQuestionList: InsertQuestionList
)