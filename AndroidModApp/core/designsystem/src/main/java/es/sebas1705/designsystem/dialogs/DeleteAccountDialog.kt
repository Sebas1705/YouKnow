package es.sebas1705.designsystem.dialogs
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

import android.media.SoundPool
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import es.iberext.youknow.core.designsystem.R
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.buttons.common.ITextButton
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.ui.theme.YouKnowTheme

/**
 * Composable that displays a dialog to recover the password.
 *
 * @param windowState [WindowState]: State of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onConfirm [Function]: Function to be executed when the user confirms the dialog.
 * @param onDismiss [Function]: Function to be executed when the user dismisses the dialog.
 *
 * @see AlertDialog
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun DeleteAccountDialog(
    windowState: WindowState = WindowState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {}
) = IDialog(
    onDismissRequest = onDismiss,
    confirmButton = {
        ITextButton(
            onClick = onConfirm,
            label = stringResource(R.string.core_designsystem_confirm),
            soundPool = soundPool
        )
    },
    modifier = Modifier
        .fillMaxWidth(windowState.widthFilter(0.9f,0.7f,0.5f)),
    dismissButton = {
        ITextButton(
            onClick = onDismiss,
            label = stringResource(R.string.core_designsystem_dismiss),
            soundPool = soundPool
        )
    },
    title = {
        IText(stringResource(R.string.core_designsystem_delete_account))
    },
    text = {
        IText(
            stringResource(R.string.core_designsystem_delete_account_text),
            textAlign = TextAlign.Justify,
        )
    }
)

@UiModePreviews
@Composable
private fun ForgotPasswordWindowPreview() {
    YouKnowTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            DeleteAccountDialog()
        }
    }
}
