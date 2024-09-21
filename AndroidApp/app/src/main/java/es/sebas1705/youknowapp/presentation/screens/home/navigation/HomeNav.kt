package es.sebas1705.youknowapp.presentation.screens.home.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.common.navToTab
import es.sebas1705.youknowapp.presentation.screens.home.common.BottomNavigationItem
import es.sebas1705.youknowapp.presentation.screens.home.common.HomeNavigationBar
import es.sebas1705.youknowapp.presentation.screens.home.common.HomeTopBar
import es.sebas1705.youknowapp.presentation.screens.home.screens.info.InfoScreen
import es.sebas1705.youknowapp.presentation.screens.home.screens.main.MainScreen
import es.sebas1705.youknowapp.presentation.screens.home.screens.play.PlayScreen
import es.sebas1705.youknowapp.presentation.screens.home.screens.profile.ProfileScreen
import es.sebas1705.youknowapp.presentation.screens.home.screens.social.SocialScreen
import es.sebas1705.youknowapp.presentation.screens.home.windows.LogoutWindow

@Composable
fun HomeNav(
    onLogOutNavigation: () -> Unit,
    onSettingsNavigation: () -> Unit
) {

    var alertDisplay by remember { mutableStateOf(false) }
    BackHandler(onBack = {})
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

    val navController = rememberNavController()
    var selectedItem by remember { mutableIntStateOf(bottomNavigationItems.size / 2) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            HomeNavigationBar(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = {
                    navController.navToTab(bottomNavigationItems[it].route)
                    selectedItem = it
                },
            )
        },
        topBar = {
            HomeTopBar(
                title = bottomNavigationItems[selectedItem].label,
                onLogoutButton = { alertDisplay = true },
                onSettingsButton = onSettingsNavigation
            )
        }
    ) {

        if (alertDisplay) {
            LogoutWindow(
                modifier = Modifier.padding(it),
                onLogout = {
                    onLogOutNavigation()
                },
                onCancel = {
                    alertDisplay = false
                }
            )
        }

        NavHost(
            navController = navController,
            startDestination = MainScreen,
            modifier = Modifier.padding(it)
        ) {
            composable<MainScreen> {
                MainScreen()
            }
            composable<ProfileScreen> {
                ProfileScreen()
            }
            composable<SocialScreen> {
                SocialScreen()
            }
            composable<PlayScreen> {
                PlayScreen()
            }
            composable<InfoScreen> {
                InfoScreen()
            }
        }
    }

}






