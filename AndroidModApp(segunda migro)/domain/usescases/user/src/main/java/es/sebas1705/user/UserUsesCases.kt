package es.sebas1705.user


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
 * @author Sebas1705 12/09/2025
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