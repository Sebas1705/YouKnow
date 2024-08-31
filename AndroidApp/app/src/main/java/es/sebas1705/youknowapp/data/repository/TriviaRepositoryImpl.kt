package es.sebas1705.youknowapp.data.repository

import es.sebas1705.youknowapp.data.remote.triviaApi.TriviaApiService
import es.sebas1705.youknowapp.domain.model.TriviaResponse
import es.sebas1705.youknowapp.domain.utils.Constants
import es.sebas1705.youknowapp.domain.repository.TriviaRepository
import javax.inject.Inject

class TriviaRepositoryImpl @Inject constructor(
    private val triviaApiService: TriviaApiService
) : TriviaRepository {

    override suspend fun getQuestions(
        amount: Int,
        category: Int?,
        difficulty: String?,
        type: String?,
        encode : String?
    ) : TriviaResponse {
        return triviaApiService.getTrivia(
            amount,
            category,
            difficulty,
            type,
            encode
        )
    }

    override suspend fun getTenRandomQuestions(): TriviaResponse {
        return triviaApiService.getTrivia(10,encode = Constants.TRIVIA_ENCODE_TEXT)
    }


}