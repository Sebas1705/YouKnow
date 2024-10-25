package es.sebas1705.youknow.data.apis.opendb.repository

import es.sebas1705.youknow.data.apis.opendb.dtos.ResponseOpendbDto
import es.sebas1705.youknow.data.apis.opendb.config.SettingsOpendb.TRIVIA_ENCODE_TEXT

/**
 * Repository interface to get trivia questions
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
interface OpendbRepository {

    /**
     * Get a list of trivia questions
     *
     * @param amount [Int]: Number of questions to get
     * @param category [Int]?: Category of the questions
     * @param difficulty [String]?: Difficulty of the questions
     * @param type [String]?: Type of the questions
     * @param encode [String]?: Encode of the questions
     *
     * @return [ResponseOpendbDto] with the list of questions
     *
     * @see ResponseOpendbDto
     */
    suspend fun getQuestions(
        amount : Int,
        category : Int? = null,
        difficulty : String? = null,
        type : String? = null,
        encode : String? = TRIVIA_ENCODE_TEXT
    ) : ResponseOpendbDto

    /**
     * Get a list of 10 random trivia questions
     *
     * @return [ResponseOpendbDto] with the list of questions
     *
     * @see ResponseOpendbDto
     */
    suspend fun getTenRandomQuestions() : ResponseOpendbDto
}