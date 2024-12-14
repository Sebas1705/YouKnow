package es.sebas1705.youknow.presentation.features.app.windows
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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.composables.InfoDialog
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme
import es.sebas1705.youknow.presentation.ui.theme.dialogTitleType
import es.sebas1705.youknow.presentation.ui.theme.titleFontFamily

/**
 * Composable that shows an AlertDialog with a loading indicator.
 *
 * @param modifier [Modifier]: Modifier to be applied to the composable.
 *
 * @see AlertDialog
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun LoadingWindow(
    windowState: WindowState = WindowState.default(),
    modifier: Modifier = Modifier
) = InfoDialog(
    modifier = modifier
        .fillMaxWidth(0.6f)
        .fillMaxHeight(0.3f),
    title = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.loading),
                textAlign = TextAlign.Center,
                style = windowState.dialogTitleType().copy(fontFamily = titleFontFamily),
            )
        }
    },
    body = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            contentAlignment = Alignment.Center
        ) {
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.loading)
            )
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    },
)

/**
 * Preview of the [LoadingWindow].
 *
 * @see LoadingWindow
 */
@UiModePreviews
@Composable
private fun LoadingWindowPreview() {
    YouKnowTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LoadingWindow()
        }
    }
}
