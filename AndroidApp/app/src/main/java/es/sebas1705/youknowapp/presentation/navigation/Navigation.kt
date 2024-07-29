package es.sebas1705.youknowapp.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import es.sebas1705.youknowapp.presentation.screens.OnBoardingScreen
import es.sebas1705.youknowapp.presentation.screens.QuizzAppScreen
import es.sebas1705.youknowapp.presentation.viewmodel.OnBoardingViewModel

@Composable
fun AppNavigation(
    startDestination: String
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Route.OnBoardingScreen.route) {
            val viewModel: OnBoardingViewModel = hiltViewModel()
            OnBoardingScreen(navController, viewModel::onEvent)
        }
        composable(Route.HomeScreen.route) {
            QuizzAppScreen(modifier = Modifier.padding(16.dp))
        }
    }
}
