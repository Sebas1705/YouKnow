package es.sebas1705.youknow.core.composables.spacers
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

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.LargePadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.HugePadding

object PaddingSpacers {

    @Composable
    fun SmallestSpacer(vertical: Boolean = true) = Spacer(
        modifier =
            if(vertical)
                Modifier.height(SmallestPadding)
            else
                Modifier.width(SmallestPadding)
    )

    @Composable
    fun SmallSpacer(vertical: Boolean = true) = Spacer(
        modifier =
            if(vertical)
                Modifier.height(SmallPadding)
            else
                Modifier.width(SmallPadding)
    )

    @Composable
    fun MediumSpacer(vertical: Boolean = true) = Spacer(
        modifier =
            if(vertical)
                Modifier.height(MediumPadding)
            else
                Modifier.width(MediumPadding)
    )

    @Composable
    fun LargeSpacer(vertical: Boolean = true) = Spacer(
        modifier =
            if(vertical)
                Modifier.height(LargePadding)
            else
                Modifier.width(LargePadding)
    )

    @Composable
    fun HugeSpacer(vertical: Boolean = true) = Spacer(
        modifier =
            if(vertical)
                Modifier.height(HugePadding)
            else
                Modifier.width(HugePadding)
    )
}