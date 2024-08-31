package es.sebas1705.youknowapp.domain.utils

import es.sebas1705.youknowapp.domain.model.TriviaQuestion
import es.sebas1705.youknowapp.domain.model.TriviaResponse

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
}