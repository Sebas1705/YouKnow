package es.sebas1705.guide.viewmodel


import es.sebas1705.common.classes.mvi.MVIBaseState

/**
 * State for Guide Screen that will handle the first time the app is opened.
 *
 * @property isLoading [Boolean]: Flag that indicates if the screen is loading data.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
data class GuideState(
    val isLoading: Boolean
) : MVIBaseState {
    companion object {

        /**
         * Default state for Guide Screen.
         *
         * @return [GuideState]
         *
         * @since 1.0.0
         * @author Sebas1705 12/09/2025
         */
        fun default() = GuideState(
            isLoading = false
        )
    }
}