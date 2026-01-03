package es.sebas1705.firestore.datasources

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import es.sebas1705.analytics.datasources.LogEventDataSource
import es.sebas1705.common.classes.TaskFlow
import es.sebas1705.common.states.ErrorDataType
import es.sebas1705.common.states.DataState
import es.sebas1705.common.utlis.alias.DataFlow
import es.sebas1705.common.utlis.alias.DataEmptyFlow
import es.sebas1705.common.utlis.extensions.types.logE
import es.sebas1705.firestore.config.SettingsFS
import es.sebas1705.firestore.documents.UserDocument
import javax.inject.Inject

/**
 * Data source for managing Firestore operations.
 *
 * @property firestore [FirebaseFirestore]: Firestore instance
 * @property logEventDataSource [LogEventDataSource]: Data source for logging events
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
class UserDocumentFirestoreDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val logEventDataSource: LogEventDataSource
) {

    //Listeners:
    private var userListener: ListenerRegistration? = null

    //Managers:
    private val taskFlow = TaskFlow(
        this.javaClass.kotlin,
        SettingsFS.ERROR_GENERIC_MESSAGE_FAIL,
        SettingsFS.ERROR_GENERIC_MESSAGE_EX
    ) { clazz, error ->
        logE(error)
        logEventDataSource.logError(clazz, error)
    }

    //References:
    private val usersReference = firestore.collection(SettingsFS.USERS_COLLECTION_NAME)
    
    //Tasks:
    /**
     * Save a user in Firestore
     *
     * @param firebaseId [String]: Id of the user to save
     * @param userDocument [UserDocument]: User to save
     *
     * @return [DataEmptyFlow]: Flow with the response of the operation
     */
    fun saveUser(
        firebaseId: String,
        userDocument: UserDocument
    ): DataEmptyFlow = taskFlow.taskFlowProducer(
        taskAction = {
            usersReference.document(firebaseId).set(userDocument)
        },
        onSuccessListener = { DataState.EmptySuccess }
    )
    
    /**
     * Get a user from Firestore
     *
     * @param userId [String]: Id of the user to get
     *
     * @return [DataFlow]<[UserDocument]>: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun getUser(
        userId: String
    ): DataFlow<UserDocument> = taskFlow.taskFlowProducer(
        taskAction = { usersReference.document(userId).get() },
        onSuccessListener = {
            val userDocument = it.toObject(UserDocument::class.java)
            if (userDocument != null) DataState.Success(userDocument)
            else taskFlow.createResponse(
                ErrorDataType.BAD_REQUEST,
                SettingsFS.USER_NOT_FOUND
            )
        }
    )

    /**
     * Add credits to a user
     *
     * @param userId [String]: Id of the user to add the credits
     * @param oldCredits [Int]: Old credits of the user
     * @param addCredits [Int]: Credits to add
     *
     * @return [DataFlow]<[Int]>: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun addCreditsToUser(
        userId: String,
        oldCredits: Int,
        addCredits: Int
    ): DataFlow<Int> = taskFlow.taskFlowProducer(
        assertChecker = { if (oldCredits + addCredits < 0) SettingsFS.ERROR_CREDITS_NEGATIVE else null },
        taskAction = {
            usersReference.document(userId)
                .update(SettingsFS.USERS_CREDITS_FIELD, oldCredits + addCredits)
        },
        onSuccessListener = { DataState.Success(oldCredits + addCredits) }
    )

    /**
     * Add points to a user
     *
     * @param userId [String]: Id of the user to add the points
     * @param addPoints [Int]: Points to add
     * @param oldPoints [Int]: Old points of the user
     *
     * @return [DataFlow]<[Int]>: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun addPointsToUser(
        userId: String,
        addPoints: Int,
        oldPoints: Int
    ): DataFlow<Int> = taskFlow.taskFlowProducer(
        taskAction = {
            usersReference.document(userId)
                .update(SettingsFS.USERS_POINTS_FIELD, oldPoints + addPoints)
        },
        onSuccessListener = { DataState.Success(oldPoints + addPoints) }
    )

    /**
     * Set the group of a user
     *
     * @param firebaseId [String]: Id of the user to set the group
     * @param groupId [String]: Id of the group to set
     *
     * @return [DataEmptyFlow]: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun setGroupToUser(
        firebaseId: String,
        groupId: String?
    ): DataEmptyFlow = taskFlow.taskFlowProducer(
        taskAction = {
            usersReference.document(firebaseId).update(SettingsFS.USERS_GROUP_FIELD, groupId)
        },
        onSuccessListener = { DataState.EmptySuccess }
    )

    /**
     * Remove the group of a user
     *
     * @param firebaseId [String]: Id of the user to remove the group
     *
     * @return [DataEmptyFlow]: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun removeGroupFromUser(
        firebaseId: String
    ): DataEmptyFlow = taskFlow.taskFlowProducer(
        taskAction = {
            usersReference.document(firebaseId).update(SettingsFS.USERS_GROUP_FIELD, null)
        },
        onSuccessListener = { DataState.EmptySuccess }
    )

    /**
     * Change the photo of a user
     *
     * @param firebaseId [String]: Id of the user to change the photo
     * @param photoUrl [String]: Url of the new photo
     *
     * @return [DataEmptyFlow]: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun changePhotoToUser(
        firebaseId: String,
        photoUrl: String
    ): DataEmptyFlow = taskFlow.taskFlowProducer(
        taskAction = {
            usersReference.document(firebaseId).update(SettingsFS.USERS_PHOTO_FIELD, photoUrl)
        },
        onSuccessListener = { DataState.EmptySuccess }
    )

    /**
     * Change the nickname of a user
     *
     * @param firebaseId [String]: Id of the user to change the nickname
     * @param nickname [String]: New nickname
     *
     * @return [DataEmptyFlow]: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun changeNicknameToUser(
        firebaseId: String,
        nickname: String
    ): DataEmptyFlow = taskFlow.taskFlowProducer(
        taskAction = {
            usersReference.document(firebaseId).update(SettingsFS.USERS_NICKNAME_FIELD, nickname)
        },
        onSuccessListener = { DataState.EmptySuccess }
    )

    /**
     * Get the group of a user
     *
     * @param firebaseId [String]: Id of the user to get the group
     *
     * @return [DataFlow]<[Boolean]>: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun containsUser(
        firebaseId: String
    ): DataFlow<Boolean> = taskFlow.taskFlowProducer(
        taskAction = { usersReference.document(firebaseId).get() },
        onSuccessListener = { DataState.Success(it.exists()) }
    )

    /**
     * Get the ranking of the users
     *
     * @return [DataFlow]<[List]<[Pair]<[String],[Int]>>>: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun getUserRanking(): DataFlow<List<Pair<String, Int>>> =
        taskFlow.taskFlowProducer(
            taskAction = { usersReference.orderBy(SettingsFS.USERS_POINTS_FIELD).get() },
            onSuccessListener = { snapshot ->
                val users = snapshot.documents.map { document ->
                    document.toObject(UserDocument::class.java)!!.let {
                        it.nickName to it.points
                    }
                }.reversed()
                DataState.Success(users)
            }
        )

    /**
     * Get a user by nickname
     *
     * @param nickname [String]: Nickname of the user to get
     *
     * @return [DataFlow]<[UserDocument]>: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun getUserByNickname(
        nickname: String
    ): DataFlow<Pair<UserDocument,String>> = taskFlow.taskFlowProducer(
        taskAction = {
            usersReference.whereEqualTo(SettingsFS.USERS_NICKNAME_FIELD, nickname).get()
        },
        onSuccessListener = {
            val userDocument = it.documents.firstOrNull()?.toObject(UserDocument::class.java)
            if (userDocument != null) DataState.Success(userDocument to it.documents.first().id)
            else taskFlow.createResponse(
                ErrorDataType.BAD_REQUEST,
                SettingsFS.USER_NOT_FOUND
            )
        }
    )

    /**
     * Delete the data of a user
     *
     * @param firebaseId [String]: Id of the user to delete the data
     *
     * @return [DataEmptyFlow]: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun deleteDataUser(
        firebaseId: String
    ): DataEmptyFlow = taskFlow.taskFlowProducer(
        taskAction = { usersReference.document(firebaseId).delete() },
        onSuccessListener = { DataState.EmptySuccess }
    )

    //Listeners:
    /**
     * Set a listener to get the user data
     *
     * @param firebaseId [String]: Id of the user to listen
     * @param onDataChange [([UserDocument]) -> [Unit]]: Function to call when the data changes
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun setUserListener(
        firebaseId: String,
        onDataChange: (UserDocument) -> Unit,
    ) {
        userListener =
            usersReference.document(firebaseId).addSnapshotListener { documentSnapshot, _ ->
                val userDocument = documentSnapshot?.toObject(UserDocument::class.java)
                if (userDocument != null) onDataChange(userDocument)
            }
    }

    /**
     * Remove the listener of the user data
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun removeUserListener() {
        userListener?.remove()
        userListener = null
    }

}