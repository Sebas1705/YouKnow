package es.sebas1705.main

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Screens of the app.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
interface AppGraph : NavKey {

    @Serializable
    object SplashScreen : AppGraph
    @Serializable
    object GuideScreen : AppGraph

    @Serializable
    object SettingsScreen : AppGraph

    @Serializable
    object SurveyScreen : AppGraph

    @Serializable
    object AuthNavigation : AppGraph

    @Serializable
    object HomeNavigation : AppGraph

    @Serializable
    object GameNavigation : AppGraph
}




