package es.sebas1705.youknowapp.presentation.screens.auth.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.presentation.common.customs.ApplyBack
import es.sebas1705.youknowapp.presentation.screens.auth.screens.LogScreen
import es.sebas1705.youknowapp.presentation.screens.auth.screens.MenuScreen
import es.sebas1705.youknowapp.presentation.screens.auth.screens.SignScreen

@Composable
fun AuthNav(
    onHomeNavigation: () -> Unit,
) {

    val authNavController = rememberNavController()

    ApplyBack(
        backId = R.drawable.back_fill
    ) {
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = authNavController,
            startDestination = MenuScreen
        ) {
            composable<MenuScreen> {
                MenuScreen(
                    onSignNavigation = {
                        authNavController.navigate(SignScreen)
                    },
                    onHomeNavigation = onHomeNavigation,
                    onLogNavigation = {
                        authNavController.navigate(LogScreen)
                    }
                )
            }
            composable<LogScreen> {
                LogScreen(onAuthSuccessNavigation = onHomeNavigation)
            }
            composable<SignScreen> {
                SignScreen(
                    onSignSuccessNavigation = {
                        authNavController.navigate(LogScreen)
                    }
                )
            }
        }
    }
}