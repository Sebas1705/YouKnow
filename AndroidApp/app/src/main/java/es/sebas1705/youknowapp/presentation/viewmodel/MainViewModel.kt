package es.sebas1705.youknowapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknowapp.domain.usecases.AppEntryUseCases
import es.sebas1705.youknowapp.presentation.navigation.Route
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.HomeScreen.route)
        private set

    init{
        appEntryUseCases.readAppEntry().onEach {
            startDestination =
                if(it) Route.HomeScreen.route
                else Route.OnBoardingScreen.route
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }

}