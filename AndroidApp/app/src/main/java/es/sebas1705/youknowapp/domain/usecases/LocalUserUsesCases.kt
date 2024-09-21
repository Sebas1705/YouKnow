package es.sebas1705.youknowapp.domain.usecases

import es.sebas1705.youknowapp.ui.Contrast
import es.sebas1705.youknowapp.domain.repository.LocalUserRepository
import kotlinx.coroutines.flow.Flow


class ReadFirstTime(
    private val localUserRepository: LocalUserRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return localUserRepository.readFirstTime()
    }
}

class SaveFirstTime(
    private val localUserRepository: LocalUserRepository
) {
    suspend operator fun invoke() {
        localUserRepository.saveFirstTime()
    }
}

class ReadAppVolume(
    private val localUserRepository: LocalUserRepository
) {
    operator fun invoke(): Flow<Float> {
        return localUserRepository.readAppVolume()
    }
}

class SaveAppVolume(
    private val localUserRepository: LocalUserRepository
) {
    suspend operator fun invoke(volume: Float) {
        localUserRepository.saveAppVolume(volume)
    }
}

class ReadAppContrast(
    private val localUserRepository: LocalUserRepository
) {
    operator fun invoke(): Flow<Contrast> {
        return localUserRepository.readAppContrast()
    }
}

class SaveAppContrast(
    private val localUserRepository: LocalUserRepository
) {
    suspend operator fun invoke(contrast: Contrast) {
        localUserRepository.saveAppContrast(contrast)
    }
}


data class LocalUsesCases(
    val readFirstTime: ReadFirstTime,
    val saveFirstTime: SaveFirstTime,
    val readAppVolume: ReadAppVolume,
    val saveAppVolume: SaveAppVolume,
    val readAppContrast: ReadAppContrast,
    val saveAppContrast: SaveAppContrast
)
