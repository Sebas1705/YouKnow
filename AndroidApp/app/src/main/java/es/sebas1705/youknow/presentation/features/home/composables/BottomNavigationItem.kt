package es.sebas1705.youknow.presentation.features.home.composables
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

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Data class that represents an item of the [HomeBottomNavigationBar].
 *
 * @param icon [ImageVector]: Icon of the item.
 * @param label [String]: Label of the item.
 * @param route [Any]: Route of the item.
 *
 * @see ImageVector
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class BottomNavigationItem(
    val icon: ImageVector,
    val label: String,
    val route: Any
)