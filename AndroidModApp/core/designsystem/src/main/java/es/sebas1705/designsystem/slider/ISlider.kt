package es.sebas1705.youknow.core.composables.slider
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

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import es.sebas1705.common.utlis.IComposablePreview
import es.sebas1705.common.utlis.extensions.composables.disabled
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * ISlider
 *
 * @param value [Float]: Value
 * @param onValueChange [(Float) -> Unit]: On value change
 * @param modifier [Modifier]: Modifier
 * @param enabled [Boolean]: Enabled
 * @param valueRange [ClosedFloatingPointRange<Float>]: Value range
 * @param steps [Int]: Steps
 * @param onValueChangeFinished [(() -> Unit)?]: On value change finished
 * @param interactionSource [MutableInteractionSource]: Interaction source
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun ISlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) = Slider(
    value,
    onValueChange,
    modifier,
    enabled,
    valueRange,
    steps,
    onValueChangeFinished,
    colors = SliderDefaults.colors(
        //Thumb
        thumbColor = MaterialTheme.colorScheme.primary,
        disabledThumbColor = MaterialTheme.colorScheme.primary.disabled(),
        //End Thumb
        //Active track
        activeTrackColor = MaterialTheme.colorScheme.primary,
        disabledActiveTrackColor = MaterialTheme.colorScheme.primary.disabled(),
        //End Active track
        //Inactive track
        inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
        disabledInactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer.disabled(),
        //End Inactive track
        //Active tick
        activeTickColor = MaterialTheme.colorScheme.onPrimary,
        disabledActiveTickColor = MaterialTheme.colorScheme.onPrimary.disabled(),
        //End Active tick
        //Inactive tick
        inactiveTickColor = MaterialTheme.colorScheme.onSecondaryContainer,
        disabledInactiveTickColor = MaterialTheme.colorScheme.onSecondaryContainer.disabled()
        //End Inactive tick
    ),
    interactionSource,
)


@IComposablePreview
@Composable
private fun PreviewE() = YouKnowTheme {
    ISlider(
        0.5f,
        {},
        steps = 9
    )
}

@IComposablePreview
@Composable
private fun PreviewD() = YouKnowTheme {
    ISlider(
        0.5f,
        {},
        enabled = false,
        steps = 9
    )
}
