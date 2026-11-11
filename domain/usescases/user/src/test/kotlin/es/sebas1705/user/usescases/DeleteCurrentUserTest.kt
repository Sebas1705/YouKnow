package es.sebas1705.user.usescases

import com.google.firebase.auth.FirebaseUser
import es.sebas1705.common.states.DataState
import es.sebas1705.common.states.ErrorDataType
import es.sebas1705.common.utlis.alias.DataEmptyFlow
import es.sebas1705.common.utlis.alias.DataFlow
import es.sebas1705.firestore.repository.FirestoreRepository
import es.sebas1705.realtime.jsons.GroupJson
import es.sebas1705.realtime.repository.RealtimeRepository
import es.sebas1705.repositories.interfaces.IAuthenticationRepository
import es.sebas1705.room.repository.DatabaseRepository
import es.sebas1705.storage.repository.StorageRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

/**
 * [DeleteCurrentUser] against mocked repositories: what is deleted, in which order, and where it
 * stops when a step fails. By default the user is the only member of the group `quiz-uid-9`.
 */
class DeleteCurrentUserTest {

    private val auth: IAuthenticationRepository = mock()
    private val firestore: FirestoreRepository = mock()
    private val realtime: RealtimeRepository = mock()
    private val storage: StorageRepository = mock()
    private val database: DatabaseRepository = mock()

    private val useCase = DeleteCurrentUser(auth, firestore, realtime, storage, database)

    private fun ok(): DataEmptyFlow = flowOf(DataState.Loading, DataState.EmptySuccess)

    private fun <T> value(data: T): DataFlow<T> = flowOf(DataState.Loading, DataState.Success(data))

    private fun failure(type: ErrorDataType = ErrorDataType.BAD_REQUEST, message: String = "boom"): DataEmptyFlow =
        flowOf(
            DataState.Loading,
            DataState.Error(DeleteCurrentUserTest::class, type, message) { _, _ -> }
        )

    private fun signedInAs(uid: String?) {
        val user: FirebaseUser? = uid?.let { id -> mock { on { this.uid } doReturn id } }
        whenever(auth.getCurrentUser()).thenReturn(user)
    }

    /** Every step succeeds and the user is the only member of a group. */
    @Before
    fun setUp() = runTest {
        signedInAs(UID)
        whenever(firestore.getUserGroupId(UID)).thenReturn(value<String?>(GROUP_ID))
        whenever(realtime.getGroup(GROUP_ID)).thenReturn(value<GroupJson?>(GroupJson("", listOf(UID))))
        whenever(realtime.changeMembersToGroup(any(), any())).thenReturn(ok())
        whenever(realtime.removeGroup(any())).thenReturn(ok())
        whenever(realtime.removeMessagesOf(UID)).thenReturn(ok())
        whenever(storage.deleteProfilePhoto(UID)).thenReturn(ok())
        whenever(database.deleteSurvey(UID)).thenReturn(1)
        whenever(firestore.deleteDataUser(UID)).thenReturn(ok())
        whenever(auth.deleteCurrentUser()).thenReturn(ok())
    }

    @Test
    fun `deletes everything in order and the account last`() = runTest {
        assertEquals(DeleteAccountResult.Success, useCase())

        inOrder(realtime, storage, database, firestore, auth) {
            verify(realtime).changeMembersToGroup(GROUP_ID, emptyList())
            verify(realtime).removeGroup(GROUP_ID)
            verify(realtime).removeMessagesOf(UID)
            verify(storage).deleteProfilePhoto(UID)
            verify(database).deleteSurvey(UID)
            verify(firestore).deleteDataUser(UID)
            verify(auth).deleteCurrentUser()
        }
    }

    @Test
    fun `nobody signed in deletes nothing`() = runTest {
        signedInAs(null)

        assertEquals(DeleteAccountResult.NotSignedIn, useCase())

        verifyNoInteractions(firestore, realtime, storage, database)
        verify(auth, never()).deleteCurrentUser()
    }

    @Test
    fun `a user without a group skips the group steps`() = runTest {
        whenever(firestore.getUserGroupId(UID)).thenReturn(value<String?>(null))

        assertEquals(DeleteAccountResult.Success, useCase())

        verify(realtime, never()).getGroup(any())
        verify(realtime, never()).changeMembersToGroup(any(), any())
        verify(realtime, never()).removeGroup(any())
        verify(firestore).deleteDataUser(UID)
    }

    @Test
    fun `a group that no longer exists is left alone`() = runTest {
        whenever(realtime.getGroup(GROUP_ID)).thenReturn(value<GroupJson?>(null))

        assertEquals(DeleteAccountResult.Success, useCase())

        verify(realtime, never()).changeMembersToGroup(any(), any())
        verify(realtime, never()).removeGroup(any())
    }

    @Test
    fun `a member leaving a group with others only drops their membership`() = runTest {
        whenever(realtime.getGroup(GROUP_ID))
            .thenReturn(value<GroupJson?>(GroupJson("", listOf("uid-2", UID, "uid-3"))))

        assertEquals(DeleteAccountResult.Success, useCase())

        verify(realtime).changeMembersToGroup(GROUP_ID, listOf("uid-2", "uid-3"))
        verify(realtime, never()).removeGroup(any())
    }

