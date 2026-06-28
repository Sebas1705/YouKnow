package es.sebas1705.main


import android.media.AudioAttributes
import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.designsystem.ComposableConstants.MAX_SOUNDS_SIMULTANEITY
import es.sebas1705.designsystem.states.rememberWindowState
import es.sebas1705.networkerror.NetworkErrorScreen
import es.sebas1705.splash.SplashScreen
import es.sebas1705.ui.theme.AppTheme

/**
 * Main screen of the app that contains the splash screen and
 * the navigation host of the app
 *
 * @param onVolumeChange (Float) -> Unit: Function that will change the volume.
 * @param onMusicChange (Boolean) -> Unit: Function that will change the music.
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
@Composable
fun MainScreen(
    onVolumeChange: (Float) -> Unit,
    onMusicChange: (Boolean, Float) -> Unit,
) {

    //ViewModels:
    val mainViewModel: MainViewModel = hiltViewModel()

    //States:
    val mainState by mainViewModel.uiState.collectAsStateWithLifecycle()
    val windowState by rememberWindowState()
    val soundPool = remember {
        SoundPool.Builder()
            .setMaxStreams(MAX_SOUNDS_SIMULTANEITY)
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
            )
            .build()
    }

    //Effects:
    LaunchedEffect(Unit) {
        mainViewModel.eventHandler(MainIntent.ChargeData)
    }

    onVolumeChange(mainState.musicVolume)

    //Content:
    AppTheme(
        themeContrast = mainState.themeContrast,
    ) {
        when {
            mainState.isSplashVisible -> SplashScreen(windowState)
            !mainState.isNetworkAvailable -> NetworkErrorScreen(windowState)
            else -> AppNav(
                mainState.startDestination,
                windowState,
                onMusicChange = { song -> onMusicChange(song, mainState.musicVolume) },
                soundPool = soundPool to mainState.soundVolume
            )
        }
    }
}
