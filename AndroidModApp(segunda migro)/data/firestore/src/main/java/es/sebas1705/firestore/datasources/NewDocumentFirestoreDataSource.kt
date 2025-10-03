package es.sebas1705.firestore.datasources

import com.google.firebase.firestore.FirebaseFirestore
import es.sebas1705.analytics.datasources.LogEventDataSource
import es.sebas1705.common.classes.TaskFlow
import es.sebas1705.common.states.DataState
import es.sebas1705.common.utlis.alias.DataFlow
import es.sebas1705.firestore.config.SettingsFS
import es.sebas1705.firestore.documents.NewDocument
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
) : ClassLogData() {

    //Managers:
    private val taskFlow = TaskFlow(
        this,
        logEventDataSource::logError,
        SettingsFS.ERROR_GENERIC_MESSAGE_FAIL,
        SettingsFS.ERROR_GENERIC_MESSAGE_EX
    )

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
            val news = it.documents.map { document ->
                document.toObject(NewDocument::class.java)!!
            }
            DataState.Success(news)
        }
    )

}