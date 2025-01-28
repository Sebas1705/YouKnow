package es.sebas1705.youknow.core.composables.surfaces
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.core.utlis.IComposablePreview
import es.sebas1705.youknow.presentation.ui.theme.OutlineThickness
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Primary surface
 *
 * @param modifier [Modifier]: Modifier
 * @param content [Composable]: Content
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun IPrimarySurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) = Surface(
    modifier = modifier,
    color = MaterialTheme.colorScheme.surfaceContainer,
    shape = MaterialTheme.shapes.medium,
    border = BorderStroke(OutlineThickness, MaterialTheme.colorScheme.primary),
    content = content
)

@IComposablePreview
@Composable
private fun Preview() = YouKnowTheme {
    IPrimarySurface(
        modifier = Modifier
            .width(200.dp)
            .height(400.dp)
    ) {
        Text("Hello, World!")
    }
}