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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.printTextInToast
import es.sebas1705.youknow.presentation.composables.ApplyBack
import es.sebas1705.youknow.presentation.composables.CustomFilledButton
import es.sebas1705.youknow.presentation.composables.Spacers.HalfSpacer
import es.sebas1705.youknow.presentation.composables.Spacers.HorizontalSpacer
import es.sebas1705.youknow.presentation.composables.TitleSurface
import es.sebas1705.youknow.presentation.features.app.windows.LoadingWindow
import es.sebas1705.youknow.presentation.features.auth.viewmodels.AuthIntent
import es.sebas1705.youknow.presentation.features.auth.viewmodels.AuthState
import es.sebas1705.youknow.presentation.features.auth.viewmodels.AuthViewModel
import es.sebas1705.youknow.presentation.features.auth.windows.ErrorInfoWindow
import es.sebas1705.youknow.presentation.features.auth.windows.ForgotPasswordWindow
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Main composable for the Log screen. It contains the [LogDesign] composable, which is the main design of the screen.
 *
 * @param windowState [WindowState]: State of the window.
 * @param authViewModel [AuthViewModel]: ViewModel for the authentication and UiState.
 * @param toHomeNav () -> Unit: Function to navigate to the home screen.
 * @param toSignNav () -> Unit: Function to navigate to the sign screen.
 *
 * @see LogDesign
 * @see AuthViewModel
 * @see WindowState
 * @see AuthState
 *
 * @author: Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun LogScreen(
    windowState: WindowState,
    authState: AuthState,
    authViewModel: AuthViewModel,
    toHomeNav: () -> Unit,
    toSignNav: () -> Unit
) {
    val context = LocalContext.current

    //Design:
    LogDesign(
        windowState,
        authState,
        onRegisterButton = toSignNav,
        onPasswordForgot = {
            authViewModel.eventHandler(AuthIntent.SendForgotPassword(it, context::printTextInToast))
        },
        onLoginButton = { email, password, onError ->
            authViewModel.eventHandler(
                AuthIntent.SignInWithEmail(
                    email = email,
                    password = password,
                    onSuccess = toHomeNav,
                    onError = onError
                )
            )
        }
    )
}

/**
 * Design of the Log screen. It contains the main design of the screen.
 * It contains the email and password fields, the forgot password and register buttons, and the login button.
 * It also contains the error and forgot password windows.
 * It uses the [ApplyBack] composable to apply the back fill color.
 *
 * @param windowState [WindowState]: State of the window.
 * @param authState [AuthState]: State of the authentication.
 * @param onRegisterButton () -> Unit: Function to do in on click create account button.
 * @param onPasswordForgot (String) -> Unit: Function to do in on click forgot password button.
 * @param onLoginButton (email: String, pass: String, onError: (String) -> Unit) -> Unit: Function to do in on click login button.
 *
 * @see ApplyBack
 * @see ErrorInfoWindow
 * @see ForgotPasswordWindow
 * @see EmailAndPassFields
 * @see ForgotAndRegisterButtons
 * @see CustomFilledButton
 * @see WindowState
 *
 * @author: Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
private fun LogDesign(
    windowState: WindowState = WindowState.default(),
    authState: AuthState = AuthState.default(),
    onRegisterButton: () -> Unit = {},
    onPasswordForgot: (String) -> Unit = {},
    onLoginButton: (email: String, pass: String, onError: (String) -> Unit) -> Unit = { e, p, o -> }
) {

    //Locals:
    val keyboard = LocalSoftwareKeyboardController.current

    //Texts:
    val defaultError = stringResource(id = R.string.login_error)

    //States:
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var error by rememberSaveable { mutableStateOf(defaultError) }


    //Flags:
    var errorFlag by rememberSaveable { mutableStateOf(false) }
    var passwordFlag by rememberSaveable { mutableStateOf(false) }

    //Windows pull ups:
    if (errorFlag) {
        ErrorInfoWindow(
            errorText = error,
            onConfirm = { errorFlag = false }
        )
    }
    if (passwordFlag) {
        ForgotPasswordWindow(
            onConfirm = {
                onPasswordForgot(it)
                passwordFlag = false
            },
            onDismiss = {
                keyboard?.hide()
                passwordFlag = false
            }
        )
    }

    //Body:
    ApplyBack(
        backId = windowState.backFill,
    ) {
        if (authState.isLoading) LoadingWindow(windowState)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = MediumPadding)
                .imePadding(),
        ) {
            item {
                Column(
                    modifier = Modifier
                        .height(windowState.heightDp)
                        .fillParentMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HorizontalSpacer(0.4f)
                    TitleSurface(text = stringResource(id = R.string.access))
                    HorizontalSpacer(0.2f)
                    EmailAndPassFields(
                        fieldsModifier = Modifier
                            .fillMaxWidth(windowState.widthType.filter(1f, 0.8f, 0.6f)),
                        email = email,
                        password = password,
                        onEmailChange = { email = it },
                        onPasswordChange = { password = it },
                    )
                    HalfSpacer()
                    ForgotAndRegisterButtons(
                        onForgotButton = {
                            keyboard?.hide()
                            passwordFlag = true
                        },
                        onRegisterButton = {
                            keyboard?.hide()
                            onRegisterButton()
                        }
                    )
                    HorizontalSpacer(0.2f)
                    CustomFilledButton(
                        text = stringResource(id = R.string.access),
                        modifier = Modifier,
                        onClick = {
                            keyboard?.hide()
                            onLoginButton(email, password) {
                                error = it
                                errorFlag = true
                            }
                        }
                    )
                    HorizontalSpacer(if (windowState.isImeVisible) 0.2f else 0.7f)
                }
            }
        }
    }
}

/**
 * Preview of the [LogDesign] composable.
 *
 * @see LogDesign
 */
@UiModePreviews
@Composable
private fun LogPreview() {
    YouKnowTheme {
        LogDesign()
    }
}





