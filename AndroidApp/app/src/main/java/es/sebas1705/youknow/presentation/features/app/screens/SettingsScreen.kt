package es.sebas1705.youknow.presentation.features.app.screens
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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.classes.states.WindowState
import es.sebas1705.youknow.core.classes.theme.ThemeContrast
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.percentageFormat
import es.sebas1705.youknow.data.local.datastore.config.DefaultValuesDS
import es.sebas1705.youknow.presentation.composables.ApplyBack
import es.sebas1705.youknow.presentation.composables.CustomFilledButton
import es.sebas1705.youknow.presentation.composables.CustomIconTextButton
import es.sebas1705.youknow.presentation.composables.Spacers.HorizontalSpacer
import es.sebas1705.youknow.presentation.composables.TitleSurface
import es.sebas1705.youknow.presentation.features.app.viewmodels.SettingsIntent
import es.sebas1705.youknow.presentation.features.app.viewmodels.SettingsState
import es.sebas1705.youknow.presentation.features.app.viewmodels.SettingsViewModel
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallestPadding
import es.sebas1705.youknow.presentation.ui.theme.YouKnowTheme

/**
 * Screen for the Settings of the app.
 *
 * @param settingsState [SettingsState]: State of the Settings.
 * @param settingsViewModel [SettingsViewModel]: ViewModel for the Settings.
 * @param onBack () -> Unit: Function to go back to the previous screen.
 *
 * @see SettingsDesign
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
fun SettingsScreen(
    windowState: WindowState,
    settingsState: SettingsState,
    settingsViewModel: SettingsViewModel,
    onBack: () -> Unit
) {
    SettingsDesign(
        windowState,
        settingsState,
        onBack,
        onVolumeSlideBarChange = {
            settingsViewModel.eventHandler(SettingsIntent.ChangeVolume(it))
        },
        onContrastClick = {
            settingsViewModel.eventHandler(SettingsIntent.ChangeContrast(it))
        },
        onSaveClick = {
            settingsViewModel.eventHandler(SettingsIntent.SaveSettings)
        },
        onRestoreClick = {
            settingsViewModel.eventHandler(
                SettingsIntent.ChangeContrast(DefaultValuesDS.APP_UI_CONTRAST)
            )
            settingsViewModel.eventHandler(
                SettingsIntent.ChangeVolume(DefaultValuesDS.APP_VOLUME)
            )
        }
    )
}

/**
 * Design of the Settings Screen.
 * It contains the UI elements of the Settings Screen.
 * It has a [Slider] to change the volume of the app and [CustomFilledButton] to change the contrast of the theme.
 * It also has a [CustomIconTextButton] to save the settings and restore the default settings.
 * It has a [IconButton] to go back to the previous screen.
 *
 * @param settingsViewModel [SettingsViewModel]: ViewModel for the Settings.
 * @param settingsState [SettingsState]: State of the Settings.
 * @param onBack () -> Unit: Function to go back to the previous screen.
 *
 * @see ApplyBack
 * @see IconButton
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Composable
private fun SettingsDesign(
    windowState: WindowState = WindowState.default(),
    settingsState: SettingsState = SettingsState.default(),
    onBack: () -> Unit = { },
    onVolumeSlideBarChange: (Float) -> Unit = { },
    onContrastClick: (ThemeContrast) -> Unit = { },
    onSaveClick: () -> Unit = { },
    onRestoreClick: () -> Unit = { }
) {
    BackHandler { onBack() }

    ApplyBack(
        backId = windowState.backFill
    ) {
        IconButton(
            modifier = Modifier
                .padding(SmallestPadding)
                .background(MaterialTheme.colorScheme.primaryContainer, CircleShape)
                .padding(SmallestPadding / 2)
                .align(Alignment.TopStart)
                .size(40.dp),
            onClick = { onBack() }) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                tint = MaterialTheme.colorScheme.tertiary,
                contentDescription = "Back",
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalSpacer(0.4f)
            TitleSurface(text = stringResource(R.string.settings_title))
            HorizontalSpacer(0.2f)
            var titleStyle = windowState.heightType.filter(
                MaterialTheme.typography.titleSmall,
                MaterialTheme.typography.titleLarge,
                MaterialTheme.typography.headlineMedium
            )
            Text(
                text = stringResource(R.string.volume) +
                        ": ${settingsState.volume.percentageFormat()}",
                color = MaterialTheme.colorScheme.onBackground,
                style = titleStyle
            )
            Slider(
                modifier = Modifier
                    .weight(windowState.heightType.filter(0.2f, 0.25f, 0.3f))
                    .padding(vertical = MediumPadding)
                    .padding(horizontal = MediumPadding)
                    .fillMaxWidth(windowState.widthType.filter(1f, 0.8f, 0.6f)),
                value = settingsState.volume,
                onValueChange = onVolumeSlideBarChange,
                steps = 39,
            )
            Text(
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
                CustomFilledButton(
                    text = stringResource(R.string.low_contrast),
                    onClick = { onContrastClick(ThemeContrast.Low) },
                )
                CustomFilledButton(
                    text = stringResource(R.string.medium_contrast),
                    onClick = { onContrastClick(ThemeContrast.Medium) },
                )
                CustomFilledButton(
                    text = stringResource(R.string.high_contrast),
                    onClick = { onContrastClick(ThemeContrast.High) },
                )
            }
            HorizontalSpacer(0.1f)
            CustomIconTextButton(
                modifier = Modifier
                    .weight(windowState.heightType.filter(0.1f, 0.15f, 0.2f))
                    .fillMaxWidth(windowState.widthType.filter(1f, 0.8f, 0.6f)),
                text = stringResource(R.string.save_settings),
                imageVector = Icons.Default.Save,
                contentColor = MaterialTheme.colorScheme.tertiary,
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                onClick = onSaveClick
            )
            HorizontalSpacer(0.1f)
            CustomIconTextButton(
                modifier = Modifier
                    .weight(windowState.heightType.filter(0.1f, 0.15f, 0.2f))
                    .fillMaxWidth(windowState.widthType.filter(1f, 0.8f, 0.6f)),
                text = stringResource(R.string.reset_defaults),
                imageVector = Icons.Default.Restore,
                contentColor = MaterialTheme.colorScheme.tertiary,
                backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                onClick = onRestoreClick
            )
            HorizontalSpacer(0.4f)
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

