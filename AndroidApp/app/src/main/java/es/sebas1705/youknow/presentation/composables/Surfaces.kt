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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.sebas1705.youknow.presentation.ui.theme.SurfaceBorderWidth

@Composable
fun CurvedBorderSurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) = Surface(
    modifier = modifier,
    color = MaterialTheme.colorScheme.primaryContainer,
    shape = MaterialTheme.shapes.medium,
    border = BorderStroke(SurfaceBorderWidth, MaterialTheme.colorScheme.primary),
    content = content
)
