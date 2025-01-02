package es.sebas1705.youknow.core.composables.buttons.segmented
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

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import es.sebas1705.youknow.core.utlis.IComposablePreview
import es.sebas1705.youknow.core.utlis.extensions.composables.disabled
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

@Composable
fun ISingleChoiceSegmentedButton(
    elements: List<Triple<String, ImageVector?, Boolean>>,
    selectedElement: MutableState<Int>,
    modifier: Modifier = Modifier,
) = SingleChoiceSegmentedButtonRow(modifier) {
    elements.forEachIndexed { index, element ->
        val selected = index == selectedElement.value
        SegmentedButton(
            selected = selected,
            onClick = { selectedElement.value = index },
            shape = SegmentedButtonDefaults.itemShape(index, elements.size),
            enabled = element.third,
            colors = SegmentedButtonDefaults.colors(
                activeContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                activeContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                activeBorderColor = MaterialTheme.colorScheme.outline,
                inactiveContainerColor = Color.Transparent,
                inactiveContentColor = MaterialTheme.colorScheme.onSurface,
                inactiveBorderColor = MaterialTheme.colorScheme.outline,
                disabledActiveContainerColor = Color.Transparent,
                disabledActiveContentColor = MaterialTheme.colorScheme.onSurface.disabled(),
                disabledActiveBorderColor = MaterialTheme.colorScheme.secondaryContainer.disabled(),
                disabledInactiveContainerColor = Color.Transparent,
                disabledInactiveContentColor = MaterialTheme.colorScheme.onSurface.disabled(),
                disabledInactiveBorderColor = MaterialTheme.colorScheme.outline.disabled()
            ),
            icon = {
                if (selected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = element.first
                    )
                } else if (element.second != null) {
                    Icon(
                        imageVector = element.second!!,
                        contentDescription = element.first
                    )
                }
            },
            label = {
                Text(
                    element.first,
                    modifier = Modifier.padding(start = SmallestPadding)
                )
            }
        )
    }
}

@IComposablePreview
@Composable
private fun Preview() = YouKnowTheme {
    var selectedElement = remember { mutableIntStateOf(0) }
    ISingleChoiceSegmentedButton(
        elements = listOf(
            Triple("Add", Icons.Default.Add, true),
            Triple("Check", Icons.Default.Check, false),
        ),
        selectedElement = selectedElement
    )
}
