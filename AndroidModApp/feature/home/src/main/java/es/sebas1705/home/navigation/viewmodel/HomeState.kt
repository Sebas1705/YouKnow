package es.sebas1705.youknow.presentation.features.home.navigation.viewmodel
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

import com.google.firebase.auth.FirebaseUser
import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.domain.model.social.UserModel

/**
 * State for [HomeViewModel] that will handle the user's data.
 *
 * @property isLoading [Boolean]: If the screen is loading.
 * @property userModel [UserModel]: User's data.
 * @property firebaseUser [FirebaseUser]: Firebase user's data.
 * @property infoUsers [MutableList]<[UserModel]>: Users' data.
 *
 * @see MVIBaseState
 * @see UserModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class HomeState(
    val isLoading: Boolean,
    val userModel: UserModel?,
    val firebaseUser: FirebaseUser?,
    val infoUsers: Map<String, UserModel>
) : MVIBaseState {
    companion object {

        /**
         * Default [HomeState] with no user.
         *
         * @return [HomeState]
         *
         * @since 1.0.0
         * @author Sebastián Ramiro Entrerrios García
         */
        fun default() = HomeState(
            isLoading = false,
            userModel = null,
            firebaseUser = null,
            infoUsers = mapOf()
        )

        /**
         * Default [HomeState] with user.
         *
         * @return [HomeState]
         *
         * @since 1.0.0
         * @author Sebastián Ramiro Entrerrios García
         */
        fun defaultWithUser() = HomeState(
            isLoading = false,
            userModel = UserModel.default(),
            firebaseUser = null,
            infoUsers = mapOf()
        )
    }
}