package es.sebas1705.game.quiz.viewmodel


import es.sebas1705.common.games.Languages
import es.sebas1705.common.games.quiz.QuizMode
import es.sebas1705.common.games.quiz.QuizStatus
import es.sebas1705.common.classes.mvi.MVIBaseState
import es.sebas1705.models.games.QuestionModel

/**
 * Data class that represents the state of the Quiz Screen.
 *
 * @param isLoading [Boolean]: Flag that indicates if the screen is loading.
 * @param actualQuestion [Int]: Number of the current question.
 * @param points [Int]: Points obtained in the game.
 * @param correctAnswers [Int]: Number of correct answers.
 * @param lives [Int]: Number of lives.
 * @param questions [List]: List of questions.
 * @param status [QuizStatus]: Status of the game.
 * @param mode [QuizMode]: Mode of the game.
 * @param languages [Languages]: Language of the questions.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
data class QuizState(
    var isLoading: Boolean,
    var actualQuestion: Int,
    var points: Int,
    var correctAnswers: Int,
    var lives: Int,
    var questions: List<QuestionModel>,
    var status: QuizStatus,
    var mode: QuizMode?,
    var languages: Languages
) : MVIBaseState {

    companion object {
        /**
         * Default state of the Quiz Screen.
         *
         * @return [QuizState]: Default state of the Quiz Screen.
         *
         * @since 1.0.0
         * @author Sebas1705 12/09/2025
         */
        fun default() = QuizState(
            isLoading = false,
            actualQuestion = 0,
            points = 0,
            correctAnswers = 0,
            lives = 3,
            questions = emptyList(),
            status = QuizStatus.SELECTION_MODE,
            mode = null,
            languages = Languages.ANY
        )
    }
}