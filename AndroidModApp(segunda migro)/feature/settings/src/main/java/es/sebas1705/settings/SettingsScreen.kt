package es.sebas1705.settings


import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.common.states.WindowState
import es.sebas1705.settings.design.SettingsDesign
import es.sebas1705.settings.viewmodel.SettingsIntent
import es.sebas1705.settings.viewmodel.SettingsViewModel

/**
 * Screen for the Settings of the app.
 *
 * @param windowState [WindowState]: State of the Settings.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onBack () -> Unit: Function to go back to the previous screen.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun SettingsScreen(
    soundPool: Pair<SoundPool, Float>,
    onBack: () -> Unit
) {

    //ViewModel:
    val settingsViewModel: SettingsViewModel = hiltViewModel()

    //States:
    val settingsState by settingsViewModel.uiState.collectAsStateWithLifecycle()
    val isLoading by settingsViewModel.loading.collectAsStateWithLifecycle()

    //Effects:
    LaunchedEffect(null) {
        settingsViewModel.eventHandler(SettingsIntent.ReadSettings)
    }

    //Body:
    SettingsDesign(
        settingsState,
        isLoading,
        soundPool,
        onBack,
        onMusicVolumeSlideBarChange = {
            settingsViewModel.eventHandler(SettingsIntent.ChangeSettings(
                settingsState.settings.copy(musicVolume = it)
            ))
        },
        onSoundVolumeSliderBarChange = {
            settingsViewModel.eventHandler(SettingsIntent.ChangeSettings(
                settingsState.settings.copy(soundVolume = it)
            ))
        },
        onContrastClick = {
            settingsViewModel.eventHandler(SettingsIntent.ChangeSettings(
                settingsState.settings.copy(appContrast = it)
            ))
        },
        onLanguageClick = {
            settingsViewModel.eventHandler(SettingsIntent.ChangeSettings(
                settingsState.settings.copy(language = it)
            ))
        },
        onRestoreClick = {
            settingsViewModel.eventHandler(SettingsIntent.RestoreSettings)
        }
    )
}



