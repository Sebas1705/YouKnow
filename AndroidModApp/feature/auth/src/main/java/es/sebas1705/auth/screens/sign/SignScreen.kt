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

import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.presentation.features.auth.screens.sign.design.SignDesign
import es.sebas1705.youknow.presentation.features.auth.screens.sign.viewmodel.SignIntent
import es.sebas1705.youknow.presentation.features.auth.screens.sign.viewmodel.SignState
import es.sebas1705.youknow.presentation.features.auth.screens.sign.viewmodel.SignViewModel

/**
 * Sign Screen that will allow the user to sign up or log in.
 * It will show a form with email and password fields.
 * If the user is not registered, it will allow the user to sign up.
 *
 * @param windowState [WindowState]: State of the window.
 * @param toLogNav () -> Unit: Function to navigate to the Log Screen.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun SignScreen(
    windowState: WindowState,
    soundPool: Pair<SoundPool, Float>,
    toLogNav: () -> Unit,
) {
    //ViewModel:
    val signViewModel: SignViewModel = hiltViewModel()

    //State:
    val signState: SignState by signViewModel.uiState.collectAsStateWithLifecycle()

    //Body:
    SignDesign(
        windowState,
        signState,
        soundPool,
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

