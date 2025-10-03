package es.sebas1705.game.mysterynumber.viewmodel


import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.mysterynumber.MysteryNumberMode
import es.sebas1705.common.games.mysterynumber.MysteryNumberStatus
import es.sebas1705.common.classes.mvi.MVIBaseState
import es.sebas1705.models.games.NumberModel

/**
 * Data class that represents the state of the MysteryNumber Screen.
 *
 * @param isLoading [Boolean]: Flag that indicates if the screen is loading.
 * @param points [Int]: Points obtained in the game.
 * @param lives [Int]: Number of lives.
 * @param timeRemaining [Float]: Time remaining in the game.
 * @param numberModel [NumberModel]: Number to guess.
 * @param status [MysteryNumberStatus]: Status of the game.
 * @param mode [MysteryNumberMode]: Mode of the game.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
data class MysteryNumberState(
    var isLoading: Boolean,
    var points: Int,
    var lives: Int,
    var timeRemaining: Float,
    var numberModel: NumberModel,
    var status: MysteryNumberStatus,
    var mode: MysteryNumberMode?
) : MVIBaseState {

    companion object {
        /**
         * Default state of the MysteryNumber Screen.
         *
         * @return [MysteryNumberState]: Default state of the MysteryNumber Screen.
         *
         * @since 1.0.0
         * @author Sebas1705 12/09/2025
         */
        fun default() = MysteryNumberState(
            isLoading = false,
            points = 0,
            lives = 20,
            timeRemaining = 0f,
            numberModel = NumberModel(-1, Difficulty.ANY),
            status = MysteryNumberStatus.SELECTION_MODE,
            mode = null
        )
    }
}