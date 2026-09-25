package es.sebas1705.files

import es.sebas1705.files.config.SettingsFL
import es.sebas1705.files.datasource.interfaces.FileDataSource
import es.sebas1705.files.json.FamiliesJson
import es.sebas1705.files.json.QuestionJson
import es.sebas1705.files.json.WordJson
import kotlinx.serialization.KSerializer
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

/**
 * The games read their default data from these assets on first run (the guide fills the local
 * database from them). A missing file or a format the decoder does not accept leaves the games
 * empty and stops the guide with an "Empty words" error, so check the real files, under the exact
 * names the code opens.
 */
class DefaultDataAssetsTest {

    private fun <T> decodeAsset(name: String, serializer: KSerializer<T>): List<T> {
        val file = File("src/main/assets/$name")
        assertTrue("missing asset ${file.path}", file.isFile)
        return FileDataSource.decode(file.readText(), serializer)
    }

    @Test
    fun `default words decode`() {
        val words = decodeAsset(SettingsFL.WORD_DEFAULT_BD_JSON, WordJson.serializer())
        assertTrue("only ${words.size} words", words.size > 1000)
        assertTrue(words.all { it.word.isNotBlank() && it.definitions.isNotEmpty() })
    }

    @Test
    fun `default questions decode`() {
        val questions = decodeAsset(SettingsFL.QUESTION_DEFAULT_BD_JSON, QuestionJson.serializer())
        assertTrue("only ${questions.size} questions", questions.size > 1000)
        assertTrue(questions.all { it.correctAnswer in it.answers })
    }

    @Test
    fun `default families decode`() {
        val families = decodeAsset(SettingsFL.FAMILIES_DEFAULT_BD_JSON, FamiliesJson.serializer())
        assertTrue("only ${families.size} families", families.size > 1000)
        assertTrue(families.all { it.correctAnswer in it.answers })
    }
}
