package es.sebas1705.auth


import es.sebas1705.auth.usescases.GetFirebaseUser
import es.sebas1705.auth.usescases.SendForgotPassword
import es.sebas1705.auth.usescases.SignGoogle
import es.sebas1705.auth.usescases.SignInEmailUser
import es.sebas1705.auth.usescases.SignOut
import es.sebas1705.auth.usescases.SignUpEmailUser

/**
 * Use cases for authentication
 *
 * @property signUpEmailUser [SignUpEmailUser]: use case to sign up with email
 * @property signInEmailUser [SignInEmailUser]: use case to sign in with email
 * @property signGoogle [SignGoogle]: use case to sign with google
 * @property signOut [SignOut]: use case to sign out
 * @property sendForgotPassword [SendForgotPassword]: use case to send forgot password
 * @property getFirebaseUser [GetFirebaseUser]: use case to get firebase user
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
data class AuthUsesCases(
    val signUpEmailUser: SignUpEmailUser,
    val signInEmailUser: SignInEmailUser,
    val signGoogle: SignGoogle,
    val signOut: SignOut,
    val sendForgotPassword: SendForgotPassword,
    val getFirebaseUser: GetFirebaseUser,
)