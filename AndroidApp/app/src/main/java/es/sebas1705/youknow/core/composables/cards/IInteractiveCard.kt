package es.sebas1705.youknow.core.composables.cards
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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.sebas1705.youknow.core.composables.buttons.icon.IStandardIconButton
import es.sebas1705.youknow.core.utlis.IComposablePreview
import es.sebas1705.youknow.core.utlis.extensions.composables.makeBold
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

@Composable
fun IInteractiveCard(
    title: String,
    subtitle: String = "",
    buttons: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier
) = IPrimaryCard(modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SmallPadding),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.makeBold(),
            modifier = Modifier
                .padding(SmallPadding),
        )

        if (subtitle.isNotEmpty()) Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(SmallPadding),
        )

        buttons()
    }
}

@IComposablePreview
@Composable
private fun Preview() = YouKnowTheme {
    IInteractiveCard(
        title = "Title",
        subtitle = "Subtitle",
        buttons = {
            IStandardIconButton(
                imageVector = Icons.Default.Link,
                contentDescription = "Link",
                onClick = {}
            )
        }
    )
}
