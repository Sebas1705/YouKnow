package es.sebas1705.designsystem.bars
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.sebas1705.common.utlis.IComposablePreview
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.ui.theme.YouKnowTheme

/**
 * Personalized top bar
 *
 * @param title [Composable]: Title
 * @param modifier [Modifier]: Modifier
 * @param navigationIcon [Composable]: Navigation icon
 * @param actions [RowScope.() -> Unit]: Actions
 * @param scrollBehavior [TopAppBarScrollBehavior]: Scroll behavior
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ITopBar(
    title: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit) = { },
    actions: @Composable (RowScope.() -> Unit) = { },
    scrollBehavior: TopAppBarScrollBehavior? = null
) = TopAppBar(
    title,
    modifier,
    navigationIcon,
    actions,
    colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.surface,
        scrolledContainerColor = MaterialTheme.colorScheme.surface,
        navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        actionIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
    ),
    scrollBehavior = scrollBehavior,
)

@OptIn(ExperimentalMaterial3Api::class)
@IComposablePreview
@Composable
private fun Preview() = YouKnowTheme {
    ITopBar(
        title = { IText("Hola mundo") }
    )
}
