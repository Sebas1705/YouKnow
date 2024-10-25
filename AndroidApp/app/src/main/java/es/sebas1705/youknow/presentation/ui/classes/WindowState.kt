package es.sebas1705.youknow.presentation.ui.classes
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

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.utlis.PreviewSettings

/**
 * Data class that represents the state of window and its properties for design purposes.
 *
 * @param widthDp Dp: Width of the window.
 * @param heightDp Dp: Height of the window.
 * @param widthType SizeType: Type of the width.
 * @param heightType SizeType: Type of the height.
 * @param isImeVisible Boolean: True if the IME is visible, false otherwise.
 * @param isPortrait Boolean: True if the window is in portrait mode, false otherwise.
 * @param backFill Int: Resource of the background image for the fill state.
 * @param backEmpty Int: Resource of the background image for the empty state.
 * @param isLandscapeAndIme Boolean: True if the window is in landscape mode and the IME is visible, false otherwise.
 *
 * @see SizeType
 * @see Dp
 *
 * @author: Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class WindowState(
    val widthDp: Dp = PreviewSettings.WIDTH.dp,
    val heightDp: Dp = PreviewSettings.HEIGHT.dp,
    val widthType: SizeType = SizeType.COMPACT,
    val heightType: SizeType = SizeType.COMPACT,
    val isImeVisible: Boolean = false,
    val isPortrait: Boolean = true,
    val backFill: Int = R.drawable.back_portrait_fill,
    val backEmpty: Int = R.drawable.back_portrait_empty,
    val isLandscapeAndIme: Boolean = !isPortrait and isImeVisible
)