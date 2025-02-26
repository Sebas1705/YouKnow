package es.sebas1705.game.mysterynumber.viewmodel
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
import es.sebas1705.common.games.mysterynumber.MysteryNumberMode
import es.sebas1705.common.games.mysterynumber.MysteryNumberStatus
import es.sebas1705.common.mvi.MVIBaseState
import es.sebas1705.models.games.NumberModel

/**
 * Data class that represents the state of the MysteryNumber Screen.
 *
 * @param isLoading [Boolean]: Flag that indicates if the screen is loading.
 * @param points [Int]: Points obtained in the game.
 * @param lives [Int]: Number of lives.
 * @param timeRemaining [Float]: Time remaining in the game.
 * @param numberModel [NumberModel]: Number to guess.
 * @param status [MysteryNumberStatus]: Status of the game.
 * @param mode [MysteryNumberMode]: Mode of the game.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class MysteryNumberState(
    var isLoading: Boolean,
    var points: Int,
    var lives: Int,
    var timeRemaining: Float,
    var numberModel: NumberModel,
    var status: MysteryNumberStatus,
    var mode: MysteryNumberMode?
) : MVIBaseState {

    companion object {
        /**
         * Default state of the MysteryNumber Screen.
         *
         * @return [MysteryNumberState]: Default state of the MysteryNumber Screen.
         *
         * @since 1.0.0
         * @author Sebastián Ramiro Entrerrios García
         */
        fun default() = MysteryNumberState(
            isLoading = false,
            points = 0,
            lives = 20,
            timeRemaining = 0f,
            numberModel = NumberModel(-1, Difficulty.ANY),
            status = MysteryNumberStatus.SELECTION_MODE,
            mode = null
        )
    }
}