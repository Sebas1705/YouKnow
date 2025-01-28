package es.sebas1705.youknow.core.composables.chips
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
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import es.sebas1705.youknow.core.utlis.IComposablePreview
import es.sebas1705.youknow.core.utlis.extensions.composables.disabled
import es.sebas1705.youknow.presentation.ui.theme.OutlineThickness
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Personalized suggestion chip
 *
 * @param onClick [() -> Unit]: On click
 * @param label [String]: Label
 * @param modifier [Modifier]: Modifier
 * @param enabled [Boolean]: Enabled
 * @param icon [ImageVector?]: Icon
 * @param interactionSource [MutableInteractionSource?]: Interaction source
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios
 */
@Composable
fun ISuggestionChip(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector? = null,
    interactionSource: MutableInteractionSource? = null
) = SuggestionChip(
    onClick = onClick,
    label = { Text(label) },
    modifier = modifier,
    enabled = enabled,
    icon = { icon?.let { Icon(it, contentDescription = null) } },
    colors = SuggestionChipDefaults.suggestionChipColors(
        containerColor = MaterialTheme.colorScheme.surface,
        labelColor = MaterialTheme.colorScheme.onSurface,
        iconContentColor = MaterialTheme.colorScheme.onSurface,
        disabledContainerColor = MaterialTheme.colorScheme.surface.disabled(),
        disabledLabelColor = MaterialTheme.colorScheme.onSurface.disabled(),
        disabledIconContentColor = MaterialTheme.colorScheme.onSurface.disabled()
    ),
    border = BorderStroke(
        OutlineThickness,
        MaterialTheme.colorScheme.onSurface
    ),
    interactionSource = interactionSource
)

@IComposablePreview
@Composable
private fun PreviewE() = YouKnowTheme {
    ISuggestionChip(
        onClick = { },
        label = "Suggestion Chip",
        enabled = true,
        icon = Icons.Filled.Add
    )
}

@IComposablePreview
@Composable
private fun PreviewD() = YouKnowTheme {
    ISuggestionChip(
        onClick = { },
        label = "Suggestion Chip",
        enabled = true,
        icon = Icons.Filled.Add
    )
}

