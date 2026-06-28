package es.sebas1705.game.mysterynumber.viewmodel


import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.mysterynumber.MysteryNumberMode
import es.sebas1705.common.classes.mvi.MVIBaseIntent

/**
 * Sealed interface that represents the possible intents of the MysteryNumber Screen.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
sealed interface MysteryNumberIntent : MVIBaseIntent {

    data class GenerateGame(
        val difficulty: Difficulty,
        val lives: Int
    ) : MysteryNumberIntent

    data object ResetGame : MysteryNumberIntent

    data class SelectMode(
        val mysteryNumberMode: MysteryNumberMode
    ) : MysteryNumberIntent

    data class Response(
        val response: Int,
        val time: Float
    ) : MysteryNumberIntent

    data class OutGame(
        val onSuccess: () -> Unit
    ) : MysteryNumberIntent
}