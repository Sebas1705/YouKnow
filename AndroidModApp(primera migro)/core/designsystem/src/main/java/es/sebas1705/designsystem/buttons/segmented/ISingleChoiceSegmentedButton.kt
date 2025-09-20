package es.sebas1705.designsystem.buttons.segmented
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

import android.content.Context
import android.media.SoundPool
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import es.sebas1705.common.utlis.IComposablePreview
import es.sebas1705.common.utlis.extensions.composables.disabled
import es.sebas1705.designsystem.ComposableConstants.LOOP_N
import es.sebas1705.designsystem.ComposableConstants.NAV_BUTTON_SOUND
import es.sebas1705.designsystem.ComposableConstants.PRIORITY_SOUND
import es.sebas1705.designsystem.ComposableConstants.RATE
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.ui.theme.Paddings.SmallestPadding
import es.sebas1705.ui.theme.YouKnowTheme

/**
 * Personalized single choice segmented button
 *
 * @param elements [List<Triple<String, ImageVector?, Boolean>]: Elements
 * @param selectedElement [MutableState<Int>]: Selected element
 * @param modifier [Modifier]: Modifier
 * @param soundPool [Pair]<[SoundPool], [Float]>: Sound pool
 * @param soundRes [Int]: Sound resource
 * @param context [Context]: Context
 * @param soundId [Int]: Sound id
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios
 */
@Composable
fun ISingleChoiceSegmentedButton(
    elements: List<Triple<String, ImageVector?, Boolean>>,
    selectedElement: MutableState<Int>,
    modifier: Modifier = Modifier,
    soundPool: Pair<SoundPool, Float>? = null,
    soundRes: Int = NAV_BUTTON_SOUND,
    context: Context = LocalContext.current,
    soundId: Int? = remember { soundPool?.first?.load(context, soundRes, PRIORITY_SOUND) }
) = SingleChoiceSegmentedButtonRow(modifier) {
    elements.forEachIndexed { index, element ->
        val selected = index == selectedElement.value
        SegmentedButton(
            selected = selected,
            onClick = {
                soundPool?.first?.play(
                    soundId ?: 0,
                    soundPool.second,
                    soundPool.second,
                    PRIORITY_SOUND,
                    LOOP_N,
                    RATE
                )
                selectedElement.value = index
            },
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
                IText(
                    element.first,
                    modifier = Modifier.padding(start = SmallestPadding),
                    maxLines = 1
                )
            }
        )
    }
}

@IComposablePreview
@Composable
private fun Preview() = YouKnowTheme {
    val selectedElement = remember { mutableIntStateOf(0) }
    ISingleChoiceSegmentedButton(
        elements = listOf(
            Triple("Add", Icons.Default.Add, true),
            Triple("Check", Icons.Default.Check, false),
        ),
        selectedElement = selectedElement
    )
}
