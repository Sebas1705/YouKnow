package es.sebas1705.models.social


/**
 * Model to represent a message
 *
 * @property text [String]: Text of the message
 * @property time [Long]: Time of the message
 * @property authorId [String]: Id of the author of the message
 * @property authorName [String]: Name of the author of the message
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
data class MessageModel(
    val text: String,
    val time: Long,
    val authorId: String,
    val authorName: String,
) {

    /**
     * Get the message id
     *
     * @return [String]: Message id
     */
    val messageId: String get() = this.authorId.toString() + "-" + this.time.toString()
}