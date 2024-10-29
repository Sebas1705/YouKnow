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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.composables.ApplyBack
import es.sebas1705.youknow.presentation.composables.CustomFilledButton
import es.sebas1705.youknow.presentation.composables.Spacers.DoubleSpacer
import es.sebas1705.youknow.presentation.composables.Spacers.QuadSpacer
import es.sebas1705.youknow.presentation.composables.TitleSurface
import es.sebas1705.youknow.presentation.features.auth.viewmodels.AuthIntent
import es.sebas1705.youknow.presentation.features.auth.viewmodels.AuthState
import es.sebas1705.youknow.presentation.features.auth.viewmodels.AuthViewModel
import es.sebas1705.youknow.presentation.features.auth.windows.ErrorInfoWindow
import es.sebas1705.youknow.presentation.ui.classes.WindowState
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

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
    authViewModel: AuthViewModel,
    toLogNav: () -> Unit,
) {
    //States:
    val authState: AuthState by authViewModel.uiState.collectAsStateWithLifecycle()

    //Design:
    SignDesign(
        windowState,
        authState,
        onSignButtonAction = { email, pass, nick, onError ->
            authViewModel.eventHandler(
                AuthIntent.SignUpWithEmail(
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

/**
 * Design of the Sign Screen.
 * It will show a form with email and password fields.
 *
 * @param onSignButtonAction () -> Unit: Function that will be called when the user signs up or logs in successfully.
 * @param windowState [WindowState]: State of the window.
 * @param authState [AuthState]: State of the authentication.
 *
 * @see AuthViewModel
 * @see AuthIntent
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
private fun SignDesign(
    windowState: WindowState = WindowState(),
    authState: AuthState = AuthState.default(),
    onSignButtonAction: (email: String, pass: String, nickname: String, onError: (String) -> Unit) -> Unit = { e, p, n, o -> }
) {

    //Locals:
    val keyboard = LocalSoftwareKeyboardController.current

    //Texts:
    val defaultErrorText = stringResource(id = R.string.textLogError)
    val notMatchErrorText = stringResource(id = R.string.emailPasswordNotMatch)
    val bothText = stringResource(id = R.string.both)
    val emailText = stringResource(id = R.string.email)
    val passwordText = stringResource(id = R.string.password)

    //States:
    var email by remember { mutableStateOf("") }
    var emailRepeat by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordRepeat by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(defaultErrorText) }

    //Flags:
    var errorFlag by rememberSaveable { mutableStateOf(false) }

    //Actions:
    val activateError: (String) -> Unit = { error = it; errorFlag = true }

    if (errorFlag) {
        ErrorInfoWindow(
            errorText = error,
            onConfirm = { errorFlag = false }
        )
    }

    ApplyBack(
        backId = windowState.backFill,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = MediumPadding)
                .imePadding(),
        ) {
            item {
                Column(
                    modifier = windowState.heightType.filter(
                        Modifier.fillMaxWidth(),
                        Modifier.height(windowState.heightDp).fillMaxWidth(),
                        Modifier.height(windowState.heightDp).fillParentMaxWidth()
                    ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (authState.isLoading) CircularProgressIndicator(
                        Modifier
                            .padding(MediumPadding)
                    )
                    else TitleSurface(text = stringResource(id = R.string.signup))
                    QuadSpacer()
                    SignField(
                        fieldsModifier = Modifier
                            .fillMaxWidth(windowState.widthType.filter(1f, 0.8f, 0.6f)),
                        userName = userName,
                        onUserNameChange = { userName = it },
                        email = email,
                        onEmailChange = { email = it },
                        emailRepeat = emailRepeat,
                        onEmailRepeatChange = { emailRepeat = it },
                        password = password,
                        onPasswordChange = { password = it },
                        passwordRepeat = passwordRepeat,
                        onPasswordRepeatChange = { passwordRepeat = it }
                    )
                    DoubleSpacer()
                    CustomFilledButton(
                        text = stringResource(id = R.string.signup),
                        modifier = Modifier,
                        onClick = {
                            keyboard?.hide()
                            if (email == emailRepeat && password == passwordRepeat)
                                onSignButtonAction(email, password, userName, activateError)
                            else {
                                val who = when {
                                    email != emailRepeat && password != passwordRepeat -> bothText
                                    email != emailRepeat -> emailText
                                    else -> passwordText
                                }
                                activateError("$notMatchErrorText ($who)")
                            }
                        }
                    )
                }
            }
        }
    }
}

/**
 * Preview of the Sign Screen.
 *
 * @see SignDesign
 */
@UiModePreviews
@Composable
private fun SignPreview() {
    YouKnowTheme {
        SignDesign()
    }
}

