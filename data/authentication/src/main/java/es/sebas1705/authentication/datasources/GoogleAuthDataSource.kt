package es.sebas1705.authentication.datasources

import android.content.Context
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import es.sebas1705.analytics.datasources.LogEventDataSource
import es.sebas1705.authentication.config.SettingsAuth
import es.sebas1705.authentication.config.getCredentialRequestGoogle
import es.sebas1705.common.classes.TaskFlow
import es.sebas1705.common.states.ErrorDataType
import es.sebas1705.common.states.DataState
import es.sebas1705.common.utlis.alias.DataEmptyFlow
import es.sebas1705.common.utlis.extensions.types.logE
import javax.inject.Inject

/**
 * Authentication repository implementation
 *
 * @property credentialManager [CredentialManager]: credential manager to get the google credential
 * @property firebaseAuth [FirebaseAuth]: firebase authentication instance
 * @property logEventDataSource [LogEventDataSource]: data source for logging events
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
class GoogleAuthDataSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val logEventDataSource: LogEventDataSource,
    @param:ApplicationContext private val context: Context
) {

    //Properties:
    private var credentialManager: CredentialManager? = null

    //Managers:
    private val taskFlow = TaskFlow(
        this.javaClass.kotlin,
        SettingsAuth.ERROR_GENERIC_MESSAGE_FAIL,
        SettingsAuth.ERROR_GENERIC_MESSAGE_EX
    ) { clazz, error ->
        logE(error)
        logEventDataSource.logError(clazz, error)
    }

    //Tasks:
    /**
     * Signs in with Google using the provided context.
     *
     * @return [DataEmptyFlow]: A flow response indicating the result of the sign-in operation.
     *
     * @since 0.1.0
     * @author Sebas1705 09/09/2025
     */
    suspend fun signWithGoogle(): DataEmptyFlow {
        credentialManager = CredentialManager.create(context)
        var credential: Credential? = null
        var error = ""
        try {
            credential = credentialManager!!.getCredential(
                context,
                context.getCredentialRequestGoogle
            ).credential
        } catch (e: Exception) {
            logE("Error getting google credential: ${e.message}")
            error = e.message ?: SettingsAuth.ERROR_GENERIC_MESSAGE_FAIL
        }
        return taskFlow.taskFlowProducer(
            assertChecker = {
                if (credential == null) error
                else if (
                    credential is CustomCredential &&
                    credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
                )
                    null
                else SettingsAuth.WRONG_CREDENTIALS
            },
            taskAction = {
                val googleIdTokenCredential =
                    GoogleIdTokenCredential.createFrom(credential!!.data)
                val authCredential =
                    GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)
                firebaseAuth.signInWithCredential(authCredential)
            },
            onSuccessListener = {
                if (it.user != null) DataState.EmptySuccess
                else taskFlow.createResponse(
                    ErrorDataType.INTERNAL,
                    SettingsAuth.NOT_LOGGED_USER
                )
            }
        )
    }
}