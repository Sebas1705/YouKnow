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

import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.common.states.WindowState
import es.sebas1705.settings.design.SettingsDesign
import es.sebas1705.settings.viewmodel.SettingsIntent
import es.sebas1705.settings.viewmodel.SettingsViewModel

/**
 * Screen for the Settings of the app.
 *
 * @param windowState [WindowState]: State of the Settings.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onBack () -> Unit: Function to go back to the previous screen.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun SettingsScreen(
    windowState: WindowState,
    soundPool: Pair<SoundPool, Float>,
    onBack: () -> Unit
) {

    //ViewModel:
    val settingsViewModel: SettingsViewModel = hiltViewModel()

    //States:
    val settingsState by settingsViewModel.uiState.collectAsStateWithLifecycle()

    //Effects:
    LaunchedEffect(null) {
        settingsViewModel.eventHandler(SettingsIntent.ReadSettings)
    }

    //Body:
    SettingsDesign(
        windowState,
        settingsState,
        soundPool,
        onBack,
        onMusicVolumeSlideBarChange = {
            settingsViewModel.eventHandler(SettingsIntent.ChangeMusicVolume(it))
        },
        onSoundVolumeSliderBarChange = {
            settingsViewModel.eventHandler(SettingsIntent.ChangeSoundVolume(it))
        },
        onContrastClick = {
            settingsViewModel.eventHandler(SettingsIntent.ChangeContrast(it))
        },
        onLanguageClick = {
            settingsViewModel.eventHandler(SettingsIntent.ChangeLanguage(it))
        },
        onRestoreClick = {
            settingsViewModel.eventHandler(SettingsIntent.RestoreSettings)
        }
    )
}



