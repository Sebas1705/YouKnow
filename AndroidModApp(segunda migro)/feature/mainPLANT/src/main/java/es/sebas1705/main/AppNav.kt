package es.sebas1705.main

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import es.sebas1705.common.utlis.extensions.primitives.pushAndFree
import es.sebas1705.common.utlis.extensions.types.logI
import es.sebas1705.main.viewmodel.MainIntent
import es.sebas1705.main.viewmodel.MainViewModel
import es.sebas1705.mvisample.MviSampleScreen
import es.sebas1705.mvvmsample.MvvmSampleScreen
import es.sebas1705.splash.SplashScreen

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
                SplashScreen()
            }
            entry<AppGraph.MvvmSampleScreen> {
                MvvmSampleScreen {
                    backStack.pushAndFree(AppGraph.MVISampleScreen)
                }
            }
            entry<AppGraph.MVISampleScreen> {
                MviSampleScreen {
                    backStack.pushAndFree(AppGraph.MvvmSampleScreen)
                }
            }
        }
    )
}

