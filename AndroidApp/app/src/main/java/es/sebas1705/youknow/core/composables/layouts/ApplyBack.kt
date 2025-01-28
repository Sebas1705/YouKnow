package es.sebas1705.youknow.core.composables.layouts
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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.utlis.IComposablePreview
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Apply back
 *
 * @param backId [Int]: Back id
 * @param modifier [Modifier]: Modifier
 * @param paddingValues [PaddingValues]: Padding values
 * @param applyCondition [Boolean]: Apply condition
 * @param content [BoxScope.() -> Unit]: Content
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun ApplyBack(
    backId: Int,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    applyCondition: Boolean = true,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        if (applyCondition) Image(
            painter = painterResource(id = backId),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        content()
    }
}

@IComposablePreview
@Composable
private fun Preview() = YouKnowTheme {
    ApplyBack(
        backId = R.drawable.back_landscape_fill,
        modifier = Modifier
            .height(500.dp)
            .width(200.dp)
    ) {

    }
}
