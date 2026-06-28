package es.sebas1705.guide.viewmodel


import es.sebas1705.common.classes.mvi.MVIBaseIntent

/**
 * Sealed class that represents the possible intents for Guide Screen.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
sealed interface GuideIntent : MVIBaseIntent {

    data class ChargeData(
        val onSuccess: () -> Unit
    ) : GuideIntent
}
