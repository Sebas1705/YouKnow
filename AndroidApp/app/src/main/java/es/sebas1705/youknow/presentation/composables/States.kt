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

import android.content.res.Configuration
import android.view.ViewTreeObserver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.classes.theme.SizeType
import es.sebas1705.youknow.core.utlis.toDp

/**
 * Remember the state of the window
 *
 * @return MutableState<WindowState>
 *
 * @see WindowState
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun rememberWindowState(): MutableState<WindowState> {

    val windowState = remember { mutableStateOf(WindowState.default()) }

    val view = LocalView.current
    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val height = view.height.toDp(view.context)
            val width = view.width.toDp(view.context)
            val portrait =
                view.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
            windowState.value = WindowState(
                widthDp = width,
                heightDp = height,
                widthType = SizeType.getWidth(width),
                heightType = SizeType.getHeight(height),
                isImeVisible = ViewCompat.getRootWindowInsets(view)
                    ?.isVisible(WindowInsetsCompat.Type.ime()) != false,
                isPortrait = portrait,
                backFill = if (portrait) R.drawable.back_portrait_fill else R.drawable.back_landscape_fill,
                backEmpty = if (portrait) R.drawable.back_portrait_empty else R.drawable.back_landscape_empty
            )
        }

        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
    return windowState
}