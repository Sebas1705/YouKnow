package es.sebas1705.youknowapp.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.youknowapp.domain.repository.AnalyticsRepository
import es.sebas1705.youknowapp.domain.repository.AuthRepository
import es.sebas1705.youknowapp.domain.repository.LocalUserRepository
import es.sebas1705.youknowapp.domain.repository.TriviaRepository
import es.sebas1705.youknowapp.domain.usecases.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserRepository: LocalUserRepository
    ): LocalUsesCases = LocalUsesCases(
        readFirstTime = ReadFirstTime(localUserRepository),
        saveFirstTime = SaveFirstTime(localUserRepository),
        readAppVolume = ReadAppVolume(localUserRepository),
        saveAppVolume = SaveAppVolume(localUserRepository),
        readAppContrast = ReadAppContrast(localUserRepository),
        saveAppContrast = SaveAppContrast(localUserRepository)
    )

    @Provides
    @Singleton
    fun provideTriviaRepositoryUsesCases(
        triviaRepository: TriviaRepository
    ): TriviaUsesCases = TriviaUsesCases(
        getTriviaTenQuestions = GetTriviaTenQuestions(triviaRepository)
    )

    @Provides
    @Singleton
    fun provideAuthUsesCases(
        authRepository: AuthRepository
    ): AuthUsesCases = AuthUsesCases(
        signUpWithEmail = SignUpWithEmail(authRepository),
        signInWithEmail = SignInWithEmail(authRepository),
        signWithGoogle = SignWithGoogle(authRepository),
        signWithFacebook = SignWithFacebook(authRepository),
        signOut = SignOut(authRepository),
        isUserLogged = IsUserLogged(authRepository),
        getCurrentUser = GetCurrentUser(authRepository)
    )

    @Provides
    @Singleton
    fun provideAnalyticsUsesCases(
        analyticsRepository: AnalyticsRepository
    ): AnalyticsUsesCases = AnalyticsUsesCases(
        logEvent = LogEvent(analyticsRepository),
        setUserProperty = SetUserProperty(analyticsRepository)
    )
}