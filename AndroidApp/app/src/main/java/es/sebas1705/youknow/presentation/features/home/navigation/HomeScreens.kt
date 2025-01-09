package es.sebas1705.youknow.presentation.features.home.navigation
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

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.SportsEsports
import androidx.compose.ui.graphics.vector.ImageVector
import es.sebas1705.youknow.R
import kotlinx.serialization.Serializable

data class HomeItem(
    val strRes: Int,
    val iconUnselected: ImageVector,
    val iconSelected: ImageVector,
    val destination: HomeScreens
)

val homes = listOf(
    HomeItem(
        R.string.Chat,
        Icons.AutoMirrored.Outlined.Chat,
        Icons.AutoMirrored.Filled.Chat,
        HomeScreens.ChatScreen
    ),
    HomeItem(
        R.string.Profile,
        Icons.Outlined.Person,
        Icons.Filled.Person,
        HomeScreens.ProfileScreen
    ),
    HomeItem(R.string.Main, Icons.Outlined.Home, Icons.Filled.Home, HomeScreens.MainScreen),
    HomeItem(
        R.string.Play,
        Icons.Outlined.SportsEsports,
        Icons.Filled.SportsEsports,
        HomeScreens.PlayScreen
    ),
    HomeItem(R.string.Groups, Icons.Outlined.Groups, Icons.Filled.Groups, HomeScreens.GroupsScreen),
)

interface HomeScreens {
    @Serializable
    object MainScreen : HomeScreens

    @Serializable
    object ProfileScreen : HomeScreens

    @Serializable
    object ChatScreen : HomeScreens

    @Serializable
    object PlayScreen : HomeScreens

    @Serializable
    object GroupsScreen : HomeScreens
}

