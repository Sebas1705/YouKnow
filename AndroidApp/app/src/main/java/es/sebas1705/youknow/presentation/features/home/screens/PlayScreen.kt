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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.icon.IStandardIconButton
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.texts.IText
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.game.navigation.GameScreens
import es.sebas1705.youknow.presentation.features.game.navigation.games
import es.sebas1705.youknow.presentation.features.home.viewmodels.UserState
import es.sebas1705.youknow.presentation.features.home.viewmodels.UserViewModel
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Play Screen that will show the game to the user.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun PlayScreen(
    windowState: WindowState,
    userState: UserState,
    userViewModel: UserViewModel,
    onGameNavigation: (GameScreens) -> Unit
) {
    BackHandler {}
    PlayDesign(
        windowState = windowState,
        userState = userState,
        onGameNavigation = onGameNavigation
    )
}

/**
 * Design of the Play Screen.
 *
 * @see ApplyBack
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
private fun PlayDesign(
    windowState: WindowState = WindowState.default(),
    userState: UserState = UserState.default(),
    onGameNavigation: (GameScreens) -> Unit = {}
) {
    ApplyBack(
        windowState.backEmpty
    ) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.Center
        ) {
            items(games.size) {
                val game = games[it]
                Column {
                    IStandardIconButton(
                        onClick = { onGameNavigation(game.destination) },
                        imageResource = game.icon,
                        contentDescription = stringResource(game.strRes)
                    )
                    IText(
                        text = stringResource(game.strRes),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

/**
 * Preview of the Play Screen.
 *
 * @see PlayDesign
 */
@UiModePreviews
@Composable
private fun PlayPreview() {
    YouKnowTheme {
        PlayDesign()
    }
}