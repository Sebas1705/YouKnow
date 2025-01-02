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

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.composables.spacers.IVerSpacer
import es.sebas1705.youknow.core.composables.textfields.IEmailTextField
import es.sebas1705.youknow.core.composables.textfields.IOutlinedTextField
import es.sebas1705.youknow.core.composables.textfields.IPasswordTextField
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding

/**
 * Sign Field that will allow the user to sign up.
 * It will show a form with username, email and password fields.
 * The email and password fields will be repeated.
 *
 * @param fieldsModifier [Modifier]: Modifier for the fields.
 * @param userName [String]: Username of the user.
 * @param onUserNameChange (String) -> Unit: Function to handle the username change.
 * @param email [String]: Email of the user.
 * @param onEmailChange (String) -> Unit: Function to handle the email change.
 * @param emailRepeat [String]: Repeat email of the user.
 * @param onEmailRepeatChange (String) -> Unit: Function to handle the repeat email change.
 * @param password [String]: Password of the user.
 * @param onPasswordChange (String) -> Unit: Function to handle the password change.
 * @param passwordRepeat [String]: Repeat password of the user.
 * @param onPasswordRepeatChange (String) -> Unit: Function to handle the repeat password change.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun ColumnScope.SignField(
    fieldsModifier: Modifier = Modifier,
    userName: String,
    onUserNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    emailRepeat: String,
    onEmailRepeatChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordRepeat: String,
    onPasswordRepeatChange: (String) -> Unit
) {
    //Texts:
    val userNameText = stringResource(id = R.string.username)
    val emailText = stringResource(id = R.string.email)
    val passwordText = stringResource(id = R.string.password)
    val repeatText = stringResource(id = R.string.repeat)

    //Fields:
    IOutlinedTextField(
        modifier = fieldsModifier,
        value = userName,
        onValueChange = onUserNameChange,
        label = userNameText,
        placeholder = userNameText
    )
    IVerSpacer(height = SmallPadding)
    IEmailTextField(
        modifier = fieldsModifier,
        value = email,
        onValueChange = onEmailChange,
    )
    IVerSpacer(height = SmallPadding)
    IEmailTextField(
        modifier = fieldsModifier,
        value = emailRepeat,
        onValueChange = onEmailRepeatChange,
        label = "$repeatText $emailText",
        placeholder = "$repeatText $emailText"
    )
    IVerSpacer(height = SmallPadding)
    IPasswordTextField(
        modifier = fieldsModifier,
        value = password,
        onValueChange = onPasswordChange
    )
    IVerSpacer(height = SmallPadding)
    IPasswordTextField(
        modifier = fieldsModifier,
        value = passwordRepeat,
        onValueChange = onPasswordRepeatChange,
        label = "$repeatText $passwordText",
        placeholder = "$repeatText $passwordText"
    )
}