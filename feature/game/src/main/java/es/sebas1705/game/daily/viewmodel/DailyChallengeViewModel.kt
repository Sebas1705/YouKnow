package es.sebas1705.game.daily.viewmodel


import android.app.Application
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.auth.AuthUsesCases
import es.sebas1705.common.classes.mvi.MVIBaseViewModel
import es.sebas1705.common.utlis.extensions.composables.printTextInToast
import es.sebas1705.dailychallengeusescases.DailyChallengeUsesCases
import es.sebas1705.user.UserUsesCases
import kotlinx.coroutines.Dispatchers
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/** Every player's "today" for the challenge, UTC so the shared round doesn't fragment by timezone. */
private val DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE

/**
 * ViewModel for the Daily Challenge: a short, single round of questions shared by every player on
 * a given UTC day (see [DailyChallengeUsesCases.getOrCreateDailyChallenge]).
 *
 * @param dailyChallengeUsesCases [DailyChallengeUsesCases]: UseCases for the daily challenge.
 * @param userUsesCases [UserUsesCases]: UseCases for the user.
 * @param authUsesCases [AuthUsesCases]: UseCases for the Auth.
 * @param application [Application]: Application context.
 *
 * @author Sebas1705 30/09/2026
 * @since 1.3.0
 */
@HiltViewModel
class DailyChallengeViewModel @Inject constructor(
    private val dailyChallengeUsesCases: DailyChallengeUsesCases,
    private val userUsesCases: UserUsesCases,
    private val authUsesCases: AuthUsesCases,
    private val application: Application
) : MVIBaseViewModel<DailyChallengeState, DailyChallengeIntent>() {

    /** The UTC day of the round being played: the day its points are credited to, even past midnight. */
    @Volatile
    private var challengeDate: String = LocalDate.now(ZoneOffset.UTC).format(DATE_FORMATTER)

    override fun initState(): DailyChallengeState = DailyChallengeState.default()

    override fun onInit() {
        intentHandler(DailyChallengeIntent.LoadChallenge)
    }

    override fun intentHandler(intent: DailyChallengeIntent) {
        when (intent) {
            is DailyChallengeIntent.LoadChallenge -> loadChallenge()
            is DailyChallengeIntent.Response -> response(intent)
            is DailyChallengeIntent.OutGame -> outGame(intent)
        }
    }

    //Actions:
    private fun loadChallenge() = execute(Dispatchers.IO) {
        val today = LocalDate.now(ZoneOffset.UTC).format(DATE_FORMATTER)
        challengeDate = today
        dailyChallengeUsesCases.getOrCreateDailyChallenge(
            date = today,
            onLoading = { updateUi { it.copy(status = DailyChallengeStatus.LOADING, loadError = null) } },
            onSuccess = { questions ->
                updateUi {
                    it.copy(
                        status = DailyChallengeStatus.RUNNING,
                        questions = questions,
                        actualQuestion = 0,
                        points = 0,
                        correctAnswers = 0,
                    )
                }
            },
            onError = { error ->
                updateUi { it.copy(loadError = error) }
            }
        )
    }

    private fun response(
        intent: DailyChallengeIntent.Response
    ) {
        val state = _uiState.value
        val question = state.questions[state.actualQuestion]
        val correct = intent.response == question.correctAnswer
        val last = state.actualQuestion + 1 == state.questions.size
        val questionPoints = (if (correct) 1 else 0) *
                (question.difficulty.points * question.quizType.multiPoints).toInt()
        updateUi {
            it.copy(
                status = if (last) DailyChallengeStatus.FINISHED else DailyChallengeStatus.RUNNING,
                points = it.points + questionPoints,
                actualQuestion = it.actualQuestion + 1,
                correctAnswers = it.correctAnswers + (if (correct) 1 else 0),
            )
        }
    }

    private fun outGame(
        intent: DailyChallengeIntent.OutGame
    ) = addPointsAndCredits(_uiState.value.points, intent.onSuccess)


    //Privates:
    private fun addPointsAndCredits(
        points: Int,
        onSuccess: () -> Unit
    ) = execute(Dispatchers.IO) {
        if (points <= 0) {
            execute { onSuccess() }
            return@execute
        }
        userUsesCases.getUser(
            firebaseId = authUsesCases.getFirebaseUser()!!.uid,
            onLoading = { },
            onSuccess = { user ->
                Log.i("DailyChallengeViewModel", "User loaded")
                execute(Dispatchers.IO) {
                    // One batch: the points, the credits, the round event and the day's completion.
                    // The rules accept the day once per player, so a replay is refused here.
                    userUsesCases.addRoundRewards(
                        user = user,
                        points = points,
                        credits = points / (10..100).random(),
                        dailyDate = challengeDate,
                        onSuccess = { execute { onSuccess() } },
                        onError = { error -> stopAndError(error, application::printTextInToast) }
                    )
                }
            },
            onError = { error -> stopAndError(error, application::printTextInToast) }
        )
    }

    private fun stopAndError(error: String, onError: (String) -> Unit) {
        execute { onError(error) }
    }
}
