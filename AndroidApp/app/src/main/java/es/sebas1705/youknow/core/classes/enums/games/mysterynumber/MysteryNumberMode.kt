package es.sebas1705.youknow.core.classes.enums.games.mysterynumber
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
import androidx.compose.material.icons.filled.DashboardCustomize
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.ui.graphics.vector.ImageVector
import es.sebas1705.youknow.R

/**
 * Enum class that represents the modes of the Mystery Number game.
 *
 * @param strRes [Int]: String resource of the mode.
 * @param icon [ImageVector]: Icon of the mode.
 * @param lives [Int]: Lives of the mode.
 * @param multiPoints [Double]: Multiplier of the points.
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
enum class MysteryNumberMode(
    val strRes: Int,
    val icon: ImageVector,
    val lives: Int,
    val multiPoints: Double
) {
    TIME_ATTACK(R.string.time_attack, Icons.Filled.HourglassBottom, 100, 1.2),
    ALEATORY(R.string.aleatory, Icons.Filled.Casino, 20, 1.0),
    CUSTOM(R.string.custom, Icons.Filled.DashboardCustomize, 20, 1.0),
}