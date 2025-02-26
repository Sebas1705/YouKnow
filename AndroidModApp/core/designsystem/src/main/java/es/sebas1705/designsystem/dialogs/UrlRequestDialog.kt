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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import es.iberext.youknow.core.designsystem.R
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.buttons.common.ITextButton
import es.sebas1705.ui.theme.YouKnowTheme
import es.sebas1705.youknow.core.composables.textfields.IOutlinedTextField

/**
 * Dialog to request a URL.
 *
 * @param modifier [Modifier]: Modifier to be applied to the dialog.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param windowState [WindowState]: State of the window.
 * @param onConfirmButton [Function1<String, Unit]: Function to be executed when the user confirms the dialog.
 * @param onDismissAction [Function0<Unit>]: Function to be executed when the user dismisses the dialog.
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun UrlRequestDialog(
    modifier: Modifier = Modifier,
    soundPool: Pair<SoundPool, Float>? = null,
    windowState: WindowState = WindowState.default(),
    onConfirmButton: (String) -> Unit = {},
    onDismissAction: () -> Unit = {}
) {
    val context = LocalContext.current
    var photoUrl by remember { mutableStateOf("") }

    IDialog(
        onDismissRequest = onDismissAction,
        confirmButton = {
            ITextButton(
                onClick = { onConfirmButton(photoUrl) },
                label = stringResource(R.string.core_designsystem_confirm),
                soundPool = soundPool
            )
        },
        modifier = modifier,
        dismissButton = {
            ITextButton(
                onClick = onDismissAction,
                label = stringResource(R.string.core_designsystem_dismiss),
                soundPool = soundPool
            )
        },
        title = {
            Image(
                painter = painterResource(es.iberext.youknow.core.resources.R.drawable.sign_user),
                contentDescription = stringResource(R.string.core_designsystem_profile),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
            )
        },
        text = {
            IOutlinedTextField(
                value = photoUrl,
                onValueChange = { photoUrl = it },
                label = stringResource(R.string.core_designsystem_url_photo),
                placeholder = stringResource(R.string.core_designsystem_url_photo),
                leadingIcon = Icons.Filled.Link to {},
                soundPool = soundPool,
            )
        }
    )
}

@UiModePreviews
@Composable
private fun UrlRequestWindowPreview() {
    YouKnowTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            UrlRequestDialog()
        }
    }
}
