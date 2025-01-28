package es.sebas1705.youknow.domain.usecases
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

import es.sebas1705.youknow.core.classes.enums.games.Languages
import es.sebas1705.youknow.core.classes.enums.theme.ThemeContrast
import es.sebas1705.youknow.data.local.datastore.repository.DatastoreRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case to read if the app is being opened for the first time
 *
 * @param datastoreRepository [DatastoreRepository]: repository to get the preferences
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class ReadFirstTime(
    private val datastoreRepository: DatastoreRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return datastoreRepository.readFirstTime()
    }
}

/**
 * Use case to save that the app is not being opened for the first time
 *
 * @param datastoreRepository [DatastoreRepository]: repository to save the preferences
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class SaveFirstTime(
    private val datastoreRepository: DatastoreRepository
) {
    suspend operator fun invoke() {
        return datastoreRepository.saveFirstTime()
    }
}

/**
 * Use case to read the music volume
 *
 * @param datastoreRepository [DatastoreRepository]: repository to get the preferences
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class ReadMusicVolume(
    private val datastoreRepository: DatastoreRepository
) {
    operator fun invoke(): Flow<Float> {
        return datastoreRepository.readMusicVolume()
    }
}

/**
 * Use case to save the music volume
 *
 * @param datastoreRepository [DatastoreRepository]: repository to save the preferences
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class SaveMusicVolume(
    private val datastoreRepository: DatastoreRepository
) {
    suspend operator fun invoke(
        volume: Float,
        onLoading: () -> Unit,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            onLoading()
            datastoreRepository.saveMusicVolume(volume)
            onSuccess()
        } catch (e: Exception) {
            onError(e.message.toString())
        }
    }
}

/**
 * Use case to read the sound volume
 *
 * @param datastoreRepository [DatastoreRepository]: repository to get the preferences
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class ReadSoundVolume(
    private val datastoreRepository: DatastoreRepository
) {
    operator fun invoke(): Flow<Float> {
        return datastoreRepository.readSoundVolume()
    }
}

/**
 * Use case to save the sound volume
 *
 * @param datastoreRepository [DatastoreRepository]: repository to save the preferences
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class SaveSoundVolume(
    private val datastoreRepository: DatastoreRepository
) {
    suspend operator fun invoke(
        volume: Float,
        onLoading: () -> Unit,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            onLoading()
            datastoreRepository.saveSoundVolume(volume)
            onSuccess()
        } catch (e: Exception) {
            onError(e.message.toString())
        }
    }
}

/**
 * Use case to read the app contrast
 *
 * @param datastoreRepository [DatastoreRepository]: repository to get the preferences
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class ReadAppContrast(
    private val datastoreRepository: DatastoreRepository
) {
    operator fun invoke(): Flow<ThemeContrast> {
        return datastoreRepository.readAppContrast()
    }
}

/**
 * Use case to save the app contrast
 *
 * @param datastoreRepository [DatastoreRepository]: repository to save the preferences
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class SaveAppContrast(
    private val datastoreRepository: DatastoreRepository
) {
    suspend operator fun invoke(
        themeContrast: ThemeContrast,
        onLoading: () -> Unit,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            onLoading()
            datastoreRepository.saveAppContrast(themeContrast)
            onSuccess()
        } catch (e: Exception) {
            onError(e.message.toString())
        }
    }
}

/**
 * Use case to read the game language
 *
 * @param datastoreRepository [DatastoreRepository]: repository to get the preferences
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class ReadGameLanguage(
    private val datastoreRepository: DatastoreRepository
) {
    operator fun invoke(): Flow<Languages> {
        return datastoreRepository.readGameLanguage()
    }
}

/**
 * Use case to save the game language
 *
 * @param datastoreRepository [DatastoreRepository]: repository to save the preferences
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class SaveGameLanguage(
    private val datastoreRepository: DatastoreRepository
) {
    suspend operator fun invoke(
        languages: Languages,
        onLoading: () -> Unit,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            onLoading()
            datastoreRepository.saveGameLanguage(languages)
            onSuccess()
        } catch (e: Exception) {
            onError(e.message.toString())
        }
    }
}

/**
 * Data class that contains all the use cases related to the Datastore
 *
 * @property readFirstTime [ReadFirstTime]: use case to read if the app is being opened for the first time
 * @property saveFirstTime [SaveFirstTime]: use case to save that the app is not being opened for the first time
 * @property readMusicVolume [ReadMusicVolume]: use case to read the music volume
 * @property saveMusicVolume [SaveMusicVolume]: use case to save the music volume
 * @property readSoundVolume [ReadSoundVolume]: use case to read the sound volume
 * @property saveSoundVolume [SaveSoundVolume]: use case to save the sound volume
 * @property readAppContrast [ReadAppContrast]: use case to read the app contrast
 * @property saveAppContrast [SaveAppContrast]: use case to save the app contrast
 * @property readGameLanguage [ReadGameLanguage]: use case to read the game language
 * @property saveGameLanguage [SaveGameLanguage]: use case to save the game language
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class DatastoreUsesCases(
    val readFirstTime: ReadFirstTime,
    val saveFirstTime: SaveFirstTime,
    val readMusicVolume: ReadMusicVolume,
    val saveMusicVolume: SaveMusicVolume,
    val readSoundVolume: ReadSoundVolume,
    val saveSoundVolume: SaveSoundVolume,
    val readAppContrast: ReadAppContrast,
    val saveAppContrast: SaveAppContrast,
    val readGameLanguage: ReadGameLanguage,
    val saveGameLanguage: SaveGameLanguage
)
