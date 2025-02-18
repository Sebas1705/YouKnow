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

import es.sebas1705.common.games.Languages
import es.sebas1705.common.theme.ThemeContrast
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface to write and read data from the preferences
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
interface DatastoreRepository {

    /**
     * Save the first time the user opens the app
     */
    suspend fun saveFirstTime()

    /**
     * Read if the user has opened the app for the first time
     *
     * @return a flow with the value of the first time
     */
    fun readFirstTime(): Flow<Boolean>

    /**
     * Save the volume of the music
     *
     * @param volume the volume of the app
     */
    suspend fun saveMusicVolume(volume: Float)

    /**
     * Read the volume of the music
     *
     * @return a flow with the volume of the app
     */
    fun readMusicVolume(): Flow<Float>

    /**
     * Save the volume of the sound
     *
     * @param volume the volume of the app
     */
    suspend fun saveSoundVolume(volume: Float)

    /**
     * Read the volume of the sound
     *
     * @return a flow with the volume of the app
     */
    fun readSoundVolume(): Flow<Float>

    /**
     * Save the contrast of the app
     *
     * @param contrast the contrast of the app
     */
    suspend fun saveAppContrast(contrast: ThemeContrast)

    /**
     * Read the contrast of the app
     *
     * @return a flow with the contrast of the app ([ThemeContrast])
     */
    fun readAppContrast(): Flow<ThemeContrast>

    /**
     * Save the game language
     *
     * @param language the language of the game
     */
    suspend fun saveGameLanguage(language: Languages)

    /**
     * Read the game language
     *
     * @return a flow with the language of the game ([Languages])
     */
    fun readGameLanguage(): Flow<Languages>


}