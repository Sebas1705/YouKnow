package es.sebas1705.firestore.documents

import com.google.firebase.firestore.util.CustomClassMapper
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Maps documents through Firestore's own CustomClassMapper (what `toObject` / `set` use), with the
 * field names the documents really have in the youknow-tfg project.
 */
class NewDocumentMappingTest {

    @Test
    fun `news documents with snake_case fields map onto NewDocument`() {
        val stored = mapOf(
            "title_es" to "Título",
            "title_en" to "Title",
            "body_es" to "Cuerpo",
            "body_en" to "Body",
        )

        val news = CustomClassMapper.convertToCustomClass(stored, NewDocument::class.java, null)

        assertEquals(NewDocument("Título", "Title", "Cuerpo", "Body"), news)
    }

    @Test
    fun `NewDocument serializes back to the stored field names`() {
        @Suppress("UNCHECKED_CAST")
        val serialized = CustomClassMapper.convertToPlainJavaTypes(
            NewDocument("Título", "Title", "Cuerpo", "Body")
        ) as Map<String, Any?>

        assertEquals(setOf("title_es", "title_en", "body_es", "body_en"), serialized.keys)
    }

    @Test
    fun `user documents keep the field names the security rules validate`() {
        @Suppress("UNCHECKED_CAST")
        val serialized = CustomClassMapper.convertToPlainJavaTypes(UserDocument()) as Map<String, Any?>

        assertEquals(
            setOf("email", "provider", "nickName", "photoUrl", "groupId", "points", "credits", "logged"),
            serialized.keys
        )
    }
}
