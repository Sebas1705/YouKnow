package es.sebas1705.designsystem.textfields
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import es.iberext.youknow.core.designsystem.R
import es.sebas1705.common.utlis.IComposablePreview
import es.sebas1705.common.utlis.extensions.composables.disabled
import es.sebas1705.designsystem.ComposableConstants.ICON_BUTTON_SOUND
import es.sebas1705.designsystem.ComposableConstants.LOOP_N
import es.sebas1705.designsystem.ComposableConstants.PRIORITY_SOUND
import es.sebas1705.designsystem.ComposableConstants.RATE
import es.sebas1705.designsystem.buttons.icon.IStandardIconButton
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.ui.theme.YouKnowTheme

/**
 * Filled text field
 *
 * @param value [String]: Value
 * @param onValueChange [Function1<String, Unit]: On value change
 * @param modifier [Modifier]: Modifier
 * @param label [String]: Label
 * @param placeholder [String]: Placeholder
 * @param enabled [Boolean]: Enabled
 * @param readOnly [Boolean]: Read only
 * @param textStyle [TextStyle]: Text style
 * @param leadingIcon [Pair<ImageVector, () -> Unit>]: Leading icon
 * @param leadingEnabled [Boolean]: Leading enabled
 * @param trailingIcon [Pair<ImageVector, () -> Unit>]: Trailing icon
 * @param trailingEnabled [Boolean]: Trailing enabled
 * @param prefix [String]: Prefix
 * @param suffix [String]: Suffix
 * @param supportingText [String]: Supporting text
 * @param isError [Boolean]: Is error
 * @param visualTransformation [VisualTransformation]: Visual transformation
 * @param keyboardOptions [KeyboardOptions]: Keyboard options
 * @param keyboardActions [KeyboardActions]: Keyboard actions
 * @param singleLine [Boolean]: Single line
 * @param maxLines [Int]: Max lines
 * @param minLines [Int]: Min lines
 * @param interactionSource [MutableInteractionSource]: Interaction source
 * @param soundPool [Pair]<[SoundPool], [Float]>: Sound pool
 * @param soundRes [Int]: Sound resource
 * @param context [Context]: Context,
 * @param soundId [Int]: Sound id
 *
 * @since 1.0.0
 * @Version 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
@Composable
fun IFilledTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    leadingIcon: Pair<ImageVector, () -> Unit>? = null,
    leadingEnabled: Boolean = true,
    trailingIcon: Pair<ImageVector, () -> Unit>? = null,
    trailingEnabled: Boolean = true,
    prefix: String? = null,
    suffix: String? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource? = null,
    soundPool: Pair<SoundPool, Float>? = null,
    soundRes: Int = ICON_BUTTON_SOUND,
    context: Context = LocalContext.current,
    soundId: Int? = remember { soundPool?.first?.load(context, soundRes, PRIORITY_SOUND) }
) = TextField(
    value,
    onValueChange,
    modifier,
    enabled,
    readOnly,
    textStyle,
    label?.let { { IText(it, style = MaterialTheme.typography.bodySmall) } },
    placeholder?.let { { IText(it, style = textStyle) } },
    leadingIcon?.let {
        {
            IStandardIconButton(
                onClick = {
                    soundPool?.first?.play(
                        soundId ?: 0,
                        soundPool.second,
                        soundPool.second,
                        PRIORITY_SOUND,
                        LOOP_N,
                        RATE
                    )
                    it.second()
                },
                contentDescription = label
                    ?: stringResource(R.string.core_designsystem_leading_content),
                imageVector = it.first,
                enabled = leadingEnabled
            )
        }
    },
    trailingIcon?.let {
        {
            IStandardIconButton(
                onClick = {
                    soundPool?.first?.play(
                        soundId ?: 0,
                        soundPool.second,
                        soundPool.second,
                        PRIORITY_SOUND,
                        LOOP_N,
                        RATE
                    )
                    it.second()
                },
                contentDescription = label
                    ?: stringResource(R.string.core_designsystem_trailing_content),
                imageVector = it.first,
                enabled = trailingEnabled
            )
        }
    },
    prefix?.let { { IText(it, style = textStyle) } },
    suffix?.let { { IText(it, style = textStyle) } },
    supportingText?.let { { IText(it) } },
    isError,
    visualTransformation,
    keyboardOptions,
    keyboardActions,
    singleLine,
    maxLines,
    minLines,
    interactionSource,
    colors = TextFieldDefaults.colors(
        //Container
        focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        errorContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        disabledContainerColor = MaterialTheme.colorScheme.surfaceContainer.disabled(),
        //End Container
        //Text
        focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        errorTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant.disabled(),
        //End Text
        //Cursor
        cursorColor = MaterialTheme.colorScheme.primary,
        errorCursorColor = MaterialTheme.colorScheme.error,
        //End Cursor
        //Indicator
        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
        unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
        errorIndicatorColor = MaterialTheme.colorScheme.error,
        disabledIndicatorColor = MaterialTheme.colorScheme.primary.disabled(),
        //End Indicator
        //Leading Icon
        focusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        errorLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant.disabled(),
        //End Leading Icon
        //Trailing Icon
        focusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        errorTrailingIconColor = MaterialTheme.colorScheme.error,
        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant.disabled(),
        //End Trailing Icon
        //Label
        focusedLabelColor = MaterialTheme.colorScheme.primary,
        unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
        errorLabelColor = MaterialTheme.colorScheme.error,
        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant.disabled(),
        //End Label
        //Placeholder
        focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        errorPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant.disabled(),
        //End Placeholder
        //Supporting Text
        focusedSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        errorSupportingTextColor = MaterialTheme.colorScheme.error,
        disabledSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant.disabled(),
        //End Supporting Text
        //Prefix
        focusedPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
        errorPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledPrefixColor = MaterialTheme.colorScheme.onSurfaceVariant.disabled(),
        //End Prefix
        //Suffix
        focusedSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant,
        errorSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledSuffixColor = MaterialTheme.colorScheme.onSurfaceVariant.disabled(),
        //End Suffix
        selectionColors = null,
    )
)

@IComposablePreview
@Composable
private fun PreviewE() = YouKnowTheme {
    IFilledTextField(
        "Value",
        {},
        label = "Label",
        placeholder = "Placeholder",
        leadingIcon = Icons.Filled.Search to {},
        trailingIcon = Icons.Filled.Cancel to {},
        prefix = "Prefix",
        suffix = "Suffix",
        supportingText = "Supporting Text",
    )
}

@IComposablePreview
@Composable
private fun PreviewD() = YouKnowTheme {
    IFilledTextField(
        "Value",
        {},
        label = "Label",
        placeholder = "Placeholder",
        leadingIcon = Icons.Filled.Search to {},
        trailingIcon = Icons.Filled.Cancel to {},
        prefix = "Prefix",
        suffix = "Suffix",
        supportingText = "Supporting Text",
        enabled = false
    )
}

@IComposablePreview
@Composable
private fun PreviewErr() = YouKnowTheme {
    IFilledTextField(
        "Value",
        {},
        label = "Label",
        placeholder = "Placeholder",
        leadingIcon = Icons.Filled.Search to {},
        trailingIcon = Icons.Filled.Cancel to {},
        prefix = "Prefix",
        suffix = "Suffix",
        supportingText = "Supporting Text",
        isError = true
    )
}

