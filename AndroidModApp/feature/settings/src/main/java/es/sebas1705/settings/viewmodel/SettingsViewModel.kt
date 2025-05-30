package es.sebas1705.settings.viewmodel
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

import android.app.Application
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.common.mvi.MVIBaseViewModel
import es.sebas1705.common.utlis.extensions.composables.printTextInToast
import es.sebas1705.datastore.config.DefaultValuesDS
import es.sebas1705.settings.SettingUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel for Settings Screen that will handle the UI logic and the data flow.
 *
 * @param settingUsesCases [SettingUsesCases]: Use cases for the Settings.
 * @param application [Application]: Application context.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingUsesCases: SettingUsesCases,
    private val application: Application
) : MVIBaseViewModel<SettingsState, SettingsIntent>() {

    override fun initState(): SettingsState = SettingsState.default()

    override fun intentHandler(
        intent: SettingsIntent
    ) {
        when (intent) {
            is SettingsIntent.ReadSettings -> readSettings()
            is SettingsIntent.ChangeContrast -> changeContrast(intent)
            is SettingsIntent.ChangeMusicVolume -> changeMusicVolume(intent)
            is SettingsIntent.ChangeSoundVolume -> changeSoundVolume(intent)
            is SettingsIntent.ChangeLanguage -> changeLanguage(intent)
            is SettingsIntent.RestoreSettings -> restoreSettings()
        }
    }

    //Actions:
    private fun readSettings() = execute(Dispatchers.IO) {
        execute(Dispatchers.IO) {
            settingUsesCases.readMusicVolume().collect { volume ->
                Log.i("SettingsViewModel", "readSettings: $volume")
                updateUi { it.copy(musicVolume = volume) }
            }
        }
        execute(Dispatchers.IO) {
            settingUsesCases.readSoundVolume().collect { volume ->
                Log.i("SettingsViewModel", "readSettings: $volume")
                updateUi { it.copy(soundVolume = volume) }
            }
        }
        execute(Dispatchers.IO) {
            settingUsesCases.readAppContrast().collect { contrast ->
                Log.i("SettingsViewModel", "readSettings: $contrast")
                updateUi { it.copy(themeContrast = contrast) }
            }
        }
        execute(Dispatchers.IO) {
            settingUsesCases.readGameLanguage().collect { language ->
                Log.i("SettingsViewModel", "readSettings: $language")
                updateUi { it.copy(gameLanguage = language) }
            }
        }
    }

    private fun restoreSettings() = execute(Dispatchers.IO) {
        settingUsesCases.saveMusicVolume(
            volume = DefaultValuesDS.MUSIC_VOLUME,
            onLoading = { startLoading() },
            onSuccess = {
                updateUi { it.copy(musicVolume = DefaultValuesDS.MUSIC_VOLUME) }
                execute(Dispatchers.IO) {
                    settingUsesCases.saveAppContrast(
                        themeContrast = DefaultValuesDS.APP_UI_CONTRAST,
                        onLoading = {},
                        onSuccess = {
                            updateUi { it.copy(themeContrast = DefaultValuesDS.APP_UI_CONTRAST) }
                            execute(Dispatchers.IO) {
                                settingUsesCases.saveGameLanguage(
                                    languages = DefaultValuesDS.GAME_LANGUAGE,
                                    onLoading = {},
                                    onSuccess = {
                                        updateUi { it.copy(gameLanguage = DefaultValuesDS.GAME_LANGUAGE) }
                                        execute(Dispatchers.IO) {
                                            settingUsesCases.saveSoundVolume(
                                                volume = DefaultValuesDS.SOUND_VOLUME,
                                                onLoading = {},
                                                onSuccess = {
                                                    updateUi { it.copy(soundVolume = DefaultValuesDS.SOUND_VOLUME) }
                                                    stopLoading()
                                                },
                                                onError = {
                                                    stopAndError(
                                                        it,
                                                        application::printTextInToast
                                                    )
                                                }
                                            )
                                        }
                                    },
                                    onError = { stopAndError(it, application::printTextInToast) }
                                )
                            }
                        },
                        onError = { stopAndError(it, application::printTextInToast) }
                    )
                }
            },
            onError = { stopAndError(it, application::printTextInToast) },
        )
    }

    private fun changeContrast(
        intent: SettingsIntent.ChangeContrast
    ) = execute(Dispatchers.IO) {
        settingUsesCases.saveAppContrast(
            intent.themeContrast,
            onLoading = { startLoading() },
            onSuccess = {
                updateUi { it.copy(themeContrast = intent.themeContrast) }
                stopLoading()
            },
            onError = { stopAndError(it, application::printTextInToast) }
        )
    }

    private fun changeMusicVolume(
        intent: SettingsIntent.ChangeMusicVolume
    ) = execute(Dispatchers.IO) {
        settingUsesCases.saveMusicVolume(
            intent.volume,
            onLoading = { startLoading() },
            onSuccess = {
                updateUi { it.copy(musicVolume = intent.volume) }
                stopLoading()
            },
            onError = { stopAndError(it, application::printTextInToast) }
        )
    }

    private fun changeSoundVolume(
        intent: SettingsIntent.ChangeSoundVolume
    ) = execute(Dispatchers.IO) {
        settingUsesCases.saveSoundVolume(
            intent.volume,
            onLoading = { startLoading() },
            onSuccess = {
                updateUi { it.copy(soundVolume = intent.volume) }
                stopLoading()
            },
            onError = { stopAndError(it, application::printTextInToast) }
        )
    }

    private fun changeLanguage(
        intent: SettingsIntent.ChangeLanguage
    ) = execute(Dispatchers.IO) {
        settingUsesCases.saveGameLanguage(
            intent.language,
            onLoading = { startLoading() },
            onSuccess = {
                updateUi { it.copy(gameLanguage = intent.language) }
                stopLoading()
            },
            onError = { stopAndError(it, application::printTextInToast) }
        )
    }

    //Privates:
    private fun startLoading() {
        updateUi { it.copy(isLoading = true) }
    }

    private fun stopLoading() {
        updateUi { it.copy(isLoading = false) }
    }

    private fun stopAndError(error: String, onError: (String) -> Unit) {
        stopLoading()
        execute { onError(error) }
    }
}



