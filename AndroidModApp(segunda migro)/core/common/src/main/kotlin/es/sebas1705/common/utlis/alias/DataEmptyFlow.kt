package es.sebas1705.common.utlis.alias

import es.sebas1705.common.states.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Type alias to represent a flow of data with a Nothing type
 *
 * @see DataState
 * @see Flow
 */
typealias DataEmptyFlow = Flow<DataState<Nothing>>