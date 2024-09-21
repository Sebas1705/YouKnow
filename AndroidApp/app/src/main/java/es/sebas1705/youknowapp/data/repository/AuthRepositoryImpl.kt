package es.sebas1705.youknowapp.data.repository

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import es.sebas1705.youknowapp.data.source.remote.authManager.getCredentialRequestGoogle
import es.sebas1705.youknowapp.domain.repository.AuthRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * Implementation of [AuthRepository] interface
 */
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val credentialManager: CredentialManager
) : AuthRepository {

    /**
     * Sign up with email and password
     *
     * @param email: String
     * @param password: String
     * @param onSuccess: () -> Unit
     * @param onError: (String?) -> Unit
     */
    override fun signUpWithEmail(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String?) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    firebaseAuth.currentUser?.sendEmailVerification()
                    onSuccess()
                }
                else onError(it.exception?.message)
            }
    }

    /**
     * Sign in with email and password
     *
     * @param email: String
     * @param password: String
     * @param onSuccess: () -> Unit
     * @param onError: (String?) -> Unit
     */
    override fun signInWithEmail(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String?) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful && firebaseAuth.currentUser?.isEmailVerified!!) onSuccess()
                else onError(it.exception?.message ?: "Email has to be verified")
            }
    }

    /**
     * Sign in with Google
     *
     * @param context: Context
     * @param onSuccess: () -> Unit
     * @param onError: (String?) -> Unit
     */
    override suspend fun signWithGoogle(
        context: Context,
        onSuccess: () -> Unit,
        onError: (String?) -> Unit
    ) {
        callbackFlow {
            try {
                val credential = credentialManager.getCredential(context, context.getCredentialRequestGoogle).credential

                // Check if the received credential is a valid Google ID Token
                if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                    val authCredential = GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)
                    val authResult = firebaseAuth.signInWithCredential(authCredential).await()
                    trySend(Result.success(authResult))
                } else {
                    throw RuntimeException("Received an invalid credential type")
                }
            } catch (e: GetCredentialCancellationException) {
                trySend(Result.failure(Exception("Sign-in was canceled. Please try again.")))

            } catch (e: Exception) {
                trySend(Result.failure(e))
            }
            awaitClose { }
        }.collect { result ->
            result.fold(
                onSuccess = { onSuccess() },
                onFailure = { onError(it.message ?: "An error occurred") }
            )
        }
    }

    /**
     * Sign in with Facebook
     *
     * @param onSuccess: () -> Unit
     * @param onError: (String?) -> Unit
     */
    override fun signWithFacebook(
        onSuccess: () -> Unit,
        onError: (String?) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    /**
     * Sign out
     *
     * @param onSuccess: () -> Unit
     * @param onError: () -> Unit
     */
    override fun signOut(
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        firebaseAuth.signOut()
        if (firebaseAuth.currentUser == null) onSuccess()
        else onError()
    }

    /**
     * Check if the user is logged
     *
     * @return Boolean
     */
    override fun isUserLogged(): Boolean {
        return firebaseAuth.currentUser != null
    }

    /**
     * Get the current user
     *
     * @return FirebaseUser
     */
    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

}