package es.sebas1705.youknow.presentation.features.settings.design
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

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.classes.theme.ThemeContrast
import es.sebas1705.youknow.core.composables.buttons.common.IFilledButton
import es.sebas1705.youknow.core.composables.buttons.common.IOutlinedButton
import es.sebas1705.youknow.core.composables.buttons.icon.IFilledTonalIconButton
import es.sebas1705.youknow.core.composables.dialogs.LoadingDialog
import es.sebas1705.youknow.core.composables.layouts.ApplyBack
import es.sebas1705.youknow.core.composables.slider.ISlider
import es.sebas1705.youknow.core.composables.spacers.IVerSpacer
import es.sebas1705.youknow.core.composables.texts.IText
import es.sebas1705.youknow.core.composables.texts.TitleSurface
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.extensions.composables.printTextInToast
import es.sebas1705.youknow.core.utlis.extensions.primitives.percentageFormat
import es.sebas1705.youknow.presentation.features.settings.viewmodel.SettingsState
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Design of the Settings Screen.
 *
 * @param WindowState [WindowState]: State of the Window.
 * @param settingsState [SettingsState]: ViewModel for the Settings.
 * @param onBack () -> Unit: Function to go back to the previous screen.
 * @param onVolumeSlideBarChange (Float) -> Unit: Function to change the volume of the app.
 * @param onContrastClick (ThemeContrast) -> Unit: Function to change the contrast of the theme.
 * @param onRestoreClick () -> Unit: Function to restore the default settings.
 *
 * @see WindowState
 * @see SettingsState
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun SettingsDesign(
    windowState: WindowState = WindowState.default(),
    settingsState: SettingsState = SettingsState.default(),
    onBack: () -> Unit = { },
    onVolumeSlideBarChange: (Float) -> Unit = { },
    onContrastClick: (ThemeContrast) -> Unit = { },
    onRestoreClick: () -> Unit = { }
) {
    BackHandler { onBack() }

    var context = LocalContext.current
    var volume by remember { mutableFloatStateOf(settingsState.volume) }

    ApplyBack(
        backId = windowState.backFill
    ) {

        if(settingsState.isLoading)
            LoadingDialog(windowState)

        IFilledTonalIconButton(
            onClick = { onBack() },
            contentDescription = stringResource(R.string.back),
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(MediumPadding)
                .size(windowState.sizeFilter(48.dp, 56.dp, 64.dp))
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IVerSpacer(0.4f)
            TitleSurface(stringResource(R.string.settings_title))
            IVerSpacer(0.2f)
            var titleStyle = windowState.heightType.filter(
                MaterialTheme.typography.titleMedium,
                MaterialTheme.typography.headlineSmall,
                MaterialTheme.typography.headlineLarge
            )
            IText(
                text = stringResource(R.string.volume) +
                        ": ${settingsState.volume.percentageFormat()}",
                color = MaterialTheme.colorScheme.onBackground,
                style = titleStyle
            )
            ISlider(
                modifier = Modifier
                    .weight(windowState.heightType.filter(0.2f, 0.25f, 0.3f))
                    .padding(vertical = MediumPadding)
                    .padding(horizontal = MediumPadding)
                    .fillMaxWidth(windowState.widthType.filter(1f, 0.8f, 0.6f)),
                value = volume,
                onValueChange = { volume = it },
                onValueChangeFinished = {
                    onVolumeSlideBarChange(volume) },
                steps = 39,
            )
            IText(
                text = stringResource(R.string.contrast) +
                        ": ${settingsState.themeContrast.name}",
                color = MaterialTheme.colorScheme.onBackground,
                style = titleStyle
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth(windowState.widthType.filter(1f, 0.8f, 0.6f))
                    .padding(vertical = MediumPadding)
            ) {
                IFilledButton(
                    label = stringResource(R.string.low_contrast),
                    onClick = { onContrastClick(ThemeContrast.Low) },
                )
                IFilledButton(
                    label = stringResource(R.string.medium_contrast),
                    onClick = { onContrastClick(ThemeContrast.Medium) },
                )
                IFilledButton(
                    label = stringResource(R.string.high_contrast),
                    onClick = { onContrastClick(ThemeContrast.High) },
                )
            }
            IVerSpacer(0.1f)
            IOutlinedButton(
                label = stringResource(R.string.reset_defaults),
                imageVector = Icons.Default.Restore,
                onClick = onRestoreClick
            )
            IVerSpacer(0.4f)
        }
    }
}

/**
 * Preview of the Settings Screen.
 *
 * @see SettingsDesign
 */
@UiModePreviews
@Composable
private fun SettingsPreview() {
    YouKnowTheme {
        SettingsDesign()
    }
}