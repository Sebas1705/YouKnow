package es.sebas1705.home.groups.composables


import android.media.SoundPool
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.designsystem.ComposableConstants
import es.sebas1705.designsystem.buttons.common.IFilledButton
import es.sebas1705.designsystem.cards.IInteractiveCard
import es.sebas1705.home.groups.viewmodel.GroupsState
import es.sebas1705.models.social.GroupModel
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.ui.theme.Paddings.SmallestPadding
import es.sebas1705.ui.theme.AppTheme

/**
 * New group composable that will show the groups to join.
 * The user can search for a group and join it.
 * The groups will be shown in a list.
 *
 * @param windowState [WindowState]: The state of the window.
 * @param groupsState [GroupsState]: The state of the Groups Screen.
 * @param groups [List]<[GroupModel]>: The list of groups to show.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onGroupClick (GroupModel) -> Unit: The click on a group.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun GroupsList(
    windowState: WindowState = WindowState.default(),
    groupsState: GroupsState = GroupsState.default(),
    groups: List<GroupModel> = groupsState.groups,
    soundPool: Pair<SoundPool, Float>? = null,
    onGroupClick: (GroupModel) -> Unit = {},
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = SmallestPadding, bottom = SmallestPadding),
    ) {
        items(groups.size) { index ->
            IInteractiveCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = SmallPadding)
                    .padding(horizontal = SmallestPadding),
                title = groups[index].name,
                subtitle = "${groups[index].members.size}/${ComposableConstants.MAX_GROUP}",
                buttons = {
                    IFilledButton(
                        label = "Join",
                        onClick = { onGroupClick(groups[index]) },
                        enabled = groups[index].members.size < ComposableConstants.MAX_GROUP,
                        soundPool = soundPool
                    )
                }
            )
        }
    }
}

@UiModePreviews
@Composable
private fun GroupsListPreview() {
    AppTheme {
        GroupsList()
    }
}