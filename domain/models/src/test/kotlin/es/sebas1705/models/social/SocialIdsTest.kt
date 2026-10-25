package es.sebas1705.models.social

import org.junit.Assert.assertEquals
import org.junit.Test

/** The realtime database keys are derived from the models, so their shape is part of the contract. */
class SocialIdsTest {

    @Test
    fun `group id is the name plus the first segment of the leader uid`() {
        val group = GroupModel("friends", "desc", listOf("abc-def-123"), "abc-def-123")

        assertEquals("friends-abc", group.groupId)
    }

    @Test
    fun `message id is the author id plus the timestamp`() {
        val message = MessageModel("hi", 1_700_000_000_000, "user-1", "Ana")

        assertEquals("user-1-1700000000000", message.messageId)
    }
}
