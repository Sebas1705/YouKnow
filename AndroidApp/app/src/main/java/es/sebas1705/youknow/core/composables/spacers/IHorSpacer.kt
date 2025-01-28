package es.sebas1705.youknow.core.composables.spacers
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

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import es.sebas1705.youknow.core.utlis.IComposablePreview
import es.sebas1705.youknow.presentation.ui.theme.Paddings.HugePadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Horizontal spacer
 *
 * @param weight [Float]: Weight
 * @param width [Dp]: Width
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun RowScope.IHorSpacer(
    weight: Float? = null,
    width: Dp = HugePadding
) = Spacer(
    modifier =
    if (weight == null)
        Modifier
            .fillMaxWidth()
            .width(width)
    else Modifier
        .fillMaxWidth()
        .weight(weight)
)

@IComposablePreview
@Composable
private fun Preview() = YouKnowTheme {
    Row {
        IHorSpacer(1f)
    }
}
