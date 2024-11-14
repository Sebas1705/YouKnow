package es.sebas1705.youknow.data.firebase.authentication.repository
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
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface to authenticate the user
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
interface AuthenticationRepository {

    /**
     * Sign up with email and password
     *
     * @param email [String]: Email to sign up
     * @param password [String]: Password to sign up
     *
     * @return [Flow] with the response of the operation
     *
     * @see Flow
     */
    fun signUpWithEmail(
        email: String,
        password: String
    ): Flow<ResponseState<Nothing>>

    /**
     * Sign in with email and password
     *
     * @param email [String]: Email to sign in
     * @param password [String]: Password to sign in
     *
     * @return [Flow] with the response of the operation
     *
     * @see Flow
     */
    fun signInWithEmail(
        email: String,
        password: String
    ): Flow<ResponseState<Nothing>>

    /**
     * Sign in with Google
     *
     * @param context [Context]: Context to sign in
     *
     * @return [Flow] with the response of the operation
     *
     * @see Flow
     */
    suspend fun signWithGoogle(
        context: Context
    ): Flow<ResponseState<Nothing>>

    /**
     * Send a forgot password email
     *
     * @param email [String]: Email to send the forgot password email
     *
     * @return [Flow] with the response of the operation
     *
     * @see Flow
     */
    fun sendForgotPassword(email: String): Flow<ResponseState<Nothing>>

    /**
     * Sign out the user
     *
     * @return [Flow] with the response of the operation
     *
     * @see Flow
     */
    fun signOut() : Boolean

    /**
     * Check if the user is logged
     *
     * @return [Boolean] with the result
     */
    fun isUserLogged(): Boolean

    /**
     * Get the current user
     *
     * @return [FirebaseUser] with the current user
     *
     * @see FirebaseUser
     */
    fun getCurrentUser(): FirebaseUser?

}