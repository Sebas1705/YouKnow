package es.sebas1705.survey


import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.common.states.WindowState
import es.sebas1705.survey.design.SurveyDesign
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
        windowState,
        surveyState,
        soundPool,
        onBack
    )
}