package es.sebas1705.services
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

import android.util.Log
import javax.inject.Inject
/*
/**
 * Service to handle the Firebase Messaging Service
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class MyFirebaseMessagingService @Inject constructor() : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        sendRegistrationToken(token)
    }

    /**
     * Send registration token
     *
     * @param token [String]: token to send
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    private fun sendRegistrationToken(token: String) {
        Log.d("Token", token)
    }

}
*/
