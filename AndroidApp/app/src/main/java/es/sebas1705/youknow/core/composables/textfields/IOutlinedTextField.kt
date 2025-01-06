package es.sebas1705.youknow.core.composables.textfields
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.composables.buttons.icon.IStandardIconButton
import es.sebas1705.youknow.core.composables.texts.IText
import es.sebas1705.youknow.core.utlis.IComposablePreview
import es.sebas1705.youknow.core.utlis.extensions.composables.disabled
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

@Composable
fun IOutlinedTextField(
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
    interactionSource: MutableInteractionSource? = null
) = OutlinedTextField(
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
                onClick = it.second,
                contentDescription = label ?: stringResource(R.string.leading_content),
                imageVector = it.first,
                enabled = leadingEnabled
            )
        }
    },
    trailingIcon?.let {
        {
            IStandardIconButton(
                onClick = it.second,
                contentDescription = label ?: stringResource(R.string.trailing_content),
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
    colors = OutlinedTextFieldDefaults.colors(
        //Text
        focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        errorTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant.disabled(),
        //End Text
        //Container
        focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        errorContainerColor = MaterialTheme.colorScheme.surfaceContainer,
        disabledContainerColor = MaterialTheme.colorScheme.surfaceContainer.disabled(),
        //End Container
        //Cursor
        cursorColor = MaterialTheme.colorScheme.primary,
        errorCursorColor = MaterialTheme.colorScheme.error,
        //End Cursor
        //Border
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
        errorBorderColor = MaterialTheme.colorScheme.error,
        disabledBorderColor = MaterialTheme.colorScheme.outline.disabled(),
        //End Border
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
    IOutlinedTextField(
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
    IOutlinedTextField(
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
    IOutlinedTextField(
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

