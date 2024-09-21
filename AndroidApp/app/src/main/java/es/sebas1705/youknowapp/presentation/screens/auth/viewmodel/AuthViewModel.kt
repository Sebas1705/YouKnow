package es.sebas1705.youknowapp.presentation.screens.auth.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.youknowapp.domain.usecases.AuthUsesCases
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUsesCases: AuthUsesCases,
) : ViewModel() {

    fun authWithEmail(
        create: Boolean,
        email: String,
        password: String,
        onSuccess: () -> Unit = {},
        onError: (String?) -> Unit = {}
    ) = if (create) authUsesCases.signUpWithEmail(email, password, onSuccess, onError)
        else authUsesCases.signInWithEmail(email, password, onSuccess, onError)

    fun signOut(
        onSuccess: () -> Unit = {},
        onError: () -> Unit = {}
    ) = authUsesCases.signOut(onSuccess, onError)

    fun authWithGoogle(
        context: Context,
        onSuccess: () -> Unit = {},
        onError: (String?) -> Unit = {}
    ) = viewModelScope.launch { authUsesCases.signWithGoogle(context, onSuccess, onError) }

}