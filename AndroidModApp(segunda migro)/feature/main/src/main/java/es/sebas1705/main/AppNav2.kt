package es.sebas1705.main

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation.compose.composable
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import es.sebas1705.auth.AuthNav
import es.sebas1705.common.utlis.extensions.primitives.pushAndFree
import es.sebas1705.common.utlis.extensions.types.logI
import es.sebas1705.designsystem.states.rememberWindowState
import es.sebas1705.game.GameNav
import es.sebas1705.guide.GuideScreen
import es.sebas1705.home.navigation.HomeNav
import es.sebas1705.main.viewmodel.MainIntent
import es.sebas1705.main.viewmodel.MainViewModel
import es.sebas1705.settings.SettingsScreen
import es.sebas1705.splash.SplashScreen
import es.sebas1705.survey.SurveyScreen

/**
 * Navigation for the app.
 *
 * @since 0.1.0
 * @author Sebas1705 01/03/2025
 */
@Composable
fun AppNav() {

    // Backstack:
    val backStack = rememberNavBackStack(AppGraph.SplashScreen)

    //ViewModels:
    val mainViewModel: MainViewModel = hiltViewModel()
    val windowState by rememberWindowState()
    var game by rememberSaveable { mutableIntStateOf(0) }

    //States:
    val mainState by mainViewModel.uiState.collectAsStateWithLifecycle()

    //Effects:
    LaunchedEffect(null) {
        mainViewModel.eventHandler(MainIntent.ChargeData)
    }

    LaunchedEffect(mainState.splashFinished) {
        if (mainState.splashFinished) {
            backStack.pushAndFree(AppGraph.MVISampleScreen)
        }
    }

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        transitionSpec = {
            ContentTransform(
                slideInHorizontally(initialOffsetX = { it }),
                ExitTransition.None
            )
        },
        popTransitionSpec = {
            ContentTransform(
                slideInHorizontally(initialOffsetX = { it }),
                ExitTransition.None
            )
        },
        entryProvider = entryProvider {
            entry<AppGraph.SplashScreen> {
                SplashScreen(windowState)
            }
            entry<AppGraph.GuideScreen> {
                GuideScreen(
                    windowState,
                    onSuccessNavigation = {
                        backStack.pushAndFree(
                            AppGraph.AuthNavigation
                        )
                    }
                )
            }
            entry<AppGraph.SettingsScreen> {
                SettingsScreen(
                    windowState,
                    onBack = {
                        appNavController.navAndPopUp(AppGraph.HomeNavigation, AppGraph.SettingsScreen)
                    }
                )
            }
            entry<AppGraph.SurveyScreen> {
                SurveyScreen(
                    windowState,
                    soundPool,
                    onBack = {
                        appNavController.navAndPopUp(AppGraph.HomeNavigation, AppGraph.SurveyScreen)
                    },
                )
            }
            entry<AppGraph.HomeNavigation> {
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
            entry<AppGraph.GameNavigation> {
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
            entry<AppGraph.AuthNavigation> {
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
    )
}

