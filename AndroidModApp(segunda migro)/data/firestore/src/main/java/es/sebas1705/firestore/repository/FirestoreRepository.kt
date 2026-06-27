package es.sebas1705.firestore.repository

import es.sebas1705.common.utlis.alias.DataEmptyFlow
import es.sebas1705.common.utlis.alias.DataFlow
import es.sebas1705.firestore.datasources.NewDocumentFirestoreDataSource
import es.sebas1705.firestore.datasources.SurveyDocumentFirestoreDataSource
import es.sebas1705.firestore.datasources.UserDocumentFirestoreDataSource
import es.sebas1705.firestore.documents.NewDocument
import es.sebas1705.firestore.documents.SurveyDocument
import es.sebas1705.firestore.documents.UserDocument
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirestoreRepository @Inject constructor(
    private val userDataSource: UserDocumentFirestoreDataSource,
    private val surveyDataSource: SurveyDocumentFirestoreDataSource,
    private val newDataSource: NewDocumentFirestoreDataSource
) {
    // User operations
    fun saveUser(firebaseId: String, userDocument: UserDocument): DataEmptyFlow = userDataSource.saveUser(firebaseId, userDocument)
    fun getUser(userId: String): DataFlow<UserDocument> = userDataSource.getUser(userId)
    fun addCreditsToUser(userId: String, oldCredits: Int, addCredits: Int): DataFlow<Int> = userDataSource.addCreditsToUser(userId, oldCredits, addCredits)
    fun addPointsToUser(userId: String, addPoints: Int, oldPoints: Int): DataFlow<Int> = userDataSource.addPointsToUser(userId, addPoints, oldPoints)
    fun setGroupToUser(firebaseId: String, groupId: String?): DataEmptyFlow = userDataSource.setGroupToUser(firebaseId, groupId)
    fun removeGroupFromUser(firebaseId: String): DataEmptyFlow = userDataSource.removeGroupFromUser(firebaseId)
    fun changePhotoToUser(firebaseId: String, photoUrl: String): DataEmptyFlow = userDataSource.changePhotoToUser(firebaseId, photoUrl)
    fun changeNicknameToUser(firebaseId: String, nickname: String): DataEmptyFlow = userDataSource.changeNicknameToUser(firebaseId, nickname)
    fun containsUser(firebaseId: String): DataFlow<Boolean> = userDataSource.containsUser(firebaseId)
    fun getUserRanking(): DataFlow<List<Pair<String, Int>>> = userDataSource.getUserRanking()
    fun getUserByNickname(nickname: String): DataFlow<Pair<UserDocument, String>> = userDataSource.getUserByNickname(nickname)
    fun deleteDataUser(firebaseId: String): DataEmptyFlow = userDataSource.deleteDataUser(firebaseId)
    fun setUserListener(firebaseId: String, onDataChange: (UserDocument) -> Unit) = userDataSource.setUserListener(firebaseId, onDataChange)
    fun removeUserListener() = userDataSource.removeUserListener()

    // Survey operations
    fun publicNewSurvey(surveyDocument: SurveyDocument): DataEmptyFlow = surveyDataSource.publicNewSurvey(surveyDocument)
    fun getSurvey(firebaseId: String): DataFlow<SurveyDocument> = surveyDataSource.getSurvey(firebaseId)
    fun getSurveys(): DataFlow<List<SurveyDocument>> = surveyDataSource.getSurveys()

    // News operations
    fun getNews(): DataFlow<List<NewDocument>> = newDataSource.getNews()
}
