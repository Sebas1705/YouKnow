package es.sebas1705.home.main
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
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.common.states.WindowState
import es.sebas1705.home.main.design.MainDesign
import es.sebas1705.home.main.viewmodel.MainIntent
import es.sebas1705.home.main.viewmodel.MainViewModel
import es.sebas1705.home.navigation.viewmodel.HomeState

/**
 * Main Screen of the app.
 *
 * @param windowState [WindowState]: The state of the window.
 * @param homeState [HomeState]: The state of the Home Screen.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onSettingsNav () -> Unit: The navigation to the settings.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun MainScreen(
    windowState: WindowState,
    homeState: HomeState,
    soundPool: Pair<SoundPool, Float>,
    onSettingsNav: () -> Unit
) {
    //ViewModel:
    val mainViewModel: MainViewModel = hiltViewModel()

    //State:
    val mainState by mainViewModel.uiState.collectAsStateWithLifecycle()

    //Effects:
    LaunchedEffect(windowState) {
        mainViewModel.eventHandler(MainIntent.GetRanking)
        mainViewModel.eventHandler(MainIntent.GetNews)
    }

    //Local:
    BackHandler {}

    //Body:
    MainDesign(
        windowState,
        homeState,
        mainState,
        soundPool,
        onReloadButton = {
            mainViewModel.eventHandler(MainIntent.RecreateGameDB)
        },
        onSettingsNav
    )
}

