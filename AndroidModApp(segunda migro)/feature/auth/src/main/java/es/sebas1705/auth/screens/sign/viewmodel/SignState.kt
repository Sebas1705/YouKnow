package es.sebas1705.auth.screens.sign.viewmodel


import es.sebas1705.common.mvi.MVIBaseState

/**
 * Data class that represents the state of the Sign Screen.
 *
 * @param isLoading [Boolean]: Loading state of the Sign Screen.
 *
 * @see MVIBaseState
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
data class SignState(
    val isLoading: Boolean
) : MVIBaseState {
    companion object {
        /**
         * Default state of the Sign Screen.
         *
         * @return [SignState]: Default state of the Sign Screen.
         *
         * @since 1.0.0
         * @Author Sebas1705 12/09/2025
         */
        fun default() = SignState(
            isLoading = false
        )
    }
}