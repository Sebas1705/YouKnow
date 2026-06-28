package es.sebas1705.main

import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.sebas1705.auth.AuthNav
import es.sebas1705.common.states.WindowState
import es.sebas1705.designsystem.states.rememberWindowState
import es.sebas1705.game.GameNav
import es.sebas1705.guide.GuideScreen
import es.sebas1705.home.navigation.HomeNav
import es.sebas1705.home.play.GameItem.Companion.games
import es.sebas1705.settings.SettingsScreen
import es.sebas1705.survey.SurveyScreen

@Composable
fun AppNav(
    startDestination: AppGraph = AppGraph.SplashScreen,
    windowState: WindowState = WindowState.default(),
    onMusicChange: (Boolean) -> Unit = {},
    soundPool: Pair<SoundPool, Float>? = null,
) {
    // NavController:
    val appNavController = rememberNavController()

    //States:
    var game by rememberSaveable { mutableIntStateOf(0) }

    //Body:
    NavHost(navController = appNavController, startDestination = startDestination) {
    }
}
