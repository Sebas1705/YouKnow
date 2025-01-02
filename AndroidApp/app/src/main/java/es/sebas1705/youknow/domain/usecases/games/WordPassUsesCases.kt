package es.sebas1705.youknow.domain.usecases.games

import es.sebas1705.youknow.domain.model.games.WordModel

class GenerateWordPass(

) {
    operator fun invoke(
        numberWords: Int,
        onLoading: () -> Unit = {},
        onSuccess: (List<WordModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        onLoading()
        onSuccess(WordModel.defaultList(numberWords))
    }
}


data class WordPassUsesCases(
    val generateWordPass: GenerateWordPass
)