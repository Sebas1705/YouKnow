package es.sebas1705.user.usescases

import es.sebas1705.common.states.DataState
import es.sebas1705.common.states.ErrorDataType
import es.sebas1705.common.utlis.alias.DataFlow
import es.sebas1705.common.utlis.extensions.types.awaitResult
import es.sebas1705.firestore.repository.FirestoreRepository
import es.sebas1705.realtime.repository.RealtimeRepository
import es.sebas1705.repositories.interfaces.IAuthenticationRepository
import es.sebas1705.room.repository.DatabaseRepository
import es.sebas1705.storage.repository.StorageRepository
import kotlin.coroutines.cancellation.CancellationException

/**
 * Outcome of [DeleteCurrentUser].
 *
 * @since 1.6.0
 * @author Sebas1705 11/11/2026
 */
sealed interface DeleteAccountResult {

    /** The user's data and their Firebase Authentication account are gone, and the session is over. */
    data object Success : DeleteAccountResult

    /** Nobody is signed in: there is nothing to delete. */
    data object NotSignedIn : DeleteAccountResult

    /**
     * Firebase refused to delete the account because the sign-in is not recent. The user's data
     * has already been deleted at that point: re-authenticate and run [DeleteCurrentUser] again to
     * finish (every step tolerates having been done already).
     */
    data object RecentLoginRequired : DeleteAccountResult

    /**
     * A step failed and the deletion stopped there. Running [DeleteCurrentUser] again is safe.
     *
     * @property message [String]: technical message, for logs rather than for the UI
     * @property dataDeleted [Boolean]: `true` when every piece of data was deleted and only the
     * Firebase Authentication account is left (so the user can still sign in, to a blank profile)
     */
    data class Failed(val message: String, val dataDeleted: Boolean) : DeleteAccountResult
}

/**
 * Use case to delete the signed-in user's account and what the app stores about them.
 *
 * The steps run in this order, stopping at the first one that fails:
 * 1. Leave the group in Realtime Database (`groups-youknow`): the user is removed from `members`;
 *    the group itself is deleted only when nobody is left. A leader who deletes their account while
 *    others remain does not dissolve the group, so it keeps a key ending in their uid until the
 *    last member leaves.
 * 2. Delete the chat messages the user wrote (`chat-global-youknow`, keys `<uid>-<time>`).
 * 3. Delete the profile photo in Cloud Storage (`profile_photos/<uid>`), if any.
 * 4. Delete the usability survey stored on this device (Room).
 * 5. Delete the profile document (`users/<uid>`). It goes last among the data, because the group is
 *    found through it: a retry after a failure in an earlier step still knows the group.
 * 6. Delete the Firebase Authentication account. It goes after all the data because the security
 *    rules need a signed-in user to allow the deletes above.
 *
 * What is NOT deleted: the published survey (`surveys/<uid>`), because the Firestore rules do not
 * let a client delete it, and the user's preferences on the device (volume, contrast, language),
 * which are not part of the account. See `docs/account-deletion.html`.
 *
 * Re-authenticate (`ReauthenticateWithPassword` or `ReauthenticateWithGoogle`) immediately before
 * calling this: Firebase refuses step 6 for a sign-in that is not recent, and by then the data is
 * already gone ([DeleteAccountResult.RecentLoginRequired]).
 *
 * @property authenticationRepository [IAuthenticationRepository]: repository to delete the account
 * @property firestoreRepository [FirestoreRepository]: repository for the profile document
 * @property realtimeRepository [RealtimeRepository]: repository for the group and the chat
 * @property storageRepository [StorageRepository]: repository for the profile photo
 * @property databaseRepository [DatabaseRepository]: repository for the data stored on the device
 *
 * @since 1.6.0
 * @author Sebas1705 11/11/2026
 */
class DeleteCurrentUser(
    private val authenticationRepository: IAuthenticationRepository,
    private val firestoreRepository: FirestoreRepository,
    private val realtimeRepository: RealtimeRepository,
    private val storageRepository: StorageRepository,
    private val databaseRepository: DatabaseRepository
) {

    suspend operator fun invoke(): DeleteAccountResult {
        val uid = authenticationRepository.getCurrentUser()?.uid ?: return DeleteAccountResult.NotSignedIn

        val dataFailure = deleteData(uid)
        if (dataFailure != null) return DeleteAccountResult.Failed(dataFailure, dataDeleted = false)

        val response = authenticationRepository.deleteCurrentUser().awaitResult()
        return when {
            response !is DataState.Error -> DeleteAccountResult.Success
            response.type == ErrorDataType.UNAUTHORIZED -> DeleteAccountResult.RecentLoginRequired
            else -> DeleteAccountResult.Failed(response.message, dataDeleted = true)
        }
    }

    /** Runs the data steps in order; the message of the first failure, or `null` when all passed. */
    private suspend fun deleteData(uid: String): String? =
        leaveGroup(uid)
            ?: failureOf(realtimeRepository.removeMessagesOf(uid))
            ?: failureOf(storageRepository.deleteProfilePhoto(uid))
            ?: deleteLocalSurvey(uid)
            ?: failureOf(firestoreRepository.deleteDataUser(uid))

    private suspend fun leaveGroup(uid: String): String? {
        val groupId = when (val response = firestoreRepository.getUserGroupId(uid).awaitResult()) {
            is DataState.Error -> return response.message
            is DataState.Success -> response.data ?: return null
            else -> return null
        }
        val group = when (val response = realtimeRepository.getGroup(groupId).awaitResult()) {
            is DataState.Error -> return response.message
            is DataState.Success -> response.data ?: return null // the group is already gone
            else -> return null
        }
        val remaining = group.members.filterNot { it == uid }
        if (uid in group.members) {
            failureOf(realtimeRepository.changeMembersToGroup(groupId, remaining))?.let { return it }
        }
        return if (remaining.isEmpty()) failureOf(realtimeRepository.removeGroup(groupId)) else null
    }

    private suspend fun deleteLocalSurvey(uid: String): String? = try {
        databaseRepository.deleteSurvey(uid)
        null
    } catch (cancellation: CancellationException) {
        throw cancellation
    } catch (exception: Exception) {
        exception.message ?: exception.javaClass.simpleName
    }

    private suspend fun failureOf(flow: DataFlow<*>): String? =
        (flow.awaitResult() as? DataState.Error)?.message
}
