package es.sebas1705.home.navigation.viewmodel


import com.google.firebase.auth.FirebaseUser
import es.sebas1705.common.mvi.MVIBaseState
import es.sebas1705.models.social.UserModel

/**
 * State for [HomeViewModel] that will handle the user's data.
 *
 * @property isLoading [Boolean]: If the screen is loading.
 * @property userModel [UserModel]: User's data.
 * @property firebaseUser [FirebaseUser]: Firebase user's data.
 * @property infoUsers [MutableList]<[UserModel]>: Users' data.
 *
 * @see MVIBaseState
 * @see UserModel
 *
 * @author Sebas1705 12/09/2025
 * @since 1.0.0
 */
data class HomeState(
    val isLoading: Boolean,
    val userModel: UserModel?,
    val firebaseUser: FirebaseUser?,
    val infoUsers: Map<String, UserModel>
) : MVIBaseState {
    companion object {

        /**
         * Default [HomeState] with no user.
         *
         * @return [HomeState]
         *
         * @since 1.0.0
         * @author Sebas1705 12/09/2025
         */
        fun default() = HomeState(
            isLoading = false,
            userModel = null,
            firebaseUser = null,
            infoUsers = mapOf()
        )

        /**
         * Default [HomeState] with user.
         *
         * @return [HomeState]
         *
         * @since 1.0.0
         * @author Sebas1705 12/09/2025
         */
        fun defaultWithUser() = HomeState(
            isLoading = false,
            userModel = UserModel.default(),
            firebaseUser = null,
            infoUsers = mapOf()
        )
    }
}