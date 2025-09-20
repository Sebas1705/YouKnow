package es.sebas1705.main


import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.sebas1705.auth.AuthNav
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.extensions.composables.navAndPopUp
import es.sebas1705.game.GameNav
import es.sebas1705.guide.GuideScreen
import es.sebas1705.home.navigation.HomeNav
import es.sebas1705.home.play.GameItem.Companion.games
import es.sebas1705.settings.SettingsScreen
import es.sebas1705.survey.SurveyScreen

/**
 * Navigation for the app.
 *
 * @param startDestination [AppGraph]: Start destination of the app.
 * @param windowState [WindowState]: State of the window.
 * @param onMusicChange (Boolean) -> Unit: Function that will change the music.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun AppNav(
    startDestination: AppGraph,
    windowState: WindowState,
    onMusicChange: (Boolean) -> Unit,
    soundPool: Pair<SoundPool, Float>,
) {
    // NavController:
    val appNavController = rememberNavController()

    //States:
    var game by rememberSaveable { mutableIntStateOf(0) }

    //Body:
    NavHost(navController = appNavController, startDestination = startDestination) {
        composable<AppGraph.GuideScreen> {
            GuideScreen(
                windowState,
                soundPool,
                onSuccessNavigation = {
                    appNavController.navigate(
                        AppGraph.AuthNavigation
                    )
                }
            )
        }
        composable<AppGraph.SettingsScreen> {
            SettingsScreen(
                windowState,
                soundPool,
                onBack = {
                    appNavController.navAndPopUp(AppGraph.HomeNavigation, AppGraph.SettingsScreen)
                }
            )
        }
        composable<AppGraph.SurveyScreen> {
            SurveyScreen(
                windowState,
                soundPool,
                onBack = {
                    appNavController.navAndPopUp(AppGraph.HomeNavigation, AppGraph.SurveyScreen)
                },
            )
        }
        composable<AppGraph.HomeNavigation> {
            HomeNav(
                windowState,
                soundPool,
                onAuthNav = {
                    appNavController.navAndPopUp(
                        AppGraph.AuthNavigation,
                        AppGraph.HomeNavigation
                    )
                },
                onSettingsNav = {
                    appNavController.navigate(AppGraph.SettingsScreen)
                },
                onGameNav = {
                    game = it
                    appNavController.navigate(AppGraph.GameNavigation)
                }
            )
        }
        composable<AppGraph.GameNavigation> {
            GameNav(
                windowState,
                soundPool,
                onMusicChange,
                game,
                onOutGameNavigation = {
                    appNavController.navAndPopUp(
                        AppGraph.HomeNavigation,
                        AppGraph.GameNavigation
                    )
                },
            )
        }
        composable<AppGraph.AuthNavigation> {
            AuthNav(
                windowState,
                soundPool,
                toHomeNav = {
                    appNavController.navAndPopUp(
                        AppGraph.HomeNavigation,
                        AppGraph.AuthNavigation
                    )
                }
            )
        }
    }
}

