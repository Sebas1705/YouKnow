package es.sebas1705.authentication.datasources

import androidx.credentials.CredentialManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import es.sebas1705.analytics.datasources.LogEventDataSource
import es.sebas1705.authentication.config.SettingsAuth
import es.sebas1705.common.classes.TaskFlow
import es.sebas1705.common.states.DataState
import es.sebas1705.common.utlis.alias.DataEmptyFlow
import javax.inject.Inject

/**
 * Authentication data source for user authentication operations.
 *
 * @property credentialManager [CredentialManager]: credential manager to get the google credential
 * @property firebaseAuth [FirebaseAuth]: firebase authentication instance
 * @property logEventDataSource [LogEventDataSource]: data source for logging events
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
class UserAuthDataSource @Inject constructor(
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

    fun sendForgotPassword(
        email: String
    ): DataEmptyFlow = taskFlow.taskFlowProducer(
        taskAction = { firebaseAuth.sendPasswordResetEmail(email) },
        onSuccessListener = { DataState.EmptySuccess }
    )

    //Functions:
    fun signOut(): Boolean {
        firebaseAuth.signOut()
        return firebaseAuth.currentUser == null
    }

    fun isUserLogged(): Boolean = firebaseAuth.currentUser != null

    fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser
}