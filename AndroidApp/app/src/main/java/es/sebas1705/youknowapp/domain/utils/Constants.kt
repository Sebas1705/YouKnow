package es.sebas1705.youknowapp.domain.utils

import androidx.compose.ui.res.stringResource
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.domain.model.Page
import es.sebas1705.youknowapp.domain.model.TriviaQuestion
import es.sebas1705.youknowapp.domain.model.TriviaResponse
import es.sebas1705.youknowapp.presentation.navigation.home.HomeRoutes

object Constants {

    const val USER_SETTINGS = "userSettings"

    const val APP_ENTRY = "appEntry"

    const val TRIVIA_BASE_URL = "https://opentdb.com/"
    const val TRIVIA_ENCODE_TEXT = "url3986"

    val TRIVIA_RESPONSE_EXAMPLE = TriviaResponse(
        0,
        mutableListOf<TriviaQuestion>().apply {
            repeat(10) {
                add(
                    TriviaQuestion(
                        "General Knowledge",
                        "hey",
                        "Easy",
                        listOf("1", "2", "3"),
                        "HelloWorld",
                        "URL"
                    )
                )
            }
        }
    )

    val HOME_ROUTES_LIST = listOf(
        HomeRoutes.ChatScreen,
        HomeRoutes.GameScreen,
        HomeRoutes.MainScreen,
        HomeRoutes.FriendsScreen,
        HomeRoutes.ProfileScreen
    )
}