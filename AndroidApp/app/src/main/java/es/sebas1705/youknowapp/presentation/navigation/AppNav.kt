package es.sebas1705.youknowapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.sebas1705.youknowapp.common.navAndPopUp
import es.sebas1705.youknowapp.presentation.screens.auth.navigation.AuthNav
import es.sebas1705.youknowapp.presentation.screens.game.TriviaScreen
import es.sebas1705.youknowapp.presentation.screens.guide.GuideScreen
import es.sebas1705.youknowapp.presentation.screens.settings.SettingsScreen
import es.sebas1705.youknowapp.presentation.screens.home.navigation.HomeNav
import es.sebas1705.youknowapp.presentation.screens.auth.viewmodel.AuthViewModel

@Composable
fun AppNav(
    startDestination: Any
) {
    val appNavController = rememberNavController()
    val authViewModel = hiltViewModel<AuthViewModel>()

    NavHost(navController = appNavController, startDestination = startDestination) {
        composable<GuideScreen> {
            GuideScreen(
                onSuccessNavigation = {
                    appNavController.navigate(
                        AuthNavigation
                    )
                }
            )
        }
        composable<TriviaScreen> {
            TriviaScreen(
                onSuccessLogOutNavigation = {
                    appNavController.navAndPopUp(AuthNavigation, TriviaScreen)
                },
                onErrorButton = {
                    appNavController.navigate(TriviaScreen)
                }
            )
        }
        composable<SettingsScreen> {
            SettingsScreen()
        }
        composable<AuthNavigation>{
            AuthNav(
                onHomeNavigation = {
                    appNavController.navAndPopUp(HomeNavigation,AuthNavigation)
                },
            )
        }
        composable<HomeNavigation> {
            HomeNav(
                onLogOutNavigation = {
                    authViewModel.signOut()
                    appNavController.navAndPopUp(AuthNavigation, HomeNavigation)
                },
                onSettingsNavigation = {
                    appNavController.navigate(SettingsScreen)
                },
            )
        }
    }
}

