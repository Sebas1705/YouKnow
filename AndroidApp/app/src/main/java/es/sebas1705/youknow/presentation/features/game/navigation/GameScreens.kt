package es.sebas1705.youknow.presentation.features.game.navigation
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

import es.sebas1705.youknow.R
import es.sebas1705.youknow.presentation.features.game.navigation.GameScreens.FamiliesScreen
import es.sebas1705.youknow.presentation.features.game.navigation.GameScreens.MysteryNumberScreen
import es.sebas1705.youknow.presentation.features.game.navigation.GameScreens.QuizScreen
import es.sebas1705.youknow.presentation.features.game.navigation.GameScreens.WordPassScreen
import kotlinx.serialization.Serializable

data class GameItem(
    val strRes: Int,
    val icon: Int,
    val destination: GameScreens
)

val games = listOf(
    GameItem(R.string.mystery_number, R.drawable.numbers, MysteryNumberScreen),
    GameItem(R.string.quiz, R.drawable.quiz, QuizScreen),
    GameItem(R.string.word_pass, R.drawable.wordpass, WordPassScreen),
    GameItem(R.string.families, R.drawable.family, FamiliesScreen),
)

interface GameScreens {
    @Serializable
    object MysteryNumberScreen: GameScreens

    @Serializable
    object QuizScreen : GameScreens

    @Serializable
    object WordPassScreen : GameScreens

    @Serializable
    object FamiliesScreen : GameScreens
}
