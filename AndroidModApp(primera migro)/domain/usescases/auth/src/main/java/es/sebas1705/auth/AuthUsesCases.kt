package es.sebas1705.auth
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

import es.sebas1705.auth.usescases.GetFirebaseUser
import es.sebas1705.auth.usescases.SendForgotPassword
import es.sebas1705.auth.usescases.SignGoogle
import es.sebas1705.auth.usescases.SignInEmailUser
import es.sebas1705.auth.usescases.SignOut
import es.sebas1705.auth.usescases.SignUpEmailUser

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