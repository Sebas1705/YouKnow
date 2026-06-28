package es.sebas1705.settings.viewmodel


import es.sebas1705.common.classes.mvi.MVIBaseState
import es.sebas1705.datastore.config.DefaultValuesDS
import es.sebas1705.models.SettingsModel

/**
 * Data class that represents the state of the Settings Screen.
 *
 * @param isLoading [Boolean]: Loading state of the app.
 * @param settings [SettingsModel]: Current settings of the app.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
data class SettingsState(
    val settings: SettingsModel
) : MVIBaseState {
    companion object {

        /**
         * Default state of the Settings Screen.
         *
         * @return [SettingsState]: Default state of the Settings Screen.
         *
         * @since 1.0.0
         * @author Sebas1705 12/09/2025
         */
        fun default() = SettingsState(
            settings = SettingsModel(
                firstTime = DefaultValuesDS.FIRST_TIME,
                musicVolume = DefaultValuesDS.MUSIC_VOLUME,
                soundVolume = DefaultValuesDS.SOUND_VOLUME,
                appContrast = DefaultValuesDS.APP_UI_CONTRAST,
                language = DefaultValuesDS.GAME_LANGUAGE
            )
        )
    }
}