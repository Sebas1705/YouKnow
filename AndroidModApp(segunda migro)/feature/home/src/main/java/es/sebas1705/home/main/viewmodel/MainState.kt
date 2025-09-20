package es.sebas1705.youknow.presentation.features.home.features.main.viewmodel


import es.sebas1705.common.mvi.MVIBaseState
import es.sebas1705.models.social.NewModel

/**
 * State of the [MainViewModel] that will handle the data of the screen.
 *
 * @param isLoading [Boolean]: Flag to indicate if the screen is loading.
 * @param news [List]<[NewModel]>: List of news.
 * @param ranking [List]<[Pair]<[String], [Int]>>: List of the ranking.
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
data class MainState(
    val isLoading: Boolean,
    val news: List<NewModel>,
    val ranking: List<Pair<String, Int>>,
) : MVIBaseState {
    companion object {

        /**
         * Default state of the [MainState].
         *
         * @return [MainState]: Default state.
         *
         * @since 1.0.0
         * @author Sebas1705 12/09/2025
         */
        fun default() = MainState(
            isLoading = false,
            news = emptyList(),
            ranking = emptyList()
        )
    }
}