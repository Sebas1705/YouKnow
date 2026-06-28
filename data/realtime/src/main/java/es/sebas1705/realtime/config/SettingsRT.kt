package es.sebas1705.realtime.config

/**
 * Realtime references used in the app
 *
 * @property DEFAULT_REFERENCE [String]: Default reference
 * @property CHAT_GLOBAL_REFERENCE [String]: Global chat reference
 * @property GROUPS_REFERENCE [String]: Groups reference
 * @property MEMBERS_REFERENCE [String]: Members reference
 * @property MESSAGE_MAX_LENGTH [Int]: Max length of a message
 * @property MAX_MESSAGES_ON_GLOBAL_CHAT [Int]: Max messages on global chat
 * @property ERROR_GENERIC_MESSAGE_EX [String]: Generic error message by exception
 * @property ERROR_GENERIC_MESSAGE_FAIL [String]: Generic error message by failure listener
 *
 * @since 1.0.0
 * @author Sebas1705 22/09/2025
 */
object SettingsRT {
    const val DEFAULT_REFERENCE = "default"
    const val CHAT_GLOBAL_REFERENCE = "chat-global-youknow"
    const val GROUPS_REFERENCE = "groups-youknow"
    const val MEMBERS_REFERENCE = "members"
    const val MESSAGE_MAX_LENGTH = 50
    const val MAX_MESSAGES_ON_GLOBAL_CHAT = 100

    const val ERROR_GENERIC_MESSAGE_EX = "An error occurred on realtime by an exception"
    const val ERROR_GENERIC_MESSAGE_FAIL = "An error occurred on realtime by failure listener"
}

