package es.sebas1705.designsystem.badges
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

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BadgedBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.sebas1705.common.utlis.IComposablePreview
import es.sebas1705.ui.theme.YouKnowTheme
import es.sebas1705.designsystem.buttons.icon.IOutlinedIconButton

/**
 * Personalized badge box
 *
 * @param text [String]: Text to show
 * @param content [BoxScope.() -> Unit]: Content
 * @param modifier [Modifier]: Modifier
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun IBadgeBox(
    text: String,
    content: @Composable (BoxScope.() -> Unit),
    modifier: Modifier = Modifier,
) = BadgedBox(
    badge = { IBadge(text) },
    modifier = modifier,
    content = content
)

@IComposablePreview
@Composable
private fun Preview() = YouKnowTheme {
    IBadgeBox(
        text = "1",
        content = {
            IOutlinedIconButton(
                onClick = {},
                contentDescription = "Add",
                imageVector = Icons.Default.Add
            )
        }
    )
}

