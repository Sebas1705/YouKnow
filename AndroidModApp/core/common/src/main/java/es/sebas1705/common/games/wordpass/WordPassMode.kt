package es.sebas1705.youknow.core.classes.enums.games.wordpass
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

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import es.sebas1705.youknow.R

/**
 * Enum class that represents the possible modes of the WordPass game.
 *
 * @param strRes [Int]: The string resource of the mode.
 * @param icon [ImageVector]: The icon of the mode.
 * @param numWords [Int]: The number of words that the mode will have.
 * @param multiPoints [Double]: The multiplier of the points of the mode.
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
enum class WordPassMode(
    val strRes: Int,
    val icon: ImageVector,
    val numWords: Int,
    val multiPoints: Double
) {
    SURVIVAL(R.string.survival, Icons.Filled.LocalFireDepartment, 100, 1.5),
    FIRE_WHEEL(R.string.fire_wheel, Icons.Filled.Settings, Letter.entries.size, 5.0),
    ALEATORY(R.string.aleatory, Icons.Filled.Casino, 20, 1.0)
}