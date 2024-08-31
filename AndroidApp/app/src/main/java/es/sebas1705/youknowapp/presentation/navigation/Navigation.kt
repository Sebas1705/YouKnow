package es.sebas1705.youknowapp.presentation.navigation
 
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.sebas1705.youknowapp.presentation.screens.auth.LogScreen
import es.sebas1705.youknowapp.presentation.screens.auth.AuthScreen
import es.sebas1705.youknowapp.presentation.screens.prelog.OnBoardingScreen
import es.sebas1705.youknowapp.presentation.screens.auth.SignScreen
import es.sebas1705.youknowapp.presentation.screens.home.TriviaScreen
import es.sebas1705.youknowapp.presentation.viewmodel.OnBoardingViewModel

@Composable
fun AppNavigation(
    startDestination: String
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Route.OnBoardingScreen.route) {
            val viewModel: OnBoardingViewModel = hiltViewModel()
            OnBoardingScreen(
                navController = navController,
                event = viewModel::onEvent
            )
        }
        composable(Route.TriviaScreen.route) {
            TriviaScreen(navController)
        }
        composable(Route.AuthScreen.route){
            AuthScreen(navController)
        }
        composable(Route.LogScreen.route){
            LogScreen(navController)
        }
        composable(Route.SignScreen.route){
            SignScreen(navController)
        }
    }
}
