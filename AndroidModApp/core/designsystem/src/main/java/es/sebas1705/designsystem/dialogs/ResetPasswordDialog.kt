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
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.common.ITextButton
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Composable that displays a dialog to recover the password.
 *
 * @param email [String]: Email to be displayed in the dialog.
 * @param soundPool [Pair<SoundPool, Float>]: Pair of SoundPool and volume to play sounds.
 * @param windowState [WindowState]: State of the window.
 * @param onConfirm [Function]: Function to be executed when the user confirms the dialog.
 * @param onDismiss [Function]: Function to be executed when the user dismisses the dialog.
 *
 * @see AlertDialog
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun ResetPasswordDialog(
    email: String,
    soundPool: Pair<SoundPool, Float>? = null,
    windowState: WindowState = WindowState.default(),
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {}
) = IDialog(
    onDismissRequest = onDismiss,
    confirmButton = {
        ITextButton(
            onClick = onConfirm,
            label = stringResource(R.string.confirm),
            soundPool = soundPool
        )
    },
    modifier = Modifier
        .fillMaxWidth(windowState.widthFilter(0.9f,0.7f,0.5f)),
    dismissButton = {
        ITextButton(
            onClick = onDismiss,
            label = stringResource(R.string.dismiss),
            soundPool = soundPool
        )
    },
    icon = null,
    title = {
        IText(stringResource(R.string.reset_password))
    },
    text = {
        IText(
            stringResource(R.string.reset_password_text)+email+"?",
            textAlign = TextAlign.Justify
        )
    }
)

/**
 * Preview of the [ForgotPasswordDialog].
 *
 * @see ForgotPasswordDialog
 */
@UiModePreviews
@Composable
private fun ForgotPasswordWindowPreview() {
    YouKnowTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            ResetPasswordDialog("Unknown")
        }
    }
}
