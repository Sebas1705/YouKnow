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

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.presentation.features.home.navigation.MainScreen
import es.sebas1705.youknow.presentation.features.home.navigation.ProfileScreen
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Bottom Navigation Bar for the Home Screen.
 *
 * @param items [List]<[BottomNavigationItem]>: that will be displayed in the Navigation Bar.
 * @param selectedItem [Int]: that represents the selected item in the Navigation Bar.
 *
 * @see BottomNavigationItem
 * @see NavigationBar
 * @see NavigationBarItem
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun HomeBottomNavigationBar(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                modifier = Modifier
                    .padding(all = 5.dp),
                selected = index == selectedItem,
                label = {
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.labelMedium,
                    )
                },
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.label,
                        modifier = Modifier
                    )

                },
                onClick = { onItemClick(index) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.secondary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    indicatorColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        }
    }
}

/**
 * Preview for [HomeBottomNavigationBar].
 *
 * @see HomeBottomNavigationBar
 */
@UiModePreviews
@Composable
fun HomeNavigationBarPreview() {
    YouKnowTheme {
        HomeBottomNavigationBar(items = listOf(
            BottomNavigationItem(icon = Icons.Default.Home, label = "Home", ProfileScreen),
            BottomNavigationItem(icon = Icons.Default.Search, label = "Search", MainScreen),
            BottomNavigationItem(icon = Icons.Default.Bookmark, label = "Bookmark", ProfileScreen),
        ), selectedItem = 2, onItemClick = {})
    }
}