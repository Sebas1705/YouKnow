package es.sebas1705.youknow.presentation.composables
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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import java.util.Locale


@Composable
fun CustomIconButton(
    onClick: () -> Unit,
    icon: ImageVector,
    modifierButton: Modifier = Modifier,
    modifierIcon: Modifier = Modifier,
    contentDescription: String = "IconButton",
    tint: Color = LocalContentColor.current,
    enabled: Boolean = true
){
    IconButton(
        modifier = modifierButton,
        onClick = onClick,
        enabled = enabled
    ) {
        Icon(
            modifier = modifierIcon,
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tint
        )
    }
}


/**
 * Custom button with an icon and text
 *
 * @param modifier [Modifier]: Modifier for button
 * @param text [String]: Text for button
 * @param contentDescription [String]: Content description for button
 * @param icon [Painter]: Icon for button
 * @param imageVector [ImageVector]: Image vector for button
 * @param contentColor [Color]: Color for button
 * @param onClick ()->Unit: Action for button
 *
 * @see ImageVector
 * @see Painter
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun CustomIconTextButton(
    modifier: Modifier = Modifier,
    text: String,
    contentDescription: String = "",
    icon: Painter? = null,
    imageVector: ImageVector = Icons.Default.Menu,
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
    backgroundColor: Color = Color.Transparent,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .background(backgroundColor,CircleShape)
            .border(2.dp, contentColor, CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.CenterStart
    ) {
        val modifier = Modifier.padding(
            top = SmallestPadding,
            bottom = SmallestPadding,
            start = SmallPadding
        ).fillMaxHeight().fillMaxWidth(0.1f)
        if(icon != null) Image(
            painter = icon,
            contentDescription = contentDescription,
            modifier = modifier
        )else Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = contentColor,
            modifier = modifier
        )
        Text(
            text = text,
            color = contentColor,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * Custom filled button
 *
 * @param modifier [Modifier]: Modifier for button
 * @param text [String]: Text for button
 * @param colors [ButtonColors]: Colors for button
 * @param onClick ()->Unit: Action for button
 *
 * @see ButtonColors
 * @see Button
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun CustomFilledButton(
    modifier: Modifier = Modifier,
    text: String,
    colors: ButtonColors = ButtonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
        disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
    ),
    onClick: ()->Unit,
    enabled: Boolean = true
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = colors,
        shape = MaterialTheme.shapes.medium,
        enabled = enabled
    ){
        Text(
            text = text.uppercase(Locale.ROOT),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

/**
 * Custom text button
 *
 * @param modifier [Modifier]: Modifier for button
 * @param text [String]: Text for button
 * @param textStyle [TextStyle]: Text style for button
 * @param onClick ()->Unit: Action for button
 *
 * @see TextStyle
 * @see TextButton
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun CustomTextButton(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
    onClick: ()->Unit
){
    TextButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text = text,
            style = textStyle,
            color = MaterialTheme.colorScheme.primary
        )
    }
}