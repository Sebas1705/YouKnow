package es.sebas1705.designsystem.buttons.radio
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import es.sebas1705.common.utlis.IComposablePreview
import es.sebas1705.common.utlis.extensions.composables.disabled
import es.sebas1705.designsystem.ComposableConstants.LOOP_N
import es.sebas1705.designsystem.ComposableConstants.PRIORITY_SOUND
import es.sebas1705.designsystem.ComposableConstants.RADIO_BUTTON_SOUND
import es.sebas1705.designsystem.ComposableConstants.RATE
import es.sebas1705.ui.theme.YouKnowTheme

/**
 * Personalized radio button
 *
 * @param selected [Boolean]: Selected state
 * @param modifier [Modifier]: Modifier
 * @param enabled [Boolean]: Enabled
 * @param interactionSource [MutableInteractionSource]: Interaction source
 * @param onClick [() -> Unit]: Click action
 * @param soundPool [Pair]<[SoundPool], [Float]>: Sound pool
 * @param soundRes [Int]: Sound resource
 * @param context [Context]: Context
 * @param soundId [Int]: Sound id
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios
 */
@Composable
fun IRadioButton(
    selected: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    onClick: (() -> Unit)? = null,
    soundPool: Pair<SoundPool, Float>? = null,
    soundRes: Int = RADIO_BUTTON_SOUND,
    context: Context = LocalContext.current,
    soundId: Int? = remember { soundPool?.first?.load(context, soundRes, PRIORITY_SOUND) }
) = RadioButton(
    selected = selected,
    onClick = {
        soundPool?.first?.play(
            soundId ?: 0,
            soundPool.second,
            soundPool.second,
            PRIORITY_SOUND,
            LOOP_N,
            RATE
        )
        onClick?.invoke()
    },
    modifier = modifier,
    enabled = enabled,
    colors = RadioButtonDefaults.colors(
        selectedColor = MaterialTheme.colorScheme.primary,
        unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledSelectedColor = MaterialTheme.colorScheme.primary.disabled(),
        disabledUnselectedColor = MaterialTheme.colorScheme.onSurfaceVariant.disabled()
    ),
    interactionSource = interactionSource
)

@IComposablePreview
@Composable
private fun Preview() = YouKnowTheme {
    IRadioButton(
        selected = true,
        modifier = Modifier
            .width(24.dp)
            .height(24.dp)
    )
}
