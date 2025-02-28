package es.sebas1705.groups.usescases
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

import es.sebas1705.models.social.GroupModel
import es.sebas1705.realtime.repository.RealtimeRepository

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
        realtimeRepository.removeGroup(groupModel.groupId).collect { flow ->
            flow.catcher(
                onEmptySuccess = { onSuccess() },
                onError = { onError(it.message) },
            )
        }
    }
}