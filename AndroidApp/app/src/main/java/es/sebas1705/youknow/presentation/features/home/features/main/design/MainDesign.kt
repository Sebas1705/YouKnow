package es.sebas1705.youknow.presentation.features.home.features.main.design
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.fab.IFAB
import es.sebas1705.youknow.core.composables.cards.IResumeCard
import es.sebas1705.youknow.core.composables.divider.IHorDivider
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.texts.Title
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.home.features.main.viewmodel.MainState
import es.sebas1705.youknow.presentation.ui.theme.Paddings.LargePadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Design of the Main Screen.
 *
 * @see ApplyBack
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun MainDesign(
    windowState: WindowState = WindowState.default(),
    mainState: MainState = MainState.default(),
    onSettingsNav: () -> Unit = {}
) {
    ApplyBack(
        windowState.backEmpty
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Title(
                    stringResource(R.string.Home),
                    modifier = Modifier.padding(vertical = MediumPadding)
                )
            }

            item {
                IResumeCard(
                    stringResource(R.string.New_Message),
                    mainState.news.toMap(),
                    modifier = Modifier.fillMaxWidth(
                        windowState.widthFilter(0.9f, 0.7f, 0.5f)
                    )
                )
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth(
                        windowState.widthFilter(0.9f, 0.7f, 0.5f)
                    )
                ) {
                    Spacer(Modifier.height(LargePadding))
                    IHorDivider()
                    Spacer(Modifier.height(SmallPadding))
                    IHorDivider()
                    Spacer(Modifier.height(LargePadding))
                }
            }

            item {
                IResumeCard(
                    stringResource(R.string.Ranking),
                    mainState.ranking.mapIndexed { index, user ->
                        "${index + 1}º. ${user.first}" to user.second.toString()
                    }.toMap(),
                    modifier = Modifier.fillMaxWidth(
                        windowState.widthFilter(0.9f, 0.7f, 0.5f)
                    )
                )
            }
        }

        IFAB(
            onClick = onSettingsNav,
            contentDescription = stringResource(R.string.settings_title),
            imageVector = Icons.Default.Settings,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = MediumPadding, bottom = MediumPadding)
        )
    }

}

/**
 * Preview of the Main Screen.
 *
 * @see MainDesign
 */
@UiModePreviews
@Composable
private fun MainPreview() {
    YouKnowTheme {
        MainDesign()
    }
}