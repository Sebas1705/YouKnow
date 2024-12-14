package es.sebas1705.youknow.domain.usecases.user

import android.content.Context
import com.google.firebase.auth.FirebaseUser
import es.sebas1705.youknow.core.utlis.catcher
import es.sebas1705.youknow.data.firebase.authentication.repository.AuthenticationRepository
import es.sebas1705.youknow.domain.model.UserModel

class SignUpEmailUser(
    private val authenticationRepository: AuthenticationRepository,
) {
    suspend operator fun invoke(
        nickname: String,
        email: String,
        password: String,
        onLoading: () -> Unit = {},
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ) = authenticationRepository.signUpWithEmail(email, password).catcher(
        onLoading,
        onEmptySuccess = {
            onSuccess(
                UserModel.newEmailUser(
                    nickname,
                    authenticationRepository.getCurrentUser()!!
                )
            )
        },
        onError
    )
}

class SignInEmailUser(
    private val authenticationRepository: AuthenticationRepository,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        onLoading: () -> Unit = {},
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) = authenticationRepository.signInWithEmail(email, password).catcher(
        onLoading,
        onEmptySuccess = { onSuccess(authenticationRepository.getCurrentUser()!!.uid) },
        onError
    )
}

class SignGoogle(
    private val authenticationRepository: AuthenticationRepository,
) {
    suspend operator fun invoke(
        context: Context,
        onLoading: () -> Unit = {},
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) = authenticationRepository.signWithGoogle(context).catcher(
        onLoading,
        onEmptySuccess = { onSuccess(authenticationRepository.getCurrentUser()!!.uid) },
        onError
    )
}

class SignOut(
    private val authenticationRepository: AuthenticationRepository,
) {
    operator fun invoke(
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val user = authenticationRepository.getCurrentUser()!!
        val out = authenticationRepository.signOut()
        if (out)
            onSuccess(user.uid)
        else
            onError("Not possible to sign out")
    }
}

class GetFirebaseUser(
    private val authenticationRepository: AuthenticationRepository
) {
    operator fun invoke(): FirebaseUser? = authenticationRepository.getCurrentUser()
}

class SendForgotPassword(
    private val authenticationRepository: AuthenticationRepository,
) {
    suspend operator fun invoke(
        email: String,
        onLoading: () -> Unit = {},
        onEmptySuccess: () -> Unit,
        onError: (String) -> Unit
    ) = authenticationRepository.sendForgotPassword(email)
        .catcher(onLoading, onEmptySuccess, onError)
}

data class AuthUsesCases(
    val signUpEmailUser: SignUpEmailUser,
    val signInEmailUser: SignInEmailUser,
    val signGoogle: SignGoogle,
    val signOut: SignOut,
    val sendForgotPassword: SendForgotPassword,
    val getFirebaseUser: GetFirebaseUser,
)