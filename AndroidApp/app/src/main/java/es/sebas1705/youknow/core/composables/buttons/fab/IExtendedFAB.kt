package es.sebas1705.youknow.core.composables.buttons.fab
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

import android.content.Context
import android.media.SoundPool
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import es.sebas1705.youknow.core.composables.ComposableConstants.FAB_BUTTON_SOUND
import es.sebas1705.youknow.core.composables.ComposableConstants.LOOP_N
import es.sebas1705.youknow.core.composables.ComposableConstants.PRIORITY_SOUND
import es.sebas1705.youknow.core.composables.ComposableConstants.RATE
import es.sebas1705.youknow.core.utlis.IComposablePreview
import es.sebas1705.youknow.presentation.ui.theme.TonalElevation
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Personalized extended FAB
 *
 * @param onClick [() -> Unit]: Click action
 * @param label [String]: Label
 * @param modifier [Modifier]: Modifier
 * @param imageVector [ImageVector]: Image vector
 * @param imageResource [Int]: Image resource
 * @param interactionSource [MutableInteractionSource]: Interaction source
 * @param soundPool [Pair]<[SoundPool], [Float]>: Sound pool
 * @param soundRes [Int]: Sound resource
 * @param context [Context]: Context
 * @param soundId [Int]: Sound id
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun IExtendedFAB(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
    imageResource: Int? = null,
    interactionSource: MutableInteractionSource? = null,
    soundPool: Pair<SoundPool, Float>? = null,
    soundRes: Int = FAB_BUTTON_SOUND,
    context: Context = LocalContext.current,
    soundId: Int? = remember { soundPool?.first?.load(context, soundRes, PRIORITY_SOUND) }
) = ExtendedFloatingActionButton(
    {
        soundPool?.first?.play(
            soundId ?: 0,
            soundPool.second,
            soundPool.second,
            PRIORITY_SOUND,
            LOOP_N,
            RATE
        )
        onClick()
    },
    modifier,
    containerColor = MaterialTheme.colorScheme.primaryContainer,
    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
    elevation = FloatingActionButtonDefaults.elevation(
        defaultElevation = TonalElevation.Level5,
        pressedElevation = TonalElevation.Level4,
        focusedElevation = TonalElevation.Level5,
        hoveredElevation = TonalElevation.Level5,
    ),
    interactionSource = interactionSource,
    content = {
        if (imageVector != null)
            Icon(imageVector, label)
        else if (imageResource != null)
            Icon(painterResource(imageResource), label)
        Text(label)
    }
)

@IComposablePreview
@Composable
private fun Preview() = YouKnowTheme {
    IExtendedFAB(
        onClick = {},
        label = "Add",
        imageVector = Icons.Default.Add
    )
}


