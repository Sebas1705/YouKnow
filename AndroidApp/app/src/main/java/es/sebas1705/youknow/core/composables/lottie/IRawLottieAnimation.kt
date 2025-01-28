package es.sebas1705.youknow.core.composables.lottie
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

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.utlis.IComposablePreview
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Raw lottie animation
 *
 * @param rawRes [Int]: Raw res
 * @param modifier [Modifier]: Modifier
 * @param iterations [Int]: Iterations
 * @param contentScale [ContentScale]: Content scale
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun IRawLottieAnimation(
    rawRes: Int,
    modifier: Modifier = Modifier,
    iterations: Int = LottieConstants.IterateForever,
    contentScale: ContentScale = ContentScale.Crop
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(rawRes)
    )
    LottieAnimation(
        composition = composition,
        iterations = iterations,
        contentScale = contentScale,
        modifier = modifier
    )
}


@IComposablePreview
@Composable
private fun Preview() = YouKnowTheme {
    IRawLottieAnimation(
        rawRes = R.raw.lottie_load_1,
        modifier = Modifier
            .height(400.dp)
            .width(400.dp),
    )
}
