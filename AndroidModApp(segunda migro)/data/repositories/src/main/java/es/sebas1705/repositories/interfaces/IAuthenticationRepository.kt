package es.sebas1705.repositories.interfaces

import com.google.firebase.auth.FirebaseUser
import es.sebas1705.common.utlis.alias.DataEmptyFlow

/**
 * Repository interface to authenticate the user
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
interface IAuthenticationRepository {

    //Tasks:
    /**
     * Sign up with email and password
     *
     * @param email [String]: Email to sign up
     * @param password [String]: Password to sign up
     *
     * @return [DataEmptyFlow]: with the response of the operation
     *
     * @since 0.1.0
     * @author Sebas1705 09/09/2025
     */
    fun signUpWithEmail(
        email: String,
        password: String
    ): DataEmptyFlow

    /**
     * Sign in with email and password
     *
     * @param email [String]: Email to sign in
     * @param password [String]: Password to sign in
     *
     * @return [DataEmptyFlow]: with the response of the operation
     *
     * @since 0.1.0
     * @author Sebas1705 09/09/2025
     */
    fun signInWithEmail(
        email: String,
        password: String
    ): DataEmptyFlow

    /**
     * Sign in with Google
     *
     * @return [DataEmptyFlow]: with the response of the operation
     *
     * @since 0.1.0
     * @author Sebas1705 09/09/2025
     */
    suspend fun signWithGoogle(): DataEmptyFlow

    /**
     * Send a forgot password email
     *
     * @param email [String]: Email to send the forgot password email
     *
     * @return [DataEmptyFlow]: with the response of the operation
     *
     * @since 0.1.0
     * @author Sebas1705 09/09/2025
     */
    fun sendForgotPassword(email: String): DataEmptyFlow

    //Functions:

    /**
     * Sign out the user
     *
     * @return [Boolean] with the result
     *
     * @since 0.1.0
     * @author Sebas1705 09/09/2025
     */
    fun signOut(): Boolean

    /**
     * Check if the user is logged
     *
     * @return [Boolean] with the result
     *
     * @since 0.1.0
     * @author Sebas1705 09/09/2025
     */
    fun isUserLogged(): Boolean

    /**
     * Get the current user
     *
     * @return [FirebaseUser?] with the current user or null if not logged
     *
     * @since 0.1.0
     * @author Sebas1705 09/09/2025
     */
    fun getCurrentUser(): FirebaseUser?

}