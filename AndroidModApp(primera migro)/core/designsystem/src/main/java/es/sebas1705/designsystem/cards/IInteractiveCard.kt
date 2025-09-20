package es.sebas1705.designsystem.cards
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
import es.sebas1705.common.utlis.IComposablePreview
import es.sebas1705.common.utlis.extensions.composables.makeBold
import es.sebas1705.designsystem.buttons.icon.IStandardIconButton
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.ui.theme.YouKnowTheme

/**
 * Personalized interactive card
 *
 * @param title [String]: Title
 * @param modifier [Modifier]: Modifier
 * @param subtitle [String]: Subtitle
 * @param buttons [RowScope.() -> Unit]: Buttons
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios
 */
@Composable
fun IInteractiveCard(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String = "",
    buttons: @Composable RowScope.() -> Unit,
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
