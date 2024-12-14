package es.sebas1705.youknow.data.model
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

import es.sebas1705.youknow.data.firebase.analytics.config.ClassLogData


/**
 * Sealed class to represent the state of the data
 *
 * @param T: Type of the response
 *
 * @property Loading [ResponseState]: Loading state
 * @property Success [ResponseState]: Success state
 * @property EmptySuccess [ResponseState]: Empty success state
 * @property Error [ResponseState]: Error state
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed class ResponseState<out T> {

    /**
     * Catcher function to handle the different states of the response
     *
     * @param onLoading () -> Unit: Function to handle the loading state
     * @param onSuccess (T) -> Unit: Function to handle the success state
     * @param onEmptySuccess () -> Unit: Function to handle the empty success state
     * @param onError (Error) -> Unit: Function to handle the error state
     */
    suspend fun catcher(
        onLoading: suspend () -> Unit = {},
        onSuccess: suspend (T) -> Unit = {},
        onEmptySuccess: suspend () -> Unit = {},
        onError: suspend (Error) -> Unit = {}
    ) {
        when (this) {
            is Loading -> onLoading()
            is Success -> onSuccess(data)
            is EmptySuccess -> onEmptySuccess()
            is Error -> onError(this)
        }
    }

    /**
     * Loading state
     *
     * @see ResponseState
     */
    object Loading : ResponseState<Nothing>()

    /**
     * Success state
     *
     * @param data [T]: Data of the response
     *
     * @see ResponseState
     */
    data class Success<out T>(
        val data: T
    ) : ResponseState<T>()

    /**
     * Empty success state
     *
     * @see ResponseState
     */
    object EmptySuccess : ResponseState<Nothing>()

    /**
     * Error state
     *
     * @param classLogData [ClassLogData]: Class to log the error
     * @param type [ErrorResponseType]: Type of the error
     * @param message [String]: Message of the error
     * @param loggerAction (ClassLogData, String) -> Unit: Function to log the error
     *
     * @see ResponseState
     */
    data class Error(
        val classLogData: ClassLogData,
        val type: ErrorResponseType,
        val message: String,
        private val loggerAction: (ClassLogData, String) -> Unit
    ) : ResponseState<Nothing>() {
        init {
            loggerAction(classLogData, "${type.tag}: $message")
        }
    }
}