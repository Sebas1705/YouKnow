package es.sebas1705.models.games

import es.sebas1705.resources.games.Category
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.resources.games.QuizType

/**
 * Model to represent a question
 *
 * @property question [String]: Question
 * @property answers [List]<[String]>: List of answers
 * @property correctAnswer [String]: Correct answer
 * @property category [Category]: Category of the question
 * @property language [Languages]: Language of the question
 * @property difficulty [Difficulty]: Difficulty of the question
 * @property quizType [QuizType]: Type of the question
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
data class QuestionModel(
    val question: String,
    val answers: List<String>,
    val correctAnswer: String,
    val category: Category,
    val language: Languages,
    val difficulty: Difficulty,
    val quizType: QuizType
) {
    companion object {
        fun defaultMultipleList(index: Int): List<QuestionModel> = (1..index).map {
            QuestionModel(
                "Question $it",
                listOf("A", "B", "C", "D"),
                "A",
                Category.ANY,
                Languages.EN,
                listOf(Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD).random(),
                QuizType.MULTIPLE
            )
        }

        fun defaultBooleanList(index: Int): List<QuestionModel> = (1..index).map {
            QuestionModel(
                "Question $it",
                listOf("True", "False"),
                if (it % 2 == 0) "True" else "False",
                Category.ANY,
                Languages.EN,
                listOf(Difficulty.EASY, Difficulty.MEDIUM, Difficulty.HARD).random(),
                QuizType.BOOLEAN
            )
        }
    }
}