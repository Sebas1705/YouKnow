package es.sebas1705.survey.usescases


import es.sebas1705.common.utlis.extensions.types.collect
import es.sebas1705.mappers.toSurveyModel
import es.sebas1705.room.repository.DatabaseRepository
import es.sebas1705.firestore.repository.FirestoreRepository
import es.sebas1705.domain.model.stats.SurveyModel

/**
 * Use case to get the actual survey
 *
 * @param firestoreRepository [FirestoreRepository]: repository to get the survey
 * @param databaseRepository [DatabaseRepository]: repository to get the survey
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class GetActualSurvey(
    private val firestoreRepository: FirestoreRepository,
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        firebaseId: String,
        onLoading: () -> Unit,
        onSuccess: (SurveyModel?) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            onLoading()
            val surveyOrNull = databaseRepository.getSurvey(firebaseId)
            if (surveyOrNull != null) {
                onSuccess(surveyOrNull.toSurveyModel())
            } else {
                firestoreRepository.getSurvey(
                    firebaseId
                ).collect(
                    onSuccess = {
                        onSuccess(it.toSurveyModel())
                    },
                    onError = {
                        onSuccess(null)
                    }
                )
            }
        } catch (e: Exception) {
            onError(e.message.toString())
        }
    }
}