package es.sebas1705.settings.di
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

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.datastore.repository.DatastoreRepository
import es.sebas1705.settings.SettingUsesCases
import es.sebas1705.settings.usescases.ReadAppContrast
import es.sebas1705.settings.usescases.ReadFirstTime
import es.sebas1705.settings.usescases.ReadGameLanguage
import es.sebas1705.settings.usescases.ReadMusicVolume
import es.sebas1705.settings.usescases.ReadSoundVolume
import es.sebas1705.settings.usescases.SaveAppContrast
import es.sebas1705.settings.usescases.SaveFirstTime
import es.sebas1705.settings.usescases.SaveGameLanguage
import es.sebas1705.settings.usescases.SaveMusicVolume
import es.sebas1705.settings.usescases.SaveSoundVolume
import javax.inject.Singleton

/**
 * Module to provide all the use cases of the domain layer
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    /**
     * Function to provide settings use cases
     *
     * @param datastoreRepository [DatastoreRepository]: Repository to access to the datastore
     *
     * @return [SettingUsesCases]: Use cases of the settings
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideSettingsUseCases(
        datastoreRepository: DatastoreRepository
    ): SettingUsesCases = SettingUsesCases(
        readFirstTime = ReadFirstTime(datastoreRepository),
        saveFirstTime = SaveFirstTime(datastoreRepository),
        readMusicVolume = ReadMusicVolume(datastoreRepository),
        saveMusicVolume = SaveMusicVolume(datastoreRepository),
        readSoundVolume = ReadSoundVolume(datastoreRepository),
        saveSoundVolume = SaveSoundVolume(datastoreRepository),
        readAppContrast = ReadAppContrast(datastoreRepository),
        saveAppContrast = SaveAppContrast(datastoreRepository),
        readGameLanguage = ReadGameLanguage(datastoreRepository),
        saveGameLanguage = SaveGameLanguage(datastoreRepository)
    )

}