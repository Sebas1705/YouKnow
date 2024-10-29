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
import es.sebas1705.youknow.data.firebase.analytics.repository.AnalyticsRepositoryImpl
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

    override suspend fun postOrUpdateUser(
        userModel: UserModel
    ) {
        database.userDao().insertOrReplace(userModel.toUserEntity())
    }

    override fun deleteUser(
        userModel: UserModel
    ) = flow {
        try {
            emit(ResponseState.Loading)
            val rowsAffected = database.userDao().deleteById(userModel.firebaseId)
            if (rowsAffected != 0)
                emit(ResponseState.EmptySuccess)
            else
                emit(
                    ResponseState.Error(
                        this@DatabaseRepositoryImpl as ClassLogData,
                        ErrorResponseType.NotFound,
                        SettingsDB.USER_ID_NOT_FOUND,
                        analyticsRepository::logError
                    )
                )
        } catch (e: Exception) {
            ResponseState.Error(
                this@DatabaseRepositoryImpl as ClassLogData,
                ErrorResponseType.InternalError,
                e.message ?: SettingsFS.ERROR_GENERIC_MESSAGE,
                analyticsRepository::logError
            )
        }
    }

    override fun getUser(
        firebaseId: String
    ) = flow {
        try {
            emit(ResponseState.Loading)
            val userEntity = database.userDao().getByID(firebaseId)
            if (userEntity != null)
                emit(ResponseState.Success(userEntity.toUserModel()))
            else
                emit(
                    ResponseState.Error(
                        this@DatabaseRepositoryImpl as ClassLogData,
                        ErrorResponseType.NotFound,
                        SettingsDB.USER_ID_NOT_FOUND,
                        analyticsRepository::logError
                    )
                )
        } catch (e: Exception) {
            ResponseState.Error(
                this@DatabaseRepositoryImpl as ClassLogData,
                ErrorResponseType.InternalError,
                e.message ?: SettingsFS.ERROR_GENERIC_MESSAGE,
                analyticsRepository::logError
            )
        }
    }
}