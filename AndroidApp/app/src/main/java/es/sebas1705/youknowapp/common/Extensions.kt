package es.sebas1705.youknowapp.common

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.domain.model.Page
import java.net.URLDecoder
import java.nio.charset.StandardCharsets


//Strings:
/**
 * Decode a string from URL format using [URLDecoder.decode]
 * and standard charset [StandardCharsets.UTF_8]
 */
fun String.decodeUrl(): String {
    return URLDecoder.decode(this, StandardCharsets.UTF_8.toString())
}

//Context:
/**
 * Generate the guide pages form of [Page]
 */
fun Context.generateGuidePages() = listOf(
    Page(
        this.getString(R.string.titlePage1),
        this.getString(R.string.desPage1),
        R.drawable.back_boarding
    ),
    Page(
        this.getString(R.string.titlePage2),
        this.getString(R.string.desPage2),
        R.drawable.back_boarding
    ),
    Page(
        this.getString(R.string.titlePage3),
        this.getString(R.string.desPage3),
        R.drawable.back_boarding
    )
)


//NavHostController:
/**
 * Navigate to a route and popUp to another route
 */
fun NavHostController.navAndPopUp(route: Any,popUpTo: Any){
    this.navigate(route) {
        popUpTo(popUpTo) {
            inclusive = true
        }
    }
}

/**
 * Navigate to a tab
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
