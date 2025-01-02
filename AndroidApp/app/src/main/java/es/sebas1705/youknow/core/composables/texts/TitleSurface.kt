package es.sebas1705.youknow.core.composables.texts
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

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.sebas1705.youknow.core.composables.cards.IPrimaryCard
import es.sebas1705.youknow.core.utlis.IComposablePreview
import es.sebas1705.youknow.core.utlis.extensions.composables.makeItalic
import es.sebas1705.youknow.core.utlis.extensions.composables.makeTitle
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

@Composable
fun TitleSurface(
    text: String,
    modifier: Modifier = Modifier,
) {
    IPrimaryCard(
        modifier = modifier
    ) {
        Title(
            text = text,
            style = MaterialTheme.typography.displayMedium
                .makeTitle()
                .makeItalic(),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(
                vertical = SmallPadding,
                horizontal = MediumPadding
            )
        )
    }
}

@IComposablePreview
@Composable
private fun Preview() = YouKnowTheme {
    TitleSurface("Hello World")
}