package es.sebas1705.auth.screens.menu.viewmodel
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
import es.sebas1705.common.mvi.MVIBaseIntent

/**
 * Sealed interface that represents the possible actions that can be performed in the Menu Screen.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
sealed interface MenuIntent : MVIBaseIntent {

    data class SignWithGoogle(
        val context: Context,
        val onSuccess: () -> Unit
    ) : MenuIntent

}