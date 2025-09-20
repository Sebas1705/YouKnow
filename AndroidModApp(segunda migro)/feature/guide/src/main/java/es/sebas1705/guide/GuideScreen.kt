package es.sebas1705.guide


import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.common.states.WindowState
import es.sebas1705.guide.design.GuideDesign
import es.sebas1705.guide.viewmodel.GuideIntent
import es.sebas1705.guide.viewmodel.GuideViewModel

/**
 * Guide Screen that will show the user a guide of the app.
 * The user can navigate through the guide and start the app.
 *
 * @param windowState [WindowState]: The state of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onSuccessNavigation () -> Unit: Function that will be called when the user finishes the guide.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun GuideScreen(
    windowState: WindowState,
    soundPool: Pair<SoundPool, Float>,
    onSuccessNavigation: () -> Unit,
) {
    //ViewModel:
    val guideViewModel: GuideViewModel = hiltViewModel()

    //State:
    val guideState by guideViewModel.uiState.collectAsStateWithLifecycle()

    //Body:
    GuideDesign(
        windowState,
        guideState,
        soundPool,
        onSuccessNavigation = {
            guideViewModel.eventHandler(GuideIntent.ChargeData {
                onSuccessNavigation()
            })
        },
    )
}






