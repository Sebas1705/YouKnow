package es.sebas1705.youknowapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.youknowapp.data.local.manager.LocalUserManagerImpl
import es.sebas1705.youknowapp.domain.manager.LocalUserManager
import es.sebas1705.youknowapp.domain.usecases.AppEntryUseCases
import es.sebas1705.youknowapp.domain.usecases.ReadAppEntry
import es.sebas1705.youknowapp.domain.usecases.SaveAppEntry
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
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

}