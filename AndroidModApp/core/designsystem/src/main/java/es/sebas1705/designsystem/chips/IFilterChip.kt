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
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import es.sebas1705.common.utlis.IComposablePreview
import es.sebas1705.common.utlis.extensions.composables.disabled
import es.sebas1705.youknow.presentation.ui.theme.OutlineThickness
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Personalized filter chip
 *
 * @param onClick [() -> Unit]: On click
 * @param selected [Boolean]: Selected
 * @param label [String]: Label
 * @param modifier [Modifier]: Modifier
 * @param enabled [Boolean]: Enabled
 * @param leadingIcon [ImageVector?]: Leading icon
 * @param trailingIcon [ImageVector?]: Trailing icon
 * @param interactionSource [MutableInteractionSource?]: Interaction source
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios
 */
@Composable
fun IFilterChip(
    onClick: () -> Unit,
    selected: Boolean,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    interactionSource: MutableInteractionSource? = null
) = FilterChip(
    onClick = onClick,
    selected = selected,
    label = { Text(label) },
    modifier = modifier,
    enabled = enabled,
    leadingIcon = { leadingIcon?.let { Icon(it, contentDescription = null) } },
    trailingIcon = { trailingIcon?.let { Icon(it, contentDescription = null) } },
    colors = FilterChipDefaults.filterChipColors(
        containerColor = MaterialTheme.colorScheme.surface,
        labelColor = MaterialTheme.colorScheme.onSurface,
        iconColor = MaterialTheme.colorScheme.onSurface,
        disabledContainerColor = MaterialTheme.colorScheme.surface.disabled(),
        disabledLabelColor = MaterialTheme.colorScheme.onSurface.disabled(),
        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurface.disabled(),
        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface.disabled(),
        selectedContainerColor = MaterialTheme.colorScheme.primary,
        disabledSelectedContainerColor = MaterialTheme.colorScheme.primary.disabled(),
        selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
        selectedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
        selectedTrailingIconColor = MaterialTheme.colorScheme.onPrimary
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
    IFilterChip(
        onClick = { },
        selected = true,
        label = "Filter Chip",
        enabled = true,
        leadingIcon = Icons.Filled.Add
    )
}

@IComposablePreview
@Composable
private fun PreviewD() = YouKnowTheme {
    IFilterChip(
        onClick = { },
        selected = false,
        label = "Filter Chip",
        enabled = true,
        trailingIcon = Icons.Filled.Add
    )
}

