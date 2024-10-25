package es.sebas1705.youknow.presentation.features.game.screens
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

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import es.sebas1705.youknow.presentation.features.game.viewmodels.ResponseState
import es.sebas1705.youknow.presentation.features.game.viewmodels.TriviaViewModel
import es.sebas1705.youknow.R
import es.sebas1705.youknow.data.apis.opendb.dtos.ResponseOpendbDto
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.data.apis.opendb.config.SettingsOpendb.TRIVIA_RESPONSE_EXAMPLE
import es.sebas1705.youknow.data.apis.opendb.config.decodeUrl
import es.sebas1705.youknow.presentation.composables.ApplyBack
import es.sebas1705.youknow.presentation.features.auth.viewmodels.AuthViewModel
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Screen that shows the trivia questions to the user.
 *
 * @param onSuccessLogOutNavigation () -> Unit: Function to navigate to the log out screen.
 * @param onErrorButton () -> Unit: Function to handle the error button.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun TriviaScreen(
    onSuccessLogOutNavigation: () -> Unit = {},
    onErrorButton: () -> Unit = {},
) {

    val authViewModel: AuthViewModel = hiltViewModel()
    val viewModel: TriviaViewModel = hiltViewModel()
    val responseState by viewModel.responseState.collectAsState()

    when (responseState) {
        is ResponseState.Loading ->
            LoadingDesign()

        is ResponseState.Success ->
            TriviaDesign(
                data = (responseState as ResponseState.Success).responseOpendbDto,
                authViewModel = authViewModel,
                onSuccessLogOutNavigation = onSuccessLogOutNavigation
            )

        is ResponseState.Error ->
            ErrorDesign(
                message = (responseState as ResponseState.Error).message,
                onErrorButton = onErrorButton
            )

    }
}

@Composable
private fun LoadingDesign() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}

@UiModePreviews
@Composable
private fun LoadingPreview() {
    YouKnowTheme {
        LoadingDesign()
    }
}

@Composable
private fun ErrorDesign(
    message: String = "Error",
    onErrorButton: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.error),
        contentAlignment = Alignment.Center
    ) {
        TextButton(
            onClick = onErrorButton,
        ) {
            Text(
                text = message,
                color = MaterialTheme.colorScheme.onError
            )
        }
    }
}

@UiModePreviews
@Composable
private fun ErrorPreview() {
    YouKnowTheme {
        ErrorDesign()
    }
}

@Composable
private fun TriviaDesign(
    data: ResponseOpendbDto = TRIVIA_RESPONSE_EXAMPLE,
    authViewModel: AuthViewModel? = null,
    onSuccessLogOutNavigation: () -> Unit = {},
) {
    ApplyBack(
        R.drawable.back_portrait_fill
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxHeight(0.1f)
                    .clickable {
                    }
            )
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
    ) {
        data.questionOpendbDtos.forEach {
            item {
                Text(
                    text = it.question.decodeUrl(),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@UiModePreviews
@Composable
private fun TriviaPreview() {
    YouKnowTheme {
        TriviaDesign()
    }
}





