package es.sebas1705.youknow.core.classes.enums.games
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

/**
 * Enum class for the difficulty of the game.
 *
 * @property id [String?]: Difficulty id
 * @property strRes [Int]: Difficulty string resource
 * @property points [Int]: Difficulty points
 * @property maxMysteryNumber [Int]: Difficulty max mystery number
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
enum class Difficulty(
    val id: String?,
    val strRes: Int,
    val points: Int,
    val maxMysteryNumber: Int
) {
    ANY(null, R.string.any, 10, 0),
    EASY("easy", R.string.dif_easy, 5, 100),
    MEDIUM("medium", R.string.dif_medium, 10, 1_000),
    HARD("hard", R.string.dif_hard, 15, 1_000_000);

    companion object {
        fun getDifficulty(value: String): Difficulty = entries.find { it.id == value } ?: ANY
    }
}