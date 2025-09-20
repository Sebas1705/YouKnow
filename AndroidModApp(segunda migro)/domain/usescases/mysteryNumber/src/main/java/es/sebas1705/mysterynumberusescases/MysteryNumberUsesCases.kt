package es.sebas1705.mysterynumberusescases


import es.sebas1705.mysterynumberusescases.usescases.GenerateRandomNumber

/**
 * Use cases for the mystery number game
 *
 * @property generateRandomNumber [GenerateRandomNumber]: Use case to generate a random number
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
data class MysteryNumberUsesCases(
    val generateRandomNumber: GenerateRandomNumber
)