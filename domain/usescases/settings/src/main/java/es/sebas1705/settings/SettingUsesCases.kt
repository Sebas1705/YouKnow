package es.sebas1705.settings

data class SettingUsesCases(
    val readSettingsUseCase: ReadSettingsUseCase,
    val updateSettingsUseCase: UpdateSettingsUseCase,
    val saveFirstTime: SaveFirstTime,
    val readGameLanguage: ReadGameLanguage
)
