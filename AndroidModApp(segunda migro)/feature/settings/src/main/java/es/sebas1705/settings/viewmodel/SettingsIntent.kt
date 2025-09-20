package es.sebas1705.settings.viewmodel

import es.sebas1705.common.mvi.MVIBaseIntent
import es.sebas1705.common.theme.ThemeContrast
import es.sebas1705.models.SettingsModel
import es.sebas1705.resources.games.Languages

/**
 * Sealed class that represents the possible intents of the Settings Screen.
 *
 * @see MVIBaseIntent
 * @see ThemeContrast
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
sealed interface SettingsIntent : MVIBaseIntent {
    data object ReadSettings : SettingsIntent
    data class ChangeSettings(val settings: SettingsModel) : SettingsIntent
    data object RestoreSettings : SettingsIntent
}