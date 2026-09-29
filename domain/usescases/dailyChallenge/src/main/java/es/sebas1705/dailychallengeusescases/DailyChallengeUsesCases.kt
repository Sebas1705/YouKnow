package es.sebas1705.dailychallengeusescases


import es.sebas1705.dailychallengeusescases.usescases.GetOrCreateDailyChallenge

/**
 * Use cases for the daily challenge
 *
 * @property getOrCreateDailyChallenge [GetOrCreateDailyChallenge]: Use case to fetch (or publish) today's challenge
 *
 * @since 1.3.0
 * @author Sebas1705 30/09/2026
 */
data class DailyChallengeUsesCases(
    val getOrCreateDailyChallenge: GetOrCreateDailyChallenge
)
