package es.sebas1705.youknow.presentation.features.home.features.groups.viewmodel
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

import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.domain.model.social.GroupModel
import es.sebas1705.youknow.domain.model.social.MessageModel

/**
 * State of the [GroupsViewModel] that will handle the data of the screen.
 *
 * @property isLoading [Boolean]: Flag that indicates if the data is loading.
 * @property groups [List]<[GroupModel]>: List of groups.
 *
 * @see MVIBaseState
 * @see GroupModel
 * @see MessageModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class GroupsState(
    val isLoading: Boolean,
    val groups: List<GroupModel>
) : MVIBaseState {
    companion object {

        /**
         * Default state of the [GroupsViewModel].
         *
         * @return [GroupsState]: Default state.
         */
        fun default() = GroupsState(
            isLoading = false,
            groups = emptyList()
        )
    }
}