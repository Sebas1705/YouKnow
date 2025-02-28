package es.sebas1705.youknow.domain.usecases.user
/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import es.sebas1705.youknow.core.utlis.extensions.types.catcher
import es.sebas1705.youknow.data.firebase.firestore.repository.FirestoreRepository
import es.sebas1705.youknow.data.firebase.realtime.repository.RealtimeRepository
import es.sebas1705.youknow.domain.model.social.GroupModel
import es.sebas1705.youknow.domain.model.social.UserModel

/**
 * Use case to set a listener to get user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to get the user
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class SetUserListener(
    private val firestoreRepository: FirestoreRepository
) {
    operator fun invoke(
        firebaseId: String,
        onDataChange: (UserModel) -> Unit
    ) = firestoreRepository.setUserListener(firebaseId, onDataChange)
}

/**
 * Use case to remove the listener to get user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to remove the listener
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class RemoveUserListener(
    private val firestoreRepository: FirestoreRepository
) {
    operator fun invoke() = firestoreRepository.removeUserListener()
}

/**
 * Use case to save user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to save the user
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class SaveUser(
    private val firestoreRepository: FirestoreRepository,
) {
    suspend operator fun invoke(
        userModel: UserModel,
        onLoading: () -> Unit = {},
        onEmptySuccess: () -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.saveUser(userModel).catcher(onLoading, onEmptySuccess, onError)
}

/**
 * Use case to get user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to get the user
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class GetUser(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(
        firebaseId: String,
        onLoading: () -> Unit,
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.getUser(firebaseId).catcher(onLoading, onSuccess, onError)
}

/**
 * Use case to check if user exists
 *
 * @property firestoreRepository [FirestoreRepository]: repository to check if user exists
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class ContainsUser(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(
        firebaseId: String,
        onLoading: () -> Unit = {},
        onSuccess: (Boolean) -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.containsUser(firebaseId).catcher(onLoading, onSuccess, onError)
}

/**
 * Use case to add credits to user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to add credits to user
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class AddCreditsToUser(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(
        user: UserModel,
        creditsToAdd: Int,
        onLoading: () -> Unit = {},
        onSuccess: (Int) -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.addCreditsToUser(user.firebaseId, user.credits, creditsToAdd)
        .catcher(onLoading, onSuccess, onError)
}

/**
 * Use case to add points to user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to add points to user
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class AddPointsToUser(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(
        user: UserModel,
        pointsToAdd: Int,
        onLoading: () -> Unit = {},
        onSuccess: (Int) -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.addPointsToUser(user.firebaseId, user.points, pointsToAdd)
        .catcher(onLoading, onSuccess, onError)
}

/**
 * Use case to set a group to user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to set a group to user
 * @property realTimeRepository [RealtimeRepository]: repository to set a group to user
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class SetGroupToUser(
    private val firestoreRepository: FirestoreRepository,
    private val realTimeRepository: RealtimeRepository
) {
    suspend operator fun invoke(
        group: GroupModel,
        user: UserModel,
        creator: Boolean,
        onLoading: () -> Unit = {},
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.setGroupToUser(user.firebaseId, group.groupId)
        .catcher(
            onLoading,
            onEmptySuccess = {
                if (!creator) {
                    val list = group.members.toMutableList()
                    list.add(user.firebaseId)
                    val response =
                        if (group.members.isEmpty())
                            realTimeRepository.pushMembersToGroup(
                                group.groupId,
                                list.toList()
                            )
                        else
                            realTimeRepository.changeMembersToGroup(
                                group.groupId,
                                list.toList()
                            )
                    response.catcher(
                        onEmptySuccess = { onSuccess() },
                        onError = onError
                    )
                } else onSuccess()
            },
            onError
        )
}

/**
 * Use case to remove a group to user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to remove a group to user
 * @property realTimeRepository [RealtimeRepository]: repository to remove a group to user
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class RemoveGroupToUser(
    private val firestoreRepository: FirestoreRepository,
    private val realTimeRepository: RealtimeRepository
) {
    suspend operator fun invoke(
        group: GroupModel,
        firebaseId: String,
        onLoading: () -> Unit = {},
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.removeGroupFromUser(firebaseId)
        .catcher(
            onLoading,
            onEmptySuccess = {
                val list = group.members.toMutableList()
                list.remove(firebaseId)
                realTimeRepository.changeMembersToGroup(
                    group.groupId,
                    list.toList()
                ).catcher(
                    onEmptySuccess = { onSuccess() },
                    onError = onError
                )
            },
            onError
        )
}

/**
 * Use case to change photo to user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to change photo to user
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class ChangePhotoToUser(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(
        firebaseId: String,
        photoUrl: String,
        onLoading: () -> Unit = {},
        onEmptySuccess: () -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.changePhotoToUser(firebaseId, photoUrl)
        .catcher(onLoading, onEmptySuccess, onError)
}

/**
 * Use case to change nickname to user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to change nickname to user
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class ChangeNicknameToUser(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(
        firebaseId: String,
        nickname: String,
        onLoading: () -> Unit = {},
        onEmptySuccess: () -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.changeNicknameToUser(firebaseId, nickname)
        .catcher(onLoading, onEmptySuccess, onError)
}

/**
 * Use case to get user ranking
 *
 * @property firestoreRepository [FirestoreRepository]: repository to get user ranking
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class GetUserRanking(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(
        onLoading: () -> Unit,
        onSuccess: (List<Pair<String, Int>>) -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.getUserRanking().catcher(onLoading, onSuccess, onError)
}

/**
 * Use case to get user by nickname
 *
 * @property firestoreRepository [FirestoreRepository]: repository to get user by nickname
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class GetUserByNickname(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(
        nickname: String,
        onLoading: () -> Unit,
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.getUserByNickname(nickname).catcher(onLoading, onSuccess, onError)
}

/**
 * Use case to delete data user
 *
 * @property firestoreRepository [FirestoreRepository]: repository to delete data user
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class DeleteDataUser(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(
        firebaseId: String,
        onLoading: () -> Unit = {},
        onEmptySuccess: () -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.deleteDataUser(firebaseId)
        .catcher(onLoading, onEmptySuccess, onError)
}

/**
 * Use cases to user
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
data class UserUsesCases(
    //Setters:
    val setUserListener: SetUserListener,
    val removeUserListener: RemoveUserListener,
    val saveUser: SaveUser,
    val setGroupToUser: SetGroupToUser,
    val removeGroupToUser: RemoveGroupToUser,
    val addCreditsToUser: AddCreditsToUser,
    val addPointsToUser: AddPointsToUser,
    val changePhotoToUser: ChangePhotoToUser,
    val changeNicknameToUser: ChangeNicknameToUser,
    val deleteDataUser: DeleteDataUser,
    //Getters:
    val getUser: GetUser,
    val containsUser: ContainsUser,
    val getUserRanking: GetUserRanking,
    val getUserByNickname: GetUserByNickname
)