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
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Output
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.utlis.Constants
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.makeBold
import es.sebas1705.youknow.domain.model.GroupModel
import es.sebas1705.youknow.presentation.composables.ApplyBack
import es.sebas1705.youknow.presentation.composables.CurvedBorderSurface
import es.sebas1705.youknow.presentation.composables.CustomIconButton
import es.sebas1705.youknow.presentation.composables.InteractiveCard
import es.sebas1705.youknow.presentation.ui.theme.CardDividerThickness
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
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CurvedBorderSurface(
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
                    CustomIconButton(
                        onClick = onOutButton,
                        icon = Icons.Default.Output,
                        modifierButton = Modifier.padding(SmallestPadding),
                        contentDescription = "Out",
                    )
                }
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    thickness = CardDividerThickness,
                    modifier = Modifier.padding(
                        vertical = SmallestPadding,
                        horizontal = SmallPadding
                    )
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

        ApplyBack(
            backId = windowState.backEmpty,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SmallPadding),
            ) {
                stickyHeader {
                    Text(
                        text = "Members (${groupModel.members.size}/${Constants.MAX_GROUP}):",
                        style = MaterialTheme.typography.titleMedium.makeBold(),
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(SmallestPadding),
                    )
                }

                items(groupModel.members.size) { index ->
                    InteractiveCard(
                        title = groupModel.members[index].split("-")[1],
                        subtitle = if (groupModel.leaderUID == groupModel.members[index]) "(Leader)" else "",
                        buttons = {
                            CustomIconButton(
                                onClick = { onInfoButton(groupModel.members[index].split("-")[0]) },
                                icon = Icons.Filled.AddCircleOutline,
                                contentDescription = "Fight",
                                modifierIcon = Modifier.padding(SmallestPadding),
                            )
                            if (admin) CustomIconButton(
                                onClick = { onKickButton(groupModel.members[index]) },
                                icon = Icons.Filled.Delete,
                                contentDescription = "Delete",
                                modifierIcon = Modifier.padding(SmallestPadding),
                            )
                        },
                        modifier = Modifier.padding(bottom = SmallPadding)
                    )
                }
            }
        }
    }
}

@UiModePreviews
@Composable
fun GroupPreview() {
    YouKnowTheme {
        Group(
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