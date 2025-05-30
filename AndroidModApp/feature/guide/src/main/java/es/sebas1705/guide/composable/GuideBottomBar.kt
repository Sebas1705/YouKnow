package es.sebas1705.guide.composable
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

import android.media.SoundPool
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import es.sebas1705.common.utlis.IComposablePreview
import es.sebas1705.designsystem.buttons.common.IFilledButton
import es.sebas1705.designsystem.buttons.common.ITextButton
import es.sebas1705.guide.design.generateGuidePages
import es.sebas1705.ui.theme.IndicatorSize
import es.sebas1705.ui.theme.OutlineThickness
import es.sebas1705.ui.theme.Paddings.MediumPadding
import es.sebas1705.ui.theme.Paddings.SmallestPadding
import es.sebas1705.ui.theme.PageIndicatorSeparator
import es.sebas1705.ui.theme.YouKnowTheme
import kotlinx.coroutines.launch

/**
 * Bottom bar for the guide screen.
 * It contains the page indicator and the buttons to navigate between pages.
 * The buttons will be enabled or disabled depending on the current page.
 * The last button will be enabled only on the last page.
 *
 * @param size [Int]: Number of pages.
 * @param pagerState [PagerState]: State of the pager.
 * @param buttonState [State]<[List]<[String]>>: State of the buttons.
 * @param onSuccess () -> Unit: Function to execute when the last button is clicked.
 *
 * @see PagerState
 * @see State
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun GuideBottomBar(
    size: Int,
    pagerState: PagerState,
    buttonState: State<List<String>>,
    soundPool: Pair<SoundPool, Float>?,
    onSuccess: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceContainer)
    ) {
        HorizontalDivider(
            modifier = Modifier,
            thickness = OutlineThickness,
            color = MaterialTheme.colorScheme.onSurface
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding)
                .padding(vertical = SmallestPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(PageIndicatorSeparator)
            ) {
                repeat(size) {
                    Box(
                        modifier = Modifier
                            .size(IndicatorSize)
                            .clip(CircleShape)
                            .background(
                                color = if (it == pagerState.currentPage) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.onSurface
                            )
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                val scope = rememberCoroutineScope()
                if (buttonState.value[0].isNotEmpty()) {
                    ITextButton(
                        label = buttonState.value[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        },
                        soundPool = soundPool
                    )
                }
                IFilledButton(
                    label = buttonState.value[1],
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == size - 1) onSuccess()
                            else pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                        }
                    },
                    soundPool = soundPool
                )
            }
        }
    }
}

@IComposablePreview
@Composable
fun Preview(){
    val context = LocalContext.current
    val pageList = context.generateGuidePages()
    val next = "Next"
    val back = "Back"
    val start = "Start"

    val pagerState = rememberPagerState(initialPage = 0) { pageList.size }
    val buttonState = remember {
        derivedStateOf {
            listOf(
                if (pagerState.currentPage == 0) "" else back,
                if (pagerState.currentPage < pageList.size - 1) next else start
            )
        }
    }
    YouKnowTheme {
        GuideBottomBar(pageList.size, pagerState, buttonState, null) {}
    }
}