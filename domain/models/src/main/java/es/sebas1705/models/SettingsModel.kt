package es.sebas1705.models

import es.sebas1705.common.theme.ThemeContrast
import es.sebas1705.resources.games.Languages

/**
 * Represents the settings of the application.
 *
 * @property firstTime Indicates if this is the first time the app is being run.
 * @property musicVolume The volume level for music.
 * @property soundVolume The volume level for sound effects.
 * @property appContrast The contrast setting for the app's theme.
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
data class SettingsModel (
    val firstTime: Boolean,
    val musicVolume: Float,
    val soundVolume: Float,
    val appContrast: ThemeContrast,
    val language: Languages
)