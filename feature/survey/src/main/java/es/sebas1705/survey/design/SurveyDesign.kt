package es.sebas1705.survey.design


import android.media.SoundPool
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.sebas1705.common.states.WindowState
import es.sebas1705.survey.viewmodel.SurveyState
import es.sebas1705.domain.model.stats.SurveyModel

/**
 * Survey Design of the app.
 *
 * @param windowState [WindowState]: state of the window
 * @param soundPool [Pair]<[SoundPool], [Float]>: sound pool and volume
 * @param onBack () -> Unit: action to go back
 *
 * @since 1.0.0
 * @author Sebas1705 21/09/2025
 */
@Composable
fun SurveyDesign(
    windowState: WindowState = WindowState.default(),
    surveyState: SurveyState = SurveyState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onBack: () -> Unit = {}
) {
    val pagerState = rememberPagerState(initialPage = 0) { SurveyModel.PAGES_N }
    Scaffold(
        topBar = {

        }
    ) { padding ->
        Box(
            modifier = Modifier.padding(padding)
        )
    }

}