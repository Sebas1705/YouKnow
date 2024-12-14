package es.sebas1705.youknow.presentation.features.app.screens
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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.composables.AppIcon
import es.sebas1705.youknow.presentation.composables.ApplyBack
import es.sebas1705.youknow.presentation.composables.Spacers.DoubleSpacer
import es.sebas1705.youknow.presentation.composables.Spacers.SimpleSpacer
import es.sebas1705.youknow.presentation.composables.TitleApp
import es.sebas1705.youknow.presentation.composables.rememberWindowState
import es.sebas1705.youknow.presentation.features.app.navigation.AppNav
import es.sebas1705.youknow.presentation.features.app.viewmodels.SettingsIntent
import es.sebas1705.youknow.presentation.features.app.viewmodels.SettingsState
import es.sebas1705.youknow.presentation.features.app.viewmodels.SettingsViewModel
import es.sebas1705.youknow.presentation.features.app.viewmodels.SplashIntent
import es.sebas1705.youknow.presentation.features.app.viewmodels.SplashState
import es.sebas1705.youknow.presentation.features.app.viewmodels.SplashViewModel
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme
import kotlinx.coroutines.delay

/**
 * SplashScreen is the first screen that the user sees when the app is opened.
 * It is responsible for loading the data from the cloud and the settings of the app.
 * It also shows the app icon and the app name. It has a loading bar that is shown while the data is being loaded.
 * When the data is loaded, it navigates to the [AppNav] screen.
 *
 * @see SplashDesign
 * @see WindowState
 * @see SplashState
 * @see SettingsState
 * @see SettingsViewModel
 * @see SplashViewModel
 *
 * @author Sebastian Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun SplashScreen() {

    val context = LocalContext.current

    //ViewModels:
    val splashViewModel: SplashViewModel = hiltViewModel()
    val settingsViewModel: SettingsViewModel = hiltViewModel()
    splashViewModel.eventHandler(SplashIntent.SetConnectivityCallback(context))

    //States:
    val splashState by splashViewModel.uiState.collectAsStateWithLifecycle()
    val settingsState by settingsViewModel.uiState.collectAsStateWithLifecycle()
    val windowState by rememberWindowState()
    var isLoaded by remember { mutableStateOf(false) }

    //Loading effect:
    LaunchedEffect(isLoaded) {
        if (!isLoaded) {
            splashViewModel.eventHandler(SplashIntent.ChargeCloudData)
            settingsViewModel.eventHandler(SettingsIntent.ChargeSettings)
            delay(2000)
            splashViewModel.eventHandler(SplashIntent.FinishSplashScreen)
            isLoaded = true
        }
    }

    //Design:
    SplashStructure(
        windowState,
        splashState,
        settingsState,
        settingsViewModel
    )
}

/**
 * SplashDesign is the design of the [SplashScreen].
 * It shows the app icon, the app name and a loading bar.
 *
 * @param windowState [WindowState]: is the state of the window.
 * @param splashState [SplashState]: is the state of the splash screen.
 * @param settingsState [SettingsState]: is the state of the settings.
 * @param settingsViewModel [SettingsViewModel]: is the ViewModel of the settings.
 *
 * @see WindowState
 * @see SplashState
 * @see SettingsState
 * @see SettingsViewModel
 * @see AppIcon
 * @see TitleApp
 * @see ApplyBack
 * @see AppNav
 * @see YouKnowTheme
 *
 * @since 1.0.0
 * @author Sebastian Ramiro Entrerrios García
 */
@Composable
private fun SplashStructure(
    windowState: WindowState = WindowState.default(),
    splashState: SplashState = SplashState.default(),
    settingsState: SettingsState = SettingsState.default(),
    settingsViewModel: SettingsViewModel? = null,
) {

    //Body:
    YouKnowTheme(
        themeContrast = settingsState.themeContrast
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            if (splashState.isSplashScreenVisible) SplashDesign(windowState)
            else {
                if (splashState.isNetworkAvailable) AppNav(
                    splashState.startDestination,
                    windowState,
                    settingsState,
                    settingsViewModel!!
                )
                else NetworkErrorScreen(windowState)
            }
        }
    }
}

/**
 * SplashDesign is the design of the [SplashScreen].
 * It shows the app icon, the app name and a loading bar.
 *
 * @param windowState [WindowState]: is the state of the window.
 *
 * @since 1.0.0
 * @author Sebastian Ramiro Entrerrios García
 */
@Composable
fun SplashDesign(
    windowState: WindowState = WindowState.default(),
){
    ApplyBack(
        backId = windowState.backEmpty,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AppIcon(
                    Modifier
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight(0.4f)
                )
                SimpleSpacer()
                TitleApp()
                DoubleSpacer()
                LinearProgressIndicator()
            }
        }
    }
}

/**
 * Preview for [SplashDesign]
 *
 * @see SplashDesign
 */
@UiModePreviews
@Composable
private fun SplashPreview() {
    YouKnowTheme {
        SplashDesign()
    }
}
