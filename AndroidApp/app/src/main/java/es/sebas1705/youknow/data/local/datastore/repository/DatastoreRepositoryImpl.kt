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
import es.sebas1705.youknow.presentation.ui.classes.ThemeContrast
import es.sebas1705.youknow.data.config.local.datastore.DefaultValues
import es.sebas1705.youknow.data.config.local.datastore.PreferencesKeys
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
    private val context: Context
) : DatastoreRepository {

    override suspend fun saveFirstTime() {
        context.dataStore.edit {
            it[PreferencesKeys.FIRST_TIME] = true
        }
    }

    override fun readFirstTime(): Flow<Boolean> {
        return context.dataStore.data.map{
            it[PreferencesKeys.FIRST_TIME] ?: DefaultValues.FIRST_TIME
        }
    }

    override suspend fun saveAppVolume(volume: Float) {
        require(volume in 0.0..1.0){
            "Volume must be between 0.0 and 1.0 (Volume: $volume)"
        }
        context.dataStore.edit {
            it[PreferencesKeys.APP_VOLUME] = volume
        }
    }

    override fun readAppVolume(): Flow<Float> {
        return context.dataStore.data.map{
            it[PreferencesKeys.APP_VOLUME] ?: DefaultValues.APP_VOLUME
        }
    }

    override suspend fun saveAppContrast(themeContrast: ThemeContrast) {
        context.dataStore.edit {
            it[PreferencesKeys.APP_CONTRAST] = themeContrast.ordinal
        }
    }

    override fun readAppContrast(): Flow<ThemeContrast> {
        return context.dataStore.data.map{
            ThemeContrast.entries.find { contrast ->
                contrast.ordinal == (it[PreferencesKeys.APP_CONTRAST] ?: DefaultValues.APP_Ui_CONTRAST)
            } ?: ThemeContrast.Low
        }
    }

}