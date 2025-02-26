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
import es.sebas1705.designsystem.buttons.common.IFilledButton
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.ui.theme.YouKnowTheme

/**
 * Nick dialog
 *
 * @param modifier [Modifier]: Modifier
 * @param soundPool [Pair<SoundPool, Float>]: Sound pool
 * @param nickname [String]: Nickname
 * @param firebaseId [String]: Firebase id
 * @param onConfirm ([String]) -> Unit: On confirm
 * @param onDismiss () -> Unit: On dismiss
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun NickDialog(
    modifier: Modifier = Modifier,
    soundPool: Pair<SoundPool, Float>? = null,
    nickname: String = "temp",
    firebaseId: String = "------",
    onConfirm: (String) -> Unit = {},
    onDismiss: () -> Unit = {}
) = IDialog(
    onDismissRequest = onDismiss,
    confirmButton = {
        IFilledButton(
            label = stringResource(R.string.core_designsystem_confirm),
            onClick = { onConfirm(nickname) },
            soundPool = soundPool
        )
    },
    modifier = modifier,
    dismissButton = {
        IFilledButton(
            label = stringResource(R.string.core_designsystem_dismiss),
            onClick = onDismiss,
            soundPool = soundPool
        )
    },
    title = {
        IText(stringResource(R.string.core_designsystem_change_nickname_title))
    },
    text = {
        IText(
            stringResource(R.string.core_designsystem_change_nickname_body)
                    + " "
                    + nickname
                    + " "
                    + stringResource(R.string.core_designsystem_change_nickname_body2)
                    + " "
                    + nickname + "-" + firebaseId
                    + stringResource(R.string.core_designsystem_change_nickname_body3)
        )
    }
)

@UiModePreviews
@Composable
private fun NickWindowPreview() {
    YouKnowTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            NickDialog()
        }
    }
}
