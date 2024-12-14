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
import es.sebas1705.youknow.core.classes.theme.ThemeContrast
import es.sebas1705.youknow.data.firebase.analytics.config.ClassLogData
import es.sebas1705.youknow.data.firebase.analytics.config.Layer
import es.sebas1705.youknow.data.firebase.analytics.config.Repository
import es.sebas1705.youknow.data.local.datastore.config.DefaultValuesDS
import es.sebas1705.youknow.data.local.datastore.config.KeysDS
import es.sebas1705.youknow.data.local.datastore.config.dataStore
import kotlinx.coroutines.flow.Flow
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
) : DatastoreRepository, ClassLogData {

    override val layer: Layer = Layer.Data
    override val repository: Repository = Repository.Datastore

    private val datastore = context.dataStore

    /**
     * Save the first time the user opens the app
     */
    override suspend fun saveFirstTime() {
        datastore.edit {
            it[KeysDS.FIRST_TIME] = true
        }
    }

    /**
     * Read if the user has opened the app for the first time
     *
     * @return a flow with the value of the first time
     */
    override fun readFirstTime(): Flow<Boolean> {
        return datastore.data.map {
            it[KeysDS.FIRST_TIME] ?: DefaultValuesDS.FIRST_TIME
        }
    }

    /**
     * Save the volume of the app
     *
     * @param volume the volume of the app ranging from 0.0 to 1.0
     */
    override suspend fun saveAppVolume(volume: Float) {
        require(volume in 0.0..1.0) {
            "Volume must be between 0.0 and 1.0 (Volume: $volume)"
        }
        datastore.edit {
            it[KeysDS.APP_VOLUME] = volume
        }
    }

    /**
     * Read the volume of the app
     *
     * @return a flow with the volume of the app
     */
    override fun readAppVolume(): Flow<Float> {
        return datastore.data.map {
            it[KeysDS.APP_VOLUME] ?: DefaultValuesDS.APP_VOLUME
        }
    }

    /**
     * Save the contrast of the app
     *
     * @param contrast the contrast of the app
     */
    override suspend fun saveAppContrast(contrast: ThemeContrast) {
        datastore.edit {
            it[KeysDS.APP_CONTRAST] = contrast.ordinal
        }
    }

    /**
     * Read the contrast of the app
     *
     * @return a flow with the contrast of the app ([ThemeContrast])
     */
    override fun readAppContrast(): Flow<ThemeContrast> {
        return datastore.data.map {
            ThemeContrast.entries.find { contrast ->
                contrast.ordinal == (it[KeysDS.APP_CONTRAST] ?: DefaultValuesDS.APP_UI_CONTRAST)
            } ?: ThemeContrast.Low
        }
    }

}