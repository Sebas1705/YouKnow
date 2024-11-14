package es.sebas1705.youknow.domain.model

import es.sebas1705.youknow.data.firebase.realtime.jsons.GroupJson

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

/**
 * Model to represent a group
 *
 * @property name [String]: Name of the group
 * @property description [String]: Description of the group
 * @property members [List]<[Long]>: List of the members of the group
 * @property leaderUID [Long]: UID of the leader of the group
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class GroupModel(
    val name: String,
    val description: String,
    val members: List<String>,
    val leaderUID: String,
){

    fun toGroupJson(): GroupJson {
        return GroupJson(
            description = this.description,
            members = this.members,
        )
    }

    val groupId: String get() = this.name + "-" + this.leaderUID
}