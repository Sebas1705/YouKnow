package es.sebas1705.game.families.viewmodel


import es.sebas1705.common.games.Category
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.families.FamiliesMode
import es.sebas1705.common.mvi.MVIBaseIntent

/**
 * Sealed interface that represents the possible intents of the Families Screen.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
sealed interface FamiliesIntent : MVIBaseIntent {

    data object ReadLanguages : FamiliesIntent

    data object ResetGame : FamiliesIntent

    data class GenerateGame(
        val category: Category,
        val difficulty: Difficulty,
        val numFamilies: Int,
    ) : FamiliesIntent
    
    data class SelectMode(
        val familiesMode: FamiliesMode
    ) : FamiliesIntent

    data class Response(
        val response: String
    ) : FamiliesIntent

    data class OutGame(
        val onSuccess: () -> Unit
    ) : FamiliesIntent
}
