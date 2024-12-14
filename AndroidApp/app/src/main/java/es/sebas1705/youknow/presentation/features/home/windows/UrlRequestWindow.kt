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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.composables.CustomOutlinedTextField
import es.sebas1705.youknow.presentation.composables.RequestDialog
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 *
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun UrlRequestWindow(
    windowState: WindowState = WindowState.default(),
    modifier: Modifier = Modifier,
    onConfirmButton: (String) -> Unit = {},
    onDismissAction: () -> Unit = {}
) {
    val context = LocalContext.current
    var photoUrl by remember { mutableStateOf("") }

    RequestDialog(
        confirmButton = {
            Button(onClick = { onConfirmButton(photoUrl) }) {
                Text(text = stringResource(R.string.confirm))
            }
        },
        dismissButton = {
            Button(onClick = onDismissAction) {
                Text(text = stringResource(R.string.dismiss))
            }
        },
        onDismissRequest = onDismissAction,
        modifier = modifier,
        title = {
            Image(
                painter = painterResource(R.drawable.sign_user),
                contentDescription = stringResource(R.string.Profile),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
            )
        },
        body = {
            CustomOutlinedTextField(
                value = photoUrl,
                onValueChange = { photoUrl = it },
                label = stringResource(R.string.url_photo),
                placeholder = stringResource(R.string.url_photo),
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Link,
                        contentDescription = "Url linked image"
                    )
                }
            )
        },
    )
}

/**
 * Preview for [LogoutWindow]
 *
 * @see LogoutWindow
 */
@UiModePreviews
@Composable
private fun UrlRequestWindowPreview() {
    YouKnowTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            UrlRequestWindow()
        }
    }
}
