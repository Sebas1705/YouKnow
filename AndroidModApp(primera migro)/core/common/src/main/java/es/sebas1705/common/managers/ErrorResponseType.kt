package es.sebas1705.common.managers
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

/**
 * Sealed class to represent the type of the error response
 *
 * @param tag [String]: Tag of the error
 *
 * @property BadRequest [ErrorResponseType]: Bad request error
 * @property InternalError [ErrorResponseType]: Internal error
 * @property NotFound [ErrorResponseType]: Not found error
 * @property Unauthorized [ErrorResponseType]: Unauthorized error
 * @property Forbidden [ErrorResponseType]: Forbidden error
 * @property Conflict [ErrorResponseType]: Conflict error
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed class ErrorResponseType(val tag: String) {
    data object BadRequest : ErrorResponseType("Bad request error")
    data object InternalError : ErrorResponseType("Internal error")
    data object NotFound : ErrorResponseType("Not found error")
    data object Unauthorized : ErrorResponseType("Unauthorized error")
    data object Forbidden : ErrorResponseType("Forbidden error")
    data object Conflict : ErrorResponseType("Conflict error")
}
