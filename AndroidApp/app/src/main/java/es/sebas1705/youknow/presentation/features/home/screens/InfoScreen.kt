package es.sebas1705.youknow.presentation.features.home.screens
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

import androidx.compose.runtime.Composable
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.composables.ApplyBack
import es.sebas1705.youknow.presentation.features.home.viewmodels.HomeState
import es.sebas1705.youknow.presentation.features.home.viewmodels.UserViewModel
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Info Screen that will show the information about the app.
 *
 * @see ApplyBack
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun InfoScreen(
    userViewModel: UserViewModel,
    homeState: HomeState
) {
    InfoDesign()
}

/**
 * Design of the Info Screen.
 *
 * @see ApplyBack
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
private fun InfoDesign(
) {
    ApplyBack(
        R.drawable.back_portrait_empty
    ) {

    }

}

/**
 * Preview of the [InfoDesign].
 *
 * @see InfoDesign
 */
@UiModePreviews
@Composable
private fun InfoPreview() {
    YouKnowTheme {
        InfoDesign()
    }
}