    @Test
    fun `the leader leaving a group with others does not dissolve it`() = runTest {
        // The group id ends in the leader's uid: the leader is still just one of the members.
        val leaderGroup = "quiz-$UID"
        whenever(firestore.getUserGroupId(UID)).thenReturn(value<String?>(leaderGroup))
        whenever(realtime.getGroup(leaderGroup))
            .thenReturn(value<GroupJson?>(GroupJson("", listOf(UID, "uid-2"))))

        assertEquals(DeleteAccountResult.Success, useCase())

        verify(realtime).changeMembersToGroup(leaderGroup, listOf("uid-2"))
        verify(realtime, never()).removeGroup(any())
    }

    @Test
    fun `the last member leaving deletes the empty group`() = runTest {
        assertEquals(DeleteAccountResult.Success, useCase())

        verify(realtime).changeMembersToGroup(GROUP_ID, emptyList())
        verify(realtime).removeGroup(GROUP_ID)
    }

    @Test
    fun `an empty group the user was not listed in is deleted without rewriting members`() = runTest {
        whenever(realtime.getGroup(GROUP_ID)).thenReturn(value<GroupJson?>(GroupJson("", emptyList())))

        assertEquals(DeleteAccountResult.Success, useCase())

        verify(realtime, never()).changeMembersToGroup(any(), any())
        verify(realtime).removeGroup(GROUP_ID)
    }

    @Test
    fun `a failure reading the group stops before deleting anything`() = runTest {
        whenever(firestore.getUserGroupId(UID)).thenReturn(
            flowOf(DataState.Loading, DataState.Error(DeleteCurrentUserTest::class, ErrorDataType.BAD_REQUEST, "offline") { _, _ -> })
        )

        assertEquals(DeleteAccountResult.Failed("offline", dataDeleted = false), useCase())

        verify(firestore, never()).deleteDataUser(any())
        verify(auth, never()).deleteCurrentUser()
    }

    @Test
    fun `a failure leaving the group keeps the profile and the account so a retry still finds the group`() = runTest {
        whenever(realtime.changeMembersToGroup(any(), any())).thenReturn(failure(message = "denied"))

        assertEquals(DeleteAccountResult.Failed("denied", dataDeleted = false), useCase())

        verify(realtime, never()).removeMessagesOf(any())
        verify(firestore, never()).deleteDataUser(any())
        verify(auth, never()).deleteCurrentUser()
    }

    @Test
    fun `a failure deleting the messages stops there`() = runTest {
        whenever(realtime.removeMessagesOf(UID)).thenReturn(failure(message = "chat"))

        assertEquals(DeleteAccountResult.Failed("chat", dataDeleted = false), useCase())

        verify(storage, never()).deleteProfilePhoto(any())
        verify(firestore, never()).deleteDataUser(any())
        verify(auth, never()).deleteCurrentUser()
    }

    @Test
    fun `a failure deleting the photo stops there`() = runTest {
        whenever(storage.deleteProfilePhoto(UID)).thenReturn(failure(message = "storage"))

        assertEquals(DeleteAccountResult.Failed("storage", dataDeleted = false), useCase())

        verify(firestore, never()).deleteDataUser(any())
        verify(auth, never()).deleteCurrentUser()
    }

    @Test
    fun `a failure on the local database stops there`() = runTest {
        whenever(database.deleteSurvey(UID)).doThrow(IllegalStateException("room"))

        assertEquals(DeleteAccountResult.Failed("room", dataDeleted = false), useCase())

        verify(firestore, never()).deleteDataUser(any())
        verify(auth, never()).deleteCurrentUser()
    }

    @Test
    fun `a failure deleting the profile keeps the account`() = runTest {
        whenever(firestore.deleteDataUser(UID)).thenReturn(failure(message = "firestore"))

        assertEquals(DeleteAccountResult.Failed("firestore", dataDeleted = false), useCase())

        verify(auth, never()).deleteCurrentUser()
    }

    @Test
    fun `an old sign-in is reported after the data is gone`() = runTest {
        whenever(auth.deleteCurrentUser()).thenReturn(failure(ErrorDataType.UNAUTHORIZED))

        assertEquals(DeleteAccountResult.RecentLoginRequired, useCase())

        verify(firestore).deleteDataUser(UID)
    }

    @Test
    fun `any other failure deleting the account says the data is already deleted`() = runTest {
        whenever(auth.deleteCurrentUser()).thenReturn(failure(message = "network"))

        assertEquals(DeleteAccountResult.Failed("network", dataDeleted = true), useCase())
    }

    @Test
    fun `retrying after the account step failed finishes the deletion`() = runTest {
        whenever(auth.deleteCurrentUser())
            .thenReturn(failure(ErrorDataType.UNAUTHORIZED))
            .thenReturn(ok())

        assertEquals(DeleteAccountResult.RecentLoginRequired, useCase())

        // The profile is gone now, and with it the group id: the group steps have nothing to do.
        whenever(firestore.getUserGroupId(UID)).thenReturn(value<String?>(null))

        assertEquals(DeleteAccountResult.Success, useCase())
        verify(auth, times(2)).deleteCurrentUser()
    }

    private companion object {
        const val UID = "uid-1"
        const val GROUP_ID = "quiz-uid-9"
    }
}
