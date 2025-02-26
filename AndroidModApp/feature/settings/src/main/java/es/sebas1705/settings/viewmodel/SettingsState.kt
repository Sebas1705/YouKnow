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

import es.sebas1705.common.games.Languages
import es.sebas1705.common.mvi.MVIBaseState
import es.sebas1705.common.theme.ThemeContrast
import es.sebas1705.datastore.config.DefaultValuesDS

/**
 * Data class that represents the state of the Settings Screen.
 *
 * @param isLoading [Boolean]: Loading state of the app.
 * @param themeContrast [ThemeContrast]: Theme contrast of the app.
 * @param musicVolume [Float]: Music volume of the app.
 * @param soundVolume [Float]: Sound volume of the app.
 * @param gameLanguage [Languages]: Language of the game.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class SettingsState(
    val isLoading: Boolean,
    val themeContrast: ThemeContrast,
    val musicVolume: Float,
    val soundVolume: Float,
    val gameLanguage: Languages
) : MVIBaseState {
    companion object {

        /**
         * Default state of the Settings Screen.
         *
         * @return [SettingsState]: Default state of the Settings Screen.
         *
         * @since 1.0.0
         * @author Sebastián Ramiro Entrerrios García
         */
        fun default() = SettingsState(
            isLoading = false,
            themeContrast = DefaultValuesDS.APP_UI_CONTRAST,
            musicVolume = DefaultValuesDS.MUSIC_VOLUME,
            soundVolume = DefaultValuesDS.SOUND_VOLUME,
            gameLanguage = DefaultValuesDS.GAME_LANGUAGE
        )
    }
}