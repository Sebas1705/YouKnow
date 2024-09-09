package es.sebas1705.youknowapp.presentation.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import es.sebas1705.youknowapp.domain.utils.Constants

@Composable
fun HomeBottomBar(
    navController: NavController? = null
){
    var selectedItem by remember { mutableIntStateOf(2) }
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary
    ) {
        Constants.HOME_ROUTES_LIST.forEachIndexed { index, routes ->
            NavigationBarItem(
                selected = index == selectedItem,
                label = {
                    Text(routes.label)
                },
                icon = {
                    Icon(
                        Icons.Sharp.Home,
                        contentDescription = routes.label,
                        modifier = Modifier
                    )
                },
                onClick = {
                    selectedItem = index
                    navController?.navigate(routes.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}