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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.composables.OnlyTextRequestDialog
import es.sebas1705.youknow.presentation.composables.RequestDialog
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 *
 *
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun NickWindow(
    modifier: Modifier = Modifier,
    nickname: String = "temp",
    firebaseId: String = "------",
    onConfirmButton: (String) -> Unit = {},
    onDismissAction: () -> Unit = {}
) = OnlyTextRequestDialog(
    modifier = modifier,
    onConfirm = { onConfirmButton(nickname) },
    onDismiss = onDismissAction,
    title = stringResource(R.string.change_nickname_title),
    body = stringResource(R.string.change_nickname_body)
            + " "
            + nickname
            + " "
            + stringResource(R.string.change_nickname_body2)
            + " "
            + nickname + "-" + firebaseId
            + stringResource(R.string.change_nickname_body3),
)

/**
 * Preview for [NickWindow]
 *
 * @see NickWindow
 */
@UiModePreviews
@Composable
private fun NickWindowPreview() {
    YouKnowTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            NickWindow()
        }
    }
}
