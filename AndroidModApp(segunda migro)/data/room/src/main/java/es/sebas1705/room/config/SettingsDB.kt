package es.sebas1705.room.config

/**
 * Object with the settings of the database
 *
 * @property DATABASE_NAME [String]: Database name
 * @property QUESTION_TABLE [String]: Question table name
 * @property FAMILIES_TABLE [String]: Families table name
 * @property WORD_TABLE [String]: Word table name
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
object SettingsDB {
    const val DATABASE_NAME = "YouKnow_database"
    const val QUESTION_TABLE = "questions_table"
    const val FAMILIES_TABLE = "families_table"
    const val WORD_TABLE = "word_table"
    const val SURVEY_TABLE = "survey_table"
}