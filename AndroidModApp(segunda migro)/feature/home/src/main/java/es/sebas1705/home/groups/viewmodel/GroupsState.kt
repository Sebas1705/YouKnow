package es.sebas1705.home.groups.viewmodel


import es.sebas1705.common.mvi.MVIBaseState
import es.sebas1705.models.social.GroupModel

/**
 * State of the [GroupsViewModel] that will handle the data of the screen.
 *
 * @property isLoading [Boolean]: Flag that indicates if the data is loading.
 * @property groups [List]<[GroupModel]>: List of groups.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
data class GroupsState(
    val isLoading: Boolean,
    val groups: List<GroupModel>
) : MVIBaseState {
    companion object {

        /**
         * Default state of the [GroupsViewModel].
         *
         * @return [GroupsState]: Default state.
         */
        fun default() = GroupsState(
            isLoading = false,
            groups = emptyList()
        )
    }
}