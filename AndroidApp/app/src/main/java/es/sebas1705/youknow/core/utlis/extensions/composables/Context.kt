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
import android.widget.Toast
import es.sebas1705.youknow.R
import es.sebas1705.youknow.domain.model.PageModel

/**
 * Generate a list of [PageModel] to use in the guide
 *
 * @receiver [Context]: context of the app
 *
 * @return [List]<[PageModel]>: list of pages
 *
 * @see PageModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
fun Context.generateGuidePages() = listOf(
    PageModel(
        this.getString(R.string.titlePage1),
        this.getString(R.string.desPage1),
        R.drawable.back_boarding
    ),
    PageModel(
        this.getString(R.string.titlePage2),
        this.getString(R.string.desPage2),
        R.drawable.back_boarding
    ),
    PageModel(
        this.getString(R.string.titlePage3),
        this.getString(R.string.desPage3),
        R.drawable.back_boarding
    )
)

/**
 * Print a text generating a [Toast]
 *
 * @receiver [Context]: context of the app
 *
 * @param message [String]: the message to print
 *
 * @see Toast
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
fun Context.printTextInToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}