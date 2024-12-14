package es.sebas1705.youknow.presentation.composables
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

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import es.sebas1705.youknow.R

/**
 * Custom OutlinedTextField
 *
 * @param modifier Modifier: modifier of the OutlinedTextField
 * @param value String: value of the OutlinedTextField
 * @param placeholder String: placeholder of the OutlinedTextField
 * @param label String: label of the OutlinedTextField
 * @param password Boolean: if the OutlinedTextField is a password field
 * @param singleLine Boolean: if the OutlinedTextField is a single line field
 * @param keyboardOptions KeyboardOptions: keyboard options of the OutlinedTextField
 * @param onValueChange (String) -> Unit: function to handle the value change of the OutlinedTextField
 *
 * @see OutlinedTextField
 * @see IconButton
 * @see Icon
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String = "",
    label: String = "",
    password: Boolean = false,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit = {},
    leadingIcon: (@Composable () -> Unit)? = {
        Icon(
            imageVector = if (password) Icons.Filled.Password else Icons.Filled.Mail,
            contentDescription = label
        )
    },
    trailingIcon: (@Composable () -> Unit)? = null
) {

    var passwordVisible by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }

    val trailingIcon =
        if (trailingIcon != null) trailingIcon
        else if (password) {
            @Composable {
                IconButton(
                    onClick = { passwordVisible = !passwordVisible }
                ) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "Toggle password visibility"
                    )
                }
            }
        } else null

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            if (!isFocused) Text(text = placeholder)
        },
        leadingIcon = leadingIcon,
        label = {
            Text(text = label)
        },
        modifier = modifier
            .onFocusChanged {
                isFocused = it.isFocused
            },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            cursorColor = MaterialTheme.colorScheme.tertiary
        ),
        textStyle = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
        keyboardOptions = keyboardOptions,
        visualTransformation = if (passwordVisible || !password) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = trailingIcon,
        singleLine = singleLine
    )
}

/**
 * Custom OutlinedTextField for password
 *
 * @param modifier Modifier: modifier of the OutlinedTextField
 * @param value String: value of the OutlinedTextField
 * @param placeholder String: placeholder of the OutlinedTextField
 * @param label String: label of the OutlinedTextField
 * @param onValueChange (String) -> Unit: function to handle the value change of the OutlinedTextField
 *
 * @see CustomOutlinedTextField
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun CustomTextFieldPassword(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String = stringResource(id = R.string.password),
    label: String = stringResource(id = R.string.password),
    onValueChange: (String) -> Unit = {}
) = CustomOutlinedTextField(
    modifier = modifier,
    value = value,
    placeholder = placeholder,
    label = label,
    password = true,
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    onValueChange = onValueChange,
)

/**
 * Custom OutlinedTextField for email
 *
 * @param modifier Modifier: modifier of the OutlinedTextField
 * @param value String: value of the OutlinedTextField
 * @param placeholder String: placeholder of the OutlinedTextField
 * @param label String: label of the OutlinedTextField
 * @param onValueChange (String) -> Unit: function to handle the value change of the OutlinedTextField
 *
 * @see CustomOutlinedTextField
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun CustomTextFieldEmail(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String = stringResource(id = R.string.email),
    label: String = stringResource(id = R.string.email),
    onValueChange: (String) -> Unit = {}
) = CustomOutlinedTextField(
    modifier = modifier,
    value = value,
    placeholder = placeholder,
    label = label,
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    onValueChange = onValueChange
)

/**
 * Simple CustomTextField without leading icon and with default values
 *
 * @param modifier Modifier: modifier of the OutlinedTextField
 * @param value String: value of the OutlinedTextField
 * @param placeholder String: placeholder of the OutlinedTextField
 * @param label String: label of the OutlinedTextField
 * @param onValueChange (String) -> Unit: function to handle the value change of the OutlinedTextField
 *
 * @see CustomOutlinedTextField
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun SimpleOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String = "",
    label: String = "",
    onValueChange: (String) -> Unit = {},
) = CustomOutlinedTextField(
    modifier = modifier,
    value = value,
    placeholder = placeholder,
    label = label,
    onValueChange = onValueChange,
    leadingIcon = null,
)