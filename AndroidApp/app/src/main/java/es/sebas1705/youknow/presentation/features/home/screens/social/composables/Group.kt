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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import es.sebas1705.youknow.core.utlis.Constants
import es.sebas1705.youknow.domain.model.GroupModel
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding

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
@Composable
fun Group(
    groupModel: GroupModel,
    admin: Boolean = true,
) {
    Surface(
        color = MaterialTheme.colorScheme.onPrimaryContainer,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(SmallPadding),
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = groupModel.name,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(SmallPadding),
                )
                HorizontalDivider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    Text(
                        text = groupModel.description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(3f)
                            .padding(SmallPadding),
                    )
                    VerticalDivider()
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .padding(SmallPadding),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Members",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier
                                .fillMaxWidth(),
                        )
                        Text(
                            text = "${groupModel.members.size}/${Constants.MAX_GROUP}",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier
                                .fillMaxWidth(),
                        )
                    }
                }

            }

            Text(
                text = "Members:",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.tertiaryContainer,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SmallestPadding),
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f)
                    .padding(SmallPadding),
            ) {
                items(groupModel.members.size) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(SmallPadding),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(SmallPadding),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Member $index",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .padding(SmallPadding),
                            )
                            IconButton(
                                onClick = { /*TODO*/ },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AddCircleOutline,
                                    contentDescription = "Fight",
                                    modifier = Modifier
                                        .padding(SmallPadding),
                                )
                            }
                            if (admin) IconButton(
                                onClick = { /*TODO*/ },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    modifier = Modifier
                                        .padding(SmallPadding),
                                )
                            }
                        }
                    }

                }
            }
        }
    }

}