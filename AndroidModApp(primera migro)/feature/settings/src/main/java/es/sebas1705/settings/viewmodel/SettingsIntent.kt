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
import es.sebas1705.common.mvi.MVIBaseIntent
import es.sebas1705.common.theme.ThemeContrast

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
    data class ChangeMusicVolume(val volume: Float) : SettingsIntent
    data class ChangeSoundVolume(val volume: Float) : SettingsIntent
    data class ChangeLanguage(val language: Languages) : SettingsIntent
    data object RestoreSettings : SettingsIntent
}