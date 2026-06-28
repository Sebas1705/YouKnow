package es.sebas1705.common.states

import es.sebas1705.common.states.ErrorDataType.BAD_REQUEST
import es.sebas1705.common.states.ErrorDataType.CONFLICT
import es.sebas1705.common.states.ErrorDataType.FORBIDDEN
import es.sebas1705.common.states.ErrorDataType.INTERNAL
import es.sebas1705.common.states.ErrorDataType.NOT_FOUND
import es.sebas1705.common.states.ErrorDataType.UNAUTHORIZED


/**
 * Sealed class to represent the type of the error response
 *
 * @param tag [String]: Tag of the error
 *
 * @property BAD_REQUEST [ErrorDataType]: Bad request error
 * @property INTERNAL [ErrorDataType]: Internal error
 * @property NOT_FOUND [ErrorDataType]: Not found error
 * @property UNAUTHORIZED [ErrorDataType]: Unauthorized error
 * @property FORBIDDEN [ErrorDataType]: Forbidden error
 * @property CONFLICT [ErrorDataType]: Conflict error
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
enum class ErrorDataType(val tag: String) {
    BAD_REQUEST("Bad request error"),
    INTERNAL("Internal error"),
    NOT_FOUND("Not found error"),
    UNAUTHORIZED("Unauthorized error"),
    FORBIDDEN("Forbidden error"),
    CONFLICT("Conflict error");

    companion object {
        /**
         * Function to get the error response type from a throwable
         *
         * @param throwable [Throwable]: Throwable to get the error response type from
         *
         * @return [ErrorDataType]: Error response type
         *
         * @since 0.1.0
         * @author Sebas1705 09/09/2025
         */
        fun fromThrowable(throwable: Throwable): ErrorDataType {
            return when (throwable) {
                is IllegalArgumentException -> BAD_REQUEST
                is IllegalStateException -> CONFLICT
                is SecurityException -> FORBIDDEN
                is NullPointerException -> NOT_FOUND
                is IllegalAccessException -> UNAUTHORIZED
                else -> INTERNAL
            }
        }
    }
}