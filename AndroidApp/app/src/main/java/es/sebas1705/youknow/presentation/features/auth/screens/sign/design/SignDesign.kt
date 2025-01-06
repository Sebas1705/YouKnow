package es.sebas1705.youknow.presentation.features.auth.screens.sign.design
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
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.spacers.IVerSpacer
import es.sebas1705.youknow.core.composables.texts.TitleSurface
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.auth.screens.sign.composables.SignField
import es.sebas1705.youknow.presentation.features.auth.screens.sign.viewmodel.SignState
import es.sebas1705.youknow.presentation.ui.theme.Paddings.HugePadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Main Design of the SignScreen.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun SignDesign(
    windowState: WindowState = WindowState.default(),
    signState: SignState = SignState.default(),
    onSignButtonAction: (email: String, pass: String, nickname: String, onError: (String) -> Unit) -> Unit = { e, p, n, o -> }
) {

    //Locals:
    val keyboard = LocalSoftwareKeyboardController.current

    //Texts:
    val defaultErrorText = stringResource(id = R.string.login_error)
    val notMatchErrorText = stringResource(id = R.string.any_not_match)
    val bothText = stringResource(id = R.string.both)
    val emailText = stringResource(id = R.string.email)
    val passwordText = stringResource(id = R.string.password)

    //States:
    var email by rememberSaveable { mutableStateOf("") }
    var emailRepeat by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordRepeat by rememberSaveable { mutableStateOf("") }
    var userName by rememberSaveable { mutableStateOf("") }
    var error by rememberSaveable { mutableStateOf(defaultErrorText) }

    //Flags:
    var errorFlag by rememberSaveable { mutableStateOf(false) }

    //Actions:
    val activateError: (String) -> Unit = { error = it; errorFlag = true }

    ApplyBack(
        windowState.backFill
    ) {
        //Dialogs:
        if (signState.isLoading)
            LoadingDialog(windowState)
        else if (errorFlag) {
            ErrorInfoDialog(
                errorText = error,
                onConfirm = { errorFlag = false }
            )
        }

        //Body:
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
                        Modifier
                            .height(windowState.heightDp)
                            .fillMaxWidth(),
                        Modifier
                            .height(windowState.heightDp)
                            .fillParentMaxWidth()
                    ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TitleSurface(text = stringResource(id = R.string.signup))
                    IVerSpacer(height = HugePadding)
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
                    IVerSpacer(height = MediumPadding)
                    IFilledButton(
                        label = stringResource(id = R.string.signup),
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