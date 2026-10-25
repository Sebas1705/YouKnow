package es.sebas1705.mappers

import es.sebas1705.models.social.MessageModel
import es.sebas1705.realtime.jsons.MessageJson
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * A realtime message key is `<authorId>-<timestamp>`; the author id itself may contain dashes, so
 * only the last one separates the two parts.
 */
class MessageMapperTest {

    @Test
    fun `model to json keeps only text and author name`() {
        val json = MessageModel("hi", 1_700_000_000_000, "uid", "Ana").toMessageJson()

        assertEquals(MessageJson(text = "hi", authorName = "Ana"), json)
    }

    @Test
    fun `json to model splits the key on its last dash`() {
        val model = MessageJson("hi", "Ana").toMessageModel("abc-def-ghi-1700000000000")

        assertEquals("abc-def-ghi", model.authorId)
        assertEquals(1_700_000_000_000, model.time)
        assertEquals("hi", model.text)
        assertEquals("Ana", model.authorName)
    }

    @Test
    fun `model survives a round trip through its own message id`() {
        val model = MessageModel("hola", 42L, "user-1", "Luis")

        assertEquals(model, model.toMessageJson().toMessageModel(model.messageId))
    }
}
