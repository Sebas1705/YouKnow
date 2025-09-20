package es.sebas1705.game.wordpass.viewmodel


import es.sebas1705.common.games.Languages
import es.sebas1705.common.games.wordpass.WordPassMode
import es.sebas1705.common.games.wordpass.WordPassStatus
import es.sebas1705.common.mvi.MVIBaseState
import es.sebas1705.models.games.WordModel

/**
 * Data class that represents the state of the WordPass Screen.
 *
 * @param isLoading [Boolean]: Flag that indicates if the screen is loading.
 * @param actualWord [Int]: Actual word that the user is playing.
 * @param points [Int]: Points that the user has.
 * @param correctAnswers [Int]: Number of correct answers that the user has.
 * @param lives [Int]: Number of lives that the user has.
 * @param words [List<WordModel>]: List of words that the game will have.
 * @param status [WordPassStatus]: Status of the game.
 * @param mode [WordPassMode?]: Mode of the game.
 * @param languages [Languages]: Languages that the game will have.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
data class WordPassState(
    var isLoading: Boolean,
    var actualWord: Int,
    var points: Int,
    var correctAnswers: Int,
    var lives: Int,
    var words: List<WordModel>,
    var status: WordPassStatus,
    var mode: WordPassMode?,
    var languages: Languages
) : MVIBaseState {

    companion object {
        /**
         * Default state of the WordPass Screen.
         *
         * @return [WordPassState]: Default state of the WordPass Screen.
         *
         * @since 1.0.0
         * @author Sebas1705 12/09/2025
         */
        fun default() = WordPassState(
            isLoading = false,
            actualWord = 0,
            points = 0,
            correctAnswers = 0,
            lives = 3,
            words = emptyList(),
            status = WordPassStatus.SELECTION_MODE,
            mode = null,
            languages = Languages.ANY
        )
    }
}