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
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.composables.buttons.common.ITextButton
import es.sebas1705.youknow.core.composables.texts.IText
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Composable that shows an AlertDialog with an error message.
 *
 * @param modifier [Modifier]: Modifier to be applied to the composable.
 * @param soundPool [Pair<SoundPool, Float>]: Pair of SoundPool and volume to play a sound when the dialog is shown.
 * @param errorText [String]: Error message to be shown.
 * @param onConfirm () -> Unit: Function to be executed when the confirm button is clicked.
 *
 * @see AlertDialog
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun ErrorInfoDialog(
    modifier: Modifier = Modifier,
    soundPool: Pair<SoundPool, Float>? = null,
    errorText: String,
    onConfirm: () -> Unit = {},
) = IDialog(
    confirmButton = {
        ITextButton(
            label = stringResource(R.string.confirm),
            onClick = onConfirm,
            soundPool = soundPool
        )
    },
    modifier = modifier,
    title = {
        IText(stringResource(R.string.title_error))
    },
    text = {
        IText(errorText)
    }
)

/**
 * Preview for [ErrorInfoDialog]
 *
 * @see ErrorInfoDialog
 */
@UiModePreviews
@Composable
private fun Preview() = YouKnowTheme {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ErrorInfoDialog(errorText = "Error")
    }
}
