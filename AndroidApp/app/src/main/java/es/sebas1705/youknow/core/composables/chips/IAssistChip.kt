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

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import es.sebas1705.youknow.core.utlis.IComposablePreview
import es.sebas1705.youknow.core.utlis.extensions.composables.disabled
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

@Composable
fun IAssistChip(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    interactionSource: MutableInteractionSource? = null
) = AssistChip(
    onClick = onClick,
    label = { Text(label) },
    modifier = modifier,
    enabled = enabled,
    leadingIcon = { leadingIcon?.let { Icon(it, contentDescription = null) } },
    trailingIcon = { trailingIcon?.let { Icon(it, contentDescription = null) } },
    colors = AssistChipDefaults.assistChipColors(
        containerColor = MaterialTheme.colorScheme.surface,
        labelColor = MaterialTheme.colorScheme.onSurface,
        leadingIconContentColor = MaterialTheme.colorScheme.primary,
        trailingIconContentColor = MaterialTheme.colorScheme.primary,
        disabledContainerColor = MaterialTheme.colorScheme.surface.disabled(),
        disabledLabelColor = MaterialTheme.colorScheme.onSurface.disabled(),
        disabledLeadingIconContentColor = MaterialTheme.colorScheme.primary.disabled(),
        disabledTrailingIconContentColor = MaterialTheme.colorScheme.primary.disabled(),
    ),
    interactionSource = interactionSource
)

@IComposablePreview
@Composable
private fun PreviewE() = YouKnowTheme {
    IAssistChip(
        onClick = { },
        label = "Assist Chip",
        enabled = true,
        leadingIcon = Icons.Filled.Add
    )
}

@IComposablePreview
@Composable
private fun PreviewD() = YouKnowTheme {
    IAssistChip(
        onClick = { },
        label = "Assist Chip",
        enabled = false,
        trailingIcon = Icons.Filled.Add
    )
}

