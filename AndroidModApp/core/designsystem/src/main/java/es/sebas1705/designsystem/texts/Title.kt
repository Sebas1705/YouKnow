package es.sebas1705.designsystem.texts
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

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import es.sebas1705.common.utlis.IComposablePreview
import es.sebas1705.ui.theme.YouKnowTheme
import es.sebas1705.ui.theme.makeTitle

/**
 * Title
 *
 * @param text [String]: Text
 * @param modifier [Modifier]: Modifier
 * @param style [TextStyle]: Style
 * @param color [Color]: Color
 * @param textAlign [TextAlign]: Text align
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun Title(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.displayLarge,
    color: Color = MaterialTheme.colorScheme.primary,
    textAlign: TextAlign = TextAlign.Center
) = IText(
    modifier = modifier,
    text = text,
    style = style.makeTitle(),
    color = color,
    textAlign = textAlign,
    overflow = TextOverflow.Ellipsis
)

@IComposablePreview
@Composable
private fun Preview() = YouKnowTheme {
    Title("Hello World")
}