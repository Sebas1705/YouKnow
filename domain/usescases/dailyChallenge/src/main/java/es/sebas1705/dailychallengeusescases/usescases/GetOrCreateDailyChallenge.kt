package es.sebas1705.dailychallengeusescases.usescases


import es.sebas1705.common.utlis.extensions.types.collect
import es.sebas1705.firestore.config.SettingsFS
import es.sebas1705.firestore.repository.FirestoreRepository
import es.sebas1705.mappers.toDailyChallengeDocument
import es.sebas1705.mappers.toQuestionModels
import es.sebas1705.models.games.QuestionModel
import es.sebas1705.quizusescases.usescases.GenerateQuestionList
import es.sebas1705.resources.games.Category
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.resources.games.QuizType

/**
 * Use case to fetch today's shared daily challenge, publishing it first if nobody has yet.
 *
 * The first device to ask on a given day generates the round (same path as a normal Quiz game)
 * and writes it to Firestore; every other device — and that same device on a later launch — just
 * reads the already-published questions, so everyone answers the exact same round. Firestore
 * security rules only allow *creating* the day's document, never updating it, so a race between
 * two devices publishing at once ends with one create landing and the other rejected; that device
 * falls back to reading what actually got published.
 *
 * @property firestoreRepository [FirestoreRepository]: Repository for the shared challenge document.
 * @property generateQuestionList [GenerateQuestionList]: Use case to fetch a fresh round of questions.
 *
 * @since 1.3.0
 * @author Sebas1705 30/09/2026
 */
class GetOrCreateDailyChallenge(
    private val firestoreRepository: FirestoreRepository,
    private val generateQuestionList: GenerateQuestionList,
) {
    suspend operator fun invoke(
        date: String,
        numQuestions: Int = DEFAULT_QUESTIONS,
        onLoading: suspend () -> Unit,
        onSuccess: suspend (List<QuestionModel>) -> Unit,
        onError: suspend (String) -> Unit,
    ) = firestoreRepository.getDailyChallenge(date).collect(
        onLoading = onLoading,
        onSuccess = { document -> onSuccess(document.toQuestionModels()) },
        onError = { message ->
            if (message == SettingsFS.DAILY_CHALLENGE_NOT_FOUND)
                publish(date, numQuestions, onLoading, onSuccess, onError)
            else
                onError(message)
        }
    )

    private suspend fun publish(
        date: String,
        numQuestions: Int,
        onLoading: suspend () -> Unit,
        onSuccess: suspend (List<QuestionModel>) -> Unit,
        onError: suspend (String) -> Unit,
    ) = generateQuestionList(
        numberQuestions = numQuestions,
        category = Category.ANY,
        difficulty = Difficulty.MEDIUM,
        languages = Languages.ANY,
        quizType = QuizType.MULTIPLE,
        onLoading = onLoading,
        onSuccess = { questions ->
            firestoreRepository.createDailyChallenge(date, questions.toDailyChallengeDocument(date)).collect(
                onSuccess = { onSuccess(questions) },
                // Most likely lost the race to publish today: someone else's version is now the
                // canonical one, so read that instead of the round this device just generated.
                onError = {
                    firestoreRepository.getDailyChallenge(date).collect(
                        onSuccess = { document -> onSuccess(document.toQuestionModels()) },
                        onError = onError
                    )
                }
            )
        },
        onError = onError
    )

    companion object {
        private const val DEFAULT_QUESTIONS = 5
    }
}
