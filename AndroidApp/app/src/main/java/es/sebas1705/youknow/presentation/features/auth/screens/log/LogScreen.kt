package es.sebas1705.youknow.presentation.features.auth.screens.log
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
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.presentation.features.auth.screens.log.design.LogDesign
import es.sebas1705.youknow.presentation.features.auth.screens.log.viewmodel.LogIntent
import es.sebas1705.youknow.presentation.features.auth.screens.log.viewmodel.LogViewModel

/**
 * Main composable for the Log screen. It contains the [LogDesign] composable, which is the main design of the screen.
 *
 * @param windowState [WindowState]: State of the window.
 * @param toHomeNav () -> Unit: Function to navigate to the home screen.
 * @param toSignNav () -> Unit: Function to navigate to the sign screen.
 *
 * @author: Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun LogScreen(
    windowState: WindowState,
    toHomeNav: () -> Unit,
    toSignNav: () -> Unit
) {

    //ViewModel:
    val logViewModel: LogViewModel = hiltViewModel()

    //State:
    val logState by logViewModel.uiState.collectAsStateWithLifecycle()

    //Design:
    LogDesign(
        windowState,
        logState,
        onRegisterButton = toSignNav,
        onPasswordForgot = {
            logViewModel.eventHandler(LogIntent.SendForgotPassword(it))
        },
        onLoginButton = { email, password, onError ->
            logViewModel.eventHandler(
                LogIntent.SignInWithEmail(
                    email = email,
                    password = password,
                    onSuccess = toHomeNav,
                    onError = onError
                )
            )
        }
    )
}







