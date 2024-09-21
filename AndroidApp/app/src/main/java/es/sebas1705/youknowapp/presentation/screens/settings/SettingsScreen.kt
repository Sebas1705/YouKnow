package es.sebas1705.youknowapp.presentation.screens.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.common.Previews
import es.sebas1705.youknowapp.data.source.local.localUserManager.DefaultValues
import es.sebas1705.youknowapp.domain.usecases.AuthUsesCases
import es.sebas1705.youknowapp.presentation.common.buttons.CustomFilledButton
import es.sebas1705.youknowapp.presentation.common.customs.ApplyBack
import es.sebas1705.youknowapp.presentation.viewmodel.SettingsViewModel
import es.sebas1705.youknowapp.ui.Contrast
import es.sebas1705.youknowapp.ui.theme.CurvedShape
import es.sebas1705.youknowapp.ui.theme.HugePadding
import es.sebas1705.youknowapp.ui.theme.MediumPadding
import es.sebas1705.youknowapp.ui.theme.SmallPadding
import es.sebas1705.youknowapp.ui.theme.YouKnowTheme
import javax.inject.Inject

@Composable
fun SettingsScreen() {
    val settingsViewModel: SettingsViewModel = hiltViewModel()
    SettingsDesign(settingsViewModel)
}

@Composable
private fun SettingsDesign(
    settingsViewModel: SettingsViewModel? = null
) {
    ApplyBack(
        backId = R.drawable.back_fill
    ) {
        var volume by remember { mutableFloatStateOf(settingsViewModel?.volumeApp ?: 0.5f) }
        var contrast by remember { mutableStateOf(settingsViewModel?.contrastApp ?: Contrast.Low) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                color = MaterialTheme.colorScheme.primary,
                shape = CurvedShape,
                shadowElevation = HugePadding,
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground),
            ) {
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.displayMedium
                        .copy(fontWeight = FontWeight.ExtraBold),
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(vertical = 24.dp,horizontal = 16.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = stringResource(R.string.volume) + ": $volume",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleLarge,
                )
                Slider(
                    value = volume,
                    onValueChange = { volume = it },
                    modifier = Modifier
                        .padding(horizontal = SmallPadding)
                        .padding(bottom = MediumPadding)
                )
                Text(
                    modifier = Modifier.padding(bottom = 20.dp),
                    text = stringResource(R.string.contrast) + ": $contrast",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CustomFilledButton(
                        text = "Low",
                        onClick = { contrast = Contrast.Low },
                    )
                    CustomFilledButton(
                        text = "Medium",
                        onClick = { contrast = Contrast.Medium },
                    )
                    CustomFilledButton(
                        text = "High",
                        onClick = { contrast = Contrast.High },
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomFilledButton(
                    text = "Save Settings",
                    onClick = {
                        settingsViewModel?.changeVolume(volume)
                        settingsViewModel?.changeContrast(contrast)
                        volume = settingsViewModel?.volumeApp ?: 0.5f
                        contrast = settingsViewModel?.contrastApp ?: Contrast.Low
                    }
                )
                CustomFilledButton(
                    text = "Restore Default Settings",
                    onClick = {
                        volume = DefaultValues.APP_VOLUME
                        contrast = DefaultValues.APP_CONTRAST
                    }
                )
            }
        }
    }
}

@Previews
@Composable
private fun SettingsPreview() {
    YouKnowTheme {
        SettingsDesign()
    }
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authUsesCases: AuthUsesCases
) : ViewModel() {

    fun getUserLogged(): FirebaseUser? {
        return authUsesCases.getCurrentUser()
    }

    fun isUserLogged(): Boolean {
        return authUsesCases.isUserLogged()
    }
}