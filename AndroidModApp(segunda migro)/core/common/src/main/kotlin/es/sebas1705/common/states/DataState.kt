package es.sebas1705.common.states

import kotlin.reflect.KClass

/**
 * Sealed class to represent the state of the data
 *
 * @param T: Type of the response
 *
 * @property Loading [DataState]: Loading state
 * @property Success [DataState]: Success state
 * @property EmptySuccess [DataState]: Empty success state
 * @property Error [DataState]: Error state
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
sealed class DataState<out T> {

    /**
     * Catcher function to handle the different states of the response
     *
     * @param onLoading () -> Unit: Function to handle the loading state
     * @param onSuccess (T) -> Unit: Function to handle the success state
     * @param onEmptySuccess () -> Unit: Function to handle the empty success state
     * @param onError (Error) -> Unit: Function to handle the error state
     *
     * @since 0.1.0
     * @author Sebas1705 09/09/2025
     */
    suspend fun catcher(
        onLoading: suspend () -> Unit = {},
        onSuccess: suspend (T) -> Unit = {},
        onEmptySuccess: suspend () -> Unit = {},
        onError: suspend (Error) -> Unit = {}
    ) = when (this) {
        is Loading -> onLoading()
        is Success -> onSuccess(data)
        is EmptySuccess -> onEmptySuccess()
        is Error -> onError(this)
    }

    /**
     * Loading state
     *
     * @since 0.1.0
     * @author Sebas1705 09/09/2025
     */
    data object Loading : DataState<Nothing>()

    /**
     * Success state
     *
     * @param data [T]: Data of the response
     *
     * @since 0.1.0
     * @author Sebas1705 09/09/2025
     */
    data class Success<out T>(
        val data: T
    ) : DataState<T>()

    /**
     * Empty success state
     *
     * @since 0.1.0
     * @author Sebas1705 09/09/2025
     */
    data object EmptySuccess : DataState<Nothing>()

    /**
     * Error state
     *
     * @param clazz [KClass<*>]: Class that produced the error
     * @param type [ErrorDataType]: Type of the error
     * @param message [String]: Message of the error
     * @param loggerAction (clazz: KClass<*>, message: String) -> Unit: Function to log the error
     *
     * @since 0.1.0
     * @author Sebas1705 09/09/2025
     */
    data class Error(
        val clazz: KClass<*>,
        val type: ErrorDataType,
        val message: String,
        private val loggerAction: (clazz: KClass<*>, message: String) -> Unit,
    ) : DataState<Nothing>() {
        init {
            loggerAction(clazz, "${type.tag}: $message")
        }
    }
}