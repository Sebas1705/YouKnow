package es.sebas1705.youknowapp.presentation.screens.home.common

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
import es.sebas1705.youknowapp.common.Previews
import es.sebas1705.youknowapp.presentation.screens.home.navigation.MainScreen
import es.sebas1705.youknowapp.presentation.screens.home.navigation.ProfileScreen
import es.sebas1705.youknowapp.ui.theme.YouKnowTheme

@Composable
fun HomeNavigationBar(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        tonalElevation = 10.dp
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
                    selectedTextColor = MaterialTheme.colorScheme.secondary,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    indicatorColor = MaterialTheme.colorScheme.onSecondary,
                )
            )
        }
    }
}

@Previews
@Composable
fun HomeNavigationBarPreview() {
    YouKnowTheme {
        HomeNavigationBar(items = listOf(
            BottomNavigationItem(icon = Icons.Default.Home, label = "Home", ProfileScreen),
            BottomNavigationItem(icon = Icons.Default.Search, label = "Search", MainScreen),
            BottomNavigationItem(icon = Icons.Default.Bookmark, label = "Bookmark", ProfileScreen),
        ), selectedItem = 2, onItemClick = {})
    }
}