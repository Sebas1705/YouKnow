package es.sebas1705.youknowapp.domain.usecases

import android.content.Context
import com.google.firebase.auth.FirebaseUser
import es.sebas1705.youknowapp.domain.repository.AuthRepository

class SignUpWithEmail(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String, password: String, onSuccess: () -> Unit, onError: (String?) -> Unit) {
        authRepository.signUpWithEmail(email, password, onSuccess, onError)
    }
}

class SignInWithEmail(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String, password: String, onSuccess: () -> Unit, onError: (String?) -> Unit) {
        authRepository.signInWithEmail(email, password, onSuccess, onError)
    }
}

class SignWithGoogle(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(context: Context, onSuccess: () -> Unit, onError: (String?) -> Unit) {
        authRepository.signWithGoogle(context, onSuccess, onError)
    }
}

class SignWithFacebook(
    private val authRepository: AuthRepository
) {
    operator fun invoke(onSuccess: () -> Unit, onError: (String?) -> Unit) {
        authRepository.signWithFacebook(onSuccess, onError)
    }
}

class SignOut(
    private val authRepository: AuthRepository
) {
    operator fun invoke(onSuccess: () -> Unit, onError: () -> Unit) {
        authRepository.signOut(onSuccess, onError)
    }
}

class IsUserLogged(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Boolean {
        return authRepository.isUserLogged()
    }
}

class GetCurrentUser(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): FirebaseUser? {
        return authRepository.getCurrentUser()
    }
}

data class AuthUsesCases(
    val signUpWithEmail: SignUpWithEmail,
    val signInWithEmail: SignInWithEmail,
    val signWithGoogle: SignWithGoogle,
    val signWithFacebook: SignWithFacebook,
    val signOut: SignOut,
    val isUserLogged: IsUserLogged,
    val getCurrentUser: GetCurrentUser
)