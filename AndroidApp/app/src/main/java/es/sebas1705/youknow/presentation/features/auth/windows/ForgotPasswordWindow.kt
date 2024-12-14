package es.sebas1705.youknow.presentation.features.auth.windows
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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.composables.CustomOutlinedTextField
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.presentation.composables.RequestDialog

/**
 * Composable that displays a dialog to recover the password.
 *
 * @param modifier [Modifier]: Modifier to be applied to the dialog.
 * @param onConfirm [Function]: Function to be executed when the user confirms the dialog.
 * @param onDismiss [Function]: Function to be executed when the user dismisses the dialog.
 *
 * @see AlertDialog
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun ForgotPasswordWindow(
    windowState: WindowState = WindowState.default(),
    modifier: Modifier = Modifier,
    onConfirm: (String) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }

    RequestDialog(
        confirmButton = {
            Button(onClick = {onConfirm(email)}) {
                Text(text = stringResource(R.string.confirm))
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = stringResource(R.string.dismiss))
            }
        },
        onDismissRequest = onDismiss,
        modifier,
        title = {
            Text(
                text = stringResource(R.string.enter_email),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
        },
        body = {
            CustomOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { email = it },
                placeholder = stringResource(R.string.email),
                label = stringResource(R.string.email),
            )
        },
    )

    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(R.string.enter_email),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
        },
        text = {
            CustomOutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { email = it },
                placeholder = stringResource(R.string.email),
                label = stringResource(R.string.email),
            )
        },
        confirmButton = {
            Button(onClick = {onConfirm(email)}) {
                Text(text = stringResource(R.string.confirm))
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = stringResource(R.string.dismiss))
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = true
        )
    )
}

/**
 * Preview of the [ForgotPasswordWindow].
 *
 * @see ForgotPasswordWindow
 */
@UiModePreviews
@Composable
private fun ForgotPasswordWindowPreview() {
    YouKnowTheme{
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ForgotPasswordWindow()
        }
    }
}