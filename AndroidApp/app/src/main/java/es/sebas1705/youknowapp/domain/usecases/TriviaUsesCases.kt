package es.sebas1705.youknowapp.domain.usecases

import es.sebas1705.youknowapp.domain.model.TriviaResponse
import es.sebas1705.youknowapp.domain.repository.TriviaRepository


class GetTriviaTenQuestions(
    private val triviaRepository : TriviaRepository
) {

    suspend operator fun invoke() : TriviaResponse {
        return triviaRepository.getTenRandomQuestions()
    }

}

data class TriviaUsesCases(
    val getTriviaTenQuestions : GetTriviaTenQuestions
)