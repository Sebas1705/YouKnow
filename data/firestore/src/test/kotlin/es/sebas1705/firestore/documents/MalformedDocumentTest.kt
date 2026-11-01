package es.sebas1705.firestore.documents

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.util.CustomClassMapper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner

/**
 * Bad documents go through Firestore's own CustomClassMapper (what `toObject` uses): the first tests
 * pin down that they really throw, the rest that the app survives them. Robolectric because the
 * mapper logs a warning for fields it ignores, like the `email` of old profiles.
 */
@RunWith(RobolectricTestRunner::class)
class MalformedDocumentTest {

    private val goodUser = mapOf<String, Any?>(
        "provider" to 1L, "nickName" to "ana", "photoUrl" to null, "groupId" to null,
        "points" to 5L, "credits" to 10L, "logged" to true,
    )
    private val goodQuestion = DailyChallengeQuestionDocument(
        "q", listOf("a", "b"), "a", "ANY", "MEDIUM", "MULTIPLE"
    )

    /** A snapshot whose `toObject` runs the real mapper over [data]. */
    private fun snapshotOf(data: Map<String, Any?>): DocumentSnapshot = mock {
        on { toObject(any<Class<Any>>()) } doAnswer {
            CustomClassMapper.convertToCustomClass(data, it.getArgument<Class<Any>>(0), null)
        }
    }

    private fun userFrom(data: Map<String, Any?>) =
        CustomClassMapper.convertToCustomClass(data, UserDocument::class.java, null)

    private fun challengeFrom(questions: Any?) = CustomClassMapper.convertToCustomClass(
        mapOf("date" to "2026-10-31", "questions" to questions),
        DailyChallengeDocument::class.java,
        null
    )

    @Test
    fun `the mapper throws on the documents that crashed the app`() {
        assertThrows(RuntimeException::class.java) { challengeFrom(listOf(1L, 2L)) }
        assertThrows(RuntimeException::class.java) { userFrom(goodUser + ("logged" to "x")) }
        assertThrows(RuntimeException::class.java) { userFrom(goodUser + ("points" to 2147483648L)) }
    }

    @Test
    fun `toObjectOrNull maps a good document`() {
        val messages = mutableListOf<String>()

        val user = snapshotOf(goodUser).toObjectOrNull(UserDocument::class.java) { messages += it }

        assertEquals(UserDocument(1, "ana", null, null, 5, 10, true), user)
        assertTrue(messages.isEmpty())
    }

    @Test
    fun `toObjectOrNull turns a bad document into null and reports only the class`() {
        val messages = mutableListOf<String>()
        val bad = goodUser + ("logged" to "x") + ("nickName" to "private-nick")

        val user = snapshotOf(bad).toObjectOrNull(UserDocument::class.java) { messages += it }

        assertNull(user)
        assertEquals(listOf("Malformed UserDocument ignored"), messages)
    }

    @Test
    fun `one bad document costs only itself in a list`() {
        val documents = listOf(
            snapshotOf(goodUser),
            snapshotOf(goodUser + ("logged" to "x")),
            snapshotOf(goodUser + ("points" to "lots")),
            snapshotOf(goodUser + ("nickName" to "bea")),
        )

        val ranking = documents.mapNotNull { it.toObjectOrNull(UserDocument::class.java) {} }

        assertEquals(listOf("ana", "bea"), ranking.map { it.nickName })
    }

    @Test
    fun `an old profile with an email still maps, and the email goes nowhere`() {
        val user = userFrom(goodUser + ("email" to "ana@example.com"))

        assertEquals("ana", user.nickName)
        assertFalse(user.toString().contains("ana@example.com"))
    }

    @Test
    fun `saved profiles carry no email`() {
        @Suppress("UNCHECKED_CAST")
        val serialized = CustomClassMapper.convertToPlainJavaTypes(UserDocument()) as Map<String, Any?>

        assertFalse("email" in serialized)
    }

    @Test
    fun `saved challenges only carry the fields the security rules allow`() {
        @Suppress("UNCHECKED_CAST")
        val serialized = CustomClassMapper.convertToPlainJavaTypes(
            DailyChallengeDocument("2026-10-31", listOf(goodQuestion))
        ) as Map<String, Any?>

        assertEquals(setOf("date", "questions"), serialized.keys)
    }

    @Test
    fun `a complete round is playable`() {
        assertTrue(DailyChallengeDocument("d", listOf(goodQuestion)).hasPlayableQuestions())
    }

    @Test
    fun `rounds that map fine but can't be played are detected`() {
        listOf(
            emptyList(),
            listOf(DailyChallengeQuestionDocument()),
            listOf(goodQuestion.copy(question = " ")),
            listOf(goodQuestion.copy(answers = emptyList())),
            listOf(goodQuestion.copy(answers = listOf("a"))),
            listOf(goodQuestion.copy(answers = listOf("a", ""))),
            listOf(goodQuestion.copy(correctAnswer = "z")),
            listOf(goodQuestion, goodQuestion.copy(correctAnswer = "z")),
        ).forEach { questions ->
            assertFalse(DailyChallengeDocument("d", questions).hasPlayableQuestions())
        }
    }

    @Test
    fun `an empty object as a question maps to something unplayable`() {
        assertFalse(challengeFrom(listOf(emptyMap<String, Any>())).hasPlayableQuestions())
    }
}
