package es.sebas1705.youknow.domain.usecases

import android.util.Log
import es.sebas1705.youknow.data.firebase.analytics.config.ClassLogData
import es.sebas1705.youknow.data.firebase.analytics.config.Layer
import es.sebas1705.youknow.data.firebase.analytics.config.Repository
import es.sebas1705.youknow.data.firebase.analytics.repository.AnalyticsRepository
import es.sebas1705.youknow.data.firebase.authentication.config.ProviderAuth
import es.sebas1705.youknow.data.firebase.authentication.repository.AuthenticationRepository
import es.sebas1705.youknow.data.firebase.firestore.repository.FirestoreRepository
import es.sebas1705.youknow.data.local.database.repository.DatabaseRepository
import es.sebas1705.youknow.domain.model.ErrorProcessType
import es.sebas1705.youknow.domain.model.ProcessState
import es.sebas1705.youknow.domain.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

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

class SignUpEmailUser(
    private val authenticationRepository: AuthenticationRepository,
){
    suspend operator fun invoke(
        nickname: String,
        mail:String,
        password: String,
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ) {
        authenticationRepository.signUpWithEmail(
            email = mail,
            password = password
        ).collect {
            it.catcher(
                onEmptySuccess = {
                    val userModel = createNewEmailUser(nickname)
                    onSuccess(userModel)
                },
                onError = {
                    onError(it.message)
                }
            )
        }
    }

    private fun createNewEmailUser(nickname: String): UserModel {
        val user = authenticationRepository.getCurrentUser()!!
        return UserModel(
            firebaseId = user.uid,
            email = user.email!!,
            provider = ProviderAuth.EMAIL,
            nickName = nickname,
            photoUrl = null,
            groupId = null,
            points = 0,
            credits = 1000,
            friends = emptyList(),
        )
    }
}

class SaveUser(
    private val databaseRepository: DatabaseRepository,
    private val firestoreRepository: FirestoreRepository,
) {
    suspend operator fun invoke(userModel: UserModel) {
        databaseRepository.postOrUpdateUser(userModel)
        firestoreRepository.saveUser(userModel)
    }
}

class GetUser {
    operator fun invoke() {
        // Get user
    }
}

data class UserUsesCases(
    val signUpEmailUser: SignUpEmailUser,
    val saveUser: SaveUser,
    val getUser: GetUser
)