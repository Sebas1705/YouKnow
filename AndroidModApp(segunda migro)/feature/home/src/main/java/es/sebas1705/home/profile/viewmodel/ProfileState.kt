package es.sebas1705.youknow.presentation.features.home.features.profile.viewmodel


import es.sebas1705.common.mvi.MVIBaseState

/**
 * State of the [ProfileViewModel] that will handle the UI changes
 *
 * @param isLoading: Boolean to show a loading spinner
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebas1705 12/09/2025
 */
data class ProfileState(
    val isLoading: Boolean
) : MVIBaseState {
    companion object {
        fun default() = ProfileState(
            isLoading = false
        )
    }
}