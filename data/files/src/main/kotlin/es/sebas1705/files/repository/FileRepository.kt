package es.sebas1705.files.repository

import es.sebas1705.files.datasource.FamiliesJsonFileDataSource
import es.sebas1705.files.datasource.QuestionJsonFileDataSource
import es.sebas1705.files.datasource.WordJsonFileDataSource
import es.sebas1705.files.json.FamiliesJson
import es.sebas1705.files.json.QuestionJson
import es.sebas1705.files.json.WordJson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FileRepository @Inject constructor(
    private val familiesDataSource: FamiliesJsonFileDataSource,
    private val questionDataSource: QuestionJsonFileDataSource,
    private val wordDataSource: WordJsonFileDataSource
) {
    suspend fun readDefaultBDFamilies(): List<FamiliesJson> =
        familiesDataSource.readJsonFile()

    suspend fun readDefaultBDQuestions(): List<QuestionJson> =
        questionDataSource.readJsonFile()

    suspend fun readDefaultBDWords(): List<WordJson> =
        wordDataSource.readJsonFile()
}
