package es.sebas1705.survey.viewmodel


import es.sebas1705.common.mvi.MVIBaseState

/**
 * State of the [SurveyViewModel]
 *
 * @property isLoading: Boolean to know if the survey is loading
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
data class SurveyState(
    val isLoading: Boolean,
) : MVIBaseState {
    companion object {
        fun default() = SurveyState(
            isLoading = false,
        )
    }
}