package es.sebas1705.youknow.presentation.features.guide.design
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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.bottombars.GuideBottomBar
import es.sebas1705.youknow.core.composables.divider.IVerDivider
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.spacers.IVerSpacer
import es.sebas1705.youknow.core.composables.texts.IText
import es.sebas1705.youknow.core.composables.texts.TitleSurface
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.extensions.composables.generateGuidePages
import es.sebas1705.youknow.presentation.features.guide.viewmodel.GuideViewModel
import es.sebas1705.youknow.presentation.ui.theme.Paddings.HugePadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.LargePadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Design of the Guide Screen.
 *
 * @param windowState [WindowState]: State of the window.
 * @param onSuccessNavigation () -> Unit: Function that will be called when the user finishes the guide.
 *
 * @see GuideViewModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun GuideDesign(
    windowState: WindowState = WindowState.default(),
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
                onSuccessNavigation()
            }
        }
    ) {
        ApplyBack(
            backId = windowState.backEmpty,
            paddingValues = it
        ) {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = MediumPadding),
                state = pagerState
            ) { pageIndex ->
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var paddings = windowState
                        .widthFilter(MediumPadding, LargePadding, HugePadding)
                    TitleSurface(
                        pageList[pageIndex].title,
                        modifier = Modifier.padding(
                            horizontal = paddings
                        )
                    )
                    Spacer(modifier = Modifier.height(MediumPadding))
                    LazyColumn(
                        Modifier.padding(horizontal = paddings)
                    ) {
                        item {
                            IText(
                                text = pageList[pageIndex].introduction,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onBackground,
                                textAlign = TextAlign.Justify
                            )
                        }

                        pageList[pageIndex].imagesAndDescription.forEachIndexed { index, page ->

                            item{
                                IVerDivider(
                                    modifier = Modifier.padding(horizontal = paddings),
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }

                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = paddings)
                                ){
                                    Image(
                                        painter = painterResource(page.first),
                                        contentDescription = null,
                                    )
                                }
                            }

                            item {
                                IVerSpacer(
                                    height =
                                    windowState.heightFilter(
                                        SmallestPadding,
                                        SmallPadding,
                                        MediumPadding
                                    )
                                )
                            }

                            item {
                                IText(
                                    text = page.second,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    textAlign = TextAlign.Justify
                                )
                            }

                            item{
                                IVerDivider(
                                    modifier = Modifier.padding(horizontal = paddings),
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }

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