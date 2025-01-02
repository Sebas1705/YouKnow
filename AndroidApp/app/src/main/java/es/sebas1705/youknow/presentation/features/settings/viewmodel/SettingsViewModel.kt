package es.sebas1705.youknow.presentation.features.settings.viewmodel
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
import android.content.Context
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknow.core.classes.mvi.MVIBaseIntent
import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.core.classes.mvi.MVIBaseViewModel
import es.sebas1705.youknow.core.classes.theme.ThemeContrast
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
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
    private val application: Application
) : MVIBaseViewModel<SettingsState, SettingsIntent>() {

    private val ctx: Context = application.applicationContext

    override fun initState(): SettingsState = SettingsState.default()

    override fun intentHandler(
        intent: SettingsIntent
    ) {
        when (intent) {
            is SettingsIntent.ReadSettings -> readSettings()
            is SettingsIntent.ChangeContrast -> changeContrast(intent)
            is SettingsIntent.ChangeVolume -> changeVolume(intent)
        }
    }

    //Actions:
    private fun readSettings() = execute(Dispatchers.IO) {
        datastoreUsesCases.readAppVolume().collect {
            updateUi { it.copy(volume = it.volume) }
        }
        datastoreUsesCases.readAppContrast().collect {
            updateUi { it.copy(themeContrast = it.themeContrast) }
        }
    }


    private fun changeContrast(
        intent: SettingsIntent.ChangeContrast
    ) = execute(Dispatchers.IO) {
        datastoreUsesCases.saveAppContrast(
            intent.themeContrast,
            onLoading = { startLoading() },
            onSuccess = {
                updateUi { it.copy(themeContrast = intent.themeContrast) }
                stopLoading()
            },
            onError = { stopAndError(it, ctx::printTextInToast) }
        )
    }

    private fun changeVolume(
        intent: SettingsIntent.ChangeVolume
    ) = execute(Dispatchers.IO) {
        datastoreUsesCases.saveAppVolume(
            intent.volume,
            onLoading = { startLoading() },
            onSuccess = {
                updateUi { it.copy(volume = intent.volume) }
                stopLoading()
            },
            onError = { stopAndError(it, ctx::printTextInToast) }
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
    val isLoading: Boolean,
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
            isLoading = false,
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
    data object ReadSettings : SettingsIntent
    data class ChangeContrast(val themeContrast: ThemeContrast) : SettingsIntent
    data class ChangeVolume(val volume: Float) : SettingsIntent
}