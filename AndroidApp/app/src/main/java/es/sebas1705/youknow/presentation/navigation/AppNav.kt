package es.sebas1705.youknow.presentation.navigation
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

import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.utlis.extensions.composables.navAndPopUp
import es.sebas1705.youknow.presentation.features.auth.navigation.AuthNav
import es.sebas1705.youknow.presentation.features.game.navigation.GameNav
import es.sebas1705.youknow.presentation.features.game.navigation.GameScreens.Companion.games
import es.sebas1705.youknow.presentation.features.guide.GuideScreen
import es.sebas1705.youknow.presentation.features.home.navigation.HomeNav
import es.sebas1705.youknow.presentation.features.settings.SettingsScreen
import es.sebas1705.youknow.presentation.features.survey.SurveyScreen
import es.sebas1705.youknow.presentation.navigation.AppGraph.Companion.graph
import org.checkerframework.checker.units.qual.A

/**
 * Navigation for the app.
 *
 * @param startDestination [AppGraph]: Start destination of the app.
 * @param windowState [WindowState]: State of the window.
 * @param onMusicChange (Boolean) -> Unit: Function that will change the music.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun AppNav(
    startDestination: AppGraph,
    windowState: WindowState,
    onMusicChange: (Boolean) -> Unit,
    soundPool: Pair<SoundPool, Float>,
) {
    // NavController:
    val appNavController = rememberNavController()

    //States:
    var game by rememberSaveable { mutableIntStateOf(0) }
    val destination by rememberSaveable { mutableIntStateOf(graph.indexOf(startDestination)) }

    //Body:
    NavHost(navController = appNavController, startDestination = graph[destination]) {
        composable<AppGraph.GuideScreen> {
            GuideScreen(
                windowState,
                soundPool,
                onSuccessNavigation = {
                    appNavController.navigate(
                        AppGraph.AuthNavigation
                    )
                }
            )
        }
        composable<AppGraph.SettingsScreen> {
            SettingsScreen(
                windowState,
                soundPool,
                onBack = {
                    appNavController.navAndPopUp(AppGraph.HomeNavigation, AppGraph.SettingsScreen)
                }
            )
        }
        composable<AppGraph.SurveyScreen> {
            SurveyScreen(
                windowState,
                soundPool,
                onBack = {
                    appNavController.navAndPopUp(AppGraph.HomeNavigation, AppGraph.SurveyScreen)
                },
            )
        }
        composable<AppGraph.HomeNavigation> {
            HomeNav(
                windowState,
                soundPool,
                onAuthNav = {
                    appNavController.navAndPopUp(
                        AppGraph.AuthNavigation,
                        AppGraph.HomeNavigation
                    )
                },
                onSettingsNav = {
                    appNavController.navigate(AppGraph.SettingsScreen)
                },
                onGameNav = {
                    game = it
                    appNavController.navigate(AppGraph.GameNavigation)
                }
            )
        }
        composable<AppGraph.GameNavigation> {
            GameNav(
                windowState,
                soundPool,
                onMusicChange,
                games[game],
                onOutGameNavigation = {
                    appNavController.navAndPopUp(
                        AppGraph.HomeNavigation,
                        AppGraph.GameNavigation
                    )
                },
            )
        }
    }
}

