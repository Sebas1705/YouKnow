package es.sebas1705.common.utlis.alias

import es.sebas1705.common.states.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Type alias to represent a flow of data
 *
 * @param T: Type of the response
 *
 * @see DataState
 * @see Flow
 */
typealias DataFlow<T> = Flow<DataState<T>>