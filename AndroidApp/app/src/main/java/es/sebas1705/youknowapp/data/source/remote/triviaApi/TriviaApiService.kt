package es.sebas1705.youknowapp.data.source.remote.triviaApi

import es.sebas1705.youknowapp.domain.model.TriviaResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TriviaApiService {

    @GET("api.php")
    suspend fun getTrivia(
        @Query("amount") amount : Int,
        @Query("category") category : Int? = null,
        @Query("difficulty") difficulty : String? = null,
        @Query("type") type : String? = null,
        @Query("encode") encode : String? = null
    ): TriviaResponse

}