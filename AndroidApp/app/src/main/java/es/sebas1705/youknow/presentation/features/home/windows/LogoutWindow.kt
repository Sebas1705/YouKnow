package es.sebas1705.youknow.presentation.features.home.windows
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.composables.buttons.common.IFilledButton
import es.sebas1705.youknow.core.composables.dialogs.IDialog
import es.sebas1705.youknow.core.composables.texts.IText
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Composable that shows a dialog to confirm the logout action. Confirm must active log out process,
 * and dismiss must close the dialog inclusive you click outside the dialog or press back button.
 *
 * @param modifier [Modifier]: Modifier to be applied to the composable.
 * @param onConfirm () -> Unit: Action to be executed when the confirm button is clicked.
 * @param onDismiss () -> Unit: Action to be executed when the dismiss button or dismiss action is active.
 *
 * @see AlertDialog
 * @see Button
 * @see Text
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun LogoutWindow(
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {}
) = IDialog(
    onDismissRequest = onDismiss,
    modifier = modifier,
    confirmButton = {
        IFilledButton(
            label = stringResource(R.string.confirm),
            onClick = onConfirm,
        )
    },
    dismissButton = {
        IFilledButton(
            label = stringResource(R.string.dismiss),
            onClick = onDismiss,
        )
    },
    title = {
        IText(stringResource(R.string.logout))
    }
)

/**
 * Preview for [LogoutWindow]
 *
 * @see LogoutWindow
 */
@UiModePreviews
@Composable
private fun LogoutWindowPreview() {
    YouKnowTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LogoutWindow()
        }
    }
}
