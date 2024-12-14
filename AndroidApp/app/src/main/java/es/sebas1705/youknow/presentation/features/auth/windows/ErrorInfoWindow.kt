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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.composables.RequestDialog
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme
import es.sebas1705.youknow.presentation.ui.theme.dialogTextType
import es.sebas1705.youknow.presentation.ui.theme.dialogTitleType

/**
 * Composable that shows an AlertDialog with an error message.
 *
 * @param modifier [Modifier]: Modifier to be applied to the composable.
 * @param errorText [String]: Error message to be shown.
 * @param onConfirm () -> Unit: Function to be executed when the confirm button is clicked.
 *
 * @see AlertDialog
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun ErrorInfoWindow(
    windowState: WindowState = WindowState.default(),
    modifier: Modifier = Modifier,
    errorText: String,
    onConfirm: () -> Unit = {},
) = RequestDialog(
    confirmButton = {
        Button(onClick = onConfirm) {
            Text(
                text = stringResource(R.string.confirm),
                style = windowState.dialogTitleType()
            )
        }
    },
    dismissButton = {},
    onDismissRequest = {},
    modifier = modifier,
    title = {
        Text(
            text = stringResource(R.string.title_error),
            style = windowState.dialogTitleType()
        )
    },
    body = {
        Text(
            text = errorText,
            style = windowState.dialogTextType(),
        )
    }
)

/**
 * Preview for [ErrorInfoWindow]
 *
 * @see ErrorInfoWindow
 */
@UiModePreviews
@Composable
private fun ErrorInfoWindowPreview() {
    YouKnowTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ErrorInfoWindow(errorText = "Error")
        }
    }
}
