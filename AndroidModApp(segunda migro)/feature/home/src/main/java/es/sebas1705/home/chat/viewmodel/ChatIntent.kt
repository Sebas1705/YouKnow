package es.sebas1705.home.chat.viewmodel


import es.sebas1705.common.classes.mvi.MVIBaseIntent
import es.sebas1705.models.social.UserModel

/**
 * Sealed interface that represents the possible actions of the [ChatViewModel].
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
sealed interface ChatIntent : MVIBaseIntent {

    data class SendMessage(
        val message: String,
        val userModel: UserModel
    ) : ChatIntent

    data object LoadChat : ChatIntent

    data object ClearChat : ChatIntent

}