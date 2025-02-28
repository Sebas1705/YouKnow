package es.sebas1705.common.games.families
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
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Enum class that represents the modes of the Families game.
 *
 * @param strRes [Int]: String resource of the mode.
 * @param icon [ImageVector]: Icon of the mode.
 * @param numFamilies [Int]: Number of families in the mode.
 * @param multiPoints [Double]: Multiplier of the points in the mode.
 *
 * @since 1.0.0
 * @Author Sebastián Ramiro Entrerrios García
 */
enum class FamiliesMode(
    val strRes: Int,
    val icon: ImageVector,
    val numFamilies: Int,
    val multiPoints: Double
) {
    SURVIVAL(es.iberext.youknow.core.resources.R.string.core_resources_survival, Icons.Filled.LocalFireDepartment, 100, 1.5),
    TIME_ATTACK(es.iberext.youknow.core.resources.R.string.core_resources_survival, Icons.Filled.HourglassBottom, 20, 1.2),
    ALEATORY(es.iberext.youknow.core.resources.R.string.core_resources_survival, Icons.Filled.Casino, 10, 1.0),
    CUSTOM(es.iberext.youknow.core.resources.R.string.core_resources_survival, Icons.Filled.DashboardCustomize, 0, 0.75)
}