package es.sebas1705.youknow.core.classes.enums.games.quiz
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
 * Enum class for quiz types
 *
 * @property id the id of the quiz type
 * @property strRes String resource
 * @property multiPoints the multiplier points
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
enum class QuizType(val id: String?, val strRes: Int, val multiPoints: Double) {
    ANY(null, R.string.any, 0.0),
    BOOLEAN("boolean", R.string.type_boolean, 0.5),
    MULTIPLE("multiple", R.string.type_multiple, 1.0);

    companion object {
        fun getType(id: String): QuizType = entries.find { it.id == id } ?: ANY
    }
}