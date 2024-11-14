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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.composables.CustomOutlinedTextField
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 *
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun CreateGroupWindow(
    modifier: Modifier = Modifier,
    onConfirmButton: (String,String) -> Unit = {_,_ ->},
    onDismissAction: () -> Unit = {}
) {
    var groupName by remember { mutableStateOf("") }
    var groupDescription by remember { mutableStateOf("") }

    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissAction,
        title = {
            Text(
                text = stringResource(R.string.titleCreateGroup),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                CustomOutlinedTextField(
                    value = groupName,
                    onValueChange = {groupName = it},
                    label = stringResource(R.string.groupName),
                    placeholder = stringResource(R.string.groupName),
                    modifier = Modifier.fillMaxWidth()
                )
                CustomOutlinedTextField(
                    value = groupDescription,
                    onValueChange = {groupDescription = it},
                    label = stringResource(R.string.groupDescription),
                    placeholder = stringResource(R.string.groupDescription),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(onClick = { onConfirmButton(groupName,groupDescription) }) {
                Text(text = "${stringResource(R.string.confirm)} (2000 credits)")
            }
        },
        dismissButton = {
            Button(onClick = onDismissAction) {
                Text(text = stringResource(R.string.dismiss))
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = true
        )
    )
}

/**
 * Preview for [LogoutWindow]
 *
 * @see LogoutWindow
 */
@UiModePreviews
@Composable
private fun CreateGroupPreview() {
    YouKnowTheme{
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CreateGroupWindow()
        }
    }
}
