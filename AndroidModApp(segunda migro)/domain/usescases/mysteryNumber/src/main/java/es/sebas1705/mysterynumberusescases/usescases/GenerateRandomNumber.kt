package es.sebas1705.mysterynumberusescases.usescases

import es.sebas1705.models.games.NumberModel
import es.sebas1705.resources.games.Difficulty

/**
 * Use case to generate a random number
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class GenerateRandomNumber {
    operator fun invoke(
        difficulty: Difficulty,
        onLoading: () -> Unit,
        onSuccess: (NumberModel) -> Unit,
    ) {
        onLoading()
        var difficultyTemp = difficulty
        if (difficultyTemp == Difficulty.ANY) {
            do {
                difficultyTemp = Difficulty.entries.random()
            } while (difficultyTemp == Difficulty.ANY)
        }
        val number = (1..difficultyTemp.maxMysteryNumber).random()
        onSuccess(
            NumberModel(
                number = number,
                difficulty = difficultyTemp
            )
        )
    }
}