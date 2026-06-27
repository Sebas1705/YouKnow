package es.sebas1705.auth

data class AuthUsesCases(
    val getFirebaseUser: GetFirebaseUser,
    val signInEmailUser: SignInEmailUser,
    val signUpEmailUser: SignUpEmailUser,
    val sendForgotPassword: SendForgotPassword,
    val signGoogle: SignGoogle,
    val signOut: SignOut
)
