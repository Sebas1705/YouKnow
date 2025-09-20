package es.sebas1705.auth.screens.log.viewmodel


import es.sebas1705.common.mvi.MVIBaseState

/**
 * Data class that represents the state of the Log Screen.
 *
 * @param isLoading [Boolean]: Loading state of the Log Screen.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
data class LogState(
    val isLoading: Boolean
) : MVIBaseState {
    companion object {
        /**
         * Default state of the Log Screen.
         *
         * @return [LogState]: Default state of the Log Screen.
         *
         * @since 1.0.0
         * @Author Sebas1705 12/09/2025
         */
        fun default() = LogState(
            isLoading = false
        )
    }
}