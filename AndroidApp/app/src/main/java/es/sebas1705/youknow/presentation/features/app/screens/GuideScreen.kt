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
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.generateGuidePages
import es.sebas1705.youknow.presentation.features.app.composables.GuideBottomBar
import es.sebas1705.youknow.presentation.composables.ApplyBack
import es.sebas1705.youknow.presentation.features.app.viewmodels.GuideIntent
import es.sebas1705.youknow.presentation.features.app.viewmodels.GuideViewModel
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Guide Screen that will show the user a guide of the app.
 * The user can navigate through the guide and start the app.
 *
 * @param onSuccessNavigation () -> Unit: Function that will be called when the user finishes the guide.
 *
 * @see GuideViewModel
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

/**
 * Design of the Guide Screen.
 *
 * @param guideViewModel [GuideViewModel]: ViewModel of the Guide Screen.
 * @param onSuccessNavigation () -> Unit: Function that will be called when the user finishes the guide.
 *
 * @see GuideViewModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
private fun GuideDesign(
    guideViewModel: GuideViewModel? = null,
    onSuccessNavigation: () -> Unit = {},
) {

    //String readers:
    val context = LocalContext.current
    val pageList = context.generateGuidePages()
    val next = stringResource(id = R.string.next)
    val back = stringResource(id = R.string.back)
    val start = stringResource(id = R.string.start)

    //States:
    val pagerState = rememberPagerState(initialPage = 0) { pageList.size }
    val buttonState = remember {
        derivedStateOf {
            listOf(
                if (pagerState.currentPage == 0) "" else back,
                if (pagerState.currentPage < pageList.size - 1) next else start
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            GuideBottomBar(pageList.size, pagerState, buttonState) {
                guideViewModel?.eventHandler(GuideIntent.SaveFirstTime)
                onSuccessNavigation()
            }
        }
    ) {
        ApplyBack(
            backId = R.drawable.back_portrait_empty,
            paddingValues = it
        ) {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = MediumPadding),
                state = pagerState
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        text = pageList[it].title,
                        modifier = Modifier.padding(horizontal = MediumPadding),
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(MediumPadding))
                    LazyColumn(
                        Modifier.padding(horizontal = MediumPadding)
                    ) {
                        item {
                            Text(
                                text = pageList[it].description,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                }
            }
        }
    }
}


/**
 * Preview of the Guide Screen.
 *
 * @see GuideDesign
 */
@UiModePreviews
@Composable
private fun GuideScreenPreview() {
    YouKnowTheme {
        GuideDesign()
    }
}




