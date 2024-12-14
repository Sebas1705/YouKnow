package es.sebas1705.youknow.data.firebase.firestore.repository

import es.sebas1705.youknow.core.utlis.FlowResponse
import es.sebas1705.youknow.core.utlis.FlowResponseNothing
import es.sebas1705.youknow.data.model.ResponseState
import es.sebas1705.youknow.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

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
     * @param userModel [UserModel]: User to save
     *
     * @return [Flow]<[ResponseState]<Nothing>>: Flow with the response of the operation
     */
    fun saveUser(
        userModel: UserModel
    ): FlowResponseNothing


    /**
     * Get a user from Firestore
     *
     * @param userId [String]: Id of the user to get
     *
     * @return [Flow]<[ResponseState]<[UserModel]>>: Flow with the response of the operation
     */
    fun getUser(
        userId: String
    ): FlowResponse<UserModel>

    /**
     * Set the logged status of a user
     *
     * @param userId [String]: Id of the user to set the logged status
     * @param logged [Boolean]: Logged status to set
     *
     * @return [Flow]<[ResponseState]<Nothing>>: Flow with the response of the operation
     */
    fun setLoggedToUser(
        firebaseId: String,
        logged: Boolean
    ): FlowResponseNothing

    /**
     * Get the logged status of a user
     *
     * @param userId [String]: Id of the user to get the logged status
     *
     * @return [FlowResponse]<[Boolean]>: Flow with the response of the operation
     */
    fun getLoggedFromUser(
        userId: String
    ): FlowResponse<Boolean>

    /**
     * Add credits to a user
     *
     * @param userId [String]: Id of the user to add the credits
     * @param oldCredits [Int]: Old credits of the user
     * @param addCredits [Int]: Credits to add
     *
     * @return [FlowResponse]<[Int]>: Flow with the response of the operation
     */
    fun addCreditsToUser(
        userId: String,
        oldCredits: Int,
        addCredits: Int
    ): FlowResponse<Int>

    /**
     * Set the group of a user
     *
     * @param firebaseId [String]: Id of the user to set the group
     * @param groupId [String]: Id of the group to set
     *
     * @return [FlowResponseNothing]: Flow with the response of the operation
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
     */
    fun containsUser(
        firebaseId: String
    ): FlowResponse<Boolean>

    /**
     * Get the ranking of the users
     *
     * @return [FlowResponse]<[List]<[UserModel]>>: Flow with the response of the operation
     */
    fun getUserRanking(): FlowResponse<List<UserModel>>

    /**
     * Get a user by nickname
     *
     * @param nickname [String]: Nickname of the user to get
     *
     * @return [FlowResponse]<[UserModel]>: Flow with the response of the operation
     */
    fun getUserByNickname(
        nickname: String
    ): FlowResponse<UserModel>

    // Listeners:
    /**
     * Set a listener to get the user data
     *
     * @param firebaseId [String]: Id of the user to listen
     * @param onDataChange [(UserModel) -> Unit]: Function to call when the data changes
     */
    fun setUserListener(
        firebaseId: String,
        onDataChange: (UserModel) -> Unit,
    )

    /**
     * Remove the listener of the user data
     */
    fun removeUserListener()
}