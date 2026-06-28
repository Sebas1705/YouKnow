package es.sebas1705.home.main.viewmodel


import es.sebas1705.common.classes.mvi.MVIBaseIntent

/**
 * Sealed interface that represents the possible actions of the [MainViewModel].
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
sealed interface MainIntent : MVIBaseIntent {

    data object GetRanking : MainIntent

    data object GetNews : MainIntent

    data object RecreateGameDB : MainIntent

}