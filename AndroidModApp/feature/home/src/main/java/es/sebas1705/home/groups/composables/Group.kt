package es.sebas1705.home.groups.composables
/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import android.media.SoundPool
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Output
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.common.utlis.extensions.composables.makeBold
import es.sebas1705.designsystem.ComposableConstants
import es.sebas1705.designsystem.buttons.icon.IFilledIconButton
import es.sebas1705.designsystem.cards.IInteractiveCard
import es.sebas1705.designsystem.divider.IHorDivider
import es.sebas1705.designsystem.surfaces.IPrimarySurface
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.home.navigation.viewmodel.HomeState
import es.sebas1705.models.social.GroupModel
import es.sebas1705.ui.theme.Paddings.SmallPadding
import es.sebas1705.ui.theme.Paddings.SmallestPadding
import es.sebas1705.ui.theme.YouKnowTheme
import es.sebas1705.youknow.feature.home.R

/**
 * Group composable that will show the group information.
 * The user can see the group information and the members of the group.
 * The user can add or delete members if it is the admin of the group.
 *
 * @param windowState [WindowState]: The state of the window.
 * @param homeState [HomeState]: The state of the home.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onOutButton () -> Unit: The out of the group.
 * @param onInfoButton (String) -> Unit: The info of the user.
 * @param onKickButton (String) -> Unit: The kick of the member.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun Group(
    windowState: WindowState = WindowState.default(),
    homeState: HomeState = HomeState.default(),
    soundPool: Pair<SoundPool, Float>? = null,
    groupModel: GroupModel = GroupModel(
        name = "aaaaaaaaa",
        description = "aaaaaaaaaaaaaa",
        leaderUID = "aaaaaaaaaaaaa",
        members = emptyList()
    ),
    onOutButton: () -> Unit = {},
    onInfoButton: (String) -> Unit = {},
    onKickButton: (String) -> Unit = {},
    onUsersLoad: (List<String>) -> Unit = {}
) {
    LaunchedEffect(groupModel) {
        onUsersLoad(
            groupModel.members
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = SmallestPadding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IPrimarySurface(
            modifier = Modifier
                .fillMaxWidth(0.95f)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = groupModel.name,
                        style = MaterialTheme.typography.titleLarge.makeBold(),
                        modifier = Modifier
                            .padding(SmallestPadding)
                            .padding(start = SmallPadding)
                    )
                    IFilledIconButton(
                        onClick = onOutButton,
                        contentDescription = "Out",
                        modifier = Modifier.padding(SmallestPadding),
                        imageVector = Icons.Default.Output,
                        soundPool = soundPool
                    )
                }
                IHorDivider(
                    modifier = Modifier.padding(
                        vertical = SmallestPadding,
                        horizontal = SmallPadding
                    ),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = groupModel.description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(SmallPadding),
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SmallPadding),
        ) {
            item {
                IText(
                    text = "Members (${groupModel.members.size}/${ComposableConstants.MAX_GROUP}):",
                    style = MaterialTheme.typography.titleMedium.makeBold(),
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(SmallestPadding),
                )
            }

            items(groupModel.members) { memberId ->
                val memberName = homeState.infoUsers[memberId]?.nickName ?: ""
                val firebaseId = homeState.userModel?.firebaseId
                val groupLeader = groupModel.leaderUID
                IInteractiveCard(
                    title = if (firebaseId == memberId) stringResource(R.string.feature_home_You) else memberName,
                    subtitle = if (groupLeader == memberId) "(${stringResource(R.string.feature_home_leader)})" else "",
                    buttons = {
                        if (firebaseId != memberId) IFilledIconButton(
                            onClick = { onInfoButton(memberId) },
                            contentDescription = stringResource(R.string.feature_home_view_profile),
                            modifier = Modifier.padding(SmallestPadding),
                            imageVector = Icons.Filled.Search,
                            soundPool = soundPool
                        )
                        if (groupLeader == firebaseId && firebaseId != memberId) IFilledIconButton(
                            onClick = { onKickButton(memberId) },
                            contentDescription = stringResource(R.string.feature_home_kick_out),
                            modifier = Modifier.padding(SmallestPadding),
                            imageVector = Icons.Filled.Delete,
                            soundPool = soundPool
                        )
                    },
                    modifier = Modifier.padding(bottom = SmallPadding)
                )
            }
        }
    }
}

@UiModePreviews
@Composable
fun GroupPreview() {
    YouKnowTheme {
        Group()
    }
}