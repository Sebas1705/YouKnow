package es.sebas1705.youknow.domain.model

import es.sebas1705.youknow.data.firebase.analytics.config.ClassLogData

/**
 * Sealed class to represent the state of an process in domain layer
 *
 *
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed class ProcessState<out T> {

    /**
     * Catcher function to handle the different states of the response
     *
     * @param onLoading () -> Unit: Action to perform when the state is Loading
     * @param onCompleteWithData (T) -> Unit: Action to perform when the state is CompleteWithData
     * @param onComplete () -> Unit: Action to perform when the state is Complete
     * @param onError (Error) -> Unit: Action to perform when the state is Error
     *
     */
    fun catcher(
        onLoading: () -> Unit = {},
        onCompleteWithData: (T) -> Unit = {},
        onComplete: () -> Unit = {},
        onError: (Error) -> Unit = {}
    ){
        when(this){
            is Loading -> onLoading()
            is CompleteWithData -> onCompleteWithData(data)
            is Complete -> onComplete()
            is Error -> onError(this)
        }
    }

    /**
     * Loading state
     *
     * @see ProcessState
     */
    object Loading : ProcessState<Nothing>()

    /**
     * Success state
     *
     * @param data [T]: Data of the response
     *
     * @see ProcessState
     */
    data class CompleteWithData<out T>(
        val data: T
    ) : ProcessState<T>()

    /**
     * Empty success state
     *
     * @see ProcessState
     */
    object Complete: ProcessState<Nothing>()

    /**
     * Error state
     *
     * @param classLogData [ClassLogData]: Class to log the error
     * @param type [ErrorProcessType]: Type of the error
     * @param message [String]: Message of the error
     * @param loggerAction (ClassLogData, String) -> Unit: Function to log the error
     *
     * @see ProcessState
     */
    data class Error(
        val classLogData: ClassLogData,
        val type: ErrorProcessType,
        val message: String,
        private val loggerAction: (ClassLogData, String) -> Unit
    ): ProcessState<Nothing>(){
        init {
            loggerAction(classLogData, "${type.tag}: $message")
        }
    }
}