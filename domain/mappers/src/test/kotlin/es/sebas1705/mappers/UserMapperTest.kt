package es.sebas1705.mappers

import es.sebas1705.core.resources.ProviderAuth
import es.sebas1705.firestore.documents.UserDocument
import es.sebas1705.models.social.UserModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UserMapperTest {

    private val document = UserDocument(
        email = "ana@example.com",
        provider = ProviderAuth.GOOGLE.ordinal,
        nickName = "ana",
        photoUrl = "https://example.com/ana.png",
        groupId = "group-1",
        points = 120,
        credits = 30,
        logged = false
    )

    @Test
    fun `document to model attaches the firebase id and resolves the provider`() {
        val model = document.toUserModel("uid-1")

        assertEquals("uid-1", model.firebaseId)
        assertEquals(ProviderAuth.GOOGLE, model.provider)
        assertEquals(document.email, model.email)
        assertEquals(document.nickName, model.nickName)
        assertEquals(document.photoUrl, model.photoUrl)
        assertEquals(document.groupId, model.groupId)
        assertEquals(document.points, model.points)
        assertEquals(document.credits, model.credits)
    }

    @Test
    fun `model to document marks the user as logged`() {
        val saved = UserModel.default().toUserDocument()

        assertTrue(saved.logged)
        assertEquals(ProviderAuth.EMAIL.ordinal, saved.provider)
    }

    @Test
    fun `document round trip keeps every field but the logged flag`() {
        assertEquals(document.copy(logged = true), document.toUserModel("uid").toUserDocument())
    }
}
