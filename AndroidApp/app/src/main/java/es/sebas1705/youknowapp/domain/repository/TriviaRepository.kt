package es.sebas1705.youknowapp.domain.repository

import es.sebas1705.youknowapp.domain.model.TriviaResponse
import es.sebas1705.youknowapp.common.Constants

/**
 * Interface to manage the trivia questions
 */
interface TriviaRepository {

    /**
     * Get a list of trivia questions
     *
     * @param amount Number of questions to get
     * @param category Category of the questions
     * @param difficulty Difficulty of the questions
     * @param type Type of the questions
     * @param encode Encode of the questions
     * @return TriviaResponse
     */
    suspend fun getQuestions(
        amount : Int,
        category : Int? = null,
        difficulty : String? = null,
        type : String? = null,
        encode : String? = Constants.TRIVIA_ENCODE_TEXT
    ) : TriviaResponse

    /**
     * Get a list of 10 random trivia questions
     *
     * @return TriviaResponse
     */
    suspend fun getTenRandomQuestions() : TriviaResponse
}