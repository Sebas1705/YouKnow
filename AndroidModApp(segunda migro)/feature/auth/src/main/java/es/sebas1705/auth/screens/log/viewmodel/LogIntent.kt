package es.sebas1705.auth.screens.log.viewmodel


import es.sebas1705.common.mvi.MVIBaseIntent

/**
 * Sealed interface that represents the possible actions that can be performed in the Log Screen.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
sealed interface LogIntent : MVIBaseIntent {

    data class SignInWithEmail(
        val email: String,
        val password: String,
        val onSuccess: () -> Unit,
        val onError: (String) -> Unit
    ) : LogIntent

    data class SendForgotPassword(
        val email: String
    ) : LogIntent

}