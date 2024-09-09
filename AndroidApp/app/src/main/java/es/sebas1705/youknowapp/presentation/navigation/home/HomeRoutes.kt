package es.sebas1705.youknowapp.presentation.navigation.home

import es.sebas1705.youknowapp.R

sealed class HomeRoutes(
    val route: String,
    val icon: Int,
    val label: String
) {
    data object MainScreen : HomeRoutes("main", R.drawable.icon,"Main")
    data object ProfileScreen : HomeRoutes("profile", R.drawable.icon,"Profile")
    data object FriendsScreen : HomeRoutes("friends", R.drawable.icon,"Friends")
    data object GameScreen : HomeRoutes("game", R.drawable.icon,"Play")
    data object ChatScreen : HomeRoutes("chat", R.drawable.icon,"Chat")
}