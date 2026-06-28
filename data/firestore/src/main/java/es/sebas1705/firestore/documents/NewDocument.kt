package es.sebas1705.firestore.documents

/**
 * Data class to represent a new document
 *
 * @property titleES [String]: Title in Spanish
 * @property titleEN [String]: Title in English
 * @property bodyES [String]: Body in Spanish
 * @property bodyEN [String]: Body in English
 *
 * @since 1.0.0
 * @author Sebas1705 22/09/2025
 */
data class NewDocument(
    val titleES: String = "",
    val titleEN: String = "",
    val bodyES: String = "",
    val bodyEN: String = "",
)