package es.sebas1705.game.daily.viewmodel


import es.sebas1705.common.classes.mvi.MVIBaseState
import es.sebas1705.models.games.QuestionModel

/** Where the daily challenge screen is: fetching/publishing today's round, answering it, or done. */
enum class DailyChallengeStatus { LOADING, RUNNING, FINISHED }

/**
 * Data class that represents the state of the Daily Challenge Screen.
 *
 * @param status [DailyChallengeStatus]: Status of the screen.
 * @param questions [List]<[QuestionModel]>: Today's shared round, same for every player.
 * @param actualQuestion [Int]: Number of the current question.
 * @param points [Int]: Points obtained in this round.
 * @param correctAnswers [Int]: Number of correct answers.
 * @param loadError [String?]: Message if today's challenge couldn't be fetched or published.
 *
 * @author Sebas1705 30/09/2026
 * @since 1.3.0
 */
data class DailyChallengeState(
    var status: DailyChallengeStatus,
    var questions: List<QuestionModel>,
    var actualQuestion: Int,
    var points: Int,
    var correctAnswers: Int,
    var loadError: String?,
) : MVIBaseState {

    companion object {
        /**
         * Default state of the Daily Challenge Screen.
         *
         * @return [DailyChallengeState]: Default state of the Daily Challenge Screen.
         *
         * @since 1.3.0
         * @author Sebas1705 30/09/2026
         */
        fun default() = DailyChallengeState(
            status = DailyChallengeStatus.LOADING,
            questions = emptyList(),
            actualQuestion = 0,
            points = 0,
            correctAnswers = 0,
            loadError = null,
        )
    }
}
