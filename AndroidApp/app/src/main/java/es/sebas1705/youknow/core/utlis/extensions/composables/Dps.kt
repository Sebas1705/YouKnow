package es.sebas1705.youknow.core.utlis.extensions.composables
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

import android.content.Context
import androidx.compose.ui.unit.Dp

/**
 * Transform a [Dp] number to a [Int] number using the context
 * to get the density of the screen in [Dp]
 *
 * @receiver [Dp]: the number in [Dp]
 *
 * @param context [Context]: context of the app
 *
 * @return [Int]: the number in px
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
fun Dp.toPx(context: Context): Int {
    val density = context.resources.displayMetrics.density
    return (this.value * density).toInt()
}