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

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.R
import es.sebas1705.youknow.presentation.composables.CustomTextButton

/**
 * Component that contains the buttons of forgot password and register question
 * These buttons are placed horizontally by a row with a vertical divider between them
 * Also, the buttons are [CustomTextButton] with the text of forgot password and wanna register
 *
 * @param onForgotButton () -> Unit: action to be executed when the forgot password button is clicked
 * @param onRegisterButton () -> Unit: action to be executed when the wanna register button is clicked
 *
 * @see CustomTextButton
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun ForgotAndRegisterButtons(
    onForgotButton: () -> Unit,
    onRegisterButton: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        CustomTextButton(
            text = stringResource(id = R.string.forgot_password),
            onClick = onForgotButton
        )
        VerticalDivider(
            modifier = Modifier.height(30.dp),
            color = MaterialTheme.colorScheme.primary
        )
        CustomTextButton(
            text = stringResource(id = R.string.wanna_register),
            onClick = onRegisterButton
        )
    }
}