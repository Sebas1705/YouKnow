package es.sebas1705.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import es.sebas1705.room.daos.FamiliesDao
import es.sebas1705.room.daos.QuestionDao
import es.sebas1705.room.daos.SurveyDao
import es.sebas1705.room.daos.WordDao
import es.sebas1705.room.entities.FamiliesEntity
import es.sebas1705.room.entities.QuestionEntity
import es.sebas1705.room.entities.SurveyEntity
import es.sebas1705.room.entities.WordEntity
import es.sebas1705.room.typeconverter.Converter

/**
 * Local database
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
@Database(
    entities = [
        QuestionEntity::class,
        FamiliesEntity::class,
        WordEntity::class,
        SurveyEntity::class
    ],
    version = 1
)
@TypeConverters(Converter::class)
abstract class Database : RoomDatabase() {

    //DAOs:
    abstract fun questionDao(): QuestionDao

    abstract fun familiesDao(): FamiliesDao

    abstract fun wordDao(): WordDao

    abstract fun surveyDao(): SurveyDao

}