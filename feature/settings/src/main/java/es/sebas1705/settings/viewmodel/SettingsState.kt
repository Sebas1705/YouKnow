package es.sebas1705.settings.viewmodel


import es.sebas1705.common.classes.mvi.MVIBaseState
import es.sebas1705.datastore.config.DefaultValuesDS
import es.sebas1705.models.SettingsModel

/**
 * Which dialog of the account deletion flow is showing.
 *
 * @property Hidden no dialog
 * @property Confirm "Are you sure?", with the reason of the last failure if there was one
 * @property ReauthPassword asks the (email and password) user to type the password again
 *
 * @since 1.6.0
 * @author Sebas1705 11/11/2026
 */
enum class DeleteAccountStep { Hidden, Confirm, ReauthPassword }

/**
 * Why an account deletion attempt did not finish; the screen maps each one to a message.
 *
 * @property WrongCredentials the password is wrong, or the Google account is not the signed-in one
 * @property RecentLoginRequired Firebase wants a fresher sign-in; the data was already deleted
 * @property DataDeletedAccountKept the data is deleted but the account is not (network?): retry
 * @property NotSignedIn there is no session any more
 * @property Generic anything else; nothing or only part of the data was deleted, retrying is safe
 *
 * @since 1.6.0
 * @author Sebas1705 11/11/2026
 */
enum class DeleteAccountError {
    WrongCredentials,
    RecentLoginRequired,
    DataDeletedAccountKept,
    NotSignedIn,
    Generic
}

/**
 * Data class that represents the state of the Settings Screen.
 *
 * @param settings [SettingsModel]: Current settings of the app.
 * @param deleteAccountStep [DeleteAccountStep]: Dialog of the account deletion showing, if any.
 * @param deleteAccountError [DeleteAccountError]: Why the last deletion attempt failed, if it did.
 * @param accountDeleted [Boolean]: The account is gone; the screen must navigate to the auth screen.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
data class SettingsState(
    val settings: SettingsModel,
    val deleteAccountStep: DeleteAccountStep = DeleteAccountStep.Hidden,
    val deleteAccountError: DeleteAccountError? = null,
    val accountDeleted: Boolean = false
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
