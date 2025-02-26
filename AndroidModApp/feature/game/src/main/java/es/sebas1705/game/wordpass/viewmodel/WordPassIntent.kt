package es.sebas1705.youknow.presentation.features.game.features.wordpass.viewmodel
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

import es.sebas1705.youknow.core.classes.enums.games.Difficulty
import es.sebas1705.youknow.core.classes.enums.games.wordpass.WordPassMode
import es.sebas1705.youknow.core.classes.mvi.MVIBaseIntent

/**
 * Sealed interface that represents the possible intents of the WordPass Screen.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface WordPassIntent : MVIBaseIntent {

    data object ReadLanguages : WordPassIntent

    data object ResetGame : WordPassIntent

    data class GenerateGame(
        val difficulty: Difficulty,
        val wordPassMode: WordPassMode,
        val numWords: Int,
    ) : WordPassIntent

    data class SelectMode(
        val wordPassMode: WordPassMode
    ) : WordPassIntent

    data class Response(
        val response: String
    ) : WordPassIntent

    data class OutGame(
        val onSuccess: () -> Unit
    ) : WordPassIntent
}