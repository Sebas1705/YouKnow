package es.sebas1705.youknow.presentation.features.home.features.main.viewmodel
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

import es.sebas1705.youknow.core.classes.mvi.MVIBaseState
import es.sebas1705.youknow.domain.model.social.NewModel

/**
 * State of the [MainViewModel] that will handle the data of the screen.
 *
 * @param isLoading [Boolean]: Flag to indicate if the screen is loading.
 * @param news [List]<[NewModel]>: List of news.
 * @param ranking [List]<[Pair]<[String], [Int]>>: List of the ranking.
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
data class MainState(
    val isLoading: Boolean,
    val news: List<NewModel>,
    val ranking: List<Pair<String, Int>>,
) : MVIBaseState {
    companion object {

        /**
         * Default state of the [MainState].
         *
         * @return [MainState]: Default state.
         *
         * @since 1.0.0
         * @author Sebastián Ramiro Entrerrios García
         */
        fun default() = MainState(
            isLoading = false,
            news = emptyList(),
            ranking = emptyList()
        )
    }
}