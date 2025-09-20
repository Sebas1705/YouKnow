package es.sebas1705.game


import es.sebas1705.feature.games.R
import kotlinx.serialization.Serializable

/**
 * Sealed interface that represents the possible screens of the game.
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
interface GameScreens {
    @Serializable
    object MysteryNumberScreen : GameScreens

    @Serializable
    object QuizScreen : GameScreens

    @Serializable
    object WordPassScreen : GameScreens

    @Serializable
    object FamiliesScreen : GameScreens
}
