package es.sebas1705.auth.screens.menu.viewmodel


import android.content.Context
import es.sebas1705.common.mvi.MVIBaseIntent

/**
 * Sealed interface that represents the possible actions that can be performed in the Menu Screen.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
sealed interface MenuIntent : MVIBaseIntent {

    data class SignWithGoogle(
        val context: Context,
        val onSuccess: () -> Unit
    ) : MenuIntent

}