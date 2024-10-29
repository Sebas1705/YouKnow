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

import es.sebas1705.youknow.presentation.ui.classes.ThemeContrast
import es.sebas1705.youknow.data.local.datastore.repository.DatastoreRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case to read if the app is being opened for the first time
 *
 * @param datastoreRepository [DatastoreRepository]: repository to get the preferences
 *
 * @return [Flow] with a [Boolean] value indicating if the app is being opened for the first time
 *
 * @see DatastoreRepository
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
 * @see DatastoreRepository
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
 * Use case to read the app volume
 *
 * @param datastoreRepository [DatastoreRepository]: repository to get the preferences
 *
 * @see DatastoreRepository
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class ReadAppVolume(
    private val datastoreRepository: DatastoreRepository
) {
    operator fun invoke(): Flow<Float> {
        return datastoreRepository.readAppVolume()
    }
}

/**
 * Use case to save the app volume
 *
 * @param datastoreRepository [DatastoreRepository]: repository to save the preferences
 *
 * @see DatastoreRepository
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class SaveAppVolume(
    private val datastoreRepository: DatastoreRepository
) {
    suspend operator fun invoke(volume: Float) {
        return datastoreRepository.saveAppVolume(volume)
    }
}

/**
 * Use case to read the app contrast
 *
 * @param datastoreRepository [DatastoreRepository]: repository to get the preferences
 *
 * @see DatastoreRepository
 * @see ThemeContrast
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
 * @see DatastoreRepository
 * @see ThemeContrast
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class SaveAppContrast(
    private val datastoreRepository: DatastoreRepository
) {
    suspend operator fun invoke(themeContrast: ThemeContrast) {
        return datastoreRepository.saveAppContrast(themeContrast)
    }
}

/**
 * Data class that contains all the use cases related to the Datastore
 *
 * @property readFirstTime [ReadFirstTime]: use case to read if the app is being opened for the first time
 * @property saveFirstTime [SaveFirstTime]: use case to save that the app is not being opened for the first time
 * @property readAppVolume [ReadAppVolume]: use case to read the app volume
 * @property saveAppVolume [SaveAppVolume]: use case to save the app volume
 * @property readAppContrast [ReadAppContrast]: use case to read the app contrast
 * @property saveAppContrast [SaveAppContrast]: use case to save the app contrast
 *
 * @see ReadFirstTime
 * @see SaveFirstTime
 * @see ReadAppVolume
 * @see SaveAppVolume
 * @see ReadAppContrast
 * @see SaveAppContrast
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class DatastoreUsesCases(
    val readFirstTime: ReadFirstTime,
    val saveFirstTime: SaveFirstTime,
    val readAppVolume: ReadAppVolume,
    val saveAppVolume: SaveAppVolume,
    val readAppContrast: ReadAppContrast,
    val saveAppContrast: SaveAppContrast
)
