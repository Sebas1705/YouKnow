package es.sebas1705.wordpassusescases.usescases


import android.util.Log
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.Languages
import es.sebas1705.mappers.toWordModel
import es.sebas1705.models.games.WordModel
import es.sebas1705.room.repository.DatabaseRepository
import es.sebas1705.common.games.wordpass.Letter

/**
 * Use case to generate a word pass
 *
 * @param databaseRepository [DatabaseRepository]: Repository to get words
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
class GenerateWordPass(
    private val databaseRepository: DatabaseRepository
) {
    suspend operator fun invoke(
        numFamilies: Int,
        letter: Letter,
        languages: Languages,
        difficulty: Difficulty,
        onLoading: () -> Unit,
        onSuccess: (List<WordModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        onLoading()
        val words = databaseRepository.getWords(
            numFamilies,
            letter,
            languages,
            difficulty,
        )
        Log.d(
            "GenerateWordPass", """
            |*Words same features* 
            |-Same letter: ${words.filter { it.letter == letter }.size} (${letter.name})
            |-Same language: ${words.filter { it.language == languages }.size} (${languages.name})
            |-Same difficulty: ${words.filter { it.difficulty == difficulty }.size} (${difficulty.name})
            |-Results: ${words.filter { it.letter == letter && it.language == languages && it.difficulty == difficulty }.size}/${words.size}
        """.trimMargin()
        )
        if (words.isEmpty())
            onError("No words found")
        else if (words.size < numFamilies)
            onError("Not enough words found")
        else
            onSuccess(words.map { it.toWordModel() })
    }
}