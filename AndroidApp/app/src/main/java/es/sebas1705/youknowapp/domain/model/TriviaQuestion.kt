package es.sebas1705.youknowapp.domain.model

import com.google.gson.annotations.SerializedName

data class TriviaQuestion(
    val category: String,
    @SerializedName("correct_answer") val correctAnswer: String,
    val difficulty: String,
    @SerializedName("incorrect_answers") val incorrectAnswers: List<String>,
    val question: String,
    val type: String
)