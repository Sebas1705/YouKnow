package es.sebas1705.groups
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

import es.sebas1705.groups.usescases.CreateGroup
import es.sebas1705.groups.usescases.RemoveGroup
import es.sebas1705.groups.usescases.RemoveGroupsListener
import es.sebas1705.groups.usescases.SetGroupsListener

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