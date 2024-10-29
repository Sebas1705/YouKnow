package es.sebas1705.youknow.data.firebase.firestore.repository

import es.sebas1705.youknow.data.model.ResponseState
import es.sebas1705.youknow.data.firebase.firestore.documents.UserDocument
import es.sebas1705.youknow.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

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
 * Repository that will handle the Firestore operations.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
interface FirestoreRepository {

    /**
     * Save a user in Firestore
     *
     * @param userModel [UserModel]: User to save
     *
     * @return [Flow]<[ResponseState]<[Boolean]>>: Flow with the response of the operation
     */
    suspend fun saveUser(userModel: UserModel)


    /**
     * Get a user from Firestore
     *
     * @param userId [String]: Id of the user to get
     *
     * @return [Flow]<[ResponseState]<[UserModel]>>: Flow with the response of the operation
     */
    fun getUser(
        userId: String
    ): Flow<ResponseState<UserModel>>

}