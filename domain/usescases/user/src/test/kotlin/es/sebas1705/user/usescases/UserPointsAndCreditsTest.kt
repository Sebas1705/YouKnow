package es.sebas1705.user.usescases

import es.sebas1705.common.states.DataState
import es.sebas1705.common.states.ErrorDataType
import es.sebas1705.common.utlis.alias.DataEmptyFlow
import es.sebas1705.common.utlis.alias.DataFlow
import es.sebas1705.core.resources.ProviderAuth
import es.sebas1705.firestore.documents.UserDocument
import es.sebas1705.firestore.repository.FirestoreRepository
import es.sebas1705.models.social.UserModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

/** GetUser, AddRoundRewards and AddCreditsToUser, the use cases every game screen ends with. */
class UserPointsAndCreditsTest {

    private val firestore: FirestoreRepository = mock()

    private val user = UserModel.default().copy(firebaseId = "uid-1", points = 40, credits = 7)

    private fun <T> success(value: T): DataFlow<T> = flowOf(DataState.Loading, DataState.Success(value))

    private fun emptySuccess(): DataEmptyFlow = flowOf(DataState.Loading, DataState.EmptySuccess)

    private fun <T> failure(message: String): DataFlow<T> = flowOf(
        DataState.Loading,
        DataState.Error(UserPointsAndCreditsTest::class, ErrorDataType.INTERNAL, message) { _, _ -> }
    )

    @Test
    fun `getUser maps the document onto a model with the requested id`() = runTest {
        whenever(firestore.getUser("uid-1")).thenReturn(
            success(UserDocument(ProviderAuth.GOOGLE.ordinal, "ana", null, "g", 10, 20, true))
        )
        val events = mutableListOf<String>()
        var result: UserModel? = null

        GetUser(firestore)("uid-1", onLoading = { events += "loading" }, onSuccess = { result = it; events += "success" }, onError = { events += "error" })

        assertEquals(listOf("loading", "success"), events)
        assertEquals("uid-1", result?.firebaseId)
        assertEquals(ProviderAuth.GOOGLE, result?.provider)
        assertEquals(10, result?.points)
        assertEquals(20, result?.credits)
    }

    @Test
    fun `getUser surfaces repository errors`() = runTest {
        whenever(firestore.getUser("uid-1")).thenReturn(failure("not found"))
        var result: UserModel? = null
        var error: String? = null

        GetUser(firestore)("uid-1", onLoading = {}, onSuccess = { result = it }, onError = { error = it })

        assertNull(result)
        assertEquals("not found", error)
    }

    @Test
    fun `addRoundRewards sends the current totals and what the round earned`() = runTest {
        whenever(firestore.addRoundRewards("uid-1", 40, 7, 15, 1, null)).thenReturn(emptySuccess())
        var stored = false

        AddRoundRewards(firestore)(user, 15, 1, onSuccess = { stored = true }, onError = { error(it) })

        assertTrue(stored)
        verify(firestore).addRoundRewards("uid-1", 40, 7, 15, 1, null)
    }

    @Test
    fun `addRoundRewards passes the day of the daily challenge`() = runTest {
        whenever(firestore.addRoundRewards("uid-1", 40, 7, 15, 1, "2026-11-11")).thenReturn(emptySuccess())
        var stored = false

        AddRoundRewards(firestore)(user, 15, 1, dailyDate = "2026-11-11", onSuccess = { stored = true }, onError = { error(it) })

        assertTrue(stored)
    }

    @Test
    fun `addRoundRewards surfaces the error when the rules refuse the round`() = runTest {
        whenever(firestore.addRoundRewards("uid-1", 40, 7, 15, 1, null)).thenReturn(failure("PERMISSION_DENIED"))
        var error: String? = null

        AddRoundRewards(firestore)(user, 15, 1, onSuccess = { error("unexpected") }, onError = { error = it })

        assertEquals("PERMISSION_DENIED", error)
    }

    @Test
    fun `addCreditsToUser sends the current and added credits and returns the new total`() = runTest {
        whenever(firestore.addCreditsToUser("uid-1", 7, 3)).thenReturn(success(10))
        var total: Int? = null

        AddCreditsToUser(firestore)(user, 3, onSuccess = { total = it }, onError = { error(it) })

        assertEquals(10, total)
    }
}
