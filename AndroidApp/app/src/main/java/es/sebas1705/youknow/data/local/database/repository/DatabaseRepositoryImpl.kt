package es.sebas1705.youknow.data.local.database.repository

import es.sebas1705.youknow.data.firebase.analytics.config.ClassLogData
import es.sebas1705.youknow.data.firebase.analytics.config.Layer
import es.sebas1705.youknow.data.firebase.analytics.config.Repository
import es.sebas1705.youknow.data.firebase.analytics.repository.AnalyticsRepositoryImpl
import es.sebas1705.youknow.data.firebase.firestore.config.SettingsFS
import es.sebas1705.youknow.data.firebase.firestore.repository.FirestoreRepositoryImpl
import es.sebas1705.youknow.data.local.database.Database
import es.sebas1705.youknow.data.local.database.daos.UserDao
import es.sebas1705.youknow.data.model.ErrorResponseType
import es.sebas1705.youknow.data.model.ResponseState
import es.sebas1705.youknow.domain.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

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
    private val analyticsRepository: AnalyticsRepositoryImpl
) : DatabaseRepository, ClassLogData {

    override val layer: Layer = Layer.Data
    override val repository: Repository = Repository.Database

    override fun postOrUpdateUser(
        userModel: UserModel
    ) = flow {
        try {
            emit(ResponseState.Loading)
            database.userDao().insertOrReplace(userModel.toUserEntity())
            emit(ResponseState.EmptySuccess)
        }catch (e: Exception){
            ResponseState.Error(
                this@DatabaseRepositoryImpl as ClassLogData,
                ErrorResponseType.InternalError,
                e.message ?: SettingsFS.ERROR_GENERIC_MESSAGE,
                analyticsRepository::logError
            )
        }
    }

    override fun deleteUser(
        userModel: UserModel
    ) = flow {
        try {
            emit(ResponseState.Loading)
            database.userDao().insertOrReplace(userModel.toUserEntity())
            emit(ResponseState.EmptySuccess)
        }catch (e: Exception){
            ResponseState.Error(
                this@DatabaseRepositoryImpl as ClassLogData,
                ErrorResponseType.InternalError,
                e.message ?: SettingsFS.ERROR_GENERIC_MESSAGE,
                analyticsRepository::logError
            )
        }
    }

    override fun getUser(firebaseId: String): Flow<ResponseState<UserModel>> {
        TODO("Not yet implemented")
    }
}