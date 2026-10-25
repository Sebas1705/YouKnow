package es.sebas1705.user.usescases

import es.sebas1705.common.states.DataState
import es.sebas1705.common.states.ErrorDataType
import es.sebas1705.common.utlis.alias.DataFlow
import es.sebas1705.core.resources.ProviderAuth
import es.sebas1705.firestore.documents.UserDocument
import es.sebas1705.firestore.repository.FirestoreRepository
import es.sebas1705.models.social.UserModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

/** GetUser, AddPointsToUser and AddCreditsToUser, the three use cases every game screen ends with. */
class UserPointsAndCreditsTest {

    private val firestore: FirestoreRepository = mock()

    private val user = UserModel.default().copy(firebaseId = "uid-1", points = 40, credits = 7)

    private fun <T> success(value: T): DataFlow<T> = flowOf(DataState.Loading, DataState.Success(value))

    private fun <T> failure(message: String): DataFlow<T> = flowOf(
        DataState.Loading,
        DataState.Error(UserPointsAndCreditsTest::class, ErrorDataType.INTERNAL, message) { _, _ -> }
    )

    @Test
    fun `getUser maps the document onto a model with the requested id`() = runTest {
        whenever(firestore.getUser("uid-1")).thenReturn(
            success(UserDocument("ana@example.com", ProviderAuth.GOOGLE.ordinal, "ana", null, "g", 10, 20, true))
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
    fun `addPointsToUser sends the current and added points and returns the new total`() = runTest {
        whenever(firestore.addPointsToUser("uid-1", 40, 15)).thenReturn(success(55))
        var total: Int? = null

        AddPointsToUser(firestore)(user, 15, onSuccess = { total = it }, onError = { error(it) })

        assertEquals(55, total)
    }

    @Test
    fun `addPointsToUser surfaces repository errors`() = runTest {
        whenever(firestore.addPointsToUser("uid-1", 40, 15)).thenReturn(failure("offline"))
        var error: String? = null

        AddPointsToUser(firestore)(user, 15, onSuccess = { error("unexpected") }, onError = { error = it })

        assertEquals("offline", error)
    }

    @Test
    fun `addCreditsToUser sends the current and added credits and returns the new total`() = runTest {
        whenever(firestore.addCreditsToUser("uid-1", 7, 3)).thenReturn(success(10))
        var total: Int? = null

        AddCreditsToUser(firestore)(user, 3, onSuccess = { total = it }, onError = { error(it) })

        assertEquals(10, total)
    }
}
