package es.sebas1705.youknow.core.utlis.extensions.types
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

import es.sebas1705.youknow.core.utlis.alias.FlowResponse
import es.sebas1705.youknow.core.utlis.alias.FlowResponseNothing

/**
 * Catcher function to handle the different states of the response
 *
 * @receiver [FlowResponse]: the response state
 *
 * @param onLoading () -> Unit: Function to handle the loading state
 * @param onSuccess (T) -> Unit: Function to handle the success state
 * @param onError (String) -> Unit: Function to handle the error state
 *
 * @see FlowResponse
 */
suspend fun <T> FlowResponse<T>.catcher(
    onLoading: () -> Unit = {},
    onSuccess: (T) -> Unit = {},
    onError: (String) -> Unit = {}
) {
    this.collect {
        it.catcher(
            onLoading = onLoading,
            onSuccess = onSuccess,
            onError = { onError(it.message) }
        )
    }
}

/**
 * Catcher function to handle the different states of the response
 *
 * @receiver [FlowResponseNothing]: the response state
 *
 * @param onLoading () -> Unit: Function to handle the loading state
 * @param onEmptySuccess () -> Unit: Function to handle the empty success state
 * @param onError (String) -> Unit: Function to handle the error state
 *
 * @see FlowResponseNothing
 */
suspend fun FlowResponseNothing.catcher(
    onLoading: suspend () -> Unit = {},
    onEmptySuccess: suspend () -> Unit = {},
    onError: suspend (String) -> Unit = {}
) {
    this.collect {
        it.catcher(
            onLoading = onLoading,
            onEmptySuccess = onEmptySuccess,
            onError = { onError(it.message) }
        )
    }
}