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
import androidx.compose.ui.Modifier
import es.sebas1705.youknow.presentation.composables.CustomTextFieldEmail
import es.sebas1705.youknow.presentation.composables.CustomTextFieldPassword
import es.sebas1705.youknow.presentation.composables.Spacers.SimpleSpacer

/**
 * Composable that contains the email and password fields customized with the [CustomTextFieldEmail] and [CustomTextFieldPassword] composable.
 * Also, a [SimpleSpacer] is placed between the fields, to separate them. The fields are placed vertically.
 *
 * @param fieldsModifier Modifier: to be applied to the fields.
 * @param email String: Current email value.
 * @param password String: Current password value.
 * @param onEmailChange (String) -> Unit: Callback to be called when the email value changes.
 * @param onPasswordChange (String) -> Unit: Callback to be called when the password value changes.
 *
 * @see CustomTextFieldEmail
 * @see CustomTextFieldPassword
 * @see SimpleSpacer
 *
 * @author: Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun EmailAndPassFields(
    fieldsModifier: Modifier = Modifier,
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
) {

    CustomTextFieldEmail(
        modifier = fieldsModifier,
        value = email,
        onValueChange = onEmailChange
    )
    SimpleSpacer()
    CustomTextFieldPassword(
        modifier = fieldsModifier,
        value = password,
        onValueChange = onPasswordChange
    )
}
