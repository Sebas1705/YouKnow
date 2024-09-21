package es.sebas1705.youknowapp.data.repository

import es.sebas1705.youknowapp.data.source.remote.triviaApi.TriviaApiService
import es.sebas1705.youknowapp.domain.model.TriviaResponse
import es.sebas1705.youknowapp.common.Constants
import es.sebas1705.youknowapp.domain.repository.TriviaRepository
import javax.inject.Inject

/**
 * Implementation of the TriviaRepository
 *
 * @param triviaApiService the service to get the trivia
 */
class TriviaRepositoryImpl @Inject constructor(
    private val triviaApiService: TriviaApiService
) : TriviaRepository {

    /**
     * Get the questions from the API
     *
     * @param amount the amount of questions to get
     * @param category the category of the questions
     * @param difficulty the difficulty of the questions
     * @param type the type of the questions
     * @param encode the encoding of the questions
     * @return the response of the API
     */
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

    /**
     * Get ten random questions from the API
     *
     * @return the response of the API
     */
    override suspend fun getTenRandomQuestions(): TriviaResponse {
        return triviaApiService.getTrivia(10,encode = Constants.TRIVIA_ENCODE_TEXT)
    }


}