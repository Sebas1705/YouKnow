package es.sebas1705.game.families.viewmodel
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

import es.sebas1705.common.games.Languages
import es.sebas1705.common.games.families.FamiliesMode
import es.sebas1705.common.games.families.FamiliesStatus
import es.sebas1705.common.mvi.MVIBaseState
import es.sebas1705.models.games.FamiliesModel

/**
 * Data class that represents the state of the Families Screen.
 *
 * @param isLoading [Boolean]: Flag that indicates if the screen is loading.
 * @param numFamily [Int]: Number of families to play.
 * @param actualFamily [Int]: Actual family.
 * @param points [Int]: Points of the user.
 * @param correctAnswers [Int]: Correct answers of the user.
 * @param lives [Int]: Lives of the user.
 * @param families [List]<[FamiliesModel]>: List of families.
 * @param status [FamiliesStatus]: Status of the game.
 * @param mode [FamiliesMode]?: Mode of the game.
 * @param languages [Languages]: Language of the game.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class FamiliesState(
    var isLoading: Boolean,
    var numFamily: Int,
    var actualFamily: Int,
    var points: Int,
    var correctAnswers: Int,
    var lives: Int,
    var families: List<FamiliesModel>,
    var status: FamiliesStatus,
    var mode: FamiliesMode?,
    var languages: Languages
) : MVIBaseState {

    companion object {
        /**
         * Default state of the Families Screen.
         *
         * @return [FamiliesState]: Default state of the Families Screen.
         *
         * @since 1.0.0
         * @author Sebastián Ramiro Entrerrios García
         */
        fun default() = FamiliesState(
            isLoading = false,
            numFamily = 0,
            actualFamily = 0,
            points = 0,
            correctAnswers = 0,
            lives = 3,
            families = emptyList(),
            status = FamiliesStatus.SELECTION_MODE,
            mode = null,
            languages = Languages.ANY
        )
    }
}