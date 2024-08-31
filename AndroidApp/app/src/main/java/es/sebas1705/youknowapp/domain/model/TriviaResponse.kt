package es.sebas1705.youknowapp.domain.model

import com.google.gson.annotations.SerializedName

data class TriviaResponse(
    val responseCode: Int,
    @SerializedName("results") val triviaQuestions: List<TriviaQuestion>
)