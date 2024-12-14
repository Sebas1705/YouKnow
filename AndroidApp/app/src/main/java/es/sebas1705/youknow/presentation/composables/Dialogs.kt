package es.sebas1705.youknow.presentation.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import es.sebas1705.youknow.R
import es.sebas1705.youknow.presentation.ui.theme.dialogBorderThickness
import es.sebas1705.youknow.presentation.ui.theme.dialogCornerRadius
import es.sebas1705.youknow.presentation.ui.theme.dialogTonalElevation

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

/**
 * Dialog to show information to the user.
 *
 * @param modifier Modifier: Modifier to be applied to the composable.
 * @param title () -> Unit: Title to be shown in the dialog.
 * @param body () -> Unit: Body to be shown in the dialog.
 *
 * @see BaseDialog
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun InfoDialog(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    body: @Composable () -> Unit,
    onDismiss: () -> Unit = {},
) = BaseDialog(
    onDismissRequest = onDismiss,
    confirmButton = {},
    modifier,
    dismissButton = {},
    icon = {},
    title,
    text = body
)

/**
 * Dialog to show a request to the user.
 *
 * @param modifier Modifier: Modifier to be applied to the composable.
 * @param onConfirm () -> Unit: Function to confirm the dialog.
 * @param onDismiss () -> Unit: Function to dismiss the dialog.
 * @param title String: Title to be shown in the dialog.
 * @param body String: Body to be shown in the dialog.
 *
 * @see RequestDialog
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun OnlyTextRequestDialog(
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    title: String,
    body: String,
) = RequestDialog(
    confirmButton = {
        Button(onClick = onConfirm) {
            Text(text = stringResource(R.string.confirm))
        }
    },
    dismissButton = {
        Button(onClick = onDismiss) {
            Text(text = stringResource(R.string.dismiss))
        }
    },
    onDismissRequest = onDismiss,
    modifier = modifier,
    title = {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
    },
    body = {
        Text(
            text = body,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
    },
)

/**
 * Dialog to show a request to the user.
 *
 * @param confirmButton () -> Unit: Function to confirm the dialog.
 * @param dismissButton () -> Unit: Function to dismiss the dialog.
 * @param onDismissRequest () -> Unit: Function to dismiss the dialog.
 * @param modifier Modifier: Modifier to be applied to the composable.
 * @param icon () -> Unit: Icon to be shown in the dialog.
 * @param title () -> Unit: Title to be shown in the dialog.
 * @param body () -> Unit: Body to be shown in the dialog.
 *
 * @see BaseDialog
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun RequestDialog(
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit = {},
    title: @Composable () -> Unit = {},
    body: @Composable () -> Unit = {},
) = BaseDialog(
    onDismissRequest,
    confirmButton,
    modifier,
    dismissButton,
    icon,
    title,
    text = body
)

/**
 * Base Dialog that will be used to show dialogs in the app.
 *
 * @param onDismissRequest () -> Unit: Function to dismiss the dialog.
 * @param confirmButton () -> Unit: Function to confirm the dialog.
 * @param modifier Modifier: Modifier to be applied to the composable.
 * @param dismissButton (() -> Unit)?: Function to dismiss the dialog.
 * @param icon (() -> Unit)?: Icon to be shown in the dialog.
 * @param title (() -> Unit)?: Title to be shown in the dialog.
 * @param text (() -> Unit)?: Text to be shown in the dialog.
 *
 * @see AlertDialog
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
private fun BaseDialog(
    onDismissRequest: () -> Unit = {},
    confirmButton: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
    dismissButton: @Composable (() -> Unit)? = null,
    icon: @Composable (() -> Unit)? = null,
    title: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
) = AlertDialog(
    onDismissRequest = onDismissRequest,
    confirmButton = confirmButton,
    modifier = modifier.border(
        dialogBorderThickness,
        MaterialTheme.colorScheme.tertiary,
        RoundedCornerShape(dialogCornerRadius)
    ),
    dismissButton = dismissButton,
    icon = icon,
    title = title,
    text = text,
    shape = RoundedCornerShape(dialogCornerRadius),
    containerColor = MaterialTheme.colorScheme.primaryContainer,
    iconContentColor = MaterialTheme.colorScheme.tertiary,
    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
    textContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
    tonalElevation = dialogTonalElevation,
    properties = DialogProperties(
        dismissOnBackPress = true,
        dismissOnClickOutside = true,
        usePlatformDefaultWidth = true
    )
)
