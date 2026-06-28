package es.sebas1705.game.quiz.viewmodel


import es.sebas1705.common.games.Category
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.QuizType
import es.sebas1705.common.games.quiz.QuizMode
import es.sebas1705.common.classes.mvi.MVIBaseIntent

/**
 * Sealed interface that represents the possible intents of the Quiz Screen.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
sealed interface QuizIntent : MVIBaseIntent {

    data object ReadLanguages : QuizIntent

    data object ResetGame : QuizIntent

    data class GenerateGame(
        val category: Category,
        val difficulty: Difficulty,
        val quizType: QuizType,
        val numQuestions: Int,
    ) : QuizIntent

    data class SelectMode(
        val quizMode: QuizMode
    ) : QuizIntent

    data class Response(
        val response: String
    ) : QuizIntent

    data class OutGame(
        val onSuccess: () -> Unit
    ) : QuizIntent
}