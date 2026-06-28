package es.sebas1705.firestore.datasources

import com.google.firebase.firestore.FirebaseFirestore
import es.sebas1705.analytics.datasources.LogEventDataSource
import es.sebas1705.common.classes.TaskFlow
import es.sebas1705.common.states.ErrorDataType
import es.sebas1705.common.states.DataState
import es.sebas1705.common.utlis.alias.DataFlow
import es.sebas1705.common.utlis.alias.DataEmptyFlow
import es.sebas1705.common.utlis.extensions.types.logE
import es.sebas1705.firestore.config.SettingsFS
import es.sebas1705.firestore.documents.SurveyDocument
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
class SurveyDocumentFirestoreDataSource @Inject constructor(
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
    private val surveysReference = firestore.collection(SettingsFS.SURVEYS_COLLECTION_NAME)


    //Tasks:
    /**
     * Save a Survey in Firestore
     *
     * @param surveyDocument [SurveyDocument]: New to save
     *
     * @return [DataEmptyFlow]: Flow with the response of the operation
     */
    fun publicNewSurvey(
        surveyDocument: SurveyDocument
    ): DataEmptyFlow = taskFlow.taskFlowProducer(
        taskAction = {
            surveysReference.document(surveyDocument.authorFirebaseId)
                .set(surveyDocument)
        },
        onSuccessListener = { DataState.EmptySuccess }
    )

    /**
     * Get a Survey from Firestore
     *
     * @param firebaseId [String]: Id of the Survey to get
     *
     * @return [DataFlow]<[SurveyDocument]>: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun getSurvey(
        firebaseId: String
    ): DataFlow<SurveyDocument> = taskFlow.taskFlowProducer(
        taskAction = { surveysReference.document(firebaseId).get() },
        onSuccessListener = {
            val surveyDocument = it.toObject(SurveyDocument::class.java)
            if (surveyDocument != null) DataState.Success(surveyDocument)
            else taskFlow.createResponse(
                ErrorDataType.BAD_REQUEST,
                SettingsFS.SURVEY_NOT_FOUND
            )
        }
    )

    /**
     * Get all Surveys
     *
     * @return [DataFlow]<[List]<[SurveyDocument]>>: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebas1705 22/09/2025
     */
    fun getSurveys(): DataFlow<List<SurveyDocument>> = taskFlow.taskFlowProducer(
        taskAction = { surveysReference.get() },
        onSuccessListener = {
            val surveys = it.documents.map { document ->
                document.toObject(SurveyDocument::class.java)!!
            }
            DataState.Success(surveys)
        }
    )

}