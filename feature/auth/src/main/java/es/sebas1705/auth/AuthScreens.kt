package es.sebas1705.auth


import kotlinx.serialization.Serializable

interface AuthScreens {

    @Serializable
    object MenuScreen : AuthScreens

    @Serializable
    object LogScreen : AuthScreens

    @Serializable
    object SignScreen : AuthScreens
}