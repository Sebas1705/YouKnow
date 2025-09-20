package es.sebas1705.news.usescases


import es.sebas1705.common.utlis.extensions.types.catcher
import es.sebas1705.mappers.toNewModel
import es.sebas1705.models.social.NewModel
import es.sebas1705.firestore.repository.FirestoreRepository

class GetNews(
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(
        onLoading: () -> Unit,
        onSuccess: (List<NewModel>) -> Unit,
        onError: (String) -> Unit
    ) = firestoreRepository.getNews().catcher(
        onLoading,
        onSuccess = { newDocuments ->
            onSuccess(newDocuments.map { it.toNewModel() })
        },
        onError
    )
}