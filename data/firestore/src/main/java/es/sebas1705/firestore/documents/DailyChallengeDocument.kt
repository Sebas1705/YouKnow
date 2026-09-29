package es.sebas1705.firestore.documents

/**
 * One question inside a [DailyChallengeDocument], as a flat, schema-stable shape (enums stored by
 * name, not ordinal, so reordering an enum in code never corrupts an already-published day).
 *
 * @property question [String]: The question text.
 * @property answers [List]<[String]>: All the possible answers, in the order they should show.
 * @property correctAnswer [String]: Which of [answers] is right.
 * @property category [String]: Name of the [es.sebas1705.resources.games.Category].
 * @property difficulty [String]: Name of the [es.sebas1705.resources.games.Difficulty].
 * @property quizType [String]: Name of the [es.sebas1705.resources.games.QuizType].
 *
 * @since 1.3.0
 * @author Sebas1705 30/09/2026
 */
data class DailyChallengeQuestionDocument(
    val question: String = "",
    val answers: List<String> = emptyList(),
    val correctAnswer: String = "",
    val category: String = "",
    val difficulty: String = "",
    val quizType: String = "",
)

/**
 * The day's shared quiz round: the same [questions] for every player, published once by whichever
 * device is first to ask for that day (see `GetOrCreateDailyChallenge`) and immutable afterwards.
 *
 * @property date [String]: The challenge's day, `yyyy-MM-dd` in UTC — also the document id.
 * @property questions [List]<[DailyChallengeQuestionDocument]>: The shared round.
 *
 * @since 1.3.0
 * @author Sebas1705 30/09/2026
 */
data class DailyChallengeDocument(
    val date: String = "",
    val questions: List<DailyChallengeQuestionDocument> = emptyList(),
)
