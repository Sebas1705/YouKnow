package es.sebas1705.realtime.repository
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

import es.sebas1705.common.utlis.alias.FlowResponseNothing
import es.sebas1705.realtime.jsons.GroupJson
import es.sebas1705.realtime.jsons.MessageJson


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
     * @param messageId [String]: Message id
     * @param value [MessageJson]: Message to add
     *
     * @return [FlowResponseNothing] with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun addMessageToGlobalChat(
        messageId: String,
        value: MessageJson
    ): FlowResponseNothing

    /**
     * Add a group to the database
     *
     * @param groupId [String]: Group id
     * @param groupJson [GroupJson]: Group to add
     *
     * @return [FlowResponseNothing] with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun addGroup(
        groupId: String,
        groupJson: GroupJson
    ): FlowResponseNothing

    /**
     * Delete a group from the database
     *
     * @param groupId [String]: Group to delete
     *
     * @return [FlowResponseNothing] with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun removeGroup(groupId: String): FlowResponseNothing

    /**
     * Change the members of a group
     *
     * @param groupId [String]: Group id
     * @param newMembersList [List]<[String]>: New members list
     *
     * @return [FlowResponseNothing] with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
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
     * @return [FlowResponseNothing] with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun pushMembersToGroup(
        groupId: String,
        newMembersList: List<String>
    ): FlowResponseNothing

    //Sets Listeners:
    /**
     * Get the messages from the global chat
     *
     * @property onDataChange ([List]<[MessageJson]>) -> [Unit]: Function to execute when the data changes
     * @property onCancelled ([String]) -> [Unit]: Function to execute when the data is cancelled
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun setMessagesListener(
        onDataChange: (List<Pair<MessageJson, String>>) -> Unit,
        onCancelled: (String) -> Unit
    )

    /**
     * Get the groups from the database
     *
     * @property onDataChange ([List]<[GroupJson]>) -> [Unit]: Function to execute when the data changes
     * @property onCancelled ([String]) -> [Unit]: Function to execute when the data is cancelled
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun setGroupsListener(
        onDataChange: (List<Pair<GroupJson, String>>) -> Unit,
        onCancelled: (String) -> Unit
    )


    //Removes Listeners:
    /**
     * Remove the listener from the messages
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun removeMessagesListener()

    /**
     * Remove the listener from the groups
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun removeGroupsListener()

}