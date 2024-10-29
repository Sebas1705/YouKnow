package es.sebas1705.youknow.data.firebase.firestore.repository
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

import com.google.firebase.firestore.FirebaseFirestore
import es.sebas1705.youknow.data.firebase.analytics.config.ClassLogData
import es.sebas1705.youknow.data.firebase.analytics.config.Layer
import es.sebas1705.youknow.data.firebase.analytics.config.Repository
import es.sebas1705.youknow.data.firebase.analytics.repository.AnalyticsRepository
import es.sebas1705.youknow.data.firebase.firestore.config.SettingsFS
import es.sebas1705.youknow.data.firebase.firestore.documents.UserDocument
import es.sebas1705.youknow.data.model.ErrorResponseType
import es.sebas1705.youknow.data.model.ResponseState
import es.sebas1705.youknow.domain.model.UserModel
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * Implementation of FirestoreRepository.
 *
 * @see es.sebas1705.youknow.data.firebase.firestore.repository.FirestoreRepository
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class FirestoreRepositoryImpl @Inject constructor(
    firestore: FirebaseFirestore,
    val analyticsRepository: AnalyticsRepository
) : FirestoreRepository, ClassLogData {

    override val layer = Layer.Data
    override val repository = Repository.Firestore

    private val usersReference = firestore.collection(SettingsFS.USERS_COLLECTION_NAME)

    override suspend fun saveUser(userModel: UserModel) {
        val userDocument = userModel.toUserDocument()
        usersReference.document(userDocument.firebaseId).set(userDocument).await()
    }

    override fun getUser(
        userId: String
    ): Flow<ResponseState<UserModel>> = flow {
        try {
            emit(ResponseState.Loading)
            val result = usersReference.document(userId).get()
                .await().toObject(UserDocument::class.java)
            if (result != null) emit(ResponseState.Success(result.toUserModel()))
            else
                emit(
                    ResponseState.Error(
                        this@FirestoreRepositoryImpl as ClassLogData,
                        ErrorResponseType.NotFound,
                        SettingsFS.NOT_FOUND_USER,
                        analyticsRepository::logError
                    )
                )

        } catch (e: Exception) {
            emit(
                ResponseState.Error(
                    this@FirestoreRepositoryImpl as ClassLogData,
                    ErrorResponseType.InternalError,
                    e.message ?: SettingsFS.ERROR_GENERIC_MESSAGE,
                    analyticsRepository::logError
                )
            )
        }
    }

}