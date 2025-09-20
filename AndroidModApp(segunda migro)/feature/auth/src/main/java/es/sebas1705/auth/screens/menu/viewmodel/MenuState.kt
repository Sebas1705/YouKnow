package es.sebas1705.auth.screens.menu.viewmodel


import es.sebas1705.common.mvi.MVIBaseState

/**
 * Data class that represents the state of the Menu Screen.
 *
 * @param isLoading [Boolean]: Loading state of the Menu Screen.
 *
 * @see MVIBaseState
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
data class MenuState(
    val isLoading: Boolean
) : MVIBaseState {
    companion object {
        fun default() = MenuState(
            isLoading = false
        )
    }
}