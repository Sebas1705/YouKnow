package es.sebas1705.mappers

import es.sebas1705.common.theme.ThemeContrast
import es.sebas1705.datastore.model.SettingsData
import es.sebas1705.models.SettingsModel
import es.sebas1705.resources.games.Languages

/**
 * Mapper to convert between [SettingsData] and [SettingsModel].
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
fun SettingsData.toModel() = SettingsModel(
    firstTime = firstTime,
    musicVolume = musicVolume,
    soundVolume = soundVolume,
    appContrast = ThemeContrast.entries[appContrast],
    language = Languages.entries[language]
)

/**
 * Mapper to convert between [SettingsModel] and [SettingsData].
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
fun SettingsModel.toData() = SettingsData(
    firstTime = firstTime,
    musicVolume = musicVolume,
    soundVolume = soundVolume,
    appContrast = appContrast.ordinal,
    language = language.ordinal,
    defaultSet = true
)