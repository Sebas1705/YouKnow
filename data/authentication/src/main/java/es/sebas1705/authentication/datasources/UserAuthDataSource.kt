package es.sebas1705.authentication.datasources

import androidx.credentials.CredentialManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import es.sebas1705.analytics.datasources.LogEventDataSource
import es.sebas1705.authentication.config.AccountProvider
import es.sebas1705.authentication.config.SettingsAuth
import es.sebas1705.authentication.config.accountProviderOf
import es.sebas1705.authentication.config.deleteFailureType
import es.sebas1705.common.classes.TaskFlow
import es.sebas1705.common.states.DataState
import es.sebas1705.common.states.ErrorDataType
import es.sebas1705.common.utlis.alias.DataEmptyFlow
import es.sebas1705.common.utlis.extensions.types.logE
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
) {

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

    fun sendForgotPassword(
        email: String
    ): DataEmptyFlow = taskFlow.taskFlowProducer(
        taskAction = { firebaseAuth.sendPasswordResetEmail(email) },
        onSuccessListener = { DataState.EmptySuccess }
    )

    /**
     * Deletes the current user from Firebase Authentication.
     *
     * @return [DataEmptyFlow]: fails with [ErrorDataType.UNAUTHORIZED] when Firebase needs a recent
     * sign-in first (re-authenticate and call this again), and with [ErrorDataType.BAD_REQUEST] for
     * any other failure, including there being no signed-in user.
     *
     * @since 1.6.0
     * @author Sebas1705 11/11/2026
     */
    fun deleteCurrentUser(): DataEmptyFlow = taskFlow.taskFlowProducer(
        taskAction = {
            firebaseAuth.currentUser?.delete()
                ?: throw IllegalStateException(SettingsAuth.NOT_LOGGED_USER)
        },
        onSuccessListener = { DataState.EmptySuccess },
        failureType = ::deleteFailureType
    )

    //Functions:
    /**
     * How the current user can confirm their identity, from the providers linked to the account.
     *
     * @return [AccountProvider]: [AccountProvider.Other] when nobody is signed in.
     *
     * @since 1.6.0
     * @author Sebas1705 11/11/2026
     */
    fun getAccountProvider(): AccountProvider = accountProviderOf(
        firebaseAuth.currentUser?.providerData?.map { it.providerId }.orEmpty()
    )

    fun signOut(): Boolean {
        firebaseAuth.signOut()
        return firebaseAuth.currentUser == null
    }

    fun isUserLogged(): Boolean = firebaseAuth.currentUser != null

    fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser
}