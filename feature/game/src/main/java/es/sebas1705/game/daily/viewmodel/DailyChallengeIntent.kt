package es.sebas1705.game.daily.viewmodel


import es.sebas1705.common.classes.mvi.MVIBaseIntent

/**
 * Sealed interface that represents the possible intents of the Daily Challenge Screen.
 *
 * @author Sebas1705 30/09/2026
 * @since 1.3.0
 */
sealed interface DailyChallengeIntent : MVIBaseIntent {

    data object LoadChallenge : DailyChallengeIntent

    data class Response(
        val response: String
    ) : DailyChallengeIntent

    data class OutGame(
        val onSuccess: () -> Unit
    ) : DailyChallengeIntent
}
