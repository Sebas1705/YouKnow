package es.sebas1705.youknow.presentation.features.guide
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

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.extensions.composables.generateGuidePages
import es.sebas1705.youknow.core.composables.bottombars.GuideBottomBar
import es.sebas1705.youknow.presentation.features.guide.viewmodel.GuideIntent
import es.sebas1705.youknow.presentation.features.guide.viewmodel.GuideViewModel
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Guide Screen that will show the user a guide of the app.
 * The user can navigate through the guide and start the app.
 *
 * @param onSuccessNavigation () -> Unit: Function that will be called when the user finishes the guide.
 *
 * @see es.sebas1705.youknow.presentation.features.guide.viewmodel.GuideViewModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun GuideScreen(
    onSuccessNavigation: () -> Unit = {},
) {
    val guideViewModel: GuideViewModel = hiltViewModel()
    val guideState by guideViewModel.uiState.collectAsStateWithLifecycle()

    GuideDesign(
        onSuccessNavigation = onSuccessNavigation,
        guideViewModel = guideViewModel
    )
}






