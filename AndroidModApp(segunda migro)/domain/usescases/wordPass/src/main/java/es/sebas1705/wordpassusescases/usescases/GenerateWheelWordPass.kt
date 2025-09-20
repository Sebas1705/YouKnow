package es.sebas1705.wordpassusescases.usescases


import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.Languages
import es.sebas1705.mappers.toWordModel
import es.sebas1705.models.games.WordModel
import es.sebas1705.room.repository.DatabaseRepository
import es.sebas1705.common.games.wordpass.Letter

/**
 * Use case to generate a word pass for the wheel
 *
 * @param databaseRepository [DatabaseRepository]: Repository to get words
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class GenerateWheelWordPass(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        difficulty: Difficulty,
        languages: Languages,
        onLoading: () -> Unit,
        onSuccess: (List<WordModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        onLoading()
        val words: MutableList<WordModel> = mutableListOf()
        Letter.entries.forEach {
            if (it == Letter.ANY)
                return@forEach
            val word = databaseRepository.getWords(
                1,
                it,
                languages,
                difficulty,
            )
            if (word.isNotEmpty())
                words.add(word[0].toWordModel())
            else
                onError("No ${it.letter} words found")
        }
        onSuccess(words)
    }
}