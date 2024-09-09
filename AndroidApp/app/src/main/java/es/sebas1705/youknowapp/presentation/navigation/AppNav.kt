package es.sebas1705.youknowapp.presentation.navigation
 
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.sebas1705.youknowapp.presentation.screens.auth.LogScreen
import es.sebas1705.youknowapp.presentation.screens.auth.AuthScreen
import es.sebas1705.youknowapp.presentation.screens.prelog.GuideScreen
import es.sebas1705.youknowapp.presentation.screens.auth.SignScreen
import es.sebas1705.youknowapp.presentation.screens.game.TriviaScreen
import es.sebas1705.youknowapp.presentation.screens.home.HomeScreen

@Composable
fun AppNav(
    startDestination: String
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(AppRoutes.GuideScreen.route) {
            GuideScreen(navController)
        }
        composable(AppRoutes.TriviaScreen.route) {
            TriviaScreen(navController)
        }
        composable(AppRoutes.AuthScreen.route){
            AuthScreen(navController)
        }
        composable(AppRoutes.LogScreen.route){
            LogScreen(navController)
        }
        composable(AppRoutes.SignScreen.route){
            SignScreen(navController)
        }
        composable(AppRoutes.HomeScreen.route){
            HomeScreen(navController)
        }
    }
}
