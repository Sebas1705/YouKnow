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

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState

/**
 * Google Fonts provider for the app.
 */
val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

/**
 * Font families used in body texts
 */
val bodyFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Gupter"),
        fontProvider = provider,
    )
)


/**
 * Font families used in display texts
 */
val displayFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Raleway"),
        fontProvider = provider,
    )
)

/**
 * Font families used in title texts
 */
val titleFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Finger Paint"),
        fontProvider = provider,
    )
)

// Default Material 3 typography values
val baseline = Typography()

/**
 * Custom typography for the app.
 */
val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
)

//Customs type filters:
@Composable
fun WindowState.dialogTitleType() = this.sizeFilter(
    MaterialTheme.typography.titleLarge,
    MaterialTheme.typography.headlineSmall,
    MaterialTheme.typography.headlineLarge
)

@Composable
fun WindowState.dialogTextType() = this.sizeFilter(
    MaterialTheme.typography.labelLarge,
    MaterialTheme.typography.bodySmall,
    MaterialTheme.typography.bodyLarge
)
