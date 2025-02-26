package es.sebas1705.youknow.presentation.features.home.features.chat.composables
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.composables.cards.IPrimaryCard
import es.sebas1705.youknow.core.composables.divider.IHorDivider
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.extensions.primitives.millisToFormatDate
import es.sebas1705.youknow.domain.model.social.MessageModel
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Message card that will show the message information.
 * The user can see the message information.
 *
 * @param messageModel [MessageModel]: The message model.
 * @param isCurrentUser Boolean: If the message is from the current user.
 * @param windowState [WindowState]: The state of the window.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun MessageCard(
    messageModel: MessageModel,
    isCurrentUser: Boolean,
    windowState: WindowState = WindowState.default()
) = Column(
    modifier = Modifier.fillMaxWidth()
) {
    IPrimaryCard(
        modifier = Modifier
            .fillMaxWidth(
                windowState.widthType.filter(0.8f, 0.6f, 0.4f)
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
        IHorDivider(modifier = Modifier.padding(horizontal = SmallestPadding))
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