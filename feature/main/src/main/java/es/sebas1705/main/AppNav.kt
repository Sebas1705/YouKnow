package es.sebas1705.main

import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import es.sebas1705.auth.AuthNav
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.extensions.primitives.pushAndFree
import es.sebas1705.game.GameNav
import es.sebas1705.guide.GuideScreen
import es.sebas1705.home.navigation.HomeNav
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
    // Back stack:
    val appBackStack = rememberNavBackStack(startDestination)

    //States:
    var game by rememberSaveable { mutableIntStateOf(0) }

    //Body:
    NavDisplay(
        backStack = appBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<AppGraph.GuideScreen> {
                GuideScreen(
                    windowState,
                    onSuccessNavigation = {
                        appBackStack.pushAndFree(AppGraph.AuthNavigation)
                    }
                )
            }
            entry<AppGraph.SettingsScreen> {
                SettingsScreen(
                    windowState,
                    onBack = { appBackStack.removeLastOrNull() }
                )
            }
            entry<AppGraph.SurveyScreen> {
                SurveyScreen(
                    windowState,
                    soundPool,
                    onBack = { appBackStack.removeLastOrNull() },
                )
            }
            entry<AppGraph.HomeNavigation> {
                HomeNav(
                    windowState,
                    soundPool,
                    onAuthNav = { appBackStack.pushAndFree(AppGraph.AuthNavigation) },
                    onSettingsNav = { appBackStack.add(AppGraph.SettingsScreen) },
                    onGameNav = {
                        game = it
                        appBackStack.add(AppGraph.GameNavigation)
                    }
                )
            }
            entry<AppGraph.GameNavigation> {
                GameNav(
                    windowState,
                    soundPool,
                    onMusicChange,
                    game,
                    onOutGameNavigation = { appBackStack.removeLastOrNull() },
                )
            }
            entry<AppGraph.AuthNavigation> {
                AuthNav(
                    windowState,
                    soundPool,
                    toHomeNav = { appBackStack.pushAndFree(AppGraph.HomeNavigation) }
                )
            }
        }
    )
}
