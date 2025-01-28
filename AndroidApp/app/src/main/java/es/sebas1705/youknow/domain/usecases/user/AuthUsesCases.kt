package es.sebas1705.youknow.domain.usecases.user
/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import android.content.Context
import com.google.firebase.auth.FirebaseUser
import es.sebas1705.youknow.core.utlis.extensions.types.catcher
import es.sebas1705.youknow.data.firebase.authentication.repository.AuthenticationRepository
import es.sebas1705.youknow.domain.model.social.UserModel

/**
 * Use case to sign up with email
 *
 * @property authenticationRepository [AuthenticationRepository]: repository to sign up with email
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
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

/**
 * Use case to sign in with email
 *
 * @property authenticationRepository [AuthenticationRepository]: repository to sign in with email
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
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

/**
 * Use case to sign with google
 *
 * @property authenticationRepository [AuthenticationRepository]: repository to sign with google
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
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

/**
 * Use case to sign out
 *
 * @property authenticationRepository [AuthenticationRepository]: repository to sign out
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
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

/**
 * Use case to get firebase user
 *
 * @property authenticationRepository [AuthenticationRepository]: repository to get firebase user
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class GetFirebaseUser(
    private val authenticationRepository: AuthenticationRepository
) {
    operator fun invoke(): FirebaseUser? = authenticationRepository.getCurrentUser()
}

/**
 * Use case to send forgot password
 *
 * @property authenticationRepository [AuthenticationRepository]: repository to send forgot password
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
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

/**
 * Use cases for authentication
 *
 * @property signUpEmailUser [SignUpEmailUser]: use case to sign up with email
 * @property signInEmailUser [SignInEmailUser]: use case to sign in with email
 * @property signGoogle [SignGoogle]: use case to sign with google
 * @property signOut [SignOut]: use case to sign out
 * @property sendForgotPassword [SendForgotPassword]: use case to send forgot password
 * @property getFirebaseUser [GetFirebaseUser]: use case to get firebase user
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
data class AuthUsesCases(
    val signUpEmailUser: SignUpEmailUser,
    val signInEmailUser: SignInEmailUser,
    val signGoogle: SignGoogle,
    val signOut: SignOut,
    val sendForgotPassword: SendForgotPassword,
    val getFirebaseUser: GetFirebaseUser,
)