package es.sebas1705.youknow.presentation.features.home.screens.social.composables
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

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Output
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.buttons.icon.IFilledIconButton
import es.sebas1705.youknow.core.composables.cards.IInteractiveCard
import es.sebas1705.youknow.core.composables.divider.IHorDivider
import es.sebas1705.youknow.core.composables.surfaces.IPrimarySurface
import es.sebas1705.youknow.core.composables.texts.IText
import es.sebas1705.youknow.core.utlis.Constants
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.extensions.composables.makeBold
import es.sebas1705.youknow.domain.model.social.GroupModel
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Group composable that will show the group information.
 * The user can see the group information and the members of the group.
 * The user can add or delete members if it is the admin of the group.
 *
 * @param groupModel [GroupModel]: Group to show.
 * @param admin [Boolean]: If the user is the admin of the group.
 *
 * @see GroupModel
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Group(
    firebaseId: String,
    groupModel: GroupModel,
    windowState: WindowState = WindowState.default(),
    admin: Boolean = true,
    onOutButton: () -> Unit = {},
    onInfoButton: (String) -> Unit = {},
    onKickButton: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        IPrimarySurface (
            modifier = Modifier
                .fillMaxWidth()
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
            stickyHeader {
                IText(
                    text = "Members (${groupModel.members.size}/${Constants.MAX_GROUP}):",
                    style = MaterialTheme.typography.titleMedium.makeBold(),
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(SmallestPadding),
                )
            }

            items(groupModel.members.size) { index ->
                val member = groupModel.members[index]
                val memberId = member.split("-")[0]
                val memberName = member.split("-")[1]
                IInteractiveCard(
                    title = if (firebaseId == memberId) stringResource(R.string.You) else memberName,
                    subtitle = if (groupModel.leaderUID == memberId) "(${stringResource(R.string.leader)})" else "",
                    buttons = {
                        if (firebaseId != memberId) IFilledIconButton(
                            onClick = { onInfoButton(memberId) },
                            contentDescription = stringResource(R.string.view_profile),
                            modifier = Modifier.padding(SmallestPadding),
                            imageVector = Icons.Filled.Search,
                        )
                        if (admin && firebaseId != memberId) IFilledIconButton(
                            onClick = { onKickButton(member) },
                            contentDescription = "Delete",
                            modifier = Modifier.padding(SmallestPadding),
                            imageVector = Icons.Filled.Delete,
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
        Group(
            "",
            GroupModel(
                "Group 1",
                "Description of the group 1",
                listOf("Member 1", "Member 2", "Member 3", "Member 4", "Member 5"),
                "Leader 1"
            ),
            admin = false
        )
    }
}