package es.sebas1705.youknow.core.composables.buttons.radio
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

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.core.utlis.IComposablePreview
import es.sebas1705.youknow.core.utlis.extensions.composables.disabled
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

@Composable
fun IRadioButton(
    selected: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    onClick: (() -> Unit)? = null
) = RadioButton(
    selected = selected,
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    colors = RadioButtonDefaults.colors(
        selectedColor = MaterialTheme.colorScheme.primary,
        unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledSelectedColor = MaterialTheme.colorScheme.primary.disabled(),
        disabledUnselectedColor = MaterialTheme.colorScheme.onSurfaceVariant.disabled()
    ),
    interactionSource = interactionSource
)

@IComposablePreview
@Composable
private fun Preview() = YouKnowTheme {
    IRadioButton(
        selected = true,
        modifier = Modifier
            .width(24.dp)
            .height(24.dp)
    )
}
