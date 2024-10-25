package es.sebas1705.youknow.presentation.ui.theme
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

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import es.sebas1705.youknow.presentation.ui.classes.ThemeContrast

private val lowContrastLightScheme = lightColorScheme(
    primary = primaryLightLowContrast,
    onPrimary = onPrimaryLightLowContrast,
    primaryContainer = primaryContainerLightLowContrast,
    onPrimaryContainer = onPrimaryContainerLightLowContrast,
    secondary = secondaryLightLowContrast,
    onSecondary = onSecondaryLightLowContrast,
    secondaryContainer = secondaryContainerLightLowContrast,
    onSecondaryContainer = onSecondaryContainerLightLowContrast,
    tertiary = tertiaryLightLowContrast,
    onTertiary = onTertiaryLightLowContrast,
    tertiaryContainer = tertiaryContainerLightLowContrast,
    onTertiaryContainer = onTertiaryContainerLightLowContrast,
    error = errorLightLowContrast,
    onError = onErrorLightLowContrast,
    errorContainer = errorContainerLightLowContrast,
    onErrorContainer = onErrorContainerLightLowContrast,
    background = backgroundLightLowContrast,
    onBackground = onBackgroundLightLowContrast,
    surface = surfaceLightLowContrast,
    onSurface = onSurfaceLightLowContrast,
    surfaceVariant = surfaceVariantLightLowContrast,
    onSurfaceVariant = onSurfaceVariantLightLowContrast,
    outline = outlineLightLowContrast,
    outlineVariant = outlineVariantLightLowContrast,
    scrim = scrimLightLowContrast,
    inverseSurface = inverseSurfaceLightLowContrast,
    inverseOnSurface = inverseOnSurfaceLightLowContrast,
    inversePrimary = inversePrimaryLightLowContrast,
    surfaceDim = surfaceDimLightLowContrast,
    surfaceBright = surfaceBrightLightLowContrast,
    surfaceContainerLowest = surfaceContainerLowestLightLowContrast,
    surfaceContainerLow = surfaceContainerLowLightLowContrast,
    surfaceContainer = surfaceContainerLightLowContrast,
    surfaceContainerHigh = surfaceContainerHighLightLowContrast,
    surfaceContainerHighest = surfaceContainerHighestLightLowContrast,
)

private val lowContrastDarkScheme = darkColorScheme(
    primary = primaryDarkLowContrast,
    onPrimary = onPrimaryDarkLowContrast,
    primaryContainer = primaryContainerDarkLowContrast,
    onPrimaryContainer = onPrimaryContainerDarkLowContrast,
    secondary = secondaryDarkLowContrast,
    onSecondary = onSecondaryDarkLowContrast,
    secondaryContainer = secondaryContainerDarkLowContrast,
    onSecondaryContainer = onSecondaryContainerDarkLowContrast,
    tertiary = tertiaryDarkLowContrast,
    onTertiary = onTertiaryDarkLowContrast,
    tertiaryContainer = tertiaryContainerDarkLowContrast,
    onTertiaryContainer = onTertiaryContainerDarkLowContrast,
    error = errorDarkLowContrast,
    onError = onErrorDarkLowContrast,
    errorContainer = errorContainerDarkLowContrast,
    onErrorContainer = onErrorContainerDarkLowContrast,
    background = backgroundDarkLowContrast,
    onBackground = onBackgroundDarkLowContrast,
    surface = surfaceDarkLowContrast,
    onSurface = onSurfaceDarkLowContrast,
    surfaceVariant = surfaceVariantDarkLowContrast,
    onSurfaceVariant = onSurfaceVariantDarkLowContrast,
    outline = outlineDarkLowContrast,
    outlineVariant = outlineVariantDarkLowContrast,
    scrim = scrimDarkLowContrast,
    inverseSurface = inverseSurfaceDarkLowContrast,
    inverseOnSurface = inverseOnSurfaceDarkLowContrast,
    inversePrimary = inversePrimaryDarkLowContrast,
    surfaceDim = surfaceDimDarkLowContrast,
    surfaceBright = surfaceBrightDarkLowContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkLowContrast,
    surfaceContainerLow = surfaceContainerLowDarkLowContrast,
    surfaceContainer = surfaceContainerDarkLowContrast,
    surfaceContainerHigh = surfaceContainerHighDarkLowContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkLowContrast,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

/**
 * Custom theme for the app.
 *
 * @param darkTheme whether the theme should be dark or light.
 * @param themeContrast the contrast level of the theme.
 * @param content the content of the theme.
 *
 * @see ThemeContrast
 *
 * @author: Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun YouKnowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    themeContrast: ThemeContrast = ThemeContrast.Low,
    content: @Composable () -> Unit
) {
    val colors = when (themeContrast) {
        ThemeContrast.Low -> when {
            darkTheme -> lowContrastDarkScheme
            else -> lowContrastLightScheme
        }

        ThemeContrast.Medium -> when {
            darkTheme -> mediumContrastDarkColorScheme
            else -> mediumContrastLightColorScheme
        }

        ThemeContrast.High -> when {
            darkTheme -> highContrastDarkColorScheme
            else -> highContrastLightColorScheme
        }
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = !darkTheme
        }
    }
    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        shapes = appShapes,
        content = content
    )
}

