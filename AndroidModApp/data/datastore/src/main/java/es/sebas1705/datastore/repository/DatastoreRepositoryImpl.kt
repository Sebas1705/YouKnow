package es.sebas1705.datastore.repository
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
import es.sebas1705.common.games.Languages
import es.sebas1705.common.managers.ClassLogData
import es.sebas1705.common.managers.Layer
import es.sebas1705.common.managers.Repository
import es.sebas1705.common.theme.ThemeContrast
import es.sebas1705.datastore.config.DefaultValuesDS
import es.sebas1705.datastore.config.KeysDS
import es.sebas1705.datastore.config.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *  Preferences repository implementation
 *
 *  @property context [Context]: context of the app
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


    override suspend fun saveFirstTime() {
        datastore.edit {
            it[KeysDS.FIRST_TIME] = true
        }
    }

    override fun readFirstTime(): Flow<Boolean> {
        return datastore.data.map {
            it[KeysDS.FIRST_TIME] ?: DefaultValuesDS.FIRST_TIME
        }
    }

    override suspend fun saveMusicVolume(volume: Float) {
        require(volume in 0.0..1.0) {
            "Volume must be between 0.0 and 1.0 (Volume: $volume)"
        }
        datastore.edit {
            it[KeysDS.MUSIC_VOLUME] = volume
        }
    }

    override fun readMusicVolume(): Flow<Float> {
        return datastore.data.map {
            it[KeysDS.MUSIC_VOLUME] ?: DefaultValuesDS.MUSIC_VOLUME
        }
    }

    override suspend fun saveSoundVolume(volume: Float) {
        require(volume in 0.0..1.0) {
            "Volume must be between 0.0 and 1.0 (Volume: $volume)"
        }
        datastore.edit {
            it[KeysDS.SOUND_VOLUME] = volume
        }
    }

    override fun readSoundVolume(): Flow<Float> {
        return datastore.data.map {
            it[KeysDS.SOUND_VOLUME] ?: DefaultValuesDS.SOUND_VOLUME
        }
    }

    override suspend fun saveAppContrast(contrast: ThemeContrast) {
        datastore.edit {
            it[KeysDS.APP_CONTRAST] = contrast.ordinal
        }
    }

    override fun readAppContrast(): Flow<ThemeContrast> {
        return datastore.data.map {
            ThemeContrast.entries.find { contrast ->
                contrast.ordinal == (it[KeysDS.APP_CONTRAST] ?: DefaultValuesDS.APP_UI_CONTRAST)
            } ?: ThemeContrast.Low
        }
    }

    override suspend fun saveGameLanguage(language: Languages) {
        datastore.edit {
            it[KeysDS.GAME_LANGUAGE] = language.ordinal
        }
    }

    override fun readGameLanguage(): Flow<Languages> {
        return datastore.data.map {
            Languages.entries.find { language ->
                language.ordinal == (it[KeysDS.GAME_LANGUAGE] ?: DefaultValuesDS.GAME_LANGUAGE)
            } ?: DefaultValuesDS.GAME_LANGUAGE
        }
    }

}