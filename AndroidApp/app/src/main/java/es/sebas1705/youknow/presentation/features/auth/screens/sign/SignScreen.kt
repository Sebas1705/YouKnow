package es.sebas1705.youknow.presentation.features.auth.screens.sign
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
import es.sebas1705.youknow.presentation.features.auth.screens.sign.design.SignDesign
import es.sebas1705.youknow.presentation.features.auth.screens.sign.viewmodel.SignIntent
import es.sebas1705.youknow.presentation.features.auth.screens.sign.viewmodel.SignState
import es.sebas1705.youknow.presentation.features.auth.screens.sign.viewmodel.SignViewModel
import es.sebas1705.youknow.presentation.features.auth.viewmodels.AuthState
import es.sebas1705.youknow.presentation.features.auth.viewmodels.AuthViewModel

/**
 * Sign Screen that will allow the user to sign up or log in.
 * It will show a form with email and password fields.
 * If the user is not registered, it will allow the user to sign up.
 *
 * @param windowState [WindowState]: State of the window.
 * @param authViewModel [AuthViewModel]: ViewModel that will handle the authentication.
 * @param toLogNav () -> Unit: Function for navigation to the Log Screen.
 *
 * @see AuthViewModel
 * @see WindowState
 * @see SignDesign
 * @see AuthState
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun SignScreen(
    windowState: WindowState,
    toLogNav: () -> Unit,
) {
    val signViewModel: SignViewModel = hiltViewModel()
    val signState: SignState by signViewModel.uiState.collectAsStateWithLifecycle()

    //Design:
    SignDesign(
        windowState,
        signState,
        onSignButtonAction = { email, pass, nick, onError ->
            signViewModel.eventHandler(
                SignIntent.SignUpWithEmail(
                    email = email,
                    password = pass,
                    nickname = nick,
                    onSuccess = toLogNav,
                    onError = onError
                )
            )
        }
    )
}

