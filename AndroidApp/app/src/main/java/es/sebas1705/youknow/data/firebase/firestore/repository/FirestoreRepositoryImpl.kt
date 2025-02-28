package es.sebas1705.youknow.data.firebase.firestore.repository
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

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import es.sebas1705.youknow.core.classes.managers.TaskFlowManager
import es.sebas1705.youknow.core.utlis.alias.FlowResponse
import es.sebas1705.youknow.core.utlis.alias.FlowResponseNothing
import es.sebas1705.youknow.data.firebase.analytics.config.ClassLogData
import es.sebas1705.youknow.data.firebase.analytics.config.Layer
import es.sebas1705.youknow.data.firebase.analytics.config.Repository
import es.sebas1705.youknow.data.firebase.analytics.repository.AnalyticsRepository
import es.sebas1705.youknow.data.firebase.firestore.config.SettingsFS
import es.sebas1705.youknow.data.firebase.firestore.documents.NewDocument
import es.sebas1705.youknow.data.firebase.firestore.documents.SurveyDocument
import es.sebas1705.youknow.data.firebase.firestore.documents.UserDocument
import es.sebas1705.youknow.data.mappers.toNewModel
import es.sebas1705.youknow.data.mappers.toSurveyDocument
import es.sebas1705.youknow.data.mappers.toSurveyModel
import es.sebas1705.youknow.data.mappers.toUserDocument
import es.sebas1705.youknow.data.mappers.toUserModel
import es.sebas1705.youknow.data.model.ErrorResponseType
import es.sebas1705.youknow.data.model.ResponseState
import es.sebas1705.youknow.domain.model.social.NewModel
import es.sebas1705.youknow.domain.model.social.UserModel
import es.sebas1705.youknow.domain.model.stats.SurveyModel
import javax.inject.Inject

/**
 * Implementation of FirestoreRepository.
 *
 * @property firestore [FirebaseFirestore]: Firestore instance
 * @property analyticsRepository [AnalyticsRepository]: Analytics repository
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
class FirestoreRepositoryImpl @Inject constructor(
    val firestore: FirebaseFirestore,
    val analyticsRepository: AnalyticsRepository
) : FirestoreRepository, ClassLogData {

    //Properties:
    override val layer = Layer.Data
    override val repository = Repository.Firestore

    //Listeners:
    private var userListener: ListenerRegistration? = null

    //Managers:
    private val taskFlowManager = TaskFlowManager(
        this,
        analyticsRepository::logError,
        SettingsFS.ERROR_GENERIC_MESSAGE_FAIL,
        SettingsFS.ERROR_GENERIC_MESSAGE_EX
    )

    //References:
    private val usersReference = firestore.collection(SettingsFS.USERS_COLLECTION_NAME)
    private val newsReference = firestore.collection(SettingsFS.NEWS_COLLECTION_NAME)
    private val surveysReference = firestore.collection(SettingsFS.SURVEYS_COLLECTION_NAME)

    override fun saveUser(
        userModel: UserModel,
    ): FlowResponseNothing = taskFlowManager.taskFlowProducer(
        taskAction = {
            usersReference.document(userModel.firebaseId).set(userModel.toUserDocument())
        },
        onSuccessListener = { ResponseState.EmptySuccess }
    )

    override fun getUser(
        userId: String
    ): FlowResponse<UserModel> = taskFlowManager.taskFlowProducer(
        taskAction = { usersReference.document(userId).get() },
        onSuccessListener = {
            val userDocument = it.toObject(UserDocument::class.java)
            if (userDocument != null) ResponseState.Success(userDocument.toUserModel(userId))
            else taskFlowManager.createResponse(
                ErrorResponseType.BadRequest,
                SettingsFS.USER_NOT_FOUND
            )
        }
    )

    override fun addCreditsToUser(
        userId: String,
        oldCredits: Int,
        addCredits: Int
    ): FlowResponse<Int> = taskFlowManager.taskFlowProducer(
        assertChecker = { if (oldCredits + addCredits < 0) SettingsFS.ERROR_CREDITS_NEGATIVE else null },
        taskAction = {
            usersReference.document(userId)
                .update(SettingsFS.USERS_CREDITS_FIELD, oldCredits + addCredits)
        },
        onSuccessListener = { ResponseState.Success(oldCredits + addCredits) }
    )

    override fun addPointsToUser(
        userId: String,
        addPoints: Int,
        oldPoints: Int
    ): FlowResponse<Int> = taskFlowManager.taskFlowProducer(
        taskAction = {
            usersReference.document(userId)
                .update(SettingsFS.USERS_POINTS_FIELD, oldPoints + addPoints)
        },
        onSuccessListener = { ResponseState.Success(oldPoints + addPoints) }
    )

    override fun setGroupToUser(
        firebaseId: String,
        groupId: String?
    ): FlowResponseNothing = taskFlowManager.taskFlowProducer(
        taskAction = {
            usersReference.document(firebaseId).update(SettingsFS.USERS_GROUP_FIELD, groupId)
        },
        onSuccessListener = { ResponseState.EmptySuccess }
    )

    override fun removeGroupFromUser(
        firebaseId: String
    ): FlowResponseNothing = taskFlowManager.taskFlowProducer(
        taskAction = {
            usersReference.document(firebaseId).update(SettingsFS.USERS_GROUP_FIELD, null)
        },
        onSuccessListener = { ResponseState.EmptySuccess }
    )

    override fun changePhotoToUser(
        firebaseId: String,
        photoUrl: String
    ): FlowResponseNothing = taskFlowManager.taskFlowProducer(
        taskAction = {
            usersReference.document(firebaseId).update(SettingsFS.USERS_PHOTO_FIELD, photoUrl)
        },
        onSuccessListener = { ResponseState.EmptySuccess }
    )

    override fun changeNicknameToUser(
        firebaseId: String, nickname: String
    ): FlowResponseNothing = taskFlowManager.taskFlowProducer(
        taskAction = {
            usersReference.document(firebaseId).update(SettingsFS.USERS_NICKNAME_FIELD, nickname)
        },
        onSuccessListener = { ResponseState.EmptySuccess }
    )

    override fun containsUser(
        firebaseId: String
    ): FlowResponse<Boolean> = taskFlowManager.taskFlowProducer(
        taskAction = { usersReference.document(firebaseId).get() },
        onSuccessListener = { ResponseState.Success(it.exists()) }
    )

    override fun getUserRanking(): FlowResponse<List<Pair<String, Int>>> =
        taskFlowManager.taskFlowProducer(
            taskAction = { usersReference.orderBy(SettingsFS.USERS_POINTS_FIELD).get() },
            onSuccessListener = { snapshot ->
                val users = snapshot.documents.map { document ->
                    document.toObject(UserDocument::class.java)!!.let {
                        it.nickName to it.points
                    }
                }.reversed()
                ResponseState.Success(users)
            }
        )

    override fun getUserByNickname(
        nickname: String
    ): FlowResponse<UserModel> = taskFlowManager.taskFlowProducer(
        taskAction = {
            usersReference.whereEqualTo(SettingsFS.USERS_NICKNAME_FIELD, nickname).get()
        },
        onSuccessListener = {
            val userDocument = it.documents.firstOrNull()?.toObject(UserDocument::class.java)
            if (userDocument != null) ResponseState.Success(userDocument.toUserModel(it.documents.first().id))
            else taskFlowManager.createResponse(
                ErrorResponseType.BadRequest,
                SettingsFS.USER_NOT_FOUND
            )
        }
    )

    override fun deleteDataUser(
        firebaseId: String
    ): FlowResponseNothing = taskFlowManager.taskFlowProducer(
        taskAction = { usersReference.document(firebaseId).delete() },
        onSuccessListener = { ResponseState.EmptySuccess }
    )

    override fun getNews(): FlowResponse<List<NewModel>> = taskFlowManager.taskFlowProducer(
        taskAction = { newsReference.get() },
        onSuccessListener = {
            val news = it.documents.map { document ->
                document.toObject(NewDocument::class.java)!!.toNewModel()
            }
            ResponseState.Success(news)
        }
    )

    override fun publicNewSurvey(
        surveyModel: SurveyModel
    ): FlowResponseNothing = taskFlowManager.taskFlowProducer(
        taskAction = {
            surveysReference.document(surveyModel.authorFirebaseId)
                .set(surveyModel.toSurveyDocument())
        },
        onSuccessListener = { ResponseState.EmptySuccess }
    )

    override fun getSurvey(
        firebaseId: String
    ): FlowResponse<SurveyModel> = taskFlowManager.taskFlowProducer(
        taskAction = { surveysReference.document(firebaseId).get() },
        onSuccessListener = {
            val surveyDocument = it.toObject(SurveyDocument::class.java)
            if (surveyDocument != null) ResponseState.Success(surveyDocument.toSurveyModel())
            else taskFlowManager.createResponse(
                ErrorResponseType.BadRequest,
                SettingsFS.SURVEY_NOT_FOUND
            )
        }
    )

    override fun getSurveys(): FlowResponse<List<SurveyModel>> = taskFlowManager.taskFlowProducer(
        taskAction = { surveysReference.get() },
        onSuccessListener = {
            val surveys = it.documents.map { document ->
                document.toObject(SurveyDocument::class.java)!!.toSurveyModel()
            }
            ResponseState.Success(surveys)
        }
    )

    //Listeners:
    override fun setUserListener(
        firebaseId: String,
        onDataChange: (UserModel) -> Unit
    ) {
        userListener =
            usersReference.document(firebaseId).addSnapshotListener { documentSnapshot, _ ->
                val userDocument = documentSnapshot?.toObject(UserDocument::class.java)
                if (userDocument != null) onDataChange(userDocument.toUserModel(firebaseId))
            }
    }

    override fun removeUserListener() {
        userListener?.remove()
        userListener = null
    }
}