package es.sebas1705.youknow.presentation.composables
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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.core.utlis.makeBold
import es.sebas1705.youknow.presentation.ui.theme.CardDividerThickness
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.TonalElevation

/**
 * Composable that represents a divider for the cards.
 *
 * @see HorizontalDivider
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun CardHDivider() = HorizontalDivider(
    color = MaterialTheme.colorScheme.onPrimaryContainer,
    thickness = CardDividerThickness,
    modifier = Modifier.padding(vertical = SmallestPadding, horizontal = SmallPadding)
)

@Composable
fun CardVDivider() = VerticalDivider(
    color = MaterialTheme.colorScheme.onPrimaryContainer,
    thickness = CardDividerThickness,
)

@Composable
fun InfoCard(
    titlesValues: Map<String, String>,
    modifier: Modifier = Modifier,
) = CustomCard(modifier) {
    for ((title, value) in titlesValues) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SmallestPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(SmallPadding)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(SmallPadding)
            )
        }
        if (title != titlesValues.keys.last()) CardHDivider()
    }
}

@Composable
fun InteractiveCard(
    title: String,
    subtitle: String = "",
    buttons: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
) = CustomCard(modifier) {
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

        CardVDivider()

        if(subtitle.isNotEmpty()) Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(SmallPadding),
        )

        CardVDivider()

        buttons()
    }
}

@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) = Card(
    modifier = modifier,
    shape = MaterialTheme.shapes.small,
    colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.primary
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = TonalElevation.Level4),
    border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
    content = content
)
