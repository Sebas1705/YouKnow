package es.sebas1705.survey.viewmodel


import es.sebas1705.common.classes.mvi.MVIBaseState
import es.sebas1705.models.stats.Opinion

/**
 * State of the [SurveyViewModel].
 *
 * @param isLoading [Boolean]: Whether the existing survey (if any) is still loading, or the
 * current one is being published.
 * @param submitted [Boolean]: Whether this survey was just published successfully.
 * @param age [String]: Author's age, kept as free text so an empty field doesn't force a "0".
 * @param profession [String]: Author's profession.
 * @param androidKnowing [Int]: Self-rated Android knowledge, 0..5.
 * @param applicationsKnowing [Int]: Self-rated general apps knowledge, 0..5.
 * @param gamesKnowing [Int]: Self-rated games knowledge, 0..5.
 * @param socialNetworksKnowing [Int]: Self-rated social networks knowledge, 0..5.
 * @param otherKnowing [String]: Anything else the author wants to note about their background.
 * @param opinions [Map]<[String], [Opinion]>: Every other question, keyed by its
 * [es.sebas1705.survey.schema.SurveyQuestion.key] (see [es.sebas1705.survey.schema.SurveySchema]).
 *
 * @since 1.3.3
 * @author Sebas1705 30/09/2026
 */
data class SurveyState(
    val isLoading: Boolean,
    val submitted: Boolean,
    val age: String,
    val profession: String,
    val androidKnowing: Int,
    val applicationsKnowing: Int,
    val gamesKnowing: Int,
    val socialNetworksKnowing: Int,
    val otherKnowing: String,
    val opinions: Map<String, Opinion>,
) : MVIBaseState {

    companion object {
        /**
         * Default state of the Survey Screen.
         *
         * @return [SurveyState]: Default state of the Survey Screen.
         *
         * @since 1.3.3
         * @author Sebas1705 30/09/2026
         */
        fun default() = SurveyState(
            isLoading = false,
            submitted = false,
            age = "",
            profession = "",
            androidKnowing = 0,
            applicationsKnowing = 0,
            gamesKnowing = 0,
            socialNetworksKnowing = 0,
            otherKnowing = "",
            opinions = emptyMap(),
        )
    }
}
