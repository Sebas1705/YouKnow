package es.sebas1705.settings
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

/**
 * Data class that contains all the use cases related to the settings
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
data class SettingUsesCases(
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