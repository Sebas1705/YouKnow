package es.sebas1705.common.utlis.extensions.composables
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController

/**
 * Navigate to a route and pop up to a route
 *
 * @receiver [NavController]: the nav controller
 *
 * @param route [Any]: the route to navigate
 * @param popUpTo [Any]: the route to pop up
 *
 * @see NavController
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@SuppressLint("RestrictedApi")
fun NavHostController.navAndPopUp(route: Any, popUpTo: Any) {
    this.navigate(route) {
        popUpTo(popUpTo) {
            inclusive = true
        }
    }
}

/**
 * Navigate to a tab and pop up to the start destination
 *
 * @receiver [NavController]: the nav controller
 *
 * @param route [Any]: the route to navigate
 *
 * @see NavController
 * @see NavController.navigate
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
fun NavController.navToTab(route: Any) {
    this.navigate(route) {
        this@navToTab.graph.startDestinationRoute?.let {
            popUpTo(it) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}