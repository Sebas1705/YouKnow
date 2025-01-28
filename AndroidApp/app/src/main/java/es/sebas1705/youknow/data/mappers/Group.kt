package es.sebas1705.youknow.data.mappers
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

import es.sebas1705.youknow.data.firebase.realtime.jsons.GroupJson
import es.sebas1705.youknow.domain.model.social.GroupModel


// MODEL

/**
 * Mapper function
 *
 * @receiver [GroupModel]: The object to convert
 *
 * @return [GroupJson]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun GroupModel.toGroupJson(): GroupJson {
    return GroupJson(
        description = this.description,
        members = this.members,
    )
}

// JSON

/**
 * Mapper function
 *
 * @receiver [GroupJson]: The object to convert
 * @param groupId [String]: The group id
 *
 * @return [GroupModel]: The converted object
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun GroupJson.toGroupModel(groupId: String): GroupModel {
    val groupData = groupId.split("-")
    return GroupModel(
        name = groupData[0],
        description = this.description,
        members = this.members,
        leaderUID = groupData[1],
    )
}