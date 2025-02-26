package es.sebas1705.auth.di
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

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.auth.AuthUsesCases
import es.sebas1705.auth.usescases.GetFirebaseUser
import es.sebas1705.auth.usescases.SendForgotPassword
import es.sebas1705.auth.usescases.SignGoogle
import es.sebas1705.auth.usescases.SignInEmailUser
import es.sebas1705.auth.usescases.SignOut
import es.sebas1705.auth.usescases.SignUpEmailUser
import es.sebas1705.authentication.repository.AuthenticationRepository
import javax.inject.Singleton

/**
 * Module to provide all the use cases of the domain layer
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    /**
     * Function to provide authentication use cases
     *
     * @param authenticationRepository [AuthenticationRepository]: Repository to access to the authentication
     *
     * @return [AuthUsesCases]: Use cases of the authentication
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    @Provides
    @Singleton
    fun provideAuthUsesCases(
        authenticationRepository: AuthenticationRepository
    ): AuthUsesCases = AuthUsesCases(
        getFirebaseUser = GetFirebaseUser(authenticationRepository),
        sendForgotPassword = SendForgotPassword(authenticationRepository),
        signInEmailUser = SignInEmailUser(authenticationRepository),
        signGoogle = SignGoogle(authenticationRepository),
        signOut = SignOut(authenticationRepository),
        signUpEmailUser = SignUpEmailUser(authenticationRepository)
    )
}