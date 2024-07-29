package es.sebas1705.youknowapp.presentation.navigation

sealed class Route(
    val route: String
) {

    data object OnBoardingScreen : Route("boarding")
    data object HomeScreen : Route("home")

}