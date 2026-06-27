package es.sebas1705.settings.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.sebas1705.repositories.interfaces.ISettingsRepository
import es.sebas1705.settings.ReadGameLanguage
import es.sebas1705.settings.ReadSettingsUseCase
import es.sebas1705.settings.SaveFirstTime
import es.sebas1705.settings.SettingUsesCases
import es.sebas1705.settings.UpdateSettingsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Provides
    @Singleton
    fun provideSettingUsesCases(settingsRepository: ISettingsRepository): SettingUsesCases =
        SettingUsesCases(
            readSettingsUseCase = ReadSettingsUseCase(settingsRepository),
            updateSettingsUseCase = UpdateSettingsUseCase(settingsRepository),
            saveFirstTime = SaveFirstTime(settingsRepository),
            readGameLanguage = ReadGameLanguage(settingsRepository)
        )
}
