package es.sebas1705.youknow.core.composables.textfields
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

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.utlis.IComposablePreview
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

@Composable
fun IPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    label: String = stringResource(R.string.password),
    placeholder: String = stringResource(R.string.password),
) {
    var passwordVisible by remember { mutableStateOf(false) }
    IOutlinedTextField(
        value,
        onValueChange,
        modifier,
        label,
        placeholder,
        enabled,
        readOnly,
        leadingIcon = Icons.Filled.Password to {},
        trailingIcon =
        (if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff) to
                { passwordVisible = !passwordVisible },
        isError = isError,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@IComposablePreview
@Composable
private fun PreviewE() = YouKnowTheme {
    IPasswordTextField(
        "Value",
        {},
        enabled = true,
        isError = false
    )
}

@IComposablePreview
@Composable
private fun PreviewD() = YouKnowTheme {
    IPasswordTextField(
        "Value",
        {},
        enabled = false,
        isError = false
    )
}