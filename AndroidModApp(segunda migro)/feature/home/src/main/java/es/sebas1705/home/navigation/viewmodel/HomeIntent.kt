package es.sebas1705.home.navigation.viewmodel


import es.sebas1705.common.mvi.MVIBaseIntent

/**
 * Intent for [HomeViewModel].
 *
 * @see MVIBaseIntent
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
sealed interface HomeIntent : MVIBaseIntent {

    data object LoadActual : HomeIntent

    data object ClearActual : HomeIntent

    data class AddCredits(
        val credits: Int
    ) : HomeIntent

    data class GetUsers(
        val firebaseIds: List<String>
    ) : HomeIntent

    data object SignOut : HomeIntent
}