package es.sebas1705.chat

data class ChatUsesCases(
    val sendMessage: SendMessage,
    val setMessagesListener: SetMessagesListener,
    val removeMessagesListener: RemoveMessagesListener
)
