package es.sebas1705.youknowapp.data.source.remote.authManager

import android.content.Context
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import es.sebas1705.youknowapp.R

val Context.getCredentialRequestGoogle: GetCredentialRequest
    get() = GetCredentialRequest.Builder()
        .addCredentialOption(
            GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(AuthManagerSettings.FILTER_BY_AUTHORIZED_ACCOUNTS)
                .setServerClientId(this.getString(R.string.default_web_client_id))
                .build()
        )
        .build()

object AuthManagerSettings {
    const val FILTER_BY_AUTHORIZED_ACCOUNTS = false
}