package es.sebas1705.youknow.presentation.features.app.screens
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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import androidx.compose.runtime.getValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.composables.ApplyBack
import es.sebas1705.youknow.presentation.composables.Spacers.SimpleSpacer
import es.sebas1705.youknow.presentation.ui.classes.WindowState
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme
import es.sebas1705.youknow.R
import es.sebas1705.youknow.presentation.composables.Subtitle

/**
 * Screen to show when there is a network error.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun NetworkErrorScreen(
    windowState: WindowState
) {
    NetworkErrorDesign(windowState)
}

/**
 *
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
private fun NetworkErrorDesign(
    windowState: WindowState = WindowState.default()
) {
    ApplyBack(
        backId = windowState.backFill,
    ) {

        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.connection_lost)
        )

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight(0.5f)
                )
                SimpleSpacer()
                Subtitle(
                    text = stringResource(id = R.string.connection_lost)
                )
            }
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