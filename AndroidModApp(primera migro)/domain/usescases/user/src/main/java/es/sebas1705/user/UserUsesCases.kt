package es.sebas1705.user
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

import es.sebas1705.user.usescases.AddCreditsToUser
import es.sebas1705.user.usescases.AddPointsToUser
import es.sebas1705.user.usescases.ChangeNicknameToUser
import es.sebas1705.user.usescases.ChangePhotoToUser
import es.sebas1705.user.usescases.ContainsUser
import es.sebas1705.user.usescases.DeleteDataUser
import es.sebas1705.user.usescases.GetUser
import es.sebas1705.user.usescases.GetUserByNickname
import es.sebas1705.user.usescases.GetUserRanking
import es.sebas1705.user.usescases.RemoveGroupToUser
import es.sebas1705.user.usescases.RemoveUserListener
import es.sebas1705.user.usescases.SaveUser
import es.sebas1705.user.usescases.SetGroupToUser
import es.sebas1705.user.usescases.SetUserListener

/**
 * Use cases to user
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
data class UserUsesCases(
    //Setters:
    val setUserListener: SetUserListener,
    val removeUserListener: RemoveUserListener,
    val saveUser: SaveUser,
    val setGroupToUser: SetGroupToUser,
    val removeGroupToUser: RemoveGroupToUser,
    val addCreditsToUser: AddCreditsToUser,
    val addPointsToUser: AddPointsToUser,
    val changePhotoToUser: ChangePhotoToUser,
    val changeNicknameToUser: ChangeNicknameToUser,
    val deleteDataUser: DeleteDataUser,
    //Getters:
    val getUser: GetUser,
    val containsUser: ContainsUser,
    val getUserRanking: GetUserRanking,
    val getUserByNickname: GetUserByNickname
)