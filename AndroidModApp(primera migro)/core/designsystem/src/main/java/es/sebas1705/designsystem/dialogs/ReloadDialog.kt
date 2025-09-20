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
import es.iberext.youknow.core.designsystem.R
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.buttons.common.ITextButton
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.ui.theme.YouKnowTheme

/**
 * Composable that shows a dialog with a reload message.
 *
 * @param windowState [WindowState]: State of the window.
 * @param soundPool [Pair<SoundPool, Float>]: Pair of SoundPool and volume to play a sound when the dialog is shown.
 * @param onConfirm () -> Unit: Function to be executed when the confirm button is clicked.
 *
 * @see AlertDialog
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun ReloadDialog(
    windowState: WindowState = WindowState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {}
) = IDialog(
    confirmButton = {
        ITextButton(
            label = stringResource(R.string.core_designsystem_confirm),
            onClick = onConfirm,
            soundPool = soundPool
        )
    },
    onDismissRequest = onDismiss,
    dismissButton = {
        ITextButton(
            label = stringResource(R.string.core_designsystem_dismiss),
            onClick = onDismiss,
            soundPool = soundPool
        )
    },
    modifier = Modifier.fillMaxWidth(0.9f),
    text = {
        IText(stringResource(R.string.core_designsystem_reload_text))
    }
)

@UiModePreviews
@Composable
private fun Preview() = YouKnowTheme {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ReloadDialog()
    }
}
