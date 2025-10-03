package es.sebas1705.home.navigation


import android.media.SoundPool
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.sebas1705.common.states.WindowState
import es.sebas1705.common.utlis.extensions.composables.navToTab
import es.sebas1705.designsystem.dialogs.LoadingDialog
import es.sebas1705.home.chat.ChatScreen
import es.sebas1705.home.groups.GroupsScreen
import es.sebas1705.home.main.MainScreen
import es.sebas1705.home.play.PlayScreen
import es.sebas1705.home.profile.ProfileScreen
import es.sebas1705.home.navigation.HomeScreens.ChatScreen
import es.sebas1705.home.navigation.HomeScreens.Companion.homes
import es.sebas1705.home.navigation.HomeScreens.GroupsScreen
import es.sebas1705.home.navigation.HomeScreens.MainScreen
import es.sebas1705.home.navigation.HomeScreens.PlayScreen
import es.sebas1705.home.navigation.HomeScreens.ProfileScreen
import es.sebas1705.home.navigation.composables.HomeBottomBar
import es.sebas1705.home.navigation.composables.HomeTopBar
import es.sebas1705.home.navigation.viewmodel.HomeIntent
import es.sebas1705.home.navigation.viewmodel.HomeViewModel

/**
 * Home Navigation Composable that will handle the navigation between the different screens of the app.
 *
 * @param windowState [WindowState]: The state of the window.
 * @param soundPool [Pair]<[SoundPool], [Float]>: Pair of the SoundPool and the volume.
 * @param onAuthNav () -> Unit: Function that will navigate to the auth screen.
 * @param onSettingsNav () -> Unit: Function that will navigate to the settings screen.
 * @param onGameNav (GameScreens) -> Unit: Function that will navigate to the game screen.
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
@Composable
fun HomeNav(
    windowState: WindowState,
    soundPool: Pair<SoundPool, Float>,
    onAuthNav: () -> Unit,
    onSettingsNav: () -> Unit,
    onGameNav: (Int) -> Unit
) {
    //viewmodel:
    val homeViewModel: HomeViewModel = hiltViewModel()

    //collecting states:
    val homeState by homeViewModel.uiState.collectAsStateWithLifecycle()

    //states:
    var selectedItem by rememberSaveable { mutableIntStateOf(2) }

    //navigation Controller:
    val navController = rememberNavController()

    //Body:
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            HomeBottomBar(
                items = homes,
                selectedItem = selectedItem,
                soundPool = soundPool,
                onItemClick = {
                    selectedItem = it
                    navController.navToTab(homes[it].destination)
                },
            )
        },
        topBar = {
            if (!windowState.isImeVisible)
                HomeTopBar(
                    windowState,
                    homeState.userModel?.points ?: 0,
                    homeState.userModel?.credits ?: 0
                )
        }
    ) { contentPadding ->

        if (homeState.isLoading)
            LoadingDialog(windowState)

        NavHost(
            navController = navController,
            startDestination = MainScreen,
            modifier =
            if (windowState.isImeVisible)
                Modifier.imePadding()
            else
                Modifier.padding(contentPadding)
        ) {
            composable<MainScreen> {
                MainScreen(
                    windowState,
                    homeState,
                    soundPool,
                    onSettingsNav
                )
            }
            composable<ProfileScreen> {
                ProfileScreen(
                    windowState,
                    homeState,
                    soundPool,
                    onAuthNav
                )
            }
            composable<ChatScreen> {
                ChatScreen(
                    windowState,
                    homeState,
                    soundPool
                )
            }
            composable<PlayScreen> {
                PlayScreen(
                    windowState,
                    soundPool,
                    onGameNav
                )
            }
            composable<GroupsScreen> {
                GroupsScreen(
                    windowState,
                    homeState,
                    soundPool,
                    onUserInfoSearch = { firebaseIds ->
                        homeViewModel.eventHandler(HomeIntent.GetUsers(firebaseIds))
                    }
                )
            }
        }
    }
}






