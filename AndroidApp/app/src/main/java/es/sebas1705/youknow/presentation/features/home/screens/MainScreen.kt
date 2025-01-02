package es.sebas1705.youknow.presentation.features.home.screens
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

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.cards.IResumeCard
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.texts.Title
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.home.viewmodels.RankingIntent
import es.sebas1705.youknow.presentation.features.home.viewmodels.RankingState
import es.sebas1705.youknow.presentation.features.home.viewmodels.RankingViewModel
import es.sebas1705.youknow.presentation.features.home.viewmodels.UserState
import es.sebas1705.youknow.presentation.features.home.viewmodels.UserViewModel
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Main Screen of the app.
 *
 * @see MainDesign
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun MainScreen(
    windowState: WindowState,
    userState: UserState,
    userViewModel: UserViewModel,
) {
    val rankingViewModel: RankingViewModel = hiltViewModel()
    val rankingState by rankingViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(windowState) {
        rankingViewModel.eventHandler(RankingIntent.GetRanking)
    }

    BackHandler {}
    MainDesign(
        windowState = windowState,
        rankingState = rankingState
    )
}

/**
 * Design of the Main Screen.
 *
 * @see ApplyBack
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
private fun MainDesign(
    windowState: WindowState = WindowState.default(),
    rankingState: RankingState = RankingState.default(),
) {
    ApplyBack(
        windowState.backEmpty
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Title(stringResource(R.string.app_name))
            }

            item {
                Title(text = stringResource(id = R.string.Ranking))
            }

            item {
                IResumeCard(
                    stringResource(R.string.Ranking),
                    rankingState.ranking.mapIndexed { index, user ->
                        "${index + 1}º. ${user.nickName}" to user.points.toString()
                    }.toMap(),
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
            }
        }
    }

}

/**
 * Preview of the Main Screen.
 *
 * @see MainDesign
 */
@UiModePreviews
@Composable
private fun MainPreview() {
    YouKnowTheme {
        MainDesign()
    }
}