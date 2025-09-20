package es.sebas1705.game.families.viewmodel


import es.sebas1705.common.games.Languages
import es.sebas1705.common.games.families.FamiliesMode
import es.sebas1705.common.games.families.FamiliesStatus
import es.sebas1705.common.mvi.MVIBaseState
import es.sebas1705.models.games.FamiliesModel

/**
 * Data class that represents the state of the Families Screen.
 *
 * @param isLoading [Boolean]: Flag that indicates if the screen is loading.
 * @param numFamily [Int]: Number of families to play.
 * @param actualFamily [Int]: Actual family.
 * @param points [Int]: Points of the user.
 * @param correctAnswers [Int]: Correct answers of the user.
 * @param lives [Int]: Lives of the user.
 * @param families [List]<[FamiliesModel]>: List of families.
 * @param status [FamiliesStatus]: Status of the game.
 * @param mode [FamiliesMode]?: Mode of the game.
 * @param languages [Languages]: Language of the game.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
data class FamiliesState(
    var isLoading: Boolean,
    var numFamily: Int,
    var actualFamily: Int,
    var points: Int,
    var correctAnswers: Int,
    var lives: Int,
    var families: List<FamiliesModel>,
    var status: FamiliesStatus,
    var mode: FamiliesMode?,
    var languages: Languages
) : MVIBaseState {

    companion object {
        /**
         * Default state of the Families Screen.
         *
         * @return [FamiliesState]: Default state of the Families Screen.
         *
         * @since 1.0.0
         * @author Sebas1705 12/09/2025
         */
        fun default() = FamiliesState(
            isLoading = false,
            numFamily = 0,
            actualFamily = 0,
            points = 0,
            correctAnswers = 0,
            lives = 3,
            families = emptyList(),
            status = FamiliesStatus.SELECTION_MODE,
            mode = null,
            languages = Languages.ANY
        )
    }
}