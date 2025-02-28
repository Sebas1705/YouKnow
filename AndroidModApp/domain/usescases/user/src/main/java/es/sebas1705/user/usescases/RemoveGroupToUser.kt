package es.sebas1705.user.usescases
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

import es.sebas1705.common.utlis.extensions.types.catcher
import es.sebas1705.firestore.repository.FirestoreRepository
import es.sebas1705.models.social.GroupModel
import es.sebas1705.realtime.repository.RealtimeRepository

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