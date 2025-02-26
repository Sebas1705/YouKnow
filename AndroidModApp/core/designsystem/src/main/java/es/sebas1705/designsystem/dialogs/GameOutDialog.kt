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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.iberext.youknow.core.designsystem.R
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.buttons.common.ITextButton
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.ui.theme.YouKnowTheme

/**
 * Dialog that will be shown when the game is over.
 *
 * @param modifier [Modifier]: Modifier to be applied to the dialog.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onConfirm [Function]: Function to be executed when the user confirms the dialog.
 * @param onDismiss [Function]: Function to be executed when the user dismisses the dialog.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun GameOutDialog(
    modifier: Modifier = Modifier,
    soundPool: Pair<SoundPool, Float>? = null,
    onConfirm : () -> Unit = {},
    onDismiss : () -> Unit = {},
) = IDialog(
    onDismissRequest = onDismiss,
    confirmButton = {
        ITextButton(
            onClick = onConfirm,
            label = stringResource(R.string.core_designsystem_confirm),
            soundPool = soundPool
        )
    },
    modifier = modifier,
    dismissButton = {
        ITextButton(
            onClick = onDismiss,
            label = stringResource(R.string.core_designsystem_dismiss),
            soundPool = soundPool
        )
    },
    icon = null,
    title = {
        IText(stringResource(id = R.string.core_designsystem_game_out_title))
    },
    text = {
        IText(stringResource(id = R.string.core_designsystem_game_out_body))
    }
)

@UiModePreviews
@Composable
private fun GameOutWindowPreview() {
    YouKnowTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            GameOutDialog()
        }
    }
}

