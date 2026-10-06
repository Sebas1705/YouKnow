package es.sebas1705.survey


import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.common.states.WindowState
import es.sebas1705.survey.design.SurveyDesign
import es.sebas1705.survey.viewmodel.SurveyIntent
import es.sebas1705.survey.viewmodel.SurveyViewModel

/**
 * Survey Screen of the app.
 *
 * @param windowState [WindowState]: state of the window
 * @param soundPool [Pair]<[SoundPool], [Float]>: sound pool and volume
 * @param onBack () -> Unit: action to go back
 *
 * @since 1.0.0
 * @author Sebas1705 21/09/2025
 */
@Composable
fun SurveyScreen(
    windowState: WindowState,
    soundPool: Pair<SoundPool, Float>,
    onBack: () -> Unit
) {
    //Viewmodel:
    val surveyViewModel: SurveyViewModel = hiltViewModel()

    //State:
    val surveyState by surveyViewModel.uiState.collectAsStateWithLifecycle()

    //Body:
    SurveyDesign(
        windowState = windowState,
        surveyState = surveyState,
        soundPool = soundPool,
        onBack = onBack,
        onUpdateAge = { surveyViewModel.eventHandler(SurveyIntent.UpdateAge(it)) },
        onUpdateProfession = { surveyViewModel.eventHandler(SurveyIntent.UpdateProfession(it)) },
        onUpdateAndroidKnowing = { surveyViewModel.eventHandler(SurveyIntent.UpdateAndroidKnowing(it)) },
        onUpdateApplicationsKnowing = { surveyViewModel.eventHandler(SurveyIntent.UpdateApplicationsKnowing(it)) },
        onUpdateGamesKnowing = { surveyViewModel.eventHandler(SurveyIntent.UpdateGamesKnowing(it)) },
        onUpdateSocialNetworksKnowing = { surveyViewModel.eventHandler(SurveyIntent.UpdateSocialNetworksKnowing(it)) },
        onUpdateOtherKnowing = { surveyViewModel.eventHandler(SurveyIntent.UpdateOtherKnowing(it)) },
        onOpinionPoints = { key, points -> surveyViewModel.eventHandler(SurveyIntent.UpdateOpinionPoints(key, points)) },
        onOpinionText = { key, text -> surveyViewModel.eventHandler(SurveyIntent.UpdateOpinionText(key, text)) },
        onSubmit = { surveyViewModel.eventHandler(SurveyIntent.Submit) },
    )
}
