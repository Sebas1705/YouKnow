package es.sebas1705.common.classes

import com.google.android.gms.tasks.Task
import es.sebas1705.common.states.DataState
import es.sebas1705.common.states.ErrorDataType
import es.sebas1705.common.utlis.alias.DataFlow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlin.reflect.KClass

/**
 * Manager class that handles Firebase Task flows and wraps results into [DataState].
 *
 * @property clazz [KClass]: The class that owns this TaskFlow (used for error logging).
 * @property genericFailMessage [String]: Message used when a Firebase Task fails.
 * @property genericExMessage [String]: Message used when an exception is thrown.
 * @property loggerAction ([KClass], [String]) -> Unit: Logger callback for errors.
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
class TaskFlow(
    private val clazz: KClass<*>,
    private val genericFailMessage: String,
    private val genericExMessage: String,
    private val loggerAction: (KClass<*>, String) -> Unit
) {

    /**
     * Wraps a Firebase [Task] operation into a [DataFlow] using callbackFlow.
     *
     * @param assertChecker Optional pre-check; returns an error message string on failure, null on success.
     * @param taskAction Suspending lambda that returns a Firebase [Task].
     * @param onSuccessListener Callback invoked when the [Task] completes successfully.
     * @param failureType Maps the exception of a failed [Task] to the [ErrorDataType] of the error
     * (default [ErrorDataType.BAD_REQUEST]), so callers can tell apart failures they must handle
     * differently, such as a rejected password.
     *
     * @return [DataFlow] emitting [DataState.Loading], then either the result or an error.
     *
     * @since 0.1.0
     */
    fun <T, Q> taskFlowProducer(
        assertChecker: suspend () -> String? = { null },
        taskAction: suspend () -> Task<Q>,
        onSuccessListener: (Q) -> DataState<T>,
        failureType: (Exception) -> ErrorDataType = { ErrorDataType.BAD_REQUEST }
    ): DataFlow<T> = callbackFlow {
        try {
            trySend(DataState.Loading)
            val assertError = assertChecker()
            if (assertError != null) {
                trySend(createResponse(ErrorDataType.BAD_REQUEST, assertError))
            } else {
                taskAction()
                    .addOnSuccessListener { result ->
                        trySend(guardedResult(result, onSuccessListener))
                        close()
                    }.addOnFailureListener { exception ->
                        trySend(
                            createResponse(
                                failureType(exception),
                                exception.message ?: genericFailMessage
                            )
                        )
                        close()
                    }
            }
        } catch (e: Exception) {
            trySend(createResponse(ErrorDataType.INTERNAL, e.message ?: genericExMessage))
            close()
        }
        awaitClose()
    }

    /**
     * Runs a success listener, which Firebase calls on the main thread outside the `try` of
     * [taskFlowProducer]. Anything it throws (a document that doesn't map onto its class, for
     * instance) would crash the app, so it becomes an error instead; the detail is logged, and the
     * caller gets [genericExMessage] so that mapper internals never reach the UI.
     */
    private fun <T, Q> guardedResult(
        result: Q,
        onSuccessListener: (Q) -> DataState<T>
    ): DataState<T> = try {
        onSuccessListener(result)
    } catch (e: Exception) {
        loggerAction(clazz, e.message ?: genericExMessage)
        createResponse(ErrorDataType.INTERNAL, genericExMessage)
    }

    /**
     * Creates a [DataState.Error] response.
     *
     * @param type [ErrorDataType]: The type of the error.
     * @param message [String]: The error message.
     *
     * @return [DataState.Error] wrapped in [DataState].
     *
     * @since 0.1.0
     */
    @Suppress("UNCHECKED_CAST")
    fun <T> createResponse(type: ErrorDataType, message: String): DataState<T> =
        DataState.Error(clazz, type, message, loggerAction) as DataState<T>
}
