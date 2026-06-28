package es.sebas1705.home.groups


import android.media.SoundPool
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.extensions.composables.printTextInToast
import es.sebas1705.home.groups.design.GroupsDesign
import es.sebas1705.home.groups.viewmodel.GroupsIntent
import es.sebas1705.home.groups.viewmodel.GroupsViewModel
import es.sebas1705.home.navigation.viewmodel.HomeState
import es.sebas1705.feature.home.R

/**
 * Groups Screen that will show the groups options.
 *
 * @param windowState [WindowState]: The state of the window.
 * @param homeState [HomeState]: The state of the home.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onUserInfoSearch (String) -> Unit: The search of the user info.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun GroupsScreen(
    windowState: WindowState,
    homeState: HomeState,
    soundPool: Pair<SoundPool, Float>,
    onUserInfoSearch: (List<String>) -> Unit,
) {
    //Locals:
    val ctx = LocalContext.current
    BackHandler {}

    //ViewModel:
    val groupsViewModel: GroupsViewModel = hiltViewModel()

    //State:
    val groupsState by groupsViewModel.uiState.collectAsStateWithLifecycle()

    //Effects:
    LaunchedEffect(homeState.userModel) {
        groupsViewModel.eventHandler(GroupsIntent.LoadGroups(homeState.userModel?.groupId))
    }

    //Body:
    GroupsDesign(
        windowState,
        groupsState,
        homeState,
        soundPool,
        groupCreator = { name, description ->
            homeState.userModel?.let { userModel ->
                groupsViewModel.eventHandler(GroupsIntent.CreateGroup(name, description, userModel))
            } ?: ctx.printTextInToast(ctx.getString(R.string.feature_home_user_not_logged))
        },
        groupJoin = { groupModel ->
            homeState.userModel?.let { userModel ->
                groupsViewModel.eventHandler(GroupsIntent.JoinGroup(groupModel, userModel))
            } ?: ctx.printTextInToast(ctx.getString(R.string.feature_home_user_not_logged))
        },
        onGroupOutButton = {
            groupsState.groups.firstOrNull { it.groupId == homeState.userModel?.groupId }
                ?.let { groupModel ->
                    homeState.userModel?.let { userModel ->
                        groupsViewModel.eventHandler(GroupsIntent.OutGroup(groupModel, userModel))
                    } ?: ctx.printTextInToast(ctx.getString(R.string.feature_home_user_not_logged))
                } ?: ctx.printTextInToast(ctx.getString(R.string.feature_home_not_in_group))
        },
        onKickButton = { user ->
            groupsState.groups.firstOrNull { it.groupId == homeState.userModel?.groupId }
                ?.let { groupModel ->
                    groupsViewModel.eventHandler(GroupsIntent.KickGroup(groupModel, user))
                } ?: ctx.printTextInToast(ctx.getString(R.string.feature_home_not_in_group))
        },
        onUserInfoSearch
    )
}