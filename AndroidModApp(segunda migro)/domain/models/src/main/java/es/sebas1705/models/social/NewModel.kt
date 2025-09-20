package es.sebas1705.models.social


/**
 * Model to represent a new
 *
 * @property titleEs [String]: Title in spanish
 * @property titleEn [String]: Title in english
 * @property bodyEs [String]: Body in spanish
 * @property bodyEn [String]: Body in english
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
data class NewModel(
    val titleEs: String,
    val titleEn: String,
    val bodyEs: String,
    val bodyEn: String
)