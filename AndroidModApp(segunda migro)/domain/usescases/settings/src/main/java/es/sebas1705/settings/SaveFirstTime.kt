package es.sebas1705.settings

import es.sebas1705.repositories.interfaces.ISettingsRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SaveFirstTime @Inject constructor(
    private val settingsRepository: ISettingsRepository
) {
    suspend operator fun invoke() {
        val current = settingsRepository.read().first()
        settingsRepository.update(current.copy(firstTime = false))
    }
}
