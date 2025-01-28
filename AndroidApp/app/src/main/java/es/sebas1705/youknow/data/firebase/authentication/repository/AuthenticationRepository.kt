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
import es.sebas1705.youknow.core.utlis.alias.FlowResponseNothing

/**
 * Repository interface to authenticate the user
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
interface AuthenticationRepository {

    //Tasks:
    /**
     * Sign up with email and password
     *
     * @param email [String]: Email to sign up
     * @param password [String]: Password to sign up
     *
     * @return [FlowResponseNothing]: with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun signUpWithEmail(
        email: String,
        password: String
    ): FlowResponseNothing

    /**
     * Sign in with email and password
     *
     * @param email [String]: Email to sign in
     * @param password [String]: Password to sign in
     *
     * @return [FlowResponseNothing]: with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun signInWithEmail(
        email: String,
        password: String
    ): FlowResponseNothing

    /**
     * Sign in with Google
     *
     * @param context [Context]: Context to sign in
     *
     * @return [FlowResponseNothing]: with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    suspend fun signWithGoogle(
        context: Context
    ): FlowResponseNothing

    /**
     * Send a forgot password email
     *
     * @param email [String]: Email to send the forgot password email
     *
     * @return [FlowResponseNothing]: with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun sendForgotPassword(email: String): FlowResponseNothing

    //Functions:

    /**
     * Sign out the user
     *
     * @return [Boolean] with the result
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun signOut(): Boolean

    /**
     * Check if the user is logged
     *
     * @return [Boolean] with the result
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun isUserLogged(): Boolean

    /**
     * Get the current user
     *
     * @return [FirebaseUser] with the current user
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun getCurrentUser(): FirebaseUser?

}