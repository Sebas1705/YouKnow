package es.sebas1705.auth


import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

interface AuthScreens : NavKey {

    @Serializable
    object MenuScreen : AuthScreens

    @Serializable
    object LogScreen : AuthScreens

    @Serializable
    object SignScreen : AuthScreens
}