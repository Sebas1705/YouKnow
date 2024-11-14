package es.sebas1705.youknow.domain.usecases.social
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
import com.google.firebase.auth.FirebaseUser
import es.sebas1705.youknow.data.firebase.authentication.config.ProviderAuth
import es.sebas1705.youknow.data.firebase.authentication.repository.AuthenticationRepository
import es.sebas1705.youknow.data.firebase.firestore.repository.FirestoreRepository
import es.sebas1705.youknow.data.firebase.realtime.repository.RealtimeRepository
import es.sebas1705.youknow.data.local.database.repository.DatabaseRepository
import es.sebas1705.youknow.domain.model.GroupModel
import es.sebas1705.youknow.domain.model.UserModel


class SignUpEmailUser(
    private val authenticationRepository: AuthenticationRepository,
){
    suspend operator fun invoke(
        nickname: String,
        mail:String,
        password: String,
        onLoading: () -> Unit,
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ) {
        authenticationRepository.signUpWithEmail(
            email = mail,
            password = password
        ).collect {
            it.catcher(
                onLoading = onLoading,
                onEmptySuccess = {
                    val userModel = createNewEmailUser(nickname)
                    onSuccess(userModel)
                },
                onError = {
                    onError(it.message)
                }
            )
        }
    }

    private fun createNewEmailUser(nickname: String): UserModel {
        val user = authenticationRepository.getCurrentUser()!!
        return UserModel(
            firebaseId = user.uid,
            email = user.email!!,
            provider = ProviderAuth.EMAIL,
            nickName = nickname,
            photoUrl = null,
            groupId = null,
            points = 0,
            credits = 1000,
            friends = emptyList(),
        )
    }
}

class SignInEmailUser(
    private val authenticationRepository: AuthenticationRepository,
) {
    suspend operator fun invoke(
        mail: String,
        password: String,
        onLoading: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        authenticationRepository.signInWithEmail(
            email = mail,
            password = password
        ).collect {
            it.catcher(
                onLoading = onLoading,
                onEmptySuccess = {
                    val user = authenticationRepository.getCurrentUser()!!
                    onSuccess(user.uid)
                },
                onError = {
                    onError(it.message)
                }
            )
        }
    }
}

class SignGoogle(
    private val authenticationRepository: AuthenticationRepository,
) {
    suspend operator fun invoke(
        context: Context,
        onLoading: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        authenticationRepository.signWithGoogle(
            context = context
        ).collect {
            it.catcher(
                onLoading = onLoading,
                onEmptySuccess = {
                    val user = authenticationRepository.getCurrentUser()!!
                    onSuccess(user.uid)
                },
                onError = {
                    onError(it.message)
                }
            )
        }
    }

    fun createNewGoogleUser(): UserModel {
        val user = authenticationRepository.getCurrentUser()!!
        return UserModel(
            firebaseId = user.uid,
            email = user.email!!,
            provider = ProviderAuth.GOOGLE,
            nickName = user.displayName!!,
            photoUrl = user.photoUrl!!.toString(),
            groupId = null,
            points = 0,
            credits = 1000,
            friends = emptyList(),
        )
    }
}

class SignOut(
    private val authenticationRepository: AuthenticationRepository,
) {
    operator fun invoke(
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val user = authenticationRepository.getCurrentUser()!!
        val out = authenticationRepository.signOut()
        if(out)
            onSuccess(user.uid)
        else
            onError("Not possible to sign out")
    }
}

class VerifyWasLogged(
    private val databaseRepository: DatabaseRepository,
    private val firestoreRepository: FirestoreRepository
){
    suspend operator fun invoke(
        firebaseId: String
    ): Boolean {
        return databaseRepository.containsUser(firebaseId) || firestoreRepository.containsUser(firebaseId)
    }
}

class VerifyJustLogged(
    private val firestoreRepository: FirestoreRepository
){
    suspend operator fun invoke(
        firebaseId: String
    ): Boolean {
        return firestoreRepository.getLoggedFromUser(firebaseId)
    }
}

class GetFirebaseUser(
    private val authenticationRepository: AuthenticationRepository
){
    operator fun invoke(): FirebaseUser? {
        return authenticationRepository.getCurrentUser()
    }
}

class SaveUser(
    private val databaseRepository: DatabaseRepository,
    private val firestoreRepository: FirestoreRepository,
) {
    suspend operator fun invoke(userModel: UserModel) {
        databaseRepository.postOrUpdateUser(userModel)
        firestoreRepository.saveUser(userModel)
    }
}

class GetUser(
    private val databaseRepository: DatabaseRepository,
    private val firestoreRepository: FirestoreRepository
) {
    suspend operator fun invoke(firebaseId: String, updateLocal: Boolean): UserModel? {
        if(!updateLocal && databaseRepository.containsUser(firebaseId))
            return databaseRepository.getUser(firebaseId)
        else{
            val user = firestoreRepository.getUser(firebaseId)
            if(user != null)
                databaseRepository.postOrUpdateUser(user)
            return user
        }
    }
}

class SetLogged(
    private val firestoreRepository: FirestoreRepository
){
    suspend operator fun invoke(uid: String,logged: Boolean){
        firestoreRepository.setLoggedToUser(uid,logged)
    }
}

class SendForgotPassword(
    private val authenticationRepository: AuthenticationRepository,
){
    suspend operator fun invoke(
        email: String,
        onLoading: () -> Unit,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ){
        authenticationRepository.sendForgotPassword(email).collect{
            it.catcher(
                onLoading = onLoading,
                onEmptySuccess = onSuccess,
                onError = { onError(it.message) }
            )
        }
    }
}

class IsUserLogged(
    private val authenticationRepository: AuthenticationRepository,
){
    operator fun invoke(): Boolean {
        return authenticationRepository.isUserLogged()
    }
}

class ChangeCredits(
    private val databaseRepository: DatabaseRepository,
    private val firestoreRepository: FirestoreRepository
){
    suspend operator fun invoke(
        firebaseId: String,
        creditsToAdd: Int
    ): Boolean {
        val user = databaseRepository.getUser(firebaseId)!!
        val newCredits = user.credits + creditsToAdd

        if(databaseRepository.updateCreditsFromUser(firebaseId,newCredits)){
            firestoreRepository.setCreditsToUser(firebaseId,newCredits)
            return true
        }
        return false
    }
}

class JoinGroupAsMember(
    private val firestoreRepository: FirestoreRepository,
    private val databaseRepository: DatabaseRepository,
    private val realTimeRepository: RealtimeRepository
){
    suspend operator fun invoke(
        group: GroupModel,
        firebaseId: String,
    ): Boolean {
        val user = databaseRepository.getUser(firebaseId)!!
        if(user.groupId == null || user.groupId == group.groupId)
            return false

        if(databaseRepository.updateGroupFromUser(firebaseId,group.groupId)) {
            firestoreRepository.setGroupToUser(firebaseId, group.groupId)
            var list = group.members.toMutableList()
            list.add(firebaseId)
            realTimeRepository.addMemberToGroup(group.groupId,list)
            return true
        }
        return false
    }
}

class JoinGroupAsLeader(
    private val firestoreRepository: FirestoreRepository,
    private val databaseRepository: DatabaseRepository
){
    suspend operator fun invoke(
        group: GroupModel,
        firebaseId: String,
    ): Boolean {
        val user = databaseRepository.getUser(firebaseId)!!
        if(user.groupId == null || user.groupId == group.groupId)
            return false

        if(databaseRepository.updateGroupFromUser(firebaseId,group.groupId)) {
            firestoreRepository.setGroupToUser(firebaseId, group.groupId)
            return true
        }
        return false
    }
}

data class UserUsesCases(
    val signUpEmailUser: SignUpEmailUser,
    val signInEmailUser: SignInEmailUser,
    val signGoogle: SignGoogle,
    val signOut: SignOut,
    val saveUser: SaveUser,
    val getUser: GetUser,
    val setLogged: SetLogged,
    val verifyWasLogged: VerifyWasLogged,
    val verifyJustLogged: VerifyJustLogged,
    val sendForgotPassword: SendForgotPassword,
    val getFirebaseUser: GetFirebaseUser,
    val isUserLogged: IsUserLogged,
    val changeCredits: ChangeCredits,
    val joinGroupAsMember: JoinGroupAsMember,
    val joinGroupAsLeader: JoinGroupAsLeader
)