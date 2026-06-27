package es.sebas1705.settings

import es.sebas1705.repositories.interfaces.ISettingsRepository
import es.sebas1705.resources.games.Languages
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReadGameLanguage @Inject constructor(
    private val settingsRepository: ISettingsRepository
) {
    operator fun invoke(): Flow<Languages> =
        settingsRepository.read().map { Languages.entries[it.language] }
}
