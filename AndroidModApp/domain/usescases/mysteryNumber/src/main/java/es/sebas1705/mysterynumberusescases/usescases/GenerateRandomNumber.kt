package es.sebas1705.mysterynumberusescases.usescases
/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import es.sebas1705.common.games.Difficulty
import es.sebas1705.models.games.NumberModel

/**
 * Use case to generate a random number
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
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