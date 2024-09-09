package es.sebas1705.youknowapp.presentation.navigation

sealed class AppRoutes(
    val route: String
) {

    data object GuideScreen : AppRoutes("guide")
    data object TriviaScreen : AppRoutes("trivia")
    data object AuthScreen : AppRoutes("auth")
    data object LogScreen : AppRoutes("log")
    data object SignScreen : AppRoutes("sign")
    data object HomeScreen : AppRoutes("home")

}