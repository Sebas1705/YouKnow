package es.sebas1705.youknow.domain.usecases.games

import es.sebas1705.youknow.core.classes.enums.Difficulty
import es.sebas1705.youknow.domain.model.games.NumberModel

class GenerateRandomNumber {
    operator fun invoke(
        difficulty: Difficulty,
        onLoading: () -> Unit,
        onSuccess: (NumberModel) -> Unit,
        onError: (String) -> Unit
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

data class MysteryNumberUsesCases(
    val generateRandomNumber: GenerateRandomNumber
)