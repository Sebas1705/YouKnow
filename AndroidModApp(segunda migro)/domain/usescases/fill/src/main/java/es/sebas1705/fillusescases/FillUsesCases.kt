package es.sebas1705.fillusescases

import es.sebas1705.fillusescases.usescases.FillByDefaultFamilies
import es.sebas1705.fillusescases.usescases.FillByDefaultQuestions
import es.sebas1705.fillusescases.usescases.FillByDefaultWords



/**
 * Use case to fill the database with default families, questions and words
 *
 * @param fillByDefaultFamilies [FillByDefaultFamilies]: Use case to fill the database with default families
 * @param fillByDefaultQuestions [FillByDefaultQuestions]: Use case to fill the database with default questions
 * @param fillByDefaultWords [FillByDefaultWords]: Use case to fill the database with default words
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
data class FillUsesCases(
    val fillByDefaultFamilies: FillByDefaultFamilies,
    val fillByDefaultQuestions: FillByDefaultQuestions,
    val fillByDefaultWords: FillByDefaultWords
)