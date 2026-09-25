package es.sebas1705.firestore.documents

import com.google.firebase.firestore.PropertyName

/**
 * Data class to represent a new document
 *
 * @property titleES [String]: Title in Spanish
 * @property titleEN [String]: Title in English
 * @property bodyES [String]: Body in Spanish
 * @property bodyEN [String]: Body in English
 *
 * The news documents in Firestore use snake_case field names (`title_es`, …), hence the
 * [PropertyName] annotations.
 *
 * @since 1.0.0
 * @author Sebas1705 22/09/2025
 */
data class NewDocument(
    @get:PropertyName("title_es") @field:PropertyName("title_es")
    val titleES: String = "",
    @get:PropertyName("title_en") @field:PropertyName("title_en")
    val titleEN: String = "",
    @get:PropertyName("body_es") @field:PropertyName("body_es")
    val bodyES: String = "",
    @get:PropertyName("body_en") @field:PropertyName("body_en")
    val bodyEN: String = "",
)