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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.composables.buttons.common.ITextButton
import es.sebas1705.youknow.core.composables.textfields.IOutlinedTextField
import es.sebas1705.youknow.core.composables.texts.IText
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Create group dialog
 *
 * @param modifier [Modifier]: Modifier
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume
 * @param onConfirm [Function2<String, String, Unit>]: On confirm
 * @param onDismiss [Function0<Unit>]: On dismiss
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun CreateGroupDialog(
    modifier: Modifier = Modifier,
    soundPool: Pair<SoundPool, Float>? = null,
    onConfirm: (String, String) -> Unit = { _, _ -> },
    onDismiss: () -> Unit = {}
) {
    val context = LocalContext.current
    var groupName by remember { mutableStateOf("") }
    var groupDescription by remember { mutableStateOf("") }

    IDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            ITextButton(
                onClick = {
                    if (groupName.isNotEmpty() && groupDescription.isNotEmpty())
                        onConfirm(groupName, groupDescription)
                    else context.printTextInToast("Please fill all the fields")
                },
                label = "${stringResource(R.string.confirm)} (2000 ${stringResource(R.string.credits)})",
                soundPool = soundPool
            )
        },
        modifier = modifier,
        dismissButton = {
            ITextButton(
                onClick = onDismiss,
                label = stringResource(R.string.dismiss),
                soundPool = soundPool
            )
        },
        icon = null,
        title = {
            IText(stringResource(R.string.titleCreateGroup))
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                IOutlinedTextField(
                    value = groupName,
                    onValueChange = { groupName = it },
                    label = stringResource(R.string.groupName),
                    placeholder = stringResource(R.string.groupName),
                    modifier = Modifier.fillMaxWidth(),
                    soundPool = soundPool
                )
                IOutlinedTextField(
                    value = groupDescription,
                    onValueChange = { groupDescription = it },
                    label = stringResource(R.string.groupDescription),
                    placeholder = stringResource(R.string.groupDescription),
                    modifier = Modifier.fillMaxWidth(),
                    soundPool = soundPool
                )
            }
        }
    )
}

/**
 * Preview for [LogoutDialog]
 *
 * @see LogoutDialog
 */
@UiModePreviews
@Composable
private fun CreateGroupPreview() = YouKnowTheme {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CreateGroupDialog()
    }
}

