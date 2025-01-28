package es.sebas1705.youknow.domain.usecases.social
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

import es.sebas1705.youknow.data.firebase.realtime.repository.RealtimeRepository
import es.sebas1705.youknow.domain.model.social.GroupModel
import es.sebas1705.youknow.domain.model.social.UserModel

/**
 * Use case to create a group
 *
 * @property realtimeRepository [RealtimeRepository]: repository to create the group
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class CreateGroup(
    private val realtimeRepository: RealtimeRepository
) {
    suspend operator fun invoke(
        name: String,
        description: String,
        userModel: UserModel,
        onSuccess: suspend (GroupModel) -> Unit,
        onError: (String) -> Unit
    ) {
        val groupModel = GroupModel(
            name = name,
            description = description,
            members = listOf(userModel.memberId()),
            leaderUID = userModel.memberId()
        )
        realtimeRepository.addGroup(groupModel).collect {
            it.catcher(
                onEmptySuccess = { onSuccess(groupModel) },
                onError = { onError(it.message) },
            )
        }
    }
}

/**
 * Use case to remove a group
 *
 * @property realtimeRepository [RealtimeRepository]: repository to remove the group
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class RemoveGroup(
    private val realtimeRepository: RealtimeRepository
) {
    suspend operator fun invoke(
        groupModel: GroupModel,
        onSuccess: suspend () -> Unit,
        onError: (String) -> Unit
    ) {
        realtimeRepository.removeGroup(groupModel.groupId).collect {
            it.catcher(
                onEmptySuccess = { onSuccess() },
                onError = { onError(it.message) },
            )
        }
    }
}

/**
 * Use case to set a listener to get groups
 *
 * @property realtimeRepository [RealtimeRepository]: repository to get groups
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class SetGroupsListener(
    private val realtimeRepository: RealtimeRepository
) {
    operator fun invoke(
        onSuccess: (List<GroupModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        realtimeRepository.setGroupsListener(
            onDataChange = onSuccess,
            onCancelled = onError
        )
    }
}

/**
 * Use case to remove the listener to get groups
 *
 * @property realtimeRepository [RealtimeRepository]: repository to remove the listener
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class RemoveGroupsListener(
    private val realtimeRepository: RealtimeRepository
) {
    operator fun invoke() {
        realtimeRepository.removeGroupsListener()
    }
}

/**
 * Use cases for the groups
 *
 * @property createGroup [CreateGroup]: Use case to create a group
 * @property removeGroup [RemoveGroup]: Use case to remove a group
 * @property setGroupsListener [SetGroupsListener]: Use case to set a listener to get groups
 * @property removeGroupsListener [RemoveGroupsListener]: Use case to remove the listener to get groups
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
data class GroupUsesCases(
    val createGroup: CreateGroup,
    val removeGroup: RemoveGroup,
    val setGroupsListener: SetGroupsListener,
    val removeGroupsListener: RemoveGroupsListener
)