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
import android.content.Context
import androidx.compose.ui.unit.Dp

/**
 * Transform a [Int] number to a [Dp] number using the context
 * to get the density of the screen in [Dp]
 *
 * @receiver [Int]: the number in px
 *
 * @param context [Context]: context of the app
 *
 * @return [Dp]: the number in [Dp]
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
fun Int.toDp(context: Context): Dp {
    val density = context.resources.displayMetrics.density
    return Dp(this / density)
}

/**
 * Reduce the number to a string with a letter at the end
 *
 * @receiver [Int]: the number to reduce
 *
 * @return [String]: the number reduced
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@SuppressLint("DefaultLocale")
fun Int.toReducedString(): String {
    return when {
        this >= 1_000_000 -> String.format("%.1fM", this / 1_000_000.0)
        this >= 1_000 -> String.format("%.1fk", this / 1_000.0)
        else -> this.toString()
    }
}
