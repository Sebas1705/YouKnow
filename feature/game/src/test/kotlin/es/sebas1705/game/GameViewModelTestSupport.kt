package es.sebas1705.game

import app.cash.turbine.ReceiveTurbine
import com.google.firebase.auth.FirebaseUser
import es.sebas1705.auth.AuthUsesCases
import es.sebas1705.auth.GetFirebaseUser
import es.sebas1705.common.states.DataState
import es.sebas1705.common.states.ErrorDataType
import es.sebas1705.common.utlis.alias.DataFlow
import es.sebas1705.datastore.model.SettingsData
import es.sebas1705.firestore.documents.UserDocument
import es.sebas1705.firestore.repository.FirestoreRepository
import es.sebas1705.repositories.interfaces.IAuthenticationRepository
import es.sebas1705.repositories.interfaces.ISettingsRepository
import es.sebas1705.resources.games.Category
import es.sebas1705.resources.games.Difficulty
import es.sebas1705.resources.games.Languages
import es.sebas1705.resources.games.QuizType
import es.sebas1705.room.entities.QuestionEntity
import es.sebas1705.settings.ReadGameLanguage
import es.sebas1705.settings.SettingUsesCases
import es.sebas1705.user.UserUsesCases
import es.sebas1705.user.usescases.AddCreditsToUser
import es.sebas1705.user.usescases.AddPointsToUser
import es.sebas1705.user.usescases.GetUser
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.robolectric.shadows.ShadowToast

/**
 * Shared wiring for the game ViewModel tests. The ViewModels run their work on the real
 * `Dispatchers.IO`, so every assertion waits on the state flow (Turbine) or on wall-clock polling
 * rather than on the virtual test scheduler. Main is a `StandardTestDispatcher`: the blocks the
 * ViewModels hand to it (callbacks, toasts) run on the Robolectric main thread while the test body
 * is suspended, which is what lets `ShadowToast` see them.
 */
internal object GameTestSupport {

    const val UID = "uid-test"
    const val WAIT_MS = 5_000L

    fun <T> success(value: T): DataFlow<T> = flowOf(DataState.Loading, DataState.Success(value))

    fun <T> failure(message: String): DataFlow<T> = flowOf(
        DataState.Loading,
        DataState.Error(GameTestSupport::class, ErrorDataType.INTERNAL, message) { _, _ -> }
    )

    fun questionEntity(index: Int, difficulty: Difficulty = Difficulty.MEDIUM, quizType: QuizType = QuizType.MULTIPLE) =
        QuestionEntity(
            question = "Question $index",
            answers = listOf("Right $index", "Wrong 1", "Wrong 2", "Wrong 3"),
            correctAnswer = "Right $index",
            category = Category.HISTORY,
            language = Languages.EN,
            difficulty = difficulty,
            quizType = quizType
        )

    /** A signed-in user whose Firestore document reads fine and accepts points and credits. */
    fun firestoreWithUser(points: Int = 0, credits: Int = 0): FirestoreRepository = mock {
        on { getUser(UID) } doReturn success(UserDocument("a@b.c", 1, "nick", null, null, points, credits, true))
        on { addPointsToUser(eq(UID), any(), any()) } doReturn success(points)
        on { addCreditsToUser(eq(UID), any(), any()) } doReturn success(credits)
    }

    /** A user whose document cannot be read. */
    fun firestoreWithoutUser(message: String = "User not found"): FirestoreRepository = mock {
        on { getUser(UID) } doReturn failure(message)
    }

    fun authUsesCases(uid: String? = UID): AuthUsesCases {
        val firebaseUser: FirebaseUser? = uid?.let { id -> mock { on { this.uid } doReturn id } }
        val repository: IAuthenticationRepository = mock { on { getCurrentUser() } doReturn firebaseUser }
        return AuthUsesCases(
            getFirebaseUser = GetFirebaseUser(repository),
            signInEmailUser = mock(),
            signUpEmailUser = mock(),
            sendForgotPassword = mock(),
            signGoogle = mock(),
            signOut = mock()
        )
    }

    /** Real GetUser / AddPointsToUser / AddCreditsToUser on the given repository, the rest mocked. */
    fun userUsesCases(firestore: FirestoreRepository): UserUsesCases = UserUsesCases(
        setUserListener = mock(),
        removeUserListener = mock(),
        saveUser = mock(),
        setGroupToUser = mock(),
        removeGroupToUser = mock(),
        addCreditsToUser = AddCreditsToUser(firestore),
        addPointsToUser = AddPointsToUser(firestore),
        changePhotoToUser = mock(),
        changeNicknameToUser = mock(),
        deleteDataUser = mock(),
        uploadProfilePhoto = mock(),
        getUser = GetUser(firestore),
        containsUser = mock(),
        getUserRanking = mock(),
        getUserByNickname = mock()
    )

    fun settingUsesCases(language: Languages = Languages.ANY): SettingUsesCases {
        val repository = object : ISettingsRepository {
            val state = MutableStateFlow(SettingsData(false, 0.5f, 0.5f, 0, language.ordinal, true))
            override fun read(): Flow<SettingsData> = state
            override suspend fun update(settingsData: SettingsData) { state.value = settingsData }
        }
        return SettingUsesCases(
            readSettingsUseCase = mock(),
            updateSettingsUseCase = mock(),
            saveFirstTime = mock(),
            readGameLanguage = ReadGameLanguage(repository)
        )
    }

    /** Waits (wall clock) until a callback handed to the ViewModel has been invoked. */
    suspend fun CompletableDeferred<Unit>.awaitCallback() = withContext(Dispatchers.Default) {
        withTimeout(WAIT_MS) { await() }
    }

    /** Waits (wall clock) for the next toast, which the ViewModels show from a background coroutine. */
    suspend fun awaitToast(): String = withContext(Dispatchers.Default) {
        withTimeout(WAIT_MS) {
            while (ShadowToast.getLatestToast() == null) delay(20)
        }
        ShadowToast.getTextOfLatestToast()
    }

    /** Gives background work a moment to (not) happen before asserting that nothing changed. */
    suspend fun settle() = withContext(Dispatchers.Default) { delay(200) }
}

/** Skips state emissions until one satisfies [predicate]; Turbine's wall-clock timeout bounds the wait. */
internal suspend fun <T> ReceiveTurbine<T>.awaitUntil(predicate: (T) -> Boolean): T {
    while (true) {
        val item = awaitItem()
        if (predicate(item)) return item
    }
}
