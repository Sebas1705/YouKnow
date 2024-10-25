package es.sebas1705.youknow.data.firebase.authentication.config

/**
 * Settings of the authentication
 *
 * @property FILTER_BY_AUTHORIZED_ACCOUNTS [Boolean]: Filter by authorized accounts
 * @property ERROR_MESSAGE [String]: Error message
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
object SettingsAuth {
    const val FILTER_BY_AUTHORIZED_ACCOUNTS = false

    const val ERROR_GENERIC_MESSAGE = "Error in the authentication process"
    const val NOT_LOGGED_USER = "Not correctly logged user to take their data"
    const val USER_NOT_OUT = "User not logged out"
    const val WRONG_CREDENTIALS = "Wrong credentials"
}