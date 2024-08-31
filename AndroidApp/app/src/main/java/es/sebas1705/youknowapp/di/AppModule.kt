package es.sebas1705.youknowapp.di

import android.app.Application
import android.content.Context
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.sebas1705.youknowapp.R
import es.sebas1705.youknowapp.data.repository.LocalUserManagerImpl
import es.sebas1705.youknowapp.data.remote.triviaApi.TriviaApiService
import es.sebas1705.youknowapp.data.repository.TriviaRepositoryImpl
import es.sebas1705.youknowapp.domain.utils.Constants
import es.sebas1705.youknowapp.domain.repository.LocalUserManager
import es.sebas1705.youknowapp.domain.repository.TriviaRepository
import es.sebas1705.youknowapp.domain.usecases.AppEntryUseCases
import es.sebas1705.youknowapp.domain.usecases.GetTriviaTenQuestions
import es.sebas1705.youknowapp.domain.usecases.ReadAppEntry
import es.sebas1705.youknowapp.domain.usecases.SaveAppEntry
import es.sebas1705.youknowapp.domain.usecases.TriviaUsesCases
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

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
    fun provideTriviaRepositoryUsesCases(
        triviaRepository: TriviaRepository
    ): TriviaUsesCases = TriviaUsesCases(
        getTriviaTenQuestions = GetTriviaTenQuestions(triviaRepository)
    )
}