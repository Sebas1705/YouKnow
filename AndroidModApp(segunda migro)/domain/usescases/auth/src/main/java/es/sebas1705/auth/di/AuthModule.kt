package es.sebas1705.auth.di


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
 * @author Sebas1705 12/09/2025
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
     * @author Sebas1705 12/09/2025
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