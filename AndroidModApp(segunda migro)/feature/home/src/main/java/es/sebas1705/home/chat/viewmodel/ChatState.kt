package es.sebas1705.home.chat.viewmodel


import es.sebas1705.common.mvi.MVIBaseState
import es.sebas1705.models.social.MessageModel

/**
 * State of the [ChatViewModel] that will handle the data of the screen.
 *
 * @property isLoading [Boolean]: True if the screen is loading data.
 * @property chatGlobal [List]<[MessageModel]>: List of messages from the global chat.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
data class ChatState(
    val isLoading: Boolean,
    val chatGlobal: List<MessageModel>,
) : MVIBaseState {
    companion object {

        /**
         * Default state of the [ChatViewModel].
         *
         * @return [ChatState]: Default state.
         *
         * @since 1.0.0
         * @author Sebas1705 12/09/2025
         */
        fun default() = ChatState(
            isLoading = false,
            chatGlobal = emptyList(),
        )
    }
}