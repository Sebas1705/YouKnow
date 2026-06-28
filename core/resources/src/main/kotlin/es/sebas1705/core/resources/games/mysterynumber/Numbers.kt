package es.sebas1705.resources.games.mysterynumber


/**
 * Enum class that represents the modes of the Mystery Number game.
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
enum class Numbers(val number: Int, val str: String) {
    ONE(1, "1"),
    TEN(10, "10"),
    HUNDRED(100, "100"),
    THOUSAND(1_000, "1K"),
    TEN_THOUSAND(10_000, "10K"),
    HUNDRED_THOUSAND(100_000, "100K")
}