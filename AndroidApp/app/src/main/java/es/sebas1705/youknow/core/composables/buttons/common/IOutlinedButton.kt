package es.sebas1705.youknow.core.composables.buttons.common
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
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.core.utlis.IComposablePreview
import es.sebas1705.youknow.core.utlis.extensions.composables.disabled
import es.sebas1705.youknow.presentation.ui.theme.OutlineThickness
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

@Composable
fun IOutlinedButton(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
    imageResource: Int? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) = OutlinedButton(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    colors = ButtonDefaults.outlinedButtonColors(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.primary,
        disabledContainerColor = MaterialTheme.colorScheme.surface.disabled(),
        disabledContentColor = MaterialTheme.colorScheme.primary.disabled(),
    ),
    border = BorderStroke(
        OutlineThickness,
        if (enabled) MaterialTheme.colorScheme.outline
        else MaterialTheme.colorScheme.outline.disabled()
    ),
    interactionSource = interactionSource,
    content = {
        val modifier = Modifier.padding(end = 8.dp)
        if (imageVector != null)
            Icon(imageVector, label, modifier)
        else if (imageResource != null)
            Icon(painterResource(imageResource), label, modifier)
        Text(label)
    }
)

@IComposablePreview
@Composable
private fun PreviewE() = YouKnowTheme {
    IOutlinedButton(
        onClick = {},
        label = "Add",
        imageVector = Icons.Default.Add
    )
}

@IComposablePreview
@Composable
private fun PreviewD() = YouKnowTheme {
    IOutlinedButton(
        onClick = {},
        enabled = false,
        label = "Add",
        imageVector = Icons.Default.Add
    )
}


