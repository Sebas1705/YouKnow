package es.sebas1705.youknow.presentation.features.app.composables
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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.presentation.composables.CustomFilledButton
import es.sebas1705.youknow.presentation.composables.CustomTextButton
import es.sebas1705.youknow.presentation.ui.theme.IndicatorSize
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.PageIndicatorSeparator
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
    onSuccess: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)

    ) {
        HorizontalDivider(
            modifier = Modifier
                .padding(
                    top = SmallestPadding,
                    bottom = SmallestPadding /2,
                    start = SmallPadding,
                    end = SmallPadding
                )
                .clip(MaterialTheme.shapes.small),
            thickness = 3.dp,
            color = MaterialTheme.colorScheme.secondary
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = SmallestPadding)
                .padding(horizontal = MediumPadding)
                .navigationBarsPadding(),
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
                    CustomTextButton(
                        text = buttonState.value[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        }
                    )
                }
                CustomFilledButton(
                    text = buttonState.value[1],
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == size - 1) onSuccess()
                            else pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                        }
                    }
                )
            }
        }
    }
}