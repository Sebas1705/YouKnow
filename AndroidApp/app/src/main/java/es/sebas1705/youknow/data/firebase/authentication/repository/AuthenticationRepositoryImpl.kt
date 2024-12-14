package es.sebas1705.youknow.data.firebase.authentication.repository
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
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import es.sebas1705.youknow.core.classes.managers.TaskFlowManager
import es.sebas1705.youknow.core.utlis.FlowResponseNothing
import es.sebas1705.youknow.data.firebase.analytics.config.ClassLogData
import es.sebas1705.youknow.data.firebase.analytics.config.Layer
import es.sebas1705.youknow.data.firebase.analytics.config.Repository
import es.sebas1705.youknow.data.firebase.analytics.repository.AnalyticsRepository
import es.sebas1705.youknow.data.firebase.authentication.config.SettingsAuth
import es.sebas1705.youknow.data.firebase.authentication.config.getCredentialRequestGoogle
import es.sebas1705.youknow.data.model.ErrorResponseType
import es.sebas1705.youknow.data.model.ResponseState
import javax.inject.Inject

/**
 * Authentication repository implementation
 *
 * @property credentialManager [CredentialManager]: credential manager to get the google credential
 * @property firebaseAuth [FirebaseAuth]: firebase authentication instance
 * @property analyticsRepository [AnalyticsRepository]: analytics repository
 *
 * @see AuthenticationRepository
 * @see CredentialManager
 * @see FirebaseAuth
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class AuthenticationRepositoryImpl @Inject constructor(
    private val credentialManager: CredentialManager,
    private val firebaseAuth: FirebaseAuth,
    private val analyticsRepository: AnalyticsRepository
) : AuthenticationRepository, ClassLogData {

    //Properties:
    override val layer = Layer.Data
    override val repository = Repository.Authentication

    //Managers:
    private val taskFlowManager = TaskFlowManager(
        this,
        analyticsRepository::logError,
        SettingsAuth.ERROR_GENERIC_MESSAGE_FAIL,
        SettingsAuth.ERROR_GENERIC_MESSAGE_EX
    )

    //Tasks:
    override fun signUpWithEmail(
        email: String,
        password: String,
    ): FlowResponseNothing = taskFlowManager.taskFlowProducer(
        taskAction = { firebaseAuth.createUserWithEmailAndPassword(email, password) },
        onSuccessListener = {
            if (it.user != null) {
                it.user?.sendEmailVerification()
                ResponseState.EmptySuccess
            } else taskFlowManager.createResponse(
                ErrorResponseType.InternalError,
                SettingsAuth.NOT_LOGGED_USER
            )
        }
    )

    override fun signInWithEmail(
        email: String,
        password: String,
    ): FlowResponseNothing = taskFlowManager.taskFlowProducer(
        taskAction = { firebaseAuth.signInWithEmailAndPassword(email, password) },
        onSuccessListener = {
            if (it.user != null) ResponseState.EmptySuccess
            else taskFlowManager.createResponse(
                ErrorResponseType.InternalError,
                SettingsAuth.NOT_LOGGED_USER
            )
        }
    )

    override suspend fun signWithGoogle(
        context: Context
    ): FlowResponseNothing {
        val credential = credentialManager.getCredential(
            context,
            context.getCredentialRequestGoogle
        ).credential
        return taskFlowManager.taskFlowProducer(
            assertChecker = {
                if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL)
                    null
                else SettingsAuth.WRONG_CREDENTIALS
            },
            taskAction = {
                val googleIdTokenCredential =
                    GoogleIdTokenCredential.createFrom(credential.data)
                val authCredential =
                    GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)
                firebaseAuth.signInWithCredential(authCredential)
            },
            onSuccessListener = {
                if (it.user != null) ResponseState.EmptySuccess
                else taskFlowManager.createResponse(
                    ErrorResponseType.InternalError,
                    SettingsAuth.NOT_LOGGED_USER
                )
            }
        )
    }

    override fun sendForgotPassword(
        email: String
    ): FlowResponseNothing = taskFlowManager.taskFlowProducer(
        taskAction = { firebaseAuth.sendPasswordResetEmail(email) },
        onSuccessListener = { ResponseState.EmptySuccess }
    )

    //Functions:
    override fun signOut(): Boolean {
        firebaseAuth.signOut()
        return firebaseAuth.currentUser == null
    }

    override fun isUserLogged(): Boolean = firebaseAuth.currentUser != null

    override fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser
}