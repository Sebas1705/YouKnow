package es.sebas1705.repositories.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.repositories.interfaces.IAnalyticsRepository
import es.sebas1705.repositories.interfaces.IAuthenticationRepository
import es.sebas1705.repositories.interfaces.IDatabaseRepository
import es.sebas1705.repositories.interfaces.IFirestoreRepository
import es.sebas1705.repositories.interfaces.IOpendbRepository
import es.sebas1705.repositories.interfaces.IRealtimeRepository
import es.sebas1705.repositories.interfaces.ISettingsRepository
import es.sebas1705.repositories.repos.AnalyticsRepository
import es.sebas1705.repositories.repos.AuthenticationRepository
import es.sebas1705.repositories.repos.DatabaseRepository
import es.sebas1705.repositories.repos.FirestoreRepository
import es.sebas1705.repositories.repos.OpendbRepository
import es.sebas1705.repositories.repos.RealtimeRepository
import es.sebas1705.repositories.repos.SettingsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesDataModule {

    @Binds
    @Singleton
    abstract fun bindAnalyticsRepository(
        impl: AnalyticsRepository
    ): IAnalyticsRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthenticationRepository
    ): IAuthenticationRepository

    @Binds
    @Singleton
    abstract fun bindDatabaseRepository(
        impl: DatabaseRepository
    ): IDatabaseRepository

    @Binds
    @Singleton
    abstract fun bindFirestoreRepository(
        impl: FirestoreRepository
    ): IFirestoreRepository

    @Binds
    @Singleton
    abstract fun bindRealtimeRepository(
        impl: RealtimeRepository
    ): IRealtimeRepository

    @Binds
    @Singleton
    abstract fun bindOpendbRepository(
        impl: OpendbRepository
    ): IOpendbRepository

    @Binds
    @Singleton
    abstract fun bindSettingsRepository(
        impl: SettingsRepository
    ): ISettingsRepository

}
