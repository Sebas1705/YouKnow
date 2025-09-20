package es.sebas1705.game.wordpass.viewmodel


import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.wordpass.WordPassMode
import es.sebas1705.common.mvi.MVIBaseIntent

/**
 * Sealed interface that represents the possible intents of the WordPass Screen.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
sealed interface WordPassIntent : MVIBaseIntent {

    data object ReadLanguages : WordPassIntent

    data object ResetGame : WordPassIntent

    data class GenerateGame(
        val difficulty: Difficulty,
        val wordPassMode: WordPassMode,
        val numWords: Int,
    ) : WordPassIntent

    data class SelectMode(
        val wordPassMode: WordPassMode
    ) : WordPassIntent

    data class Response(
        val response: String
    ) : WordPassIntent

    data class OutGame(
        val onSuccess: () -> Unit
    ) : WordPassIntent
}