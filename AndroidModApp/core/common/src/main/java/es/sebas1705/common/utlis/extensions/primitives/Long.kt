package es.sebas1705.common.utlis.extensions.primitives
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

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Format a long number to a date string
 *
 * @receiver [Long]: the number to format
 *
 * @return [String]: the date formatted
 *
 * @see DateTimeFormatter
 * @see ZoneId
 * @see Instant
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
fun Long.millisToFormatDate(): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyyy")
        .withZone(ZoneId.systemDefault())
    return formatter.format(Instant.ofEpochMilli(this))
}

/**
 * Convert a long number to seconds
 *
 * @receiver [Long]: the number to convert
 *
 * @return [Long]: the number converted to seconds
 *
 * @since 1.0.0
 */
fun Long.toSeconds(): Long = this / 1000