package es.sebas1705.youknow.presentation.ui.classes
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

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Enum class to define the size of the width and height of the window
 *
 * @property COMPACT for width less than 600dp or height less than 600dp
 * @property MEDIUM for width less than 840dp
 * @property EXPANDED for width greater than 840dp
 *
 * @property WIDTH_COMPACT_MEDIUM_LIMIT [Dp]: limit for the compact and medium size type of the width
 * @property WIDTH_MEDIUM_EXPANDED_LIMIT [Dp]: limit for the medium and expanded size type of the width
 * @property HEIGHT_COMPACT_MEDIUM_LIMIT [Dp]: limit for the compact and medium size type of the height
 * @property HEIGHT_MEDIUM_EXPANDED_LIMIT [Dp]: limit for the medium and expanded size type of the height
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
enum class SizeType {
    COMPACT,
    MEDIUM,
    EXPANDED;

    companion object {

        val WIDTH_COMPACT_MEDIUM_LIMIT = 600.dp
        val WIDTH_MEDIUM_EXPANDED_LIMIT = 840.dp

        /**
         * Transform the size in dp on a [SizeType] for the width
         *
         * @param size [Dp]: size on dp of the width of the window
         * @return [SizeType]: the size type of the width
         */
        fun getWidth(size: Dp): SizeType {
            return when {
                size < WIDTH_COMPACT_MEDIUM_LIMIT -> COMPACT
                size < WIDTH_MEDIUM_EXPANDED_LIMIT -> MEDIUM
                else -> EXPANDED
            }
        }

        val HEIGHT_COMPACT_MEDIUM_LIMIT = 480.dp
        val HEIGHT_MEDIUM_EXPANDED_LIMIT = 900.dp

        /**
         * Transform the size in dp on a [SizeType] for the height
         *
         * @param size [Dp]: size on dp of the height of the window
         * @return [SizeType]: the size type of the height
         */
        fun getHeight(size: Dp): SizeType {
            return when {
                size < HEIGHT_COMPACT_MEDIUM_LIMIT -> COMPACT
                size < HEIGHT_MEDIUM_EXPANDED_LIMIT -> MEDIUM
                else -> EXPANDED
            }
        }
    }

    /**
     * Filter the value based on the size type
     *
     * @param compactOpt [T]: the value for the compact size type
     * @param mediumOpt [T]: the value for the medium size type
     * @param expandedOpt [T]: the value for the expanded size type
     * @return [T] the value based on the size type
     */
    fun <T> filter(
        compactOpt: T,
        mediumOpt: T,
        expandedOpt: T
    ): T {
        return when (this) {
            COMPACT -> compactOpt
            MEDIUM -> mediumOpt
            EXPANDED -> expandedOpt
        }
    }
}