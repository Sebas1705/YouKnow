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
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import es.sebas1705.youknow.R
import es.sebas1705.youknow.core.utlis.UiModePreviews
import es.sebas1705.youknow.core.utlis.twoDecimalFormat
import es.sebas1705.youknow.data.local.datastore.config.DefaultValuesDS
import es.sebas1705.youknow.presentation.composables.ApplyBack
import es.sebas1705.youknow.presentation.composables.CustomEmptyIconButton
import es.sebas1705.youknow.presentation.composables.CustomFilledButton
import es.sebas1705.youknow.presentation.composables.Spacers.SimpleSpacer
import es.sebas1705.youknow.presentation.features.app.viewmodels.SettingsIntent
import es.sebas1705.youknow.presentation.features.app.viewmodels.SettingsState
import es.sebas1705.youknow.presentation.features.app.viewmodels.SettingsViewModel
import es.sebas1705.youknow.presentation.ui.classes.ThemeContrast
import es.sebas1705.youknow.presentation.ui.theme.CurvedShape
import es.sebas1705.youknow.presentation.ui.theme.Paddings.HugePadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.MediumPadding
import es.sebas1705.youknow.presentation.ui.theme.Paddings.SmallPadding
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
    settingsState: SettingsState,
    settingsViewModel: SettingsViewModel,
    onBack: () -> Unit
) {
    SettingsDesign(
        settingsViewModel,
        settingsState,
        onBack
    )
}

/**
 * Design of the Settings Screen.
 * It contains the UI elements of the Settings Screen.
 * It has a [Slider] to change the volume of the app and [CustomFilledButton] to change the contrast of the theme.
 * It also has a [CustomEmptyIconButton] to save the settings and restore the default settings.
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
    settingsViewModel: SettingsViewModel? = null,
    settingsState: SettingsState? = null,
    onBack: () -> Unit = { }
) {
    BackHandler { onBack() }

    ApplyBack(
        backId = R.drawable.back_portrait_empty
    ) {
        IconButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(80.dp)
                .padding(SmallestPadding),
            onClick = { onBack() }) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = "Back"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = CurvedShape,
                shadowElevation = HugePadding,
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
            ) {
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.displayMedium
                        .copy(fontWeight = FontWeight.ExtraBold, fontStyle = FontStyle.Italic),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = stringResource(R.string.volume) +
                            ": ${settingsState?.volume?.twoDecimalFormat() ?: "0.50"}",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleLarge,
                )
                Slider(
                    modifier = Modifier
                        .padding(horizontal = SmallPadding)
                        .padding(bottom = MediumPadding),
                    value = settingsState?.volume ?: 0.5f,
                    onValueChange = {
                        settingsViewModel?.eventHandler(
                            SettingsIntent.ChangeVolume(it)
                        )
                    },
                    steps = 19,
                )
                Text(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = stringResource(R.string.contrast) +
                            ": ${settingsState?.themeContrast?.name ?: "Low"}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CustomFilledButton(
                        text = "Low",
                        onClick = {
                            settingsViewModel?.eventHandler(
                                SettingsIntent.ChangeContrast(ThemeContrast.Low)
                            )
                        },
                    )
                    CustomFilledButton(
                        text = "Medium",
                        onClick = {
                            settingsViewModel?.eventHandler(
                                SettingsIntent.ChangeContrast(ThemeContrast.Medium)
                            )
                        },
                    )
                    CustomFilledButton(
                        text = "High",
                        onClick = {
                            settingsViewModel?.eventHandler(
                                SettingsIntent.ChangeContrast(ThemeContrast.High)
                            )
                        },
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomEmptyIconButton(
                    text = "Save Settings",
                    imageVector = Icons.Default.Save,
                    contentColor = MaterialTheme.colorScheme.tertiary,
                    onClick = {
                        settingsViewModel?.eventHandler(SettingsIntent.SaveSettings)
                    }
                )
                SimpleSpacer()
                CustomEmptyIconButton(
                    text = "Restore Default Settings",
                    imageVector = Icons.Default.Restore,
                    contentColor = MaterialTheme.colorScheme.tertiary,
                    onClick = {
                        settingsViewModel?.eventHandler(
                            SettingsIntent.ChangeContrast(DefaultValuesDS.APP_UI_CONTRAST)
                        )
                        settingsViewModel?.eventHandler(
                            SettingsIntent.ChangeVolume(DefaultValuesDS.APP_VOLUME)
                        )
                    }
                )
            }
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

