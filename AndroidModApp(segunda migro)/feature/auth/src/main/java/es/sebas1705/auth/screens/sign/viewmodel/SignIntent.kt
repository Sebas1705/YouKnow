package es.sebas1705.auth.screens.sign.viewmodel


import es.sebas1705.common.mvi.MVIBaseIntent

/**
 * Sealed interface that represents the possible actions that can be performed in the Sign Screen.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
sealed interface SignIntent : MVIBaseIntent {

    data class SignUpWithEmail(
        val email: String,
        val password: String,
        val nickname: String,
        val onSuccess: () -> Unit,
        val onError: (String) -> Unit
    ) : SignIntent

}