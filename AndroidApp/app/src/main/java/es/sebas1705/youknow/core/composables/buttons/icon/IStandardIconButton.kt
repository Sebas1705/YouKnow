package es.sebas1705.youknow.core.composables.buttons.icon
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.utlis.IComposablePreview
import es.sebas1705.youknow.core.utlis.extensions.composables.disabled
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

@Composable
fun IStandardIconButton(
    onClick: () -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
    imageResource: Int = R.drawable.icon,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) = IconButton(
    onClick,
    modifier,
    colors = IconButtonDefaults.filledIconButtonColors(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledContainerColor = Color.Transparent,
        disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant.disabled()
    ),
    enabled = enabled,
    interactionSource = interactionSource,
    content = {
        if (imageVector != null)
            Icon(imageVector, contentDescription)
        else
            Icon(painterResource(imageResource), contentDescription)
    }
)

@IComposablePreview
@Composable
private fun PreviewE() = YouKnowTheme {
    IStandardIconButton(
        onClick = {},
        contentDescription = "Add",
        imageVector = Icons.Default.Add
    )
}

@IComposablePreview
@Composable
private fun PreviewD() = YouKnowTheme {
    IStandardIconButton(
        onClick = {},
        contentDescription = "Add",
        imageVector = Icons.Default.Add,
        enabled = false
    )
}
