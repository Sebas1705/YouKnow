package es.sebas1705.youknowapp.data.di

import android.app.Application
import androidx.credentials.CredentialManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.youknowapp.data.repository.AuthRepositoryImpl
import es.sebas1705.youknowapp.data.repository.TriviaRepositoryImpl
import es.sebas1705.youknowapp.data.source.remote.triviaApi.TriviaApiService
import es.sebas1705.youknowapp.domain.repository.AuthRepository
import es.sebas1705.youknowapp.domain.repository.TriviaRepository
import es.sebas1705.youknowapp.common.Constants
import es.sebas1705.youknowapp.data.repository.AnalyticsRepositoryImpl
import es.sebas1705.youknowapp.data.repository.LocalUserRepositoryImpl
import es.sebas1705.youknowapp.domain.repository.AnalyticsRepository
import es.sebas1705.youknowapp.domain.repository.LocalUserRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideLocalUserRepository(
        application: Application
    ): LocalUserRepository = LocalUserRepositoryImpl(application)

    @Provides
    @Singleton
    fun provideTriviaApiService(): TriviaApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.TRIVIA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TriviaApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTriviaRepository(
        triviaApiService: TriviaApiService
    ): TriviaRepository = TriviaRepositoryImpl(triviaApiService)

    @Provides
    @Singleton
    fun provideCredentialManager(
        application: Application
    ): CredentialManager = CredentialManager.create(application)

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        credentialManager: CredentialManager
    ): AuthRepository = AuthRepositoryImpl(firebaseAuth, credentialManager)

    @Provides
    @Singleton
    fun provideFirebaseAnalytics(
        application: Application
    ): FirebaseAnalytics = FirebaseAnalytics.getInstance(application)

    @Provides
    @Singleton
    fun provideAnalyticsRepository(
        firebaseAnalytics: FirebaseAnalytics
    ): AnalyticsRepository = AnalyticsRepositoryImpl(firebaseAnalytics)

}