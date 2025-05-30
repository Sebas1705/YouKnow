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

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.sebas1705.common.utlis.IComposablePreview
import es.sebas1705.common.utlis.extensions.composables.disabled
import es.sebas1705.ui.theme.YouKnowTheme

/**
 * Personalized filled card
 *
 * @param modifier [Modifier]: Modifier
 * @param content [ColumnScope.() -> Unit]: Content
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios
 */
@Composable
fun IFilledCard(
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit),
) = Card(
    modifier = modifier,
    colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        contentColor = MaterialTheme.colorScheme.onSurface,
        disabledContainerColor = MaterialTheme.colorScheme.surfaceContainer.disabled(),
        disabledContentColor = MaterialTheme.colorScheme.onSurface.disabled()
    ),
    content = content
)

@IComposablePreview
@Composable
private fun Preview() = YouKnowTheme {
    IFilledCard(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
    ) {
        Text("Hello, World!")
    }
}
