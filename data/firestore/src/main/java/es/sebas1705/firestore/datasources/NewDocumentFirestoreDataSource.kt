package es.sebas1705.firestore.datasources

import com.google.firebase.firestore.FirebaseFirestore
import es.sebas1705.analytics.datasources.LogEventDataSource
import es.sebas1705.common.classes.TaskFlow
import es.sebas1705.common.states.DataState
import es.sebas1705.common.utlis.alias.DataFlow
import es.sebas1705.common.utlis.extensions.types.logE
import es.sebas1705.firestore.config.SettingsFS
import es.sebas1705.firestore.documents.NewDocument
import es.sebas1705.firestore.documents.toObjectOrNull
import javax.inject.Inject

/**
 * Data source for managing Firestore operations.
 *
 * @property firestore [FirebaseFirestore]: Firestore instance
 * @property logEventDataSource [LogEventDataSource]: Data source for logging events
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
class NewDocumentFirestoreDataSource @Inject constructor(
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
    private val newsReference = firestore.collection(SettingsFS.NEWS_COLLECTION_NAME)
    
    //Tasks:
    /**
     * Get the news
     *
     * @return [DataFlow]<[List]<[NewDocument]>>: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun getNews(): DataFlow<List<NewDocument>> = taskFlow.taskFlowProducer(
        taskAction = { newsReference.get() },
        onSuccessListener = {
            val news = it.documents.mapNotNull { document ->
                document.toObjectOrNull(NewDocument::class.java, ::logMalformed)
            }
            DataState.Success(news)
        }
    )

    //Privates:
    private fun logMalformed(message: String) {
        logE(message)
        logEventDataSource.logError(this.javaClass.kotlin, message)
    }
}