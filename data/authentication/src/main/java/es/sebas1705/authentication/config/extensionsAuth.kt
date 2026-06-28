package es.sebas1705.authentication.config

import android.content.Context
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import es.sebas1705.core.resources.ProviderAuth

/**
 * Extension function that get the Google credential request
 *
 * @receiver [Context]
 *
 * @return [GetCredentialRequest]
 *
 * @see GetCredentialRequest
 *
 * @since 0.1.0
 * @author Sebas1705 09/09/2025
 */
val Context.getCredentialRequestGoogle: GetCredentialRequest
    get() = GetCredentialRequest.Builder()
        .addCredentialOption(
            GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(SettingsAuth.FILTER_BY_AUTHORIZED_ACCOUNTS)
                .setServerClientId("875884945428-k0hdf0jcctbne94ors1khudputut8klj.apps.googleusercontent.com")
                .build()
        )
        .build()

/**
 * Extension function that get the provider of the user
 *
 * @receiver [FirebaseUser]
 *
 * @return [ProviderAuth]
 *
 * @see FirebaseUser
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
fun FirebaseUser.getProvider(): ProviderAuth {
    return when {
        this.providerData.any { it.providerId == GoogleAuthProvider.PROVIDER_ID } -> ProviderAuth.GOOGLE
        else -> ProviderAuth.EMAIL
    }
}
