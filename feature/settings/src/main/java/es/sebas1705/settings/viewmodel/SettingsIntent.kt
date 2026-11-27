package es.sebas1705.settings.viewmodel

import es.sebas1705.common.classes.mvi.MVIBaseIntent
import es.sebas1705.common.theme.ThemeContrast
import es.sebas1705.models.SettingsModel

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

    /** The user tapped "Delete account": show the confirmation. */
    data object RequestDeleteAccount : SettingsIntent

    /** The user closed the deletion dialogs. Ignored while a deletion is running. */
    data object DismissDeleteAccount : SettingsIntent

    /**
     * The user confirmed the deletion: re-authenticate (asking for the password, or showing the
     * Google account picker) and delete. Also the retry after a failed attempt.
     */
    data object ConfirmDeleteAccount : SettingsIntent

    /** The user typed their password to re-authenticate before the deletion. */
    class ReauthenticateWithPassword(val password: String) : SettingsIntent

    /** The screen has navigated away after the account was deleted. */
    data object AccountDeletedHandled : SettingsIntent
}
