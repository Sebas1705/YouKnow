package es.sebas1705.auth.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.auth.AuthUsesCases
import es.sebas1705.auth.GetFirebaseUser
import es.sebas1705.auth.SendForgotPassword
import es.sebas1705.auth.SignGoogle
import es.sebas1705.auth.SignInEmailUser
import es.sebas1705.auth.SignOut
import es.sebas1705.auth.SignUpEmailUser
import es.sebas1705.repositories.interfaces.IAuthenticationRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthUsesCases(
        authenticationRepository: IAuthenticationRepository
    ): AuthUsesCases = AuthUsesCases(
        getFirebaseUser = GetFirebaseUser(authenticationRepository),
        signInEmailUser = SignInEmailUser(authenticationRepository),
        signUpEmailUser = SignUpEmailUser(authenticationRepository),
        sendForgotPassword = SendForgotPassword(authenticationRepository),
        signGoogle = SignGoogle(authenticationRepository),
        signOut = SignOut(authenticationRepository)
    )
}
