package es.sebas1705.youknow.presentation.features.game.screens.families.composables
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.common.IOutlinedButton
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.texts.Title
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.game.viewmodels.FamiliesMode
import es.sebas1705.youknow.presentation.features.game.viewmodels.FamiliesState
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

@Composable
fun SelectionMode(
    windowState: WindowState = WindowState.default(),
    familiesState: FamiliesState = FamiliesState.default(),
    onSelectMode: (FamiliesMode) -> Unit = { }
) {
    ApplyBack(
        backId = windowState.backEmpty
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Title(
                modifier = Modifier.padding(bottom = MediumPadding),
                text = stringResource(id = R.string.mode_title)
            )

            FamiliesMode.entries.forEach { mode ->
                IOutlinedButton(
                    onClick = { onSelectMode(mode) },
                    label = stringResource(id = mode.strRes),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.15f)
                        .padding(SmallestPadding),
                    imageVector = mode.icon,
                )
            }
        }
    }
}

@UiModePreviews
@Composable
private fun SelectModePreview() {
    YouKnowTheme {
        SelectionMode()
    }
}