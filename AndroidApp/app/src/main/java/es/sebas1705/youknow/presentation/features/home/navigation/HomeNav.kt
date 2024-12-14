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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.utlis.navToTab
import es.sebas1705.youknow.presentation.composables.rememberWindowState
import es.sebas1705.youknow.presentation.features.app.windows.LoadingWindow
import es.sebas1705.youknow.presentation.features.home.composables.BottomNavigationItem
import es.sebas1705.youknow.presentation.features.home.composables.HomeBottomNavigationBar
import es.sebas1705.youknow.presentation.features.home.screens.InfoScreen
import es.sebas1705.youknow.presentation.features.home.screens.MainScreen
import es.sebas1705.youknow.presentation.features.home.screens.PlayScreen
import es.sebas1705.youknow.presentation.features.home.screens.ProfileScreen
import es.sebas1705.youknow.presentation.features.home.screens.social.SocialScreen
import es.sebas1705.youknow.presentation.features.home.viewmodels.SocialIntent
import es.sebas1705.youknow.presentation.features.home.viewmodels.SocialViewModel
import es.sebas1705.youknow.presentation.features.home.viewmodels.UserViewModel
import es.sebas1705.youknow.presentation.features.home.windows.LogoutWindow

/**
 * Home Navigation Composable that will handle the navigation between the different screens of the app.
 * It will also handle the bottom navigation bar and the floating action button.
 * It will also handle the back button to avoid the user to go back to the splash screen.
 * It will also handle the logout alert dialog.
 *
 * @param onLogOutNavigation () -> Unit: Function that will navigate to the logout screen.
 * @param onSettingsNavigation () -> Unit: Function that will navigate to the settings screen.
 *
 * @see BackHandler
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun HomeNav(
    windowState: WindowState,
    onLogOutNavigation: () -> Unit,
    onSettingsNavigation: () -> Unit
) {

    var alertDisplay by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val bottomNavigationItems by remember {
        derivedStateOf {
            listOf(
                BottomNavigationItem(
                    icon = Icons.Default.Public,
                    label = context.getString(R.string.Social),
                    route = SocialScreen
                ),
                BottomNavigationItem(
                    icon = Icons.Default.Person,
                    label = context.getString(R.string.Profile),
                    route = ProfileScreen
                ),
                BottomNavigationItem(
                    icon = Icons.Default.Home,
                    label = context.getString(R.string.Main),
                    route = MainScreen
                ),
                BottomNavigationItem(
                    icon = Icons.Default.SportsEsports,
                    label = context.getString(R.string.Play),
                    route = PlayScreen
                ),
                BottomNavigationItem(
                    icon = Icons.Default.Info,
                    label = context.getString(R.string.Info),
                    route = InfoScreen
                )
            )
        }
    }

    val userViewModel: UserViewModel = hiltViewModel()
    val userState by userViewModel.uiState.collectAsStateWithLifecycle()
    val socialViewModel: SocialViewModel = hiltViewModel()
    val socialState by socialViewModel.uiState.collectAsStateWithLifecycle()
    val navController = rememberNavController()
    val windowState by rememberWindowState()
    var selectedItem by remember { mutableIntStateOf(2) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            HomeBottomNavigationBar(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { newItem, lastItem ->
                    if (newItem != lastItem) {
                        if (bottomNavigationItems[lastItem].route == SocialScreen)
                            socialViewModel.eventHandler(SocialIntent.ClearSocial)
                        if (bottomNavigationItems[newItem].route == SocialScreen)
                            socialViewModel.eventHandler(SocialIntent.LoadSocial(userState.userModel!!.groupId))
                        navController.navToTab(bottomNavigationItems[newItem].route)
                        selectedItem = newItem
                    }
                },
            )
        },
        floatingActionButton = {
            if (selectedItem == 2)
                SmallFloatingActionButton(
                    onClick = onSettingsNavigation,
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = stringResource(R.string.settings_title)
                    )
                }
            else if (selectedItem == 1)
                SmallFloatingActionButton(
                    onClick = { alertDisplay = true },
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Logout,
                        contentDescription = stringResource(R.string.logout)
                    )
                }
        },
    ) {


        if (alertDisplay) {
            LogoutWindow(
                modifier = Modifier.padding(it),
                onConfirmButton = {
                    onLogOutNavigation()
                },
                onDismissAction = {
                    alertDisplay = false
                }
            )
        }

        if (userState.isLoading || socialState.isLoading)
            LoadingWindow(windowState)

        NavHost(
            navController = navController,
            startDestination = MainScreen,
            modifier = if (windowState.isImeVisible) Modifier.imePadding() else Modifier.padding(it)
        ) {
            composable<MainScreen> {
                MainScreen(windowState, userState, userViewModel)
            }
            composable<ProfileScreen> {
                ProfileScreen(windowState, userState, userViewModel)
            }
            composable<SocialScreen> {
                SocialScreen(windowState, userState, socialState, userViewModel, socialViewModel)
            }
            composable<PlayScreen> {
                PlayScreen(windowState, userState, userViewModel)
            }
            composable<InfoScreen> {
                InfoScreen(windowState, userState, userViewModel)
            }
        }
    }

}






