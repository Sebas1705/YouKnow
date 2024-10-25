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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.presentation.ui.theme.CurvedShape
import es.sebas1705.youknow.presentation.ui.theme.Paddings.HugePadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.LargePadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.titleFontFamily

/**
 * Custom background with a back image and a content
 *
 * @param backId [Int]: Resource id of the back image
 * @param modifier [Modifier]: Modifier
 * @param paddingValues [PaddingValues]: PaddingValues
 * @param applyCondition [Boolean]: Apply condition
 * @param content BoxScope.() -> Unit: Content
 *
 * @see Box
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun ApplyBack(
    backId: Int,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    applyCondition: Boolean = true,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize().padding(paddingValues)
    ) {
        if (applyCondition) Image(
            painter = painterResource(id = backId),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        content()
    }
}

/**
 * Customs Spacers components created with paddings values
 *
 * @property HalfSpacer: HalfSpacer with SmallestPadding
 * @property SimpleSpacer: SimpleSpacer with SmallPadding
 * @property DoubleSpacer: DoubleSpacer with MediumPadding
 * @property TripleSpacer: TripleSpacer with LargePadding
 * @property QuadSpacer: QuadSpacer with HugePadding
 *
 * @property HorizontalSpacer: HorizontalSpacer with weight in ColumnScope
 * @property VerticalSpacer: VerticalSpacer with weight in RowScope
 *
 * @see Spacer
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
object Spacers{

    @Composable
    fun HalfSpacer() = Spacer(modifier = Modifier.height(SmallestPadding))

    @Composable
    fun SimpleSpacer() = Spacer(modifier = Modifier.height(SmallPadding))

    @Composable
    fun DoubleSpacer() = Spacer(modifier = Modifier.height(MediumPadding))

    @Composable
    fun TripleSpacer() = Spacer(modifier = Modifier.height(LargePadding))

    @Composable
    fun QuadSpacer() = Spacer(modifier = Modifier.height(HugePadding))

    @Composable
    fun ColumnScope.HorizontalSpacer(weight: Float) = Spacer(modifier = Modifier.fillMaxWidth().weight(weight))

    @Composable
    fun RowScope.VerticalSpacer(weight: Float) = Spacer(modifier = Modifier.fillMaxWidth().weight(weight))
}

/**
 * Custom TitleSurface component
 *
 * @param modifier [Modifier]: Modifier
 * @param text [String]: Text
 *
 * @see Surface
 * @see Text
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun TitleSurface(
    modifier: Modifier = Modifier,
    text: String,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = CurvedShape,
        shadowElevation = HugePadding,
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.displayMedium.copy(fontFamily = titleFontFamily, fontStyle = FontStyle.Italic),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp)
        )
    }
}
