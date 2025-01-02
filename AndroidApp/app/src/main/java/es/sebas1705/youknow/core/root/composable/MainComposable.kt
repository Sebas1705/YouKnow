package es.sebas1705.youknow.core.root.composable
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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.youknow.core.composables.states.rememberWindowState
import es.sebas1705.youknow.presentation.features.networkerror.NetworkErrorScreen
import es.sebas1705.youknow.presentation.features.splash.SplashScreen
import es.sebas1705.youknow.presentation.navigation.AppNav
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Main composable of the app that contains the splash screen and
 * the navigation host of the app
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun MainComposable() {

    //Locals:
    val ctx = LocalContext.current

    //ViewModels:
    val mainViewModel: MainViewModel = hiltViewModel()

    //States:
    val mainState by mainViewModel.uiState.collectAsStateWithLifecycle()
    val windowState by rememberWindowState()

    LaunchedEffect(null) {
        mainViewModel.eventHandler(MainIntent.ChargeData)
    }

    //Content:
    YouKnowTheme(
        themeContrast = mainState.themeContrast,
    ) {
        if (mainState.isSplashVisible)
            SplashScreen(windowState)
        else {
            if (mainState.isNetworkAvailable) AppNav(
                mainState.startDestination,
                windowState
            )
            else NetworkErrorScreen(windowState)
        }
    }
}
