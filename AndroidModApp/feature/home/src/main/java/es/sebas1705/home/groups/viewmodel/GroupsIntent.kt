package es.sebas1705.home.groups.viewmodel
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

import es.sebas1705.common.mvi.MVIBaseIntent
import es.sebas1705.models.social.GroupModel
import es.sebas1705.models.social.UserModel

/**
 * Sealed interface that represents the possible actions of the [GroupsViewModel].
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface GroupsIntent : MVIBaseIntent {

    data class CreateGroup(
        val name: String,
        val description: String,
        val userModel: UserModel
    ) : GroupsIntent

    data class JoinGroup(
        val groupModel: GroupModel,
        val userModel: UserModel
    ) : GroupsIntent

    data class OutGroup(
        val groupModel: GroupModel,
        val userModel: UserModel
    ) : GroupsIntent

    data class KickGroup(
        val groupModel: GroupModel,
        val userToKickMemberId: String
    ) : GroupsIntent

    data class LoadGroups(
        val myGroupId: String?
    ) : GroupsIntent

    data object ClearGroups : GroupsIntent

}