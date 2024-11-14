package es.sebas1705.youknow.presentation.features.home.screens.social.composables
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

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.presentation.composables.Spacers.VerticalSpacer

/**
 * Icons for navigation between chat and group.
 *
 * @param modifier [Modifier]: Modifier to apply to the icons.
 * @param isGroup [Boolean]: If the user is in the group section.
 * @param changeToGroup () -> Unit: Function to change to the group section.
 * @param changeToChat () -> Unit: Function to change to the chat section.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun IconsBar(
    modifier: Modifier = Modifier,
    isGroup: Boolean,
    changeToGroup: () -> Unit,
    changeToChat: () -> Unit,
) {
    Row(
        modifier = modifier,
    ) {
        VerticalSpacer(5f)
        IconItem(
            modifier = Modifier
                .fillMaxHeight()
                .weight(20f),
            enabled = !isGroup,
            onClick = changeToGroup,
            imageVector = Icons.Default.Groups,
            contentDescription = "Group",
        )
        VerticalSpacer(1f)
        IconItem(
            modifier = Modifier
                .fillMaxHeight()
                .weight(20f),
            enabled = isGroup,
            onClick = changeToChat,
            imageVector = Icons.Default.Forum,
            contentDescription = "Menu",
        )
        VerticalSpacer(5f)
    }

}

/**
 * Icon item for the navigation between chat and group.
 * The icon will be a button that will change the section when clicked.
 * The icon will be enabled if the user is in the section of the icon.
 *
 * @param modifier [Modifier]: Modifier to apply to the icon.
 * @param enabled [Boolean]: If the icon is enabled.
 * @param onClick () -> Unit: Function to change the section.
 * @param imageVector [ImageVector]: Icon to show.
 * @param contentDescription [String]: Content description of the icon.
 *
 * @see IconsBar
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
private fun IconItem(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        shape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
        ),
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.primary,
        )
    ) {
        Icon(
            modifier = Modifier.fillMaxSize(0.7f),
            imageVector = imageVector,
            contentDescription = contentDescription,
        )
    }
}