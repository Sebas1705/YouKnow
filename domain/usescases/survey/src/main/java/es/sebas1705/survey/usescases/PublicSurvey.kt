package es.sebas1705.survey.usescases


import es.sebas1705.common.utlis.extensions.types.collect
import es.sebas1705.mappers.toSurveyDocument
import es.sebas1705.mappers.toSurveyEntity
import es.sebas1705.room.repository.DatabaseRepository
import es.sebas1705.firestore.repository.FirestoreRepository
import es.sebas1705.domain.model.stats.SurveyModel

/**
 * Use case to public a survey
 *
 * @param firestoreRepository [FirestoreRepository]: repository to public the survey
 * @param databaseRepository [DatabaseRepository]: repository to insert the survey
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class PublicSurvey(
    private val firestoreRepository: FirestoreRepository,
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        surveyModel: SurveyModel,
        onLoading: () -> Unit,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            onLoading()
            databaseRepository.insertOrReplace(surveyModel.toSurveyEntity())
            firestoreRepository.publicNewSurvey(
                surveyModel.toSurveyDocument()
            ).collect(
                onEmptySuccess = onSuccess,
                onError = onError
            )
        } catch (e: Exception) {
            onError(e.message.toString())
        }
    }
}