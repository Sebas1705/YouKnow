package es.sebas1705.realtime.jsons

/**
 * JSON representation of a message
 *
 * @property text [String]: Text of the message
 * @property authorName [String]: Name of the author
 *
 * @author Sebas1705 22/09/2025
 * @since 1.0.0
 */
data class MessageJson(
    val text: String = "",
    val authorName: String = "",
)