package es.sebas1705.youknow.domain.model
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
 * Sealed class to represent the type of error that can be thrown in the process
 *
 * @param tag [String]: Tag of the error
 *
 * @property BadParams [ErrorProcessType]: Error when the params are bad
 * @property InternalError [ErrorProcessType]: Error when there is an internal error
 * @property MissingData [ErrorProcessType]: Error when there is missing data
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed class ErrorProcessType(val tag: String){
    object BadParams: ErrorProcessType("Bad params error")
    object InternalError: ErrorProcessType("Internal error")
    object MissingData: ErrorProcessType("Missing data error")
}