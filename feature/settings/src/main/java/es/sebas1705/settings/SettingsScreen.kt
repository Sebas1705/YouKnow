package es.sebas1705.settings


import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.extensions.composables.printTextInToast
import es.sebas1705.feature.settings.R
import es.sebas1705.settings.design.SettingsDesign
import es.sebas1705.settings.viewmodel.SettingsIntent
import es.sebas1705.settings.viewmodel.SettingsViewModel

/**
 * Screen for the Settings of the app.
 *
 * @param windowState [WindowState]: State of the Settings.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onBack () -> Unit: Function to go back to the previous screen.
 * @param onSurveyNav () -> Unit: Function to navigate to the usability survey.
 * @param onAccountDeleted () -> Unit: Function to navigate to the auth screen once the account is deleted.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun SettingsScreen(
    windowState: WindowState,
    onBack: () -> Unit,
    onSurveyNav: () -> Unit = {},
    onAccountDeleted: () -> Unit = {}
) {

    //Locals:
    val ctx = LocalContext.current

    //ViewModel:
    val settingsViewModel: SettingsViewModel = hiltViewModel()

    //States:
    val settingsState by settingsViewModel.uiState.collectAsStateWithLifecycle()
    val isLoading by settingsViewModel.loading.collectAsStateWithLifecycle()

    //Effects:
    LaunchedEffect(null) {
        settingsViewModel.eventHandler(SettingsIntent.ReadSettings)
    }
    LaunchedEffect(settingsState.accountDeleted) {
        if (settingsState.accountDeleted) {
            ctx.printTextInToast(ctx.getString(R.string.feature_settings_delete_account_done))
            settingsViewModel.eventHandler(SettingsIntent.AccountDeletedHandled)
            onAccountDeleted()
        }
    }

    //Body:
    SettingsDesign(
        windowState,
        settingsState,
        isLoading,
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
        },
        onSurveyNav = onSurveyNav,
        onDeleteAccountClick = {
            settingsViewModel.eventHandler(SettingsIntent.RequestDeleteAccount)
        },
        onDeleteAccountConfirm = {
            settingsViewModel.eventHandler(SettingsIntent.ConfirmDeleteAccount)
        },
        onDeleteAccountPassword = {
            settingsViewModel.eventHandler(SettingsIntent.ReauthenticateWithPassword(it))
        },
        onDeleteAccountDismiss = {
            settingsViewModel.eventHandler(SettingsIntent.DismissDeleteAccount)
        }
    )
}
