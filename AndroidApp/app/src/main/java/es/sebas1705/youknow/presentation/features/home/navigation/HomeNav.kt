package es.sebas1705.youknow.presentation.features.home.navigation
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.texts.Title
import es.sebas1705.youknow.core.utlis.extensions.composables.navToTab
import es.sebas1705.youknow.presentation.features.home.features.chat.ChatScreen
import es.sebas1705.youknow.presentation.features.home.features.groups.GroupsScreen
import es.sebas1705.youknow.presentation.features.home.features.main.MainScreen
import es.sebas1705.youknow.presentation.features.home.features.play.PlayScreen
import es.sebas1705.youknow.presentation.features.home.features.profile.ProfileScreen
import es.sebas1705.youknow.presentation.features.home.navigation.HomeScreens.ChatScreen
import es.sebas1705.youknow.presentation.features.home.navigation.HomeScreens.GroupsScreen
import es.sebas1705.youknow.presentation.features.home.navigation.HomeScreens.MainScreen
import es.sebas1705.youknow.presentation.features.home.navigation.HomeScreens.PlayScreen
import es.sebas1705.youknow.presentation.features.home.navigation.HomeScreens.ProfileScreen
import es.sebas1705.youknow.presentation.features.home.navigation.composables.HomeBottomBar
import es.sebas1705.youknow.presentation.features.home.navigation.composables.HomeTopBar
import es.sebas1705.youknow.presentation.features.home.navigation.viewmodel.HomeIntent
import es.sebas1705.youknow.presentation.features.home.navigation.viewmodel.HomeViewModel

/**
 * Home Navigation Composable that will handle the navigation between the different screens of the app.
 *
 * @param windowState [WindowState]: The state of the window.
 * @param onAuthNav () -> Unit: Function that will navigate to the auth screen.
 * @param onSettingsNav () -> Unit: Function that will navigate to the settings screen.
 * @param onGameNav (GameScreens) -> Unit: Function that will navigate to the game screen.
 *
 * @see BackHandler
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun HomeNav(
    windowState: WindowState,
    onAuthNav: () -> Unit,
    onSettingsNav: () -> Unit,
    onGameNav: (Int) -> Unit
) {
    //viewmodel:
    val homeViewModel: HomeViewModel = hiltViewModel()

    //collecting states:
    val homeState by homeViewModel.uiState.collectAsStateWithLifecycle()

    //states:
    var selectedItem by rememberSaveable { mutableIntStateOf(2) }

    //navigation Controller:
    val navController = rememberNavController()

    homeState.userModel?.let { user ->
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                HomeBottomBar(
                    items = homes,
                    selectedItem = selectedItem,
                    onItemClick = {
                        selectedItem = it
                        navController.navToTab(homes[it].destination)
                    },
                )
            },
            topBar = {
                if (!windowState.isImeVisible)
                    HomeTopBar(
                        windowState,
                        user.points,
                        user.credits
                    )
            }
        ) {

            if (homeState.isLoading)
                LoadingDialog(windowState)

            NavHost(
                navController = navController,
                startDestination = MainScreen,
                modifier =
                if (windowState.isImeVisible)
                    Modifier.imePadding()
                else
                    Modifier.padding(it)
            ) {
                composable<MainScreen> {
                    MainScreen(
                        windowState,
                        onSettingsNav
                    )
                }
                composable<ProfileScreen> {
                    ProfileScreen(
                        windowState,
                        homeState,
                        onAuthNav
                    )
                }
                composable<ChatScreen> {
                    ChatScreen(
                        windowState,
                        homeState
                    )
                }
                composable<PlayScreen> {
                    PlayScreen(
                        windowState,
                        onGameNav
                    )
                }
                composable<GroupsScreen> {
                    GroupsScreen(
                        windowState,
                        homeState,
                        onUserInfoSearch = {
                            homeViewModel.eventHandler(HomeIntent.GetUser(it))
                        }
                    )
                }
            }
        }
    } ?: ApplyBack(backId = windowState.backFill) {
        Title(
            stringResource(R.string.user_not_found),
            modifier = Modifier.align(Alignment.Center)
        )
    }

}






