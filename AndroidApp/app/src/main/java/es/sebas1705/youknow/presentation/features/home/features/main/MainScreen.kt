package es.sebas1705.youknow.presentation.features.home.features.main
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

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.presentation.features.home.features.main.design.MainDesign
import es.sebas1705.youknow.presentation.features.home.viewmodels.RankingIntent
import es.sebas1705.youknow.presentation.features.home.viewmodels.RankingViewModel

/**
 * Main Screen of the app.
 *
 * @see es.sebas1705.youknow.presentation.features.home.features.main.design.MainDesign
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun MainScreen(
    windowState: WindowState,
    onSettingsNav: () -> Unit
) {
    val rankingViewModel: RankingViewModel = hiltViewModel()
    val rankingState by rankingViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(windowState) {
        rankingViewModel.eventHandler(RankingIntent.GetRanking)
    }

    BackHandler {}
    MainDesign(
        windowState = windowState,
        rankingState = rankingState,
        onSettingsNav
    )
}

