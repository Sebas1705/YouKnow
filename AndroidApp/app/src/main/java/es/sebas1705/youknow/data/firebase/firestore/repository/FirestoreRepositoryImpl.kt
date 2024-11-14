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
import es.sebas1705.youknow.domain.model.UserModel
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
        usersReference.document(userModel.firebaseId).set(userDocument).await()
    }

    override suspend fun getUser(
        userId: String
    ): UserModel? {
        val result = usersReference.document(userId).get()
            .await().toObject(UserDocument::class.java)
        return result?.toUserModel(userId)
    }

    override suspend fun setLoggedToUser(
        userId: String,
        logged: Boolean
    ) {
        usersReference.document(userId).update("logged", logged).await()
    }

    override suspend fun getLoggedFromUser(
        userId: String
    ): Boolean {
        val result = usersReference.document(userId).get().await()
        return result.getBoolean("logged") == true
    }

    override suspend fun setCreditsToUser(
        userId: String,
        newsCredits: Int
    ) {
        usersReference.document(userId).update("credits", newsCredits).await()
    }

    override suspend fun setGroupToUser(
        userId: String,
        groupId: String
    ) {
        usersReference.document(userId).update("groupId", groupId).await()
    }

    override suspend fun containsUser(
        userId: String
    ): Boolean {
        val result = usersReference.document(userId).get().await()
        return result.exists()
    }

}