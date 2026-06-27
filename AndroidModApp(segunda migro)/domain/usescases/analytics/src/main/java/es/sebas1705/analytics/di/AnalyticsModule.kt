package es.sebas1705.analytics.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.analytics.AnalyticsUsesCases
import es.sebas1705.analytics.LogEventUseCase
import es.sebas1705.analytics.SetUserPropertyUseCase
import es.sebas1705.repositories.interfaces.IAnalyticsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {

    @Provides
    @Singleton
    fun provideAnalyticsUsesCases(
        analyticsRepository: IAnalyticsRepository
    ): AnalyticsUsesCases = AnalyticsUsesCases(
        logEvent = LogEventUseCase(analyticsRepository),
        setUserProperty = SetUserPropertyUseCase(analyticsRepository)
    )
}
