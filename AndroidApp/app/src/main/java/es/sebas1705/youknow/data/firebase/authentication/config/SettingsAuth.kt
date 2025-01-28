package es.sebas1705.youknow.data.firebase.authentication.config

/**
 * Settings of the authentication
 *
 * @property FILTER_BY_AUTHORIZED_ACCOUNTS [Boolean]: Filter by authorized accounts
 * @property ERROR_GENERIC_MESSAGE_EX [String]: Error message by exception
 * @property ERROR_GENERIC_MESSAGE_FAIL [String]: Error message by failure listener
 * @property NOT_LOGGED_USER [String]: Not logged user message
 * @property USER_NOT_OUT [String]: User not logged out message
 * @property WRONG_CREDENTIALS [String]: Wrong credentials message
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
object SettingsAuth {
    const val FILTER_BY_AUTHORIZED_ACCOUNTS = false

    const val ERROR_GENERIC_MESSAGE_EX = "An error occurred on authentication by an exception"
    const val ERROR_GENERIC_MESSAGE_FAIL = "An error occurred on authentication by failure listener"

    const val NOT_LOGGED_USER = "Not correctly logged user to take their data"
    const val USER_NOT_OUT = "User not logged out"
    const val WRONG_CREDENTIALS = "Wrong credentials"
}