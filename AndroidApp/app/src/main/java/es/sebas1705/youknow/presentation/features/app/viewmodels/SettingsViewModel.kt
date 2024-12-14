package es.sebas1705.youknow.presentation.features.app.viewmodels
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

import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.core.classes.mvi.MVIBaseIntent
import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.core.classes.theme.ThemeContrast
import es.sebas1705.youknow.data.local.datastore.config.DefaultValuesDS
import es.sebas1705.youknow.domain.usecases.DatastoreUsesCases
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * ViewModel for Settings Screen that will handle the UI logic and the data flow.
 *
 * @param datastoreUsesCases [DatastoreUsesCases]: UseCase to handle the app settings.
 *
 * @see MVIBaseViewModel
 * @see HiltViewModel
 * @see SettingsState
 * @see SettingsIntent
 * @see DatastoreUsesCases
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val datastoreUsesCases: DatastoreUsesCases,
) : MVIBaseViewModel<SettingsState, SettingsIntent>() {

    override fun initState(): SettingsState = SettingsState.default()

    override fun intentHandler(intent: SettingsIntent) {
        when (intent) {
            is SettingsIntent.ChargeSettings -> chargeSettings()
            is SettingsIntent.SaveSettings -> saveSettings()
            is SettingsIntent.ChangeContrast -> changeContrast(intent.themeContrast)
            is SettingsIntent.ChangeVolume -> changeVolume(intent.volume)
        }
    }

    //Actions:
    private fun chargeSettings() {
        execute(Dispatchers.IO) {
            datastoreUsesCases.readAppContrast().collect { data ->
                updateUi { it.copy(themeContrast = data) }
            }
            datastoreUsesCases.readAppVolume().collect { data ->
                updateUi { it.copy(volume = data) }
            }
        }
    }

    private fun saveSettings() {
        execute(Dispatchers.IO) {
            datastoreUsesCases.saveAppContrast(uiState.value.themeContrast)
            datastoreUsesCases.saveAppVolume(uiState.value.volume)
        }
    }

    private fun changeContrast(themeContrast: ThemeContrast) {
        updateUi { it.copy(themeContrast = themeContrast) }
    }

    private fun changeVolume(volume: Float) {
        updateUi { it.copy(volume = volume) }
    }
}

/**
 * Data class that represents the state of the Settings Screen.
 *
 * @param themeContrast [ThemeContrast]: Theme contrast of the app.
 * @param volume [Float]: Volume of the app.
 *
 * @see MVIBaseState
 * @see ThemeContrast
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class SettingsState(
    val themeContrast: ThemeContrast,
    val volume: Float
) : MVIBaseState {
    companion object {

        /**
         * Default state of the Settings Screen.
         *
         * @return [SettingsState]: Default state of the Settings Screen.
         */
        fun default() = SettingsState(
            themeContrast = DefaultValuesDS.APP_UI_CONTRAST,
            volume = DefaultValuesDS.APP_VOLUME
        )
    }
}

/**
 * Sealed class that represents the possible intents of the Settings Screen.
 *
 * @see MVIBaseIntent
 * @see ThemeContrast
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface SettingsIntent : MVIBaseIntent {
    data object ChargeSettings : SettingsIntent
    data object SaveSettings : SettingsIntent
    data class ChangeContrast(val themeContrast: ThemeContrast) : SettingsIntent
    data class ChangeVolume(val volume: Float) : SettingsIntent
}