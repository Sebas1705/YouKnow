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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import es.sebas1705.youknow.core.composables.buttons.icon.IStandardIconButton
import es.sebas1705.youknow.core.composables.divider.IHorDivider
import es.sebas1705.youknow.core.utlis.IComposablePreview
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Personalized resume card
 *
 * @param title [String]: Title
 * @param titlesValues [Map<String, String>]: Titles and values
 * @param modifier [Modifier]: Modifier
 * @param imageVector [Triple<ImageVector,String,()->Unit>]: Image vector
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios
 */
@Composable
fun IResumeCard(
    title: String,
    titlesValues: Map<String, String>,
    modifier: Modifier = Modifier,
    imageVector: Triple<ImageVector, String, () -> Unit>? = null,
) = IPrimaryCard(modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(
                start = SmallPadding,
                top = SmallestPadding
            )
        )
        imageVector?.let {
            IStandardIconButton(
                imageVector = it.first,
                contentDescription = it.second,
                onClick = it.third,
                modifier = Modifier.padding(
                    end = SmallPadding,
                    top = SmallestPadding
                )
            )
        }
    }

    for ((title, value) in titlesValues) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(SmallPadding)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(SmallPadding)
            )
        }
        if (title != titlesValues.keys.last()) IHorDivider(
            modifier = Modifier.padding(horizontal = SmallPadding),
            color = MaterialTheme.colorScheme.onSurface
        )
        else Spacer(modifier = Modifier.height(SmallestPadding))
    }
}

@IComposablePreview
@Composable
private fun Preview() = YouKnowTheme {
    IResumeCard(
        "Title",
        titlesValues = mapOf(
            "Title 1" to "Value 1",
            "Title 2" to "Value 2",
            "Title 3" to "Value 3",
        ),
        imageVector = Triple(
            Icons.Filled.Link,
            "Edit"
        ) {},
    )
}
