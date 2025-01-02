package es.sebas1705.youknow.presentation.features.game.screens.mysterynumber.composables
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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Start
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.enums.Category
import es.sebas1705.youknow.core.classes.enums.Difficulty
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.common.IOutlinedButton
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.spacers.IVerSpacer
import es.sebas1705.youknow.core.composables.textfields.IOutlinedTextField
import es.sebas1705.youknow.core.composables.texts.TitleSurface
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.game.viewmodels.MysteryNumberState
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.TonalElevation
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

@Composable
fun Custom(
    windowState: WindowState = WindowState.default(),
    mysteryNumberState: MysteryNumberState = MysteryNumberState.default(),
    onStartGame: (Difficulty, Int) -> Unit = { _, _ -> }
) {
    var difficulty by remember { mutableStateOf(Difficulty.EASY) }
    var lives by remember { mutableIntStateOf(10) }

    ApplyBack(
        backId = windowState.backEmpty
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IVerSpacer(0.2f)
            TitleSurface(stringResource(R.string.custom_title))
            IVerSpacer(0.1f)
            var titleStyle = windowState.heightType.filter(
                MaterialTheme.typography.titleSmall,
                MaterialTheme.typography.titleLarge,
                MaterialTheme.typography.headlineMedium
            )
            Text(
                text = stringResource(R.string.lives) +
                        " $lives",
                color = MaterialTheme.colorScheme.onBackground,
                style = titleStyle
            )
            Slider(
                modifier = Modifier
                    .weight(windowState.heightType.filter(0.2f, 0.25f, 0.3f))
                    .padding(vertical = MediumPadding)
                    .padding(horizontal = MediumPadding)
                    .fillMaxWidth(windowState.widthType.filter(1f, 0.8f, 0.6f)),
                value = lives / 100f,
                onValueChange = { lives = (it * 100).toInt() },
                steps = 99,
            )
            Text(
                text = stringResource(R.string.difficulty) +
                        ": " + stringResource(difficulty.strRes),
                color = MaterialTheme.colorScheme.onBackground,
                style = titleStyle
            )
            LazyRow(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(windowState.widthType.filter(1f, 0.8f, 0.6f))
                    .padding(vertical = MediumPadding)
            ) {
                Difficulty.entries.forEach {
                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            RadioButton(
                                selected = difficulty == it,
                                onClick = { difficulty = it }
                            )
                            Text(
                                text = stringResource(it.strRes),
                                color = MaterialTheme.colorScheme.onBackground,
                                style = titleStyle
                            )
                        }
                    }
                }
            }
            IVerSpacer(0.1f)
            IOutlinedButton(
                onClick = { onStartGame(difficulty, lives) },
                label = stringResource(R.string.start_game),
                modifier = Modifier
                    .weight(windowState.heightType.filter(0.1f, 0.15f, 0.2f))
                    .fillMaxWidth(windowState.widthType.filter(1f, 0.8f, 0.6f)),
                imageVector = Icons.Filled.Start,
            )
            IVerSpacer(0.2f)
        }
    }
}

@UiModePreviews
@Composable
private fun CustomPreview() {
    YouKnowTheme {
        Custom()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownList(
    category: Category
) {
    var expanded by remember { mutableStateOf(false) }
    val option = stringResource(category.strRes)
    var selectedOption by remember { mutableStateOf(option) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        IOutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            label = stringResource(R.string.select_option),
            trailingIcon = (if(!expanded) Icons.Filled.ArrowDropDown else Icons.Filled.ArrowDropUp) to {},
            leadingIcon = null,
            modifier = Modifier
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = MaterialTheme.colorScheme.background,
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
            shape = MaterialTheme.shapes.small,
            shadowElevation = TonalElevation.Level5
        ) {
            Category.entries.forEach {
                val option = stringResource(it.strRes)
                DropdownMenuItem(
                    text = { Text(stringResource(it.strRes)) },
                    onClick = {
                        selectedOption = option
                        expanded = false
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }
}

