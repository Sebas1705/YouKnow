package es.sebas1705.wordpassusescases


import es.sebas1705.wordpassusescases.usescases.GenerateWheelWordPass
import es.sebas1705.wordpassusescases.usescases.GenerateWordPass
import es.sebas1705.wordpassusescases.usescases.InsertWordPassList

/**
 * Use cases for the word pass game
 *
 * @property generateWordPass [GenerateWordPass]: Use case to generate a word pass
 * @property generateWheelWordPass [GenerateWheelWordPass]: Use case to generate a word pass for the wheel
 * @property insertWordPassList [InsertWordPassList]: Use case to insert a list of words
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
data class WordPassUsesCases(
    val generateWordPass: GenerateWordPass,
    val generateWheelWordPass: GenerateWheelWordPass,
    val insertWordPassList: InsertWordPassList
)