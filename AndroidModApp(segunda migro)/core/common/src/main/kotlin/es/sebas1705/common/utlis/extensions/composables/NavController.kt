package es.sebas1705.common.utlis.extensions.composables

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

fun NavController.navToTab(route: Any) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
