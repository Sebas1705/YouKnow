package es.sebas1705.youknow.presentation.features.networkerror.design
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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.lottie.IRawLottieAnimation
import es.sebas1705.youknow.core.composables.spacers.IVerSpacer
import es.sebas1705.youknow.core.composables.texts.Subtitle
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 *
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun NetworkErrorDesign(
    windowState: WindowState = WindowState.default()
) {
    ApplyBack(
        backId = windowState.backFill,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            IRawLottieAnimation(
                rawRes = R.raw.connection_lost,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.5f)
            )
            IVerSpacer(height = SmallPadding)
            Subtitle(
                text = stringResource(id = R.string.connection_lost)
            )
        }
    }
}

/**
 * Preview of the Settings Screen.
 *
 * @see NetworkErrorDesign
 */
@UiModePreviews
@Composable
private fun NetworkErrorPreview() {
    YouKnowTheme {
        NetworkErrorDesign()
    }
}

