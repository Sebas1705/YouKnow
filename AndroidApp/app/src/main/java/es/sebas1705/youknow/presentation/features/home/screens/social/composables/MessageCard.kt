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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.millisToFormatDate
import es.sebas1705.youknow.domain.model.MessageModel
import es.sebas1705.youknow.presentation.composables.CardHDivider
import es.sebas1705.youknow.presentation.composables.CustomCard
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

@Composable
fun MessageCard(
    messageModel: MessageModel,
    isCurrentUser: Boolean,
    windowState: WindowState = WindowState.default()
) = Column(
    modifier = Modifier.fillMaxWidth()
) {
    CustomCard(
        modifier = Modifier
            .fillMaxWidth(
                windowState.widthType.filter(0.8f,0.6f,0.4f)
            )
            .align(
                if (isCurrentUser) Alignment.Start
                else Alignment.End
            )
            .padding(SmallPadding),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (isCurrentUser) "You"
                else messageModel.authorName,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(SmallPadding)
            )
            Text(
                text = messageModel.time.millisToFormatDate(),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(SmallPadding)
            )
        }
        CardHDivider()
        Text(
            text = messageModel.text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(SmallPadding),
        )
    }
}

@UiModePreviews
@Composable
private fun MessageCardPreview() {
    YouKnowTheme {
        MessageCard(
            messageModel = MessageModel(
                authorId = "1",
                authorName = "Sebas",
                text = "Hello World",
                time = System.currentTimeMillis()
            ),
            isCurrentUser = true
        )
    }
}