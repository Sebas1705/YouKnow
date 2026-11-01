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

/**
 * Whether the round can actually be played. Documents are immutable once published and a client
 * wrote them, so one that maps fine can still be useless: `questions: [{}]` gives a question with
 * no answers. Kept out of the class: the mapper would save an `isPlayable` member as a field, and
 * the security rules reject unknown fields.
 *
 * @return [Boolean]: True if there is a question and each one has a text, answers and a right answer.
 *
 * @since 1.5.0
 * @author Sebas1705 31/10/2026
 */
internal fun DailyChallengeDocument.hasPlayableQuestions(): Boolean =
    questions.isNotEmpty() && questions.all { question ->
        question.question.isNotBlank()
            && question.answers.size >= 2
            && question.answers.all { it.isNotBlank() }
            && question.correctAnswer in question.answers
    }
