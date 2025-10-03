package es.sebas1705.settings.design

import android.media.SoundPool
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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.theme.ThemeContrast
import es.sebas1705.common.utlis.UiModePreviews
import es.sebas1705.common.utlis.extensions.primitives.percentageFormat
import es.sebas1705.designsystem.buttons.common.IFilledButton
import es.sebas1705.designsystem.buttons.common.IOutlinedButton
import es.sebas1705.designsystem.buttons.icon.IFilledTonalIconButton
import es.sebas1705.designsystem.extras.DropdownList
import es.sebas1705.designsystem.layouts.ApplyBack
import es.sebas1705.designsystem.slider.ISlider
import es.sebas1705.designsystem.spacers.IVerSpacer
import es.sebas1705.designsystem.texts.IText
import es.sebas1705.designsystem.texts.TitleSurface
import es.sebas1705.feature.settings.R
import es.sebas1705.resources.games.Languages
import es.sebas1705.settings.viewmodel.SettingsState
import es.sebas1705.ui.theme.AppTheme
import es.sebas1705.ui.theme.Paddings.MediumPadding
import es.sebas1705.designsystem.dialogs.LoadingDialog

/**
 * Design of the Settings Screen.
 *
 * @param windowState [WindowState]: State of the Window.
 * @param settingsState [SettingsState]: ViewModel for the Settings.
 * @param onBack () -> Unit: Function to go back to the previous screen.
 * @param onMusicVolumeSlideBarChange (Float) -> Unit: Function to change the music volume of the app.
 * @param onSoundVolumeSliderBarChange (Float) -> Unit: Function to change the sound volume of the app.
 * @param onContrastClick (ThemeContrast) -> Unit: Function to change the contrast of the theme.
 * @param onRestoreClick () -> Unit: Function to restore the default settings.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun SettingsDesign(
    windowState: WindowState = WindowState.default(),
    settingsState: SettingsState = SettingsState.default(),
    isLoading: Boolean = false,
    soundPool: Pair<SoundPool, Float>? = null,
    onBack: () -> Unit = { },
    onMusicVolumeSlideBarChange: (Float) -> Unit = { },
    onSoundVolumeSliderBarChange: (Float) -> Unit = { },
    onContrastClick: (ThemeContrast) -> Unit = { },
    onLanguageClick: (Languages) -> Unit = { },
    onRestoreClick: () -> Unit = { }
) {
    //Local:
    BackHandler { onBack() }

    //States:
    var music by rememberSaveable { mutableFloatStateOf(settingsState.settings.musicVolume) }
    var sound by rememberSaveable { mutableFloatStateOf(settingsState.settings.soundVolume) }

    //Effects:
    LaunchedEffect(settingsState.settings) {
        music = settingsState.settings.musicVolume
        sound = settingsState.settings.soundVolume
    }

    //Body:
    ApplyBack(
        backId = windowState.backFill
    ) {

        if (isLoading)
            LoadingDialog(windowState)

        IFilledTonalIconButton(
            onClick = { onBack() },
            contentDescription = stringResource(es.sebas1705.core.resources.R.string.core_resources_back),
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(MediumPadding)
                .size(windowState.sizeFilter(48.dp, 56.dp, 64.dp)),
            soundPool = soundPool
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IVerSpacer(0.4f)
            TitleSurface(stringResource(R.string.feature_settings_settings_title))
            IVerSpacer(0.2f)
            val titleStyle = windowState.heightType.filter(
                MaterialTheme.typography.titleMedium,
                MaterialTheme.typography.headlineSmall,
                MaterialTheme.typography.headlineLarge
            )
            IText(
                text = stringResource(R.string.feature_settings_music) +
                        ": ${settingsState.settings.musicVolume.percentageFormat()}",
                color = MaterialTheme.colorScheme.onBackground,
                style = titleStyle
            )
            ISlider(
                modifier = Modifier
                    .weight(windowState.heightType.filter(0.2f, 0.25f, 0.3f))
                    .padding(vertical = MediumPadding)
                    .padding(horizontal = MediumPadding)
                    .fillMaxWidth(windowState.widthType.filter(1f, 0.8f, 0.6f)),
                value = music,
                onValueChange = { music = it },
                onValueChangeFinished = {
                    onMusicVolumeSlideBarChange(music)
                },
                steps = 39,
            )
            IText(
                text = stringResource(R.string.feature_settings_sound) +
                        ": ${settingsState.settings.soundVolume.percentageFormat()}",
                color = MaterialTheme.colorScheme.onBackground,
                style = titleStyle
            )
            ISlider(
                modifier = Modifier
                    .weight(windowState.heightType.filter(0.2f, 0.25f, 0.3f))
                    .padding(vertical = MediumPadding)
                    .padding(horizontal = MediumPadding)
                    .fillMaxWidth(windowState.widthType.filter(1f, 0.8f, 0.6f)),
                value = sound,
                onValueChange = { sound = it },
                onValueChangeFinished = {
                    onSoundVolumeSliderBarChange(sound)
                },
                steps = 39,
            )
            IText(
                text = stringResource(R.string.feature_settings_contrast) +
                        ": ${settingsState.settings.appContrast.name}",
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
                    label = stringResource(R.string.feature_settings_low_contrast),
                    onClick = { onContrastClick(ThemeContrast.Low) },
                    soundPool = soundPool
                )
                IFilledButton(
                    label = stringResource(R.string.feature_settings_medium_contrast),
                    onClick = { onContrastClick(ThemeContrast.Medium) },
                    soundPool = soundPool
                )
                IFilledButton(
                    label = stringResource(R.string.feature_settings_high_contrast),
                    onClick = { onContrastClick(ThemeContrast.High) },
                    soundPool = soundPool
                )
            }
            IVerSpacer(0.1f)
            IText(
                text = stringResource(R.string.feature_settings_Game_language) +
                        ": ${settingsState.settings.language.name}",
                color = MaterialTheme.colorScheme.onBackground,
                style = titleStyle
            )
            DropdownList(
                modifier = Modifier
                    .fillMaxWidth(
                        windowState.widthFilter(0.9f, 0.7f, 0.5f)
                    )
                    .padding(vertical = MediumPadding),
                valueRes = settingsState.settings.language.strRes,
                onValueChange = { },
                soundPool = soundPool
            ) { onChanged ->
                Languages.entries.forEach {
                    DropdownMenuItem(
                        text = { Text(stringResource(it.strRes)) },
                        onClick = {
                            onChanged(it.strRes)
                            val index = Languages.entries.indexOfFirst { option ->
                                it == option
                            }
                            onLanguageClick(Languages.entries[index])
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
            IVerSpacer(0.1f)
            IOutlinedButton(
                label = stringResource(R.string.feature_settings_reset_defaults),
                imageVector = Icons.Default.Restore,
                onClick = onRestoreClick,
                soundPool = soundPool
            )
            IVerSpacer(0.4f)
        }
    }
}

@UiModePreviews
@Composable
private fun SettingsPreview() {
    AppTheme {
        SettingsDesign()
    }
}