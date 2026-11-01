package es.sebas1705.firestore.datasources

import com.google.firebase.firestore.FirebaseFirestore
import es.sebas1705.analytics.datasources.LogEventDataSource
import es.sebas1705.common.classes.TaskFlow
import es.sebas1705.common.states.DataState
import es.sebas1705.common.states.ErrorDataType
import es.sebas1705.common.utlis.alias.DataFlow
import es.sebas1705.common.utlis.extensions.types.logE
import es.sebas1705.firestore.config.SettingsFS
import es.sebas1705.firestore.documents.DailyChallengeDocument
import es.sebas1705.firestore.documents.hasPlayableQuestions
import es.sebas1705.firestore.documents.toObjectOrNull
import javax.inject.Inject

/**
 * Data source for the shared daily challenge document.
 *
 * @property firestore [FirebaseFirestore]: Firestore instance
 * @property logEventDataSource [LogEventDataSource]: Data source for logging events
 *
 * @since 1.3.0
 * @author Sebas1705 30/09/2026
 */
class DailyChallengeDocumentFirestoreDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val logEventDataSource: LogEventDataSource
) {

    //Managers:
    private val taskFlow = TaskFlow(
        this.javaClass.kotlin,
        SettingsFS.ERROR_GENERIC_MESSAGE_FAIL,
        SettingsFS.ERROR_GENERIC_MESSAGE_EX
    ) { clazz, error ->
        logE(error)
        logEventDataSource.logError(clazz, error)
    }

    //References:
    private val dailyChallengesReference = firestore.collection(SettingsFS.DAILY_CHALLENGES_COLLECTION_NAME)

    //Tasks:
    /**
     * Get the day's published challenge, if any.
     *
     * @param date [String]: The day to read, `yyyy-MM-dd`.
     *
     * @return [DataFlow]<[DailyChallengeDocument]>: [SettingsFS.DAILY_CHALLENGE_NOT_FOUND] when no
     * one has published that day yet — the caller then generates one and calls [createDailyChallenge].
     * [SettingsFS.DAILY_CHALLENGE_MALFORMED] when the published document is unusable: it is immutable,
     * so publishing again can't replace it.
     *
     * @since 1.3.0
     * @author Sebas1705 30/09/2026
     */
    fun getDailyChallenge(date: String): DataFlow<DailyChallengeDocument> = taskFlow.taskFlowProducer(
        taskAction = { dailyChallengesReference.document(date).get() },
        onSuccessListener = {
            val document = it.toObjectOrNull(DailyChallengeDocument::class.java, ::logMalformed)
            when {
                !it.exists() -> taskFlow.createResponse(
                    ErrorDataType.BAD_REQUEST,
                    SettingsFS.DAILY_CHALLENGE_NOT_FOUND
                )

                document?.hasPlayableQuestions() == true -> DataState.Success(document)
                else -> taskFlow.createResponse(
                    ErrorDataType.INTERNAL,
                    SettingsFS.DAILY_CHALLENGE_MALFORMED
                )
            }
        }
    )

    /**
     * Publish a day's challenge. Security rules only allow this while the document doesn't already
     * exist yet, so a race between two devices ends with one create landing and the other's being
     * rejected — that device should simply re-read with [getDailyChallenge] afterwards.
     *
     * @param date [String]: The day being published, `yyyy-MM-dd`.
     * @param dailyChallengeDocument [DailyChallengeDocument]: The generated round to publish.
     *
     * @return [DataFlow]<[DailyChallengeDocument]>: The same document once written.
     *
     * @since 1.3.0
     * @author Sebas1705 30/09/2026
     */
    fun createDailyChallenge(
        date: String,
        dailyChallengeDocument: DailyChallengeDocument
    ): DataFlow<DailyChallengeDocument> = taskFlow.taskFlowProducer(
        taskAction = { dailyChallengesReference.document(date).set(dailyChallengeDocument) },
        onSuccessListener = { DataState.Success(dailyChallengeDocument) }
    )

    //Privates:
    private fun logMalformed(message: String) {
        logE(message)
        logEventDataSource.logError(this.javaClass.kotlin, message)
    }
}
