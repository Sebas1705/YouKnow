package es.sebas1705.youknow.data.local.database.repository
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

import es.sebas1705.youknow.data.firebase.analytics.config.ClassLogData
import es.sebas1705.youknow.data.firebase.analytics.config.Layer
import es.sebas1705.youknow.data.firebase.analytics.config.Repository
import es.sebas1705.youknow.data.firebase.analytics.repository.AnalyticsRepository
import es.sebas1705.youknow.data.firebase.firestore.config.SettingsFS
import es.sebas1705.youknow.data.local.database.Database
import es.sebas1705.youknow.data.local.database.config.SettingsDB
import es.sebas1705.youknow.data.model.ErrorResponseType
import es.sebas1705.youknow.data.model.ResponseState
import es.sebas1705.youknow.domain.model.UserModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Class to represent the repository of the database
 *
 * @see DatabaseRepository
 * @see ClassLogData
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class DatabaseRepositoryImpl @Inject constructor(
    private val database: Database,
    private val analyticsRepository: AnalyticsRepository
) : DatabaseRepository, ClassLogData {

    override val layer: Layer = Layer.Data
    override val repository: Repository = Repository.Database

    //Selects
    override suspend fun getUser(
        firebaseId: String
    ): UserModel? {
        val userEntity = database.userDao().getByID(firebaseId)
        return userEntity?.toUserModel()
    }

    override suspend fun containsUser(
        firebaseId: String
    ): Boolean {
        return database.userDao().contains(firebaseId) > 0
    }

    //Inserts
    override suspend fun postOrUpdateUser(
        userModel: UserModel
    ) {
        database.userDao().insertOrReplace(userModel.toUserEntity())
    }

    //Updates
    override suspend fun updateCreditsFromUser(
        userId: String,
        newCredits: Int
    ): Boolean {
        val rowsAffected = database.userDao().updateCreditsById(userId, newCredits)
        return rowsAffected > 0
    }

    override suspend fun updateGroupFromUser(
        userId: String,
        groupId: String
    ): Boolean {
        val rowsAffected = database.userDao().updateGroupById(userId, groupId)
        return rowsAffected > 0
    }

    //Deletes
    override suspend fun deleteUser(
        userModel: UserModel
    ): Boolean {
        val rowsAffected = database.userDao().deleteById(userModel.firebaseId)
        return rowsAffected > 0
    }


}