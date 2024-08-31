package es.sebas1705.youknowapp.domain.repository

import es.sebas1705.youknowapp.domain.model.TriviaResponse
import es.sebas1705.youknowapp.domain.utils.Constants

interface TriviaRepository {

    suspend fun getQuestions(
        amount : Int,
        category : Int? = null,
        difficulty : String? = null,
        type : String? = null,
        encode : String? = Constants.TRIVIA_ENCODE_TEXT
    ) : TriviaResponse

    suspend fun getTenRandomQuestions() : TriviaResponse
}