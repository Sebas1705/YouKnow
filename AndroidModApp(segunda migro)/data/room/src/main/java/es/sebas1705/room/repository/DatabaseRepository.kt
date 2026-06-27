package es.sebas1705.room.repository

import es.sebas1705.common.games.Category
import es.sebas1705.common.games.Difficulty
import es.sebas1705.common.games.Languages
import es.sebas1705.common.games.QuizType
import es.sebas1705.common.games.wordpass.Letter
import es.sebas1705.room.Database
import es.sebas1705.room.entities.FamiliesEntity
import es.sebas1705.room.entities.QuestionEntity
import es.sebas1705.room.entities.SurveyEntity
import es.sebas1705.room.entities.WordEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseRepository @Inject constructor(
    private val database: Database
) {
    suspend fun getFamilies(numFamilies: Int, category: Category, languages: Languages, difficulty: Difficulty): List<FamiliesEntity> =
        database.familiesDao().getFamilies(numFamilies, category.ordinal, languages.ordinal, difficulty.ordinal)

    suspend fun getQuestions(numQuestions: Int, category: Category, languages: Languages, difficulty: Difficulty, quizType: QuizType): List<QuestionEntity> =
        database.questionDao().getQuestions(numQuestions, category.ordinal, languages.ordinal, difficulty.ordinal, quizType.ordinal)

    suspend fun getWords(numWords: Int, letter: Letter, languages: Languages, difficulty: Difficulty): List<WordEntity> =
        database.wordDao().getWords(numWords, letter.ordinal, languages.ordinal, difficulty.ordinal)

    suspend fun getSurvey(authorFirebaseId: String): SurveyEntity? =
        database.surveyDao().getSurvey(authorFirebaseId)

    suspend fun insertOrReplace(entity: FamiliesEntity) = database.familiesDao().insertOrReplace(entity)
    suspend fun insertOrReplace(entity: QuestionEntity) = database.questionDao().insertOrReplace(entity)
    suspend fun insertOrReplace(entity: WordEntity) = database.wordDao().insertOrReplace(entity)
    suspend fun insertOrReplace(entity: SurveyEntity) = database.surveyDao().insertOrReplace(entity)
}
