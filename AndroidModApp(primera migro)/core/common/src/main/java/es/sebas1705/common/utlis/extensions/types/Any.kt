package es.sebas1705.common.utlis.extensions.types
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
 */

import android.util.Log
import es.sebas1705.common.managers.LogType

/**
 * Extension function to log messages
 *
 * @receiver [Any]: the class that calls the function
 *
 * @param message [String]: the message to log
 * @param logType [LogType]: the type of log
 *
 * @see LogType
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
fun Any.log(
    message: String,
    logType: LogType
) {
    when (logType) {
        LogType.INFO -> Log.i(this::class.java.simpleName, message)
        LogType.DEBUG -> Log.d(this::class.java.simpleName, message)
        LogType.ERROR -> Log.e(this::class.java.simpleName, message)
        LogType.WARNING -> Log.w(this::class.java.simpleName, message)
    }
}