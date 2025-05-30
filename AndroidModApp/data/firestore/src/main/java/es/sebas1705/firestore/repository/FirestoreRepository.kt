package es.sebas1705.firestore.repository
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

import es.sebas1705.common.utlis.alias.FlowResponse
import es.sebas1705.common.utlis.alias.FlowResponseNothing
import es.sebas1705.firestore.documents.NewDocument
import es.sebas1705.firestore.documents.SurveyDocument
import es.sebas1705.firestore.documents.UserDocument

/**
 * Repository that will handle the Firestore operations.
 *
 * @author Sebastián Ramiro Entrerrios García
 * @since 1.0.0
 */
interface FirestoreRepository {

    /**
     * Save a user in Firestore
     *
     * @param firebaseId [String]: Id of the user to save
     * @param userDocument [UserDocument]: User to save
     *
     * @return [FlowResponseNothing]: Flow with the response of the operation
     */
    fun saveUser(
        firebaseId: String,
        userDocument: UserDocument
    ): FlowResponseNothing


    /**
     * Get a user from Firestore
     *
     * @param userId [String]: Id of the user to get
     *
     * @return [FlowResponse]<[UserDocument]>: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun getUser(
        userId: String
    ): FlowResponse<UserDocument>

    /**
     * Add credits to a user
     *
     * @param userId [String]: Id of the user to add the credits
     * @param oldCredits [Int]: Old credits of the user
     * @param addCredits [Int]: Credits to add
     *
     * @return [FlowResponse]<[Int]>: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun addCreditsToUser(
        userId: String,
        oldCredits: Int,
        addCredits: Int
    ): FlowResponse<Int>

    /**
     * Add points to a user
     *
     * @param userId [String]: Id of the user to add the points
     * @param addPoints [Int]: Points to add
     * @param oldPoints [Int]: Old points of the user
     *
     * @return [FlowResponse]<[Int]>: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun addPointsToUser(
        userId: String,
        addPoints: Int,
        oldPoints: Int
    ): FlowResponse<Int>

    /**
     * Set the group of a user
     *
     * @param firebaseId [String]: Id of the user to set the group
     * @param groupId [String]: Id of the group to set
     *
     * @return [FlowResponseNothing]: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun setGroupToUser(
        firebaseId: String,
        groupId: String?
    ): FlowResponseNothing

    /**
     * Remove the group of a user
     *
     * @param firebaseId [String]: Id of the user to remove the group
     *
     * @return [FlowResponseNothing]: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun removeGroupFromUser(
        firebaseId: String
    ): FlowResponseNothing

    /**
     * Change the photo of a user
     *
     * @param firebaseId [String]: Id of the user to change the photo
     * @param photoUrl [String]: Url of the new photo
     *
     * @return [FlowResponseNothing]: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun changePhotoToUser(
        firebaseId: String,
        photoUrl: String
    ): FlowResponseNothing

    /**
     * Change the nickname of a user
     *
     * @param firebaseId [String]: Id of the user to change the nickname
     * @param nickname [String]: New nickname
     *
     * @return [FlowResponseNothing]: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun changeNicknameToUser(
        firebaseId: String,
        nickname: String
    ): FlowResponseNothing

    /**
     * Get the group of a user
     *
     * @param firebaseId [String]: Id of the user to get the group
     *
     * @return [FlowResponse]<[Boolean]>: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun containsUser(
        firebaseId: String
    ): FlowResponse<Boolean>

    /**
     * Get the ranking of the users
     *
     * @return [FlowResponse]<[List]<[Pair]<[String],[Int]>>>: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun getUserRanking(): FlowResponse<List<Pair<String, Int>>>

    /**
     * Get a user by nickname
     *
     * @param nickname [String]: Nickname of the user to get
     *
     * @return [FlowResponse]<[UserDocument]>: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun getUserByNickname(
        nickname: String
    ): FlowResponse<Pair<UserDocument,String>>

    /**
     * Delete the data of a user
     *
     * @param firebaseId [String]: Id of the user to delete the data
     *
     * @return [FlowResponseNothing]: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun deleteDataUser(
        firebaseId: String
    ): FlowResponseNothing


    /**
     * Get the news
     *
     * @return [FlowResponse]<[List]<[NewDocument]>>: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun getNews(): FlowResponse<List<NewDocument>>

    /**
     * Save a Survey in Firestore
     *
     * @param surveyDocument [SurveyDocument]: New to save
     *
     * @return [FlowResponseNothing]: Flow with the response of the operation
     */
    fun publicNewSurvey(
        surveyDocument: SurveyDocument
    ): FlowResponseNothing

    /**
     * Get a Survey from Firestore
     *
     * @param firebaseId [String]: Id of the Survey to get
     *
     * @return [FlowResponse]<[SurveyDocument]>: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun getSurvey(
        firebaseId: String
    ): FlowResponse<SurveyDocument>

    /**
     * Get all Surveys
     *
     * @return [FlowResponse]<[List]<[SurveyDocument]>>: Flow with the response of the operation
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun getSurveys(): FlowResponse<List<SurveyDocument>>

    // Listeners:
    /**
     * Set a listener to get the user data
     *
     * @param firebaseId [String]: Id of the user to listen
     * @param onDataChange [([UserDocument]) -> [Unit]]: Function to call when the data changes
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun setUserListener(
        firebaseId: String,
        onDataChange: (UserDocument) -> Unit,
    )

    /**
     * Remove the listener of the user data
     *
     * @since 1.0.0
     * @author Sebastián Ramiro Entrerrios García
     */
    fun removeUserListener()
}