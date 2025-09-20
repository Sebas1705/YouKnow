package es.sebas1705.chat


import es.sebas1705.chat.usescases.RemoveMessagesListener
import es.sebas1705.chat.usescases.SendMessage
import es.sebas1705.chat.usescases.SetMessagesListener

/**
 * Data class to group the chat use cases
 *
 * @param sendMessage [SendMessage]: use case to send a message
 * @param setMessagesListener [SetMessagesListener]: use case to set a listener to get messages
 * @param removeMessagesListener [RemoveMessagesListener]: use case to remove the listener to get messages
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
data class ChatUsesCases(
    val sendMessage: SendMessage,
    val setMessagesListener: SetMessagesListener,
    val removeMessagesListener: RemoveMessagesListener
)