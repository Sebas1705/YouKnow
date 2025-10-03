package es.sebas1705.survey.viewmodel


import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.common.classes.mvi.MVIBaseViewModel
import es.sebas1705.survey.SurveyUsesCases
import javax.inject.Inject

@HiltViewModel
class SurveyViewModel @Inject constructor(
    private val surveyUsesCases: SurveyUsesCases
) : MVIBaseViewModel<SurveyState, SurveyIntent>() {

    override fun initState(): SurveyState = SurveyState.default()

    override fun intentHandler(intent: SurveyIntent) {
        when (intent) {
            else -> {}
        }
    }

    //Actions:
    //Privates:
    private fun startLoading() {
        updateUi { it.copy(isLoading = true) }
    }

    private fun stopLoading() {
        updateUi { it.copy(isLoading = false) }
    }

    private fun stopAndError(error: String, onError: (String) -> Unit) {
        stopLoading()
        execute { onError(error) }
    }
}