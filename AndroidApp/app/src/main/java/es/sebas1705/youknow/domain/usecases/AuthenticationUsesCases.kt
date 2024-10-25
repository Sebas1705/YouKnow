package es.sebas1705.youknow.domain.usecases
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
import es.sebas1705.youknow.data.model.ResponseState
import es.sebas1705.youknow.data.firebase.authentication.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case to sign up with email and password
 *
 * @param authenticationRepository [AuthenticationRepository]: repository to sign up with email and password
 *
 * @return [Flow] with a [ResponseState] of [Boolean] value indicating if the sign up was successful
 *
 * @see AuthenticationRepository
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class SignUpWithEmail(
    private val authenticationRepository: AuthenticationRepository
) {
    operator fun invoke(email: String, password: String): Flow<ResponseState<Boolean>> {
        return authenticationRepository.signUpWithEmail(email, password)
    }
}

/**
 * Use case to sign in with email and password
 *
 * @param authenticationRepository [AuthenticationRepository]: repository to sign in with email and password
 *
 * @return [Flow] with a [ResponseState] of [Boolean] value indicating if the sign in was successful
 *
 * @see AuthenticationRepository
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class SignInWithEmail(
    private val authenticationRepository: AuthenticationRepository
) {
    operator fun invoke(email: String, password: String) : Flow<ResponseState<Boolean>> {
        return authenticationRepository.signInWithEmail(email, password)
    }
}

/**
 * Use case to sign in with Google
 *
 * @param authenticationRepository [AuthenticationRepository]: repository to sign in with Google
 *
 * @return [Flow] with a [ResponseState] of [Boolean] value indicating if the sign in was successful
 *
 * @see AuthenticationRepository
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class SignWithGoogle(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(context: Context) : Flow<ResponseState<Boolean>> {
        return authenticationRepository.signWithGoogle(context)
    }
}

/**
 * Use case to sign out
 *
 * @param authenticationRepository [AuthenticationRepository]: repository to sign out
 *
 * @return [Flow] with a [ResponseState] of [Boolean] value indicating if the sign out was successful
 *
 * @see AuthenticationRepository
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class SignOut(
    private val authenticationRepository: AuthenticationRepository
) {
    operator fun invoke(): Flow<ResponseState<Boolean>> {
        return authenticationRepository.signOut()
    }
}

/**
 * Use case to check if the user is logged
 *
 * @param authenticationRepository [AuthenticationRepository]: repository to check if the user is logged
 *
 * @return [Boolean] value indicating if the user is logged
 *
 * @see AuthenticationRepository
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class IsUserLogged(
    private val authenticationRepository: AuthenticationRepository
) {
    operator fun invoke() : Boolean {
        return authenticationRepository.isUserLogged()
    }
}

/**
 * Use case to get the current user
 *
 * @param authenticationRepository [AuthenticationRepository]: repository to get the current user
 *
 * @return [FirebaseUser] value indicating the current user
 *
 * @see AuthenticationRepository
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class GetCurrentUser(
    private val authenticationRepository: AuthenticationRepository
) {
    operator fun invoke() : FirebaseUser? {
        return authenticationRepository.getCurrentUser()
    }
}

/**
 * Use case to send a forgot password email
 *
 * @param authenticationRepository [AuthenticationRepository]: repository to send a forgot password email
 *
 * @return [Flow] with a [ResponseState] of [Boolean] value indicating if the email was sent
 *
 * @see AuthenticationRepository
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class SendForgotPassword(
    private val authenticationRepository: AuthenticationRepository
) {
    operator fun invoke(email: String) : Flow<ResponseState<Boolean>> {
        return authenticationRepository.sendForgotPassword(email)
    }
}

/**
 * Data class to group all the authentication use cases
 *
 * @property signUpWithEmail [SignUpWithEmail]: use case to sign up with email and password
 * @property signInWithEmail [SignInWithEmail]: use case to sign in with email and password
 * @property signWithGoogle [SignWithGoogle]: use case to sign in with Google
 * @property signOut [SignOut]: use case to sign out
 * @property isUserLogged [IsUserLogged]: use case to check if the user is logged
 * @property getCurrentUser [GetCurrentUser]: use case to get the current user
 * @property sendForgotPassword [SendForgotPassword]: use case to send a forgot password email
 *
 * @see SignUpWithEmail
 * @see SignInWithEmail
 * @see SignWithGoogle
 * @see SignOut
 * @see IsUserLogged
 * @see GetCurrentUser
 * @see SendForgotPassword
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
data class AuthenticationUsesCases(
    val signUpWithEmail: SignUpWithEmail,
    val signInWithEmail: SignInWithEmail,
    val signWithGoogle: SignWithGoogle,
    val signOut: SignOut,
    val isUserLogged: IsUserLogged,
    val getCurrentUser: GetCurrentUser,
    val sendForgotPassword: SendForgotPassword
)