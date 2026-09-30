package es.sebas1705.youknow.presentation.features.home.features.profile.viewmodel

import android.net.Uri
import es.sebas1705.common.classes.mvi.MVIBaseIntent



/**
 * Intents of the [ProfileViewModel] that will handle the actions of the screen
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
sealed interface ProfileIntent : MVIBaseIntent {

    data class UploadPhoto(
        val firebaseId: String,
        val uri: Uri
    ) : ProfileIntent

    data class ChangeNickname(
        val firebaseId: String,
        val nickname: String
    ) : ProfileIntent

    data class SendPasswordChanger(
        val email: String
    ) : ProfileIntent

    data object SignOut : ProfileIntent
}