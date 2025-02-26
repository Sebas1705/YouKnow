package es.sebas1705.youknow.presentation.features.guide.viewmodel
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

/**
 * State for Guide Screen that will handle the first time the app is opened.
 *
 * @property isLoading [Boolean]: Flag that indicates if the screen is loading data.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class GuideState(
    val isLoading: Boolean
) : MVIBaseState {
    companion object {

        /**
         * Default state for Guide Screen.
         *
         * @return [GuideState]
         *
         * @since 1.0.0
         * @author Sebastián Ramiro Entrerrios García
         */
        fun default() = GuideState(
            isLoading = false
        )
    }
}