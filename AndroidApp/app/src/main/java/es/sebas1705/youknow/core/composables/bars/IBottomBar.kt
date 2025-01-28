package es.sebas1705.youknow.core.composables.bars
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

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.sebas1705.youknow.core.composables.texts.IText
import es.sebas1705.youknow.core.utlis.IComposablePreview
import es.sebas1705.youknow.presentation.ui.theme.TonalElevation
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Personalized bottom bar
 *
 * @param modifier [Modifier]: Modifier
 * @param content [RowScope.() -> Unit]: Content
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun IBottomBar(
    modifier: Modifier = Modifier,
    content: @Composable (RowScope.() -> Unit),
) = BottomAppBar(
    modifier = modifier,
    containerColor = MaterialTheme.colorScheme.surfaceContainer,
    contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
    tonalElevation = TonalElevation.Level2,
    content = content
)

@IComposablePreview
@Composable
private fun Preview() = YouKnowTheme {
    IBottomBar {
        IText("Hola mundo")
    }
}