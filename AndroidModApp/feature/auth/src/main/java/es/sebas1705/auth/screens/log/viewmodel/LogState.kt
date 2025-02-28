package es.sebas1705.auth.screens.log.viewmodel
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

import es.sebas1705.common.mvi.MVIBaseState

/**
 * Data class that represents the state of the Log Screen.
 *
 * @param isLoading [Boolean]: Loading state of the Log Screen.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class LogState(
    val isLoading: Boolean
) : MVIBaseState {
    companion object {
        /**
         * Default state of the Log Screen.
         *
         * @return [LogState]: Default state of the Log Screen.
         *
         * @since 1.0.0
         * @Author Sebastián Ramiro Entrerrios García
         */
        fun default() = LogState(
            isLoading = false
        )
    }
}