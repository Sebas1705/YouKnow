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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.lottie.IRawLottieAnimation
import es.sebas1705.youknow.core.composables.texts.IText
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

@Composable
fun LoadingDialog(
    windowState: WindowState = WindowState.default()
) = IDialog(
    modifier = Modifier
        .fillMaxWidth(windowState.widthFilter(0.9f, 0.7f, 0.5f))
        .fillMaxHeight(windowState.heightFilter(0.6f, 0.5f, 0.4f)),
    title = {
        IText(
            stringResource(R.string.loading),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
    },
    text = {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            IRawLottieAnimation(R.raw.loading)
        }
    },
)


@UiModePreviews
@Composable
private fun Preview() = YouKnowTheme {
    YouKnowTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LoadingDialog()
        }
    }
}
