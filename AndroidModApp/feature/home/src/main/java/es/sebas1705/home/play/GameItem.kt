package es.sebas1705.home.play
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

import es.sebas1705.youknow.feature.home.R

/**
 * Data class that represents a game item.
 *
 * @property strRes [Int]: The string resource of the game.
 * @property icon [Int]: The icon of the game.
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
data class GameItem(
    val strRes: Int,
    val icon: Int
) {
    companion object {
        val games = listOf(
            GameItem(R.string.feature_home_mystery_number, R.drawable.numbers),
            GameItem(R.string.feature_home_quiz, R.drawable.quiz),
            GameItem(R.string.feature_home_word_pass, R.drawable.wordpass),
            GameItem(R.string.feature_home_families, R.drawable.family),
        )
    }
}