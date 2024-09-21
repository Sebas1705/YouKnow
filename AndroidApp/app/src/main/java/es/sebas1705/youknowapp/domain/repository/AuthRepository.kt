package es.sebas1705.youknowapp.domain.repository

import android.content.Context
import com.google.firebase.auth.FirebaseUser

/**
 * Interface to manage the authentication
 */
interface AuthRepository {

    /**
     * Sign up with email and password
     *
     * @param email Email
     * @param password Password
     * @param onSuccess Function to execute when the sign up is successful
     * @param onError Function to execute when the sign up fails
     */
    fun signUpWithEmail(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String?) -> Unit
    )

    /**
     * Sign in with email and password
     *
     * @param email Email
     * @param password Password
     * @param onSuccess Function to execute when the sign in is successful
     * @param onError Function to execute when the sign in fails
     */
    fun signInWithEmail(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String?) -> Unit
    )

    /**
     * Sign in with Google
     *
     * @param onSuccess Function to execute when the sign in is successful
     * @param onError Function to execute when the sign in fails
     */
    suspend fun signWithGoogle(
        context: Context,
        onSuccess: () -> Unit,
        onError: (String?) -> Unit
    )

    /**
     * Sign in with Facebook
     *
     * @param onSuccess Function to execute when the sign in is successful
     * @param onError Function to execute when the sign in fails
     */
    fun signWithFacebook(
        onSuccess: () -> Unit,
        onError: (String?) -> Unit
    )

    /**
     * Sign out
     *
     * @param onSuccess Function to execute when the sign out is successful
     * @param onError Function to execute when the sign out fails
     */
    fun signOut(
        onSuccess: () -> Unit,
        onError: () -> Unit
    )

    /**
     * Check if the user is logged
     *
     * @return Boolean
     */
    fun isUserLogged(): Boolean

    /**
     * Get the current user
     *
     * @return FirebaseUser
     */
    fun getCurrentUser(): FirebaseUser?

}