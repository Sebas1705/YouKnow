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

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import es.sebas1705.youknow.R
import es.sebas1705.youknow.presentation.ui.theme.titleFontFamily

/**
 * Title of the app
 *
 * @param modifier Modifier
 *
 * @see Text
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun TitleApp(
    modifier: Modifier = Modifier
){
    Title(
        modifier = modifier,
        text = stringResource(id = R.string.app_name)
    )
}

/**
 * Title
 *
 * @param modifier Modifier
 *
 * @see Text
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun Title(
    modifier: Modifier = Modifier,
    text: String,
){
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.displayLarge.copy(fontFamily = titleFontFamily),
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center
    )
}

@Composable
fun Subtitle(
    modifier: Modifier = Modifier,
    text: String,
){
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.displaySmall.copy(fontFamily = titleFontFamily),
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = TextAlign.Center
    )
}