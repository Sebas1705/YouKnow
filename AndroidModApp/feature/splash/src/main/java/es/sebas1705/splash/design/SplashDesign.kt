package es.sebas1705.youknow.presentation.features.splash.design
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.designsystem.spacers.IVerSpacer
import es.sebas1705.designsystem.texts.Title
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.ui.theme.Paddings.SmallestPadding
import es.sebas1705.ui.theme.YouKnowTheme

/**
 * Splash screen design of the app
 *
 * @param windowState [WindowState]: is the state of the window.
 *
 * @since 1.0.0
 * @author Sebastian Ramiro Entrerrios García
 */
@Composable
fun SplashDesign(
    windowState: WindowState = WindowState.default(),
) {
    ApplyBack(
        backId = windowState.backFill,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(es.iberext.youknow.core.resources.R.drawable.icon),
                contentDescription = stringResource(es.iberext.youknow.core.resources.R.string.core_resources_app_name),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.4f)
            )
            IVerSpacer(height = SmallestPadding)
            Title(stringResource(es.iberext.youknow.core.resources.R.string.core_resources_app_name))
            IVerSpacer(height = SmallPadding)
            LinearProgressIndicator()
        }
    }
}

@UiModePreviews
@Composable
private fun Preview() {
    YouKnowTheme {
        SplashDesign()
    }
}