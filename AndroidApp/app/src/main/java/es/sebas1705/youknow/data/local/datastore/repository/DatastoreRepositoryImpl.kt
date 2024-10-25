package es.sebas1705.youknow.data.local.datastore.repository
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

import android.content.Context
import androidx.datastore.preferences.core.edit
import es.sebas1705.youknow.data.firebase.analytics.config.ClassLogData
import es.sebas1705.youknow.data.firebase.analytics.config.Layer
import es.sebas1705.youknow.data.firebase.analytics.config.Repository
import es.sebas1705.youknow.data.firebase.analytics.repository.AnalyticsRepository
import es.sebas1705.youknow.data.firebase.firestore.config.SettingsFS
import es.sebas1705.youknow.data.local.database.repository.DatabaseRepositoryImpl
import es.sebas1705.youknow.presentation.ui.classes.ThemeContrast
import es.sebas1705.youknow.data.local.datastore.config.DefaultValuesDS
import es.sebas1705.youknow.data.local.datastore.config.KeysDS
import es.sebas1705.youknow.data.local.datastore.config.dataStore
import es.sebas1705.youknow.data.model.ErrorResponseType
import es.sebas1705.youknow.data.model.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *  Preferences repository implementation
 *
 *  @property context [Context]: context of the app
 *
 *  @see es.sebas1705.youknow.data.local.datastore.repository.DatastoreRepository
 *  @see Context
 *
 *  @author Sebastián Ramiro Entrerrios García
 *  @since 1.0.0
 */
class DatastoreRepositoryImpl @Inject constructor(
    private val context: Context,
    private val analyticsRepository: AnalyticsRepository
) : DatastoreRepository, ClassLogData {

    override val layer: Layer = Layer.Data
    override val repository: Repository = Repository.Datastore

    private val datastore = context.dataStore

    override fun saveFirstTime() = flow {
        try {
            emit(ResponseState.Loading)
            datastore.edit {
                it[KeysDS.FIRST_TIME] = true
                emit(ResponseState.EmptySuccess)
            }
        } catch (e: Exception) {
            ResponseState.Error(
                this@DatastoreRepositoryImpl as ClassLogData,
                ErrorResponseType.InternalError,
                e.message ?: SettingsFS.ERROR_GENERIC_MESSAGE,
                analyticsRepository::logError
            )
        }
    }

    override fun readFirstTime() = flow {
        try {
            emit(ResponseState.Loading)
            datastore.data.collect {
                emit(ResponseState.Success(it[KeysDS.FIRST_TIME] ?: DefaultValuesDS.FIRST_TIME))
            }
        } catch (e: Exception) {
            ResponseState.Error(
                this@DatastoreRepositoryImpl as ClassLogData,
                ErrorResponseType.InternalError,
                e.message ?: SettingsFS.ERROR_GENERIC_MESSAGE,
                analyticsRepository::logError
            )
        }
    }

    override fun saveAppVolume(volume: Float) = flow {
        try{
            emit(ResponseState.Loading)
            require(volume in 0.0..1.0){
                "Volume must be between 0.0 and 1.0 (Volume: $volume)"
            }
            context.dataStore.edit {
                it[KeysDS.APP_VOLUME] = volume
                emit(ResponseState.EmptySuccess)
            }
        } catch (e: Exception) {
            ResponseState.Error(
                this@DatastoreRepositoryImpl as ClassLogData,
                ErrorResponseType.InternalError,
                e.message ?: SettingsFS.ERROR_GENERIC_MESSAGE,
                analyticsRepository::logError
            )
        }
    }

    override fun readAppVolume(): Flow<Float> {
        return context.dataStore.data.map{
            it[KeysDS.APP_VOLUME] ?: DefaultValuesDS.APP_VOLUME
        }
    }

    override suspend fun saveAppContrast(themeContrast: ThemeContrast) {
        context.dataStore.edit {
            it[KeysDS.APP_CONTRAST] = themeContrast.ordinal
        }
    }

    override fun readAppContrast(): Flow<ThemeContrast> {
        return context.dataStore.data.map{
            ThemeContrast.entries.find { contrast ->
                contrast.ordinal == (it[KeysDS.APP_CONTRAST] ?: DefaultValuesDS.APP_Ui_CONTRAST)
            } ?: ThemeContrast.Low
        }
    }

}