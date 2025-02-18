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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import es.sebas1705.youknow.core.composables.cards.IPrimaryCard
import es.sebas1705.common.utlis.IComposablePreview
import es.sebas1705.common.utlis.extensions.composables.makeItalic
import es.sebas1705.common.utlis.extensions.composables.makeTitle
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Title surface
 *
 * @param text [String]: Text
 * @param modifier [Modifier]: Modifier
 * @param textAlign [TextAlign]: Text align
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun TitleSurface(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    textStyle: TextStyle = MaterialTheme.typography.displayMedium
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
            ),
            textAlign = textAlign
        )
    }
}

@IComposablePreview
@Composable
private fun Preview() = YouKnowTheme {
    TitleSurface("Hello World")
}