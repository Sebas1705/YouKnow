package es.sebas1705.youknow.presentation.features.game.windows
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.common.ITextButton
import es.sebas1705.youknow.core.composables.dialogs.IDialog
import es.sebas1705.youknow.core.composables.texts.IText
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Composable that shows an AlertDialog with a GameOut indicator.
 *
 * @param modifier [Modifier]: Modifier to be applied to the composable.
 *
 * @see AlertDialog
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun GameOutWindow(
    windowState: WindowState = WindowState.default(),
    modifier: Modifier = Modifier,
    onConfirm : () -> Unit = {},
    onDismiss : () -> Unit = {},
) = IDialog(
    onDismissRequest = onDismiss,
    confirmButton = {
        ITextButton(
            onClick = onConfirm,
            label = stringResource(R.string.confirm),
        )
    },
    modifier,
    dismissButton = {
        ITextButton(
            onClick = onDismiss,
            label = stringResource(R.string.dismiss),
        )
    },
    icon = null,
    title = {
        IText(stringResource(id = R.string.game_out_title))
    },
    text = {
        IText(stringResource(id = R.string.game_out_body))
    }
)

/**
 * Preview of the [GameOutWindow].
 *
 * @see GameOutWindow
 */
@UiModePreviews
@Composable
private fun GameOutWindowPreview() {
    YouKnowTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            GameOutWindow()
        }
    }
}

