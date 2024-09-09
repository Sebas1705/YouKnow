package es.sebas1705.youknowapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknowapp.domain.usecases.AppEntryUseCases
import es.sebas1705.youknowapp.presentation.navigation.AppRoutes
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

    var startDestination by mutableStateOf(AppRoutes.AuthScreen.route)
        private set

    init {
        appEntryUseCases.readAppEntry().onEach {
            startDestination =
                if (it) (
                        if (firebaseAuth.currentUser != null) AppRoutes.TriviaScreen.route
                        else AppRoutes.AuthScreen.route
                ) else AppRoutes.GuideScreen.route
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }

}