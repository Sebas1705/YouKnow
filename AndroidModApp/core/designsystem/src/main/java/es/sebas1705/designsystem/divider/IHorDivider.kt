package es.sebas1705.designsystem.divider
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

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import es.sebas1705.common.utlis.IComposablePreview
import es.sebas1705.ui.theme.OutlineThickness
import es.sebas1705.ui.theme.YouKnowTheme

/**
 * Horizontal divider
 *
 * @param modifier [Modifier]: Modifier
 * @param color [Color]: Color
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun IHorDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.outline
) = HorizontalDivider(
    modifier = modifier,
    thickness = OutlineThickness,
    color = color
)

@IComposablePreview
@Composable
private fun Preview() = YouKnowTheme {
    IHorDivider()
}

