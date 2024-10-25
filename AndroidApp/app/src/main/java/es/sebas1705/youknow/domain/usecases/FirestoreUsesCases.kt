package es.sebas1705.youknow.domain.usecases
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

import es.sebas1705.youknow.data.model.ResponseState
import es.sebas1705.youknow.domain.model.UserModel
import es.sebas1705.youknow.data.firebase.firestore.repository.FirestoreRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case to save a user
 *
 * @param firestoreRepository [FirestoreRepository]: repository to save a user
 *
 * @return [Flow] with a [ResponseState] of [Boolean] value indicating if the user was saved
 *
 * @see FirestoreRepository
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class SaveUser(
    private val firestoreRepository: FirestoreRepository
) {
    operator fun invoke(userModel: UserModel): Flow<ResponseState<Boolean>> {
        return firestoreRepository.saveUser(userModel.toUserDocument())
    }
}

/**
 * Use case to get a user
 *
 * @param firestoreRepository [FirestoreRepository]: repository to get a user
 *
 * @return [Flow] with a [ResponseState] of [UserModel] value indicating if the user was retrieved
 *
 * @see FirestoreRepository
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class GetUser(
    private val firestoreRepository: FirestoreRepository
) {
    operator fun invoke(userId: String): Flow<ResponseState<UserModel>> {
        return firestoreRepository.getUser(userId)
    }
}

/**
 * Use cases for Firestore operations
 *
 * @property saveUser [SaveUser]: Use case to save a user
 * @property getUser [GetUser]: Use case to get a user
 *
 * @see SaveUser
 * @see GetUser
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class FirestoreUsesCases(
    val saveUser: SaveUser,
    val getUser: GetUser
)