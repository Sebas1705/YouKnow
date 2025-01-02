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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable
import es.sebas1705.youknow.R

data class HomeItem(
    val strRes: Int,
    val icon: ImageVector,
    val destination: HomeScreens
)

val homes = listOf(
    HomeItem(R.string.Social, Icons.Default.Public, HomeScreens.SocialScreen),
    HomeItem(R.string.Profile, Icons.Default.Person, HomeScreens.ProfileScreen),
    HomeItem(R.string.Main, Icons.Default.Home, HomeScreens.MainScreen),
    HomeItem(R.string.Play, Icons.Default.SportsEsports, HomeScreens.PlayScreen),
    HomeItem(R.string.Info, Icons.Default.Info, HomeScreens.InfoScreen),
)

interface HomeScreens {
    @Serializable
    object MainScreen : HomeScreens

    @Serializable
    object ProfileScreen : HomeScreens

    @Serializable
    object SocialScreen : HomeScreens

    @Serializable
    object PlayScreen : HomeScreens

    @Serializable
    object InfoScreen : HomeScreens
}

