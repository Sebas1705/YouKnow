package es.sebas1705.youknow.data.firebase.realtime.repository
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

import es.sebas1705.youknow.core.utlis.FlowResponseNothing
import es.sebas1705.youknow.data.model.ResponseState
import es.sebas1705.youknow.domain.model.GroupModel
import es.sebas1705.youknow.domain.model.MessageModel
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface to write and read data from the realtime database
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
interface RealtimeRepository {

    //Tasks:
    /**
     * Add a message to the global chat
     *
     * @param value [MessageModel]: Message to add
     *
     * @return [Flow] with the response of the operation
     *
     * @see MessageModel
     * @see Flow
     */
    fun addMessageToGlobalChat(value: MessageModel): FlowResponseNothing

    /**
     * Add a group to the database
     *
     * @param groupModel [GroupModel]: Group to add
     *
     * @return [Flow] with the response of the operation
     *
     * @see GroupModel
     * @see Flow
     */
    fun addGroup(groupModel: GroupModel): FlowResponseNothing

    /**
     * Delete a group from the database
     *
     * @param groupModel [GroupModel]: Group to delete
     *
     * @return [Flow] with the response of the operation
     *
     * @see GroupModel
     * @see Flow
     */
    fun removeGroup(groupId: String): FlowResponseNothing

    /**
     * Change the members of a group
     *
     * @param groupId [String]: Group id
     * @param newMembersList [List]<[String]>: New members list
     *
     * @return [Flow] with the response of the operation
     *
     * @see Flow
     */
    fun changeMembersToGroup(
        groupId: String,
        newMembersList: List<String>
    ): FlowResponseNothing

    /**
     * Push members to a group
     *
     * @param groupId [String]: Group id
     * @param newMembersList [List]<[String]>: New members list
     *
     * @return [Flow] with the response of the operation
     *
     * @see Flow
     */
    fun pushMembersToGroup(
        groupId: String,
        newMembersList: List<String>
    ): FlowResponseNothing

    //Sets Listeners:
    /**
     * Get the messages from the global chat
     *
     * @return [Flow] with the [ResponseState] of the [List]<[MessageModel]>
     *
     * @see MessageModel
     * @see Flow
     */
    fun setMessagesListener(
        onDataChange: (List<MessageModel>) -> Unit,
        onCancelled: (String) -> Unit
    )

    /**
     * Get the groups from the database
     *
     * @return [Flow] with the [ResponseState] of the [List]<[GroupModel]>
     *
     * @see GroupModel
     * @see Flow
     */
    fun setGroupsListener(
        onDataChange: (List<GroupModel>) -> Unit,
        onCancelled: (String) -> Unit
    )


    //Removes Listeners:
    /**
     * Remove the listener from the messages
     */
    fun removeMessagesListener()

    /**
     * Remove the listener from the groups
     */
    fun removeGroupsListener()

}