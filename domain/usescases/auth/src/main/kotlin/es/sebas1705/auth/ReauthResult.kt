package es.sebas1705.auth

/**
 * Outcome of asking the current user to confirm their identity again.
 *
 * @since 1.6.0
 * @author Sebas1705 11/11/2026
 */
sealed interface ReauthResult {

    /** Firebase accepted the confirmation: sensitive operations will pass for a while. */
    data object Success : ReauthResult

    /** The password was wrong (or empty), or the Google account is not the signed-in one. */
    data object WrongCredentials : ReauthResult

    /**
     * Anything else (no network, the Google sheet was dismissed, nobody signed in).
     *
     * @property message [String]: technical message, for logs rather than for the UI
     */
    data class Failed(val message: String) : ReauthResult
}
