package es.sebas1705.common.utlis.extensions.types

import es.sebas1705.common.states.DataState
import es.sebas1705.common.utlis.alias.DataEmptyFlow
import es.sebas1705.common.utlis.alias.DataFlow
import kotlinx.coroutines.flow.first

/**
 * Catcher function to handle the different states of the response
 *
 * @receiver [DataFlow]: the response state
 *
 * @param onLoading () -> Unit: Function to handle the loading state
 * @param onSuccess (T) -> Unit: Function to handle the success state
 * @param onError (String) -> Unit: Function to handle the error state
 *
 * @see DataFlow
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
suspend fun <T> DataFlow<T>.collect(
    onLoading: suspend () -> Unit = {},
    onSuccess: suspend (T) -> Unit = {},
    onError: suspend (String) -> Unit = {}
) = this.collect { data ->
    data.catcher(
        onLoading = onLoading,
        onSuccess = onSuccess,
        onError = { onError(it.message) }
    )
}

/**
 * Catcher function to handle the different states of the response
 *
 * @receiver [DataEmptyFlow]: the response state
 *
 * @param onLoading () -> Unit: Function to handle the loading state
 * @param onEmptySuccess () -> Unit: Function to handle the empty success state
 * @param onError (String) -> Unit: Function to handle the error state
 *
 * @see DataEmptyFlow
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
suspend fun DataEmptyFlow.collect(
    onLoading: suspend () -> Unit = {},
    onEmptySuccess: suspend () -> Unit = {},
    onError: suspend (String) -> Unit = {}
) = this.collect { data ->
    data.catcher(
        onLoading = onLoading,
        onEmptySuccess = onEmptySuccess,
        onError = { onError(it.message) }
    )
}

/**
 * Waits for the first state that is not [DataState.Loading]: the outcome of the operation, for
 * callers that run several operations in sequence and must look at each result.
 *
 * @receiver [DataFlow]: the response state (a [DataEmptyFlow] is a `DataFlow<Nothing>`)
 *
 * @return [DataState]: [DataState.Success], [DataState.EmptySuccess] or [DataState.Error]
 *
 * @since 1.6.0
 * @author Sebas1705 11/11/2026
 */
suspend fun <T> DataFlow<T>.awaitResult(): DataState<T> = first { it !is DataState.Loading }
