package es.sebas1705.home.main


import android.media.SoundPool
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.common.states.WindowState
import es.sebas1705.home.main.design.MainDesign
import es.sebas1705.home.main.viewmodel.MainIntent
import es.sebas1705.home.main.viewmodel.MainViewModel
import es.sebas1705.home.navigation.viewmodel.HomeState

/**
 * Main Screen of the app.
 *
 * @param windowState [WindowState]: The state of the window.
 * @param homeState [HomeState]: The state of the Home Screen.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onSettingsNav () -> Unit: The navigation to the settings.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun MainScreen(
    windowState: WindowState,
    homeState: HomeState,
    soundPool: Pair<SoundPool, Float>,
    onSettingsNav: () -> Unit
) {
    //ViewModel:
    val mainViewModel: MainViewModel = hiltViewModel()

    //State:
    val mainState by mainViewModel.uiState.collectAsStateWithLifecycle()

    //Effects:
    LaunchedEffect(windowState) {
        mainViewModel.eventHandler(MainIntent.GetRanking)
        mainViewModel.eventHandler(MainIntent.GetNews)
    }

    //Local:
    BackHandler {}

    //Body:
    MainDesign(
        windowState,
        homeState,
        mainState,
        soundPool,
        onReloadButton = {
            mainViewModel.eventHandler(MainIntent.RecreateGameDB)
        },
        onSettingsNav
    )
}

