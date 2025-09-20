package es.sebas1705.settings.usescases

import es.sebas1705.common.games.Languages
import es.sebas1705.datastore.repository.DatastoreRepository

/**
 * Use case to save the game language
 *
 * @param datastoreRepository [DatastoreRepository]: repository to save the preferences
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
class SaveGameLanguage(
    private val datastoreRepository: DatastoreRepository
) {
    suspend operator fun invoke(
        languages: Languages,
        onLoading: () -> Unit,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            onLoading()
            datastoreRepository.saveGameLanguage(languages)
            onSuccess()
        } catch (e: Exception) {
            onError(e.message.toString())
        }
    }
}