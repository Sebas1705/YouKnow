package es.sebas1705.youknow.presentation.features.auth.screens.log.design
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.common.IFilledButton
import es.sebas1705.youknow.core.composables.dialogs.ErrorInfoDialog
import es.sebas1705.youknow.core.composables.dialogs.ForgotPasswordDialog
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.spacers.IVerSpacer
import es.sebas1705.youknow.core.composables.texts.TitleSurface
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.auth.screens.log.composable.EmailAndPassFields
import es.sebas1705.youknow.presentation.features.auth.screens.log.composable.ForgotAndRegisterButtons
import es.sebas1705.youknow.presentation.features.auth.screens.log.viewmodel.LogState
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Main design of the Log screen.
 *
 * @param windowState [WindowState]: State of the window.
 * @param logState [LogState]: State of the Log screen.
 * @param onRegisterButton () -> Unit: Function to navigate to the register screen.
 * @param onPasswordForgot (String) -> Unit: Function to send a password recovery email.
 * @param onLoginButton (email: String, pass: String, onError: (String) -> Unit) -> Unit: Function to login.
 *
 * @author: Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun LogDesign(
    windowState: WindowState = WindowState.default(),
    logState: LogState = LogState.default(),
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

    ApplyBack(
        windowState.backFill
    ) {
        //Dialogs:
        if (logState.isLoading)
            LoadingDialog(windowState)
        else if (errorFlag)
            ErrorInfoDialog(
                errorText = error,
                onConfirm = { errorFlag = false }
            )
        else if (passwordFlag)
            ForgotPasswordDialog(
                onConfirm = {
                    onPasswordForgot(it)
                    passwordFlag = false
                },
                onDismiss = {
                    keyboard?.hide()
                    passwordFlag = false
                }
            )

        //Body:
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
                    IVerSpacer(0.4f)
                    TitleSurface(stringResource(R.string.access))
                    IVerSpacer(0.2f)
                    EmailAndPassFields(
                        modifier = Modifier
                            .fillMaxWidth(windowState.widthType.filter(1f, 0.8f, 0.6f)),
                        email = email,
                        password = password,
                        onEmailChange = { email = it },
                        onPasswordChange = { password = it },
                    )
                    IVerSpacer(height = SmallestPadding)
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
                    IVerSpacer(0.2f)
                    IFilledButton(
                        label = stringResource(id = R.string.access),
                        modifier = Modifier,
                        onClick = {
                            keyboard?.hide()
                            onLoginButton(email, password) {
                                error = it
                                errorFlag = true
                            }
                        }
                    )
                    IVerSpacer(if (windowState.isImeVisible) 0.2f else 0.7f)
                }
            }
        }
    }
}

@UiModePreviews
@Composable
private fun LogPreview() {
    YouKnowTheme {
        LogDesign()
    }
}