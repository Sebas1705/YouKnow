package es.sebas1705.youknowapp.presentation.screens.splash

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.Firebase
import com.google.firebase.analytics.analytics
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.common.Constants
import es.sebas1705.youknowapp.common.Previews
import es.sebas1705.youknowapp.presentation.navigation.AppNav
import es.sebas1705.youknowapp.presentation.viewmodel.SettingsViewModel
import es.sebas1705.youknowapp.presentation.viewmodel.SplashViewModel
import es.sebas1705.youknowapp.ui.theme.YouKnowTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {

    val splashViewModel: SplashViewModel = hiltViewModel()
    val settingsViewModel: SettingsViewModel = hiltViewModel()
    var progress by remember { mutableFloatStateOf(0.0f) }
    var time by remember { mutableLongStateOf(0L) }

    LaunchedEffect(Unit) {
        val onChargingInc: (Float) -> Unit = { progress += it }
        val onTimeMeasured: (Long) -> Unit = { time += it }
        settingsViewModel.chargeSettings(0.5f, onChargingInc, onTimeMeasured)
        splashViewModel.chargeCloudData(0.5f, onChargingInc, onTimeMeasured)
        delay(2000)
        //splashViewModel.logTime(time)
        Firebase.analytics.logEvent("splash_screen_time", Bundle().apply {
            putLong("time", time)
        })

        Log.d("SplashScreen", "Time measured: $time")
        splashViewModel.isSplashScreenVisible = false
    }

    YouKnowTheme(
        contrast = settingsViewModel.contrastApp
    ) {
        if (splashViewModel.isSplashScreenVisible) SplashDesign { progress }
        else AppNav(splashViewModel.startDestination)
    }
}

@Composable
private fun SplashDesign(
    onProgress: () -> Float = { 0.5f }
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painter = painterResource(R.drawable.icon),
                contentDescription = stringResource(id = R.string.app_name),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.primary,
            )

            Spacer(modifier = Modifier.height(32.dp))

            LinearProgressIndicator(
                progress = { onProgress() },
            )
        }
    }
}

@Previews
@Composable
fun SplashPreview() {
    YouKnowTheme(
        contrast = Constants.PREVIEWS_CONTRAST
    ) {
        SplashDesign()
    }
}
