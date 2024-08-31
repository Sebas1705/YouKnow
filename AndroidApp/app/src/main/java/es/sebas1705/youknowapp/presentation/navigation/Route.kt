package es.sebas1705.youknowapp.presentation.navigation

sealed class Route(
    val route: String
) {

    data object OnBoardingScreen : Route("boarding")
    data object TriviaScreen : Route("trivia")
    data object AuthScreen : Route("auth")
    data object LogScreen : Route("log")
    data object SignScreen : Route("sign")

}