package es.sebas1705.survey.usescases


import es.sebas1705.common.utlis.extensions.types.catcher
import es.sebas1705.mappers.toSurveyModel
import es.sebas1705.firestore.repository.FirestoreRepository
import es.sebas1705.domain.model.stats.SurveyModel

/**
 * Use case to get all the surveys
 *
 * @param firestoreRepository [FirestoreRepository]: repository to get the surveys
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class GetAllSurveys(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(
        onLoading: () -> Unit,
        onSuccess: (List<SurveyModel>) -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.getSurveys().catcher(
        onLoading,
        onSuccess = {
            onSuccess(it.map { s -> s.toSurveyModel() })
        },
        onError
    )
}