package es.sebas1705.authentication.datasources

import androidx.credentials.CredentialManager
import com.google.firebase.auth.FirebaseAuth
import es.sebas1705.analytics.datasources.LogEventDataSource
import es.sebas1705.authentication.config.SettingsAuth
import es.sebas1705.common.classes.TaskFlow
import es.sebas1705.common.states.ErrorDataType
import es.sebas1705.common.states.DataState
import es.sebas1705.common.utlis.alias.DataEmptyFlow
import javax.inject.Inject

/**
 * Authentication data source for email authentication operations.
 *
 * @property credentialManager [CredentialManager]: credential manager to get the google credential
 * @property firebaseAuth [FirebaseAuth]: firebase authentication instance
 * @property logEventDataSource [LogEventDataSource]: data source for logging events
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
class EmailAuthDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val logEventDataSource: LogEventDataSource
) : ClassLogData() {

    //Managers:
    private val taskFlow = TaskFlow(
        this,
        logEventDataSource::logError,
        SettingsAuth.ERROR_GENERIC_MESSAGE_FAIL,
        SettingsAuth.ERROR_GENERIC_MESSAGE_EX
    )

    //Tasks:
    /**
     * Signs up a user with email and password.
     *
     * @param email [String]: The email address of the user.
     * @param password [String]: The password for the user.
     *
     * @return [DataEmptyFlow]: A flow response indicating the result of the sign-up operation.
     *
     * @since 0.1.0
     * @author Sebas1705 09/09/2025
     */
    fun signUpWithEmail(
        email: String,
        password: String,
    ): DataEmptyFlow = taskFlow.taskFlowProducer(
        taskAction = { firebaseAuth.createUserWithEmailAndPassword(email, password) },
        onSuccessListener = {
            if (it.user != null) {
                it.user?.sendEmailVerification()
                DataState.EmptySuccess
            } else taskFlow.createResponse(
                ErrorDataType.INTERNAL,
                SettingsAuth.NOT_LOGGED_USER
            )
        }
    )

    /**
     * Signs in a user with email and password.
     *
     * @param email [String]: The email address of the user.
     * @param password [String]: The password for the user.
     *
     * @return [DataEmptyFlow]: A flow response indicating the result of the sign-in operation.
     *
     * @since 0.1.0
     * @author Sebas1705 09/09/2025
     */
    fun signInWithEmail(
        email: String,
        password: String,
    ): DataEmptyFlow = taskFlow.taskFlowProducer(
        taskAction = { firebaseAuth.signInWithEmailAndPassword(email, password) },
        onSuccessListener = {
            if (it.user != null) DataState.EmptySuccess
            else taskFlow.createResponse(
                ErrorDataType.INTERNAL,
                SettingsAuth.NOT_LOGGED_USER
            )
        }
    )
}