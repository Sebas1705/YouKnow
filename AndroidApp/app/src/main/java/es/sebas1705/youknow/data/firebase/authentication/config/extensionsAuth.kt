package es.sebas1705.youknow.data.firebase.authentication.config
/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import android.content.Context
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import es.sebas1705.youknow.R

/**
 * Extension function that get the Google credential request
 *
 * @receiver [Context]
 *
 * @return [GetCredentialRequest]
 *
 * @see GetCredentialRequest
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
val Context.getCredentialRequestGoogle: GetCredentialRequest
    get() = GetCredentialRequest.Builder()
        .addCredentialOption(
            GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(SettingsAuth.FILTER_BY_AUTHORIZED_ACCOUNTS)
                .setServerClientId(this.getString(R.string.default_web_client_id))
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
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
fun FirebaseUser.getProvider(): ProviderAuth {
    return when {
        this.providerData.any { it.providerId == GoogleAuthProvider.PROVIDER_ID } -> ProviderAuth.GOOGLE
        else -> ProviderAuth.EMAIL
    }
}