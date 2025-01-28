package es.sebas1705.youknow.core.utlis.extensions.primitives
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

import android.annotation.SuppressLint

/**
 * Format a float number to a string with two decimal
 *
 * @receiver [Float]: the number to format
 *
 * @return [String]: the number formatted
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@SuppressLint("DefaultLocale")
fun Float.twoDecimalFormat(): String = String.format("%.2f", this)

/**
 * Format a float number to a string with two decimal and a percentage symbol
 *
 * @receiver [Float]: the number to format
 *
 * @return [String]: the number formatted
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@SuppressLint("DefaultLocale")
fun Float.percentageFormat(): String = String.format("%.2f", this * 100) + "%"

/**
 * Reverse a float number
 *
 * @receiver [Float]: the number to reverse
 *
 * @return [Float]: the number reversed
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
fun Float.reverseOne(): Float = 1 - this