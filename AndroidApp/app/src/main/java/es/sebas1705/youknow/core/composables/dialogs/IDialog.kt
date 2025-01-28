package es.sebas1705.youknow.core.composables.dialogs
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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import es.sebas1705.youknow.core.composables.buttons.common.ITextButton
import es.sebas1705.youknow.core.composables.texts.IText
import es.sebas1705.youknow.core.utlis.IComposablePreview
import es.sebas1705.youknow.presentation.ui.theme.TonalElevation
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Personalized dialog
 *
 * @param modifier [Modifier]: Modifier
 * @param onDismissRequest [() -> Unit]: On dismiss request
 * @param confirmButton [() -> Unit]: Confirm button
 * @param dismissButton [(() -> Unit)?]: Dismiss button
 * @param icon [(() -> Unit)?]: Icon
 * @param title [(() -> Unit)?]: Title
 * @param text [(() -> Unit)?]: Text
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios
 */
@Composable
fun IDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    confirmButton: @Composable () -> Unit = {},
    dismissButton: @Composable (() -> Unit)? = null,
    icon: @Composable (() -> Unit)? = null,
    title: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
) = AlertDialog(
    onDismissRequest = onDismissRequest,
    confirmButton = confirmButton,
    modifier = modifier,
    dismissButton = dismissButton,
    icon = icon,
    title = title,
    text = text,
    containerColor = MaterialTheme.colorScheme.background,
    iconContentColor = MaterialTheme.colorScheme.secondary,
    titleContentColor = MaterialTheme.colorScheme.primary,
    textContentColor = MaterialTheme.colorScheme.onBackground,
    properties = DialogProperties(
        dismissOnBackPress = true,
        dismissOnClickOutside = true,
        usePlatformDefaultWidth = true
    )
)


@IComposablePreview
@Composable
private fun Preview() = YouKnowTheme {
    IDialog(
        modifier = Modifier,
        onDismissRequest = {},
        confirmButton = { ITextButton({}, "Confirm") },
        dismissButton = { ITextButton({}, "Dismiss") },
        icon = { Icon(Icons.Default.Info, contentDescription = null) },
        title = { IText("Hello, World!") },
        text = { IText("This is a dialog") },
    )

}

