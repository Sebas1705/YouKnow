package es.sebas1705.youknowapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknowapp.domain.usecases.AppEntryUseCases
import es.sebas1705.youknowapp.presentation.navigation.Route
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AuthScreen.route)
        private set

    init {
        appEntryUseCases.readAppEntry().onEach {
            startDestination =
                if (it) (
                        if (firebaseAuth.currentUser != null) Route.TriviaScreen.route
                        else Route.AuthScreen.route
                ) else Route.OnBoardingScreen.route
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }

}