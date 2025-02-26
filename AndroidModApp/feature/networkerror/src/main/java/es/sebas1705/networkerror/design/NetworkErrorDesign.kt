package es.sebas1705.networkerror.design
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
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.designsystem.lottie.IRawLottieAnimation
import es.sebas1705.designsystem.spacers.IVerSpacer
import es.sebas1705.designsystem.texts.Subtitle
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.ui.theme.YouKnowTheme
import es.sebas1705.youknow.feature.networkerror.R

/**
 * Design of the Network Error Screen.
 *
 * @param windowState [WindowState]: the state of the window
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun NetworkErrorDesign(
    windowState: WindowState = WindowState.default()
) {
    //Body:
    ApplyBack(
        backId = windowState.backFill,
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            IRawLottieAnimation(
                rawRes = R.raw.lottie_connection,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.5f)
            )
            IVerSpacer(height = SmallPadding)
            Subtitle(
                text = stringResource(id = R.string.feature_networkerror_connection_lost)
            )
        }
    }
}

@UiModePreviews
@Composable
private fun NetworkErrorPreview() {
    YouKnowTheme {
        NetworkErrorDesign()
    }
}

