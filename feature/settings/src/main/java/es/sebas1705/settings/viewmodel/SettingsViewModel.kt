package es.sebas1705.settings.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.common.classes.mvi.MVIBaseViewModel
import es.sebas1705.datastore.config.DefaultValuesDS
import es.sebas1705.models.SettingsModel
import es.sebas1705.settings.ReadSettingsUseCase
import es.sebas1705.settings.UpdateSettingsUseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val readSettingsUseCase: ReadSettingsUseCase,
    private val updateSettingsUseCase: UpdateSettingsUseCase,
) : MVIBaseViewModel<SettingsState, SettingsIntent>() {

    override fun initState(): SettingsState = SettingsState.default()

    override fun intentHandler(
        intent: SettingsIntent
    ) {
        when (intent) {
            is SettingsIntent.ReadSettings -> readSettings()
            is SettingsIntent.ChangeSettings -> changeSettings(intent)
            is SettingsIntent.RestoreSettings -> restoreSettings()
        }
    }

    private fun readSettings() = execute(Dispatchers.IO) {
        readSettingsUseCase().collect { settings ->
            updateUi {
                it.copy(
                    settings = settings
                )
            }
        }
    }

    private fun restoreSettings() = execute(Dispatchers.IO) {
        updateSettingsUseCase(
            settingsModel = SettingsModel(
                firstTime = DefaultValuesDS.FIRST_TIME,
                musicVolume = DefaultValuesDS.MUSIC_VOLUME,
                soundVolume = DefaultValuesDS.SOUND_VOLUME,
                appContrast = DefaultValuesDS.APP_UI_CONTRAST,
                language = DefaultValuesDS.GAME_LANGUAGE
            )
        )
    }

    private fun changeSettings(
        intent: SettingsIntent.ChangeSettings
    ) = execute(Dispatchers.IO) {
        updateSettingsUseCase(
            settingsModel = intent.settings
        )
    }
}
