package es.sebas1705.survey.viewmodel


import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import es.sebas1705.auth.AuthUsesCases
import es.sebas1705.common.classes.mvi.MVIBaseViewModel
import es.sebas1705.common.utlis.extensions.composables.printTextInToast
import es.sebas1705.domain.model.stats.SurveyModel
import es.sebas1705.feature.survey.R
import es.sebas1705.models.stats.Opinion
import es.sebas1705.resources.games.Languages
import es.sebas1705.survey.SurveyUsesCases
import es.sebas1705.user.UserUsesCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import java.util.Locale
import javax.inject.Inject

/**
 * ViewModel for the usability Survey Screen: loads whatever the author already answered (local
 * first, then Firestore, see [SurveyUsesCases.getActualSurvey]), lets them fill in the rest across
 * [SurveyState.page]s, and publishes it (see [SurveyUsesCases.publicSurvey]).
 *
 * @param surveyUsesCases [SurveyUsesCases]: UseCases for the survey.
 * @param userUsesCases [UserUsesCases]: UseCases for the user (nickname for the author fields).
 * @param authUsesCases [AuthUsesCases]: UseCases for the Auth.
 * @param application [Application]: Application context.
 *
 * @author Sebas1705 30/09/2026
 * @since 1.3.3
 */
@HiltViewModel
class SurveyViewModel @Inject constructor(
    private val surveyUsesCases: SurveyUsesCases,
    private val userUsesCases: UserUsesCases,
    private val authUsesCases: AuthUsesCases,
    private val application: Application
) : MVIBaseViewModel<SurveyState, SurveyIntent>() {

    /** The signed-in author's nickname, fetched once on [load] and reused when publishing. */
    private var authorNickName: String = ""

    override fun initState(): SurveyState = SurveyState.default()

    override fun onInit() {
        intentHandler(SurveyIntent.Load)
    }

    override fun intentHandler(intent: SurveyIntent) {
        when (intent) {
            is SurveyIntent.Load -> load()
            is SurveyIntent.UpdateAge -> updateUi { it.copy(age = intent.age) }
            is SurveyIntent.UpdateProfession -> updateUi { it.copy(profession = intent.profession) }
            is SurveyIntent.UpdateAndroidKnowing -> updateUi { it.copy(androidKnowing = intent.value) }
            is SurveyIntent.UpdateApplicationsKnowing -> updateUi { it.copy(applicationsKnowing = intent.value) }
            is SurveyIntent.UpdateGamesKnowing -> updateUi { it.copy(gamesKnowing = intent.value) }
            is SurveyIntent.UpdateSocialNetworksKnowing -> updateUi { it.copy(socialNetworksKnowing = intent.value) }
            is SurveyIntent.UpdateOtherKnowing -> updateUi { it.copy(otherKnowing = intent.otherKnowing) }
            is SurveyIntent.UpdateOpinionPoints -> updateOpinion(intent.key) { it.copy(points = intent.points) }
            is SurveyIntent.UpdateOpinionText -> updateOpinion(intent.key) { it.copy(opinion = intent.text) }
            is SurveyIntent.Submit -> submit()
        }
    }

    //Actions:
    private fun load() = execute(Dispatchers.IO) {
        val firebaseUser = authUsesCases.getFirebaseUser() ?: return@execute
        userUsesCases.getUser(
            firebaseId = firebaseUser.uid,
            onLoading = { },
            onSuccess = { user -> authorNickName = user.nickName },
            onError = { }
        )
        surveyUsesCases.getActualSurvey(
            firebaseId = firebaseUser.uid,
            onLoading = { startLoading() },
            onSuccess = { survey ->
                stopLoading()
                if (survey != null) updateUi { it.fromModel(survey) }
            },
            onError = { error -> stopAndError(error, application::printTextInToast) }
        )
    }

    private fun updateOpinion(key: String, transform: (Opinion) -> Opinion) = updateUi {
        val current = it.opinions[key] ?: Opinion(0, "")
        it.copy(opinions = it.opinions + (key to transform(current)))
    }

    private fun submit() = execute(Dispatchers.IO) {
        val firebaseUser = authUsesCases.getFirebaseUser()
        if (firebaseUser == null) {
            execute { application.printTextInToast(application.getString(R.string.feature_survey_not_logged)) }
            return@execute
        }
        try {
            withTimeout(SUBMIT_TIMEOUT_MS) {
                publishSurvey(firebaseUser.uid)
            }
        } catch (_: TimeoutCancellationException) {
            stopAndError(application.getString(R.string.feature_survey_timeout), application::printTextInToast)
        }
    }

    private suspend fun publishSurvey(firebaseId: String) {
        surveyUsesCases.publicSurvey(
            _uiState.value.toModel(firebaseId, authorNickName),
            onLoading = { startLoading() },
            onSuccess = {
                stopLoading()
                updateUi { it.copy(submitted = true) }
            },
            onError = { error -> stopAndError(error, application::printTextInToast) }
        )
    }

    //Privates:
    private fun startLoading() = updateUi { it.copy(isLoading = true) }
    private fun stopLoading() = updateUi { it.copy(isLoading = false) }
    private fun stopAndError(error: String, onError: (String) -> Unit) {
        stopLoading()
        execute { onError(error) }
    }
}

/** Overwrites every field this survey [SurveyState] tracks with what an existing [SurveyModel] has. */
private fun SurveyState.fromModel(model: SurveyModel): SurveyState = copy(
    age = if (model.authorAge > 0) model.authorAge.toString() else age,
    profession = model.profession,
    androidKnowing = model.androidKnowing,
    applicationsKnowing = model.applicationsKnowing,
    gamesKnowing = model.gamesKnowing,
    socialNetworksKnowing = model.socialNetworksKnowing,
    otherKnowing = model.otherKnowing,
    opinions = mapOf(
        "prediction" to model.predictionOpinion,
        "synthesis" to model.synthesisOpinion,
        "familiarity" to model.familiarityOpinion,
        "generality" to model.generalityOpinion,
        "consistency" to model.consistencyOpinion,
        "learnabilityGeneral" to model.learnabilityGeneralOpinion,
        "dialogInitiative" to model.dialogInitiativeOpinion,
        "multitasking" to model.multitaskingOpinion,
        "taskControl" to model.taskControlOpinion,
        "adaptation" to model.adaptationOpinion,
        "substitution" to model.substitutionOpinion,
        "flexibilityGeneral" to model.flexibilityGeneralOpinion,
        "observationCapacity" to model.observationCapacityOpinion,
        "recuperationCapacity" to model.recuperationCapacityOpinion,
        "responseCapacity" to model.responseCapacityOpinion,
        "taskAdaptation" to model.taskAdaptationOpinion,
        "robustnessGeneral" to model.robustnessGeneralOpinion,
        "usabilityGeneral" to model.usabilityGeneralOpinion,
        "equitableUse" to model.equitableUseOpinion,
        "flexibilityUse" to model.flexibilityUseOpinion,
        "simpleAndIntuitiveUse" to model.simpleAndIntuitiveUseOpinion,
        "perceptibleInformation" to model.perceptibleInformationOpinion,
        "toleranceError" to model.toleranceErrorOpinion,
        "lowPhysicalEffort" to model.lowPhysicalEffortOpinion,
        "sizeAndSpace" to model.sizeAndSpaceOpinion,
        "visualProtanopia" to model.visualProtanopiaOpinion,
        "visualDeuteranopia" to model.visualDeuteranopiaOpinion,
        "visualTritanopia" to model.visualTritanopiaOpinion,
        "reducedVisionZoomAdapter" to model.reducedVisionZoomAdapterOpinion,
        "blindnessScreenReader" to model.blindnessScreenReaderOpinion,
        "blindnessElementsSound" to model.blindnessElementsSoundOpinion,
        "textInformation" to model.textInformationOpinion,
        "simpleText" to model.simpleTextOpinion,
        "reducedMobility" to model.reducedMobilityOpinion,
        "cognitiveSimple" to model.cognitiveSimpleOpinion,
        "colorSchemeDark" to model.colorSchemeDarkOpinion,
        "colorSchemeLight" to model.colorSchemeLightOpinion,
        "colorSchemeContrast" to model.colorSchemeContrastOpinion,
        "colorSchemeGeneral" to model.colorSchemeGeneralOpinion,
        "fontTypeTitle" to model.fontTypeTitleOpinion,
        "fontTypeBody" to model.fontTypeBodyOpinion,
        "fontTypeSpecial" to model.fontTypeSpecialOpinion,
        "fontTypeGeneral" to model.fontTypeGeneralOpinion,
        "windowAdaptation" to model.windowAdaptationOpinion,
        "navigationButton" to model.navigationButtonOpinion,
        "navigationBottomBar" to model.navigationBottomBarOpinion,
        "navigationGeneral" to model.navigationGeneralOpinion,
        "splashScreenDesign" to model.splashScreenDesignOpinion,
        "splashScreenGeneral" to model.splashScreenGeneralOpinion,
        "guideScreenDesign" to model.guideScreenDesignOpinion,
        "guideScreenContent" to model.guideScreenContentOpinion,
        "guideScreenGeneral" to model.guideScreenGeneralOpinion,
        "menuScreenDesign" to model.menuScreenDesignOpinion,
        "menuScreenContent" to model.menuScreenContentOpinion,
        "menuScreenGeneral" to model.menuScreenGeneralOpinion,
        "loginScreenDesign" to model.loginScreenDesignOpinion,
        "loginScreenContent" to model.loginScreenContentOpinion,
        "loginScreenGeneral" to model.loginScreenGeneralOpinion,
        "signScreenDesign" to model.signScreenDesignOpinion,
        "signScreenContent" to model.signScreenContentOpinion,
        "signScreenGeneral" to model.signScreenGeneralOpinion,
        "homeScreenDesign" to model.homeScreenDesignOpinion,
        "homeScreenContent" to model.homeScreenContentOpinion,
        "homeScreenGeneral" to model.homeScreenGeneralOpinion,
        "settingsScreenDesign" to model.settingsScreenDesignOpinion,
        "settingsScreenContent" to model.settingsScreenContentOpinion,
        "settingsScreenGeneral" to model.settingsScreenGeneralOpinion,
        "profileScreenDesign" to model.profileScreenDesignOpinion,
        "profileScreenContent" to model.profileScreenContentOpinion,
        "profileScreenGeneral" to model.profileScreenGeneralOpinion,
        "chatScreenDesign" to model.chatScreenDesignOpinion,
        "chatScreenContent" to model.chatScreenContentOpinion,
        "chatScreenGeneral" to model.chatScreenGeneralOpinion,
        "groupScreenDesign" to model.groupScreenDesignOpinion,
        "groupScreenContent" to model.groupScreenContentOpinion,
        "groupScreenGeneral" to model.groupScreenGeneralOpinion,
        "playScreenDesign" to model.playScreenDesignOpinion,
        "playScreenContent" to model.playScreenContentOpinion,
        "playScreenGeneral" to model.playScreenGeneralOpinion,
        "mysteryNumberScreenDesign" to model.mysteryNumberScreenDesignOpinion,
        "mysteryNumberScreenContent" to model.mysteryNumberScreenContentOpinion,
        "mysteryNumberScreenGeneral" to model.mysteryNumberScreenGeneralOpinion,
        "wordPassScreenDesign" to model.wordPassScreenDesignOpinion,
        "wordPassScreenContent" to model.wordPassScreenContentOpinion,
        "wordPassScreenGeneral" to model.wordPassScreenGeneralOpinion,
        "quizScreenDesign" to model.quizScreenDesignOpinion,
        "quizScreenContent" to model.quizScreenContentOpinion,
        "quizScreenGeneral" to model.quizScreenGeneralOpinion,
        "familiesScreenDesign" to model.familiesScreenDesignOpinion,
        "familiesScreenContent" to model.familiesScreenContentOpinion,
        "familiesScreenGeneral" to model.familiesScreenGeneralOpinion,
        "surveyScreenDesign" to model.surveyScreenDesignOpinion,
        "surveyScreenContent" to model.surveyScreenContentOpinion,
        "surveyScreenGeneral" to model.surveyScreenGeneralOpinion,
    )
)

/** Builds the [SurveyModel] this [SurveyState] describes, ready to publish. */
private fun SurveyState.toModel(firebaseId: String, nickName: String): SurveyModel {
    val opinions = this.opinions
    val language = if (Locale.getDefault().language == "es") Languages.ES else Languages.EN
    return SurveyModel(
        authorFirebaseId = firebaseId,
        authorNickName = nickName,
        authorAge = age.toIntOrNull() ?: 0,
        profession = profession,
        language = language,
        androidKnowing = androidKnowing,
        applicationsKnowing = applicationsKnowing,
        gamesKnowing = gamesKnowing,
        socialNetworksKnowing = socialNetworksKnowing,
        otherKnowing = otherKnowing,
        predictionOpinion = opinions["prediction"] ?: Opinion(0, ""),
        synthesisOpinion = opinions["synthesis"] ?: Opinion(0, ""),
        familiarityOpinion = opinions["familiarity"] ?: Opinion(0, ""),
        generalityOpinion = opinions["generality"] ?: Opinion(0, ""),
        consistencyOpinion = opinions["consistency"] ?: Opinion(0, ""),
        learnabilityGeneralOpinion = opinions["learnabilityGeneral"] ?: Opinion(0, ""),
        dialogInitiativeOpinion = opinions["dialogInitiative"] ?: Opinion(0, ""),
        multitaskingOpinion = opinions["multitasking"] ?: Opinion(0, ""),
        taskControlOpinion = opinions["taskControl"] ?: Opinion(0, ""),
        adaptationOpinion = opinions["adaptation"] ?: Opinion(0, ""),
        substitutionOpinion = opinions["substitution"] ?: Opinion(0, ""),
        flexibilityGeneralOpinion = opinions["flexibilityGeneral"] ?: Opinion(0, ""),
        observationCapacityOpinion = opinions["observationCapacity"] ?: Opinion(0, ""),
        recuperationCapacityOpinion = opinions["recuperationCapacity"] ?: Opinion(0, ""),
        responseCapacityOpinion = opinions["responseCapacity"] ?: Opinion(0, ""),
        taskAdaptationOpinion = opinions["taskAdaptation"] ?: Opinion(0, ""),
        robustnessGeneralOpinion = opinions["robustnessGeneral"] ?: Opinion(0, ""),
        usabilityGeneralOpinion = opinions["usabilityGeneral"] ?: Opinion(0, ""),
        equitableUseOpinion = opinions["equitableUse"] ?: Opinion(0, ""),
        flexibilityUseOpinion = opinions["flexibilityUse"] ?: Opinion(0, ""),
        simpleAndIntuitiveUseOpinion = opinions["simpleAndIntuitiveUse"] ?: Opinion(0, ""),
        perceptibleInformationOpinion = opinions["perceptibleInformation"] ?: Opinion(0, ""),
        toleranceErrorOpinion = opinions["toleranceError"] ?: Opinion(0, ""),
        lowPhysicalEffortOpinion = opinions["lowPhysicalEffort"] ?: Opinion(0, ""),
        sizeAndSpaceOpinion = opinions["sizeAndSpace"] ?: Opinion(0, ""),
        visualProtanopiaOpinion = opinions["visualProtanopia"] ?: Opinion(0, ""),
        visualDeuteranopiaOpinion = opinions["visualDeuteranopia"] ?: Opinion(0, ""),
        visualTritanopiaOpinion = opinions["visualTritanopia"] ?: Opinion(0, ""),
        reducedVisionZoomAdapterOpinion = opinions["reducedVisionZoomAdapter"] ?: Opinion(0, ""),
        blindnessScreenReaderOpinion = opinions["blindnessScreenReader"] ?: Opinion(0, ""),
        blindnessElementsSoundOpinion = opinions["blindnessElementsSound"] ?: Opinion(0, ""),
        textInformationOpinion = opinions["textInformation"] ?: Opinion(0, ""),
        simpleTextOpinion = opinions["simpleText"] ?: Opinion(0, ""),
        reducedMobilityOpinion = opinions["reducedMobility"] ?: Opinion(0, ""),
        cognitiveSimpleOpinion = opinions["cognitiveSimple"] ?: Opinion(0, ""),
        colorSchemeDarkOpinion = opinions["colorSchemeDark"] ?: Opinion(0, ""),
        colorSchemeLightOpinion = opinions["colorSchemeLight"] ?: Opinion(0, ""),
        colorSchemeContrastOpinion = opinions["colorSchemeContrast"] ?: Opinion(0, ""),
        colorSchemeGeneralOpinion = opinions["colorSchemeGeneral"] ?: Opinion(0, ""),
        fontTypeTitleOpinion = opinions["fontTypeTitle"] ?: Opinion(0, ""),
        fontTypeBodyOpinion = opinions["fontTypeBody"] ?: Opinion(0, ""),
        fontTypeSpecialOpinion = opinions["fontTypeSpecial"] ?: Opinion(0, ""),
        fontTypeGeneralOpinion = opinions["fontTypeGeneral"] ?: Opinion(0, ""),
        windowAdaptationOpinion = opinions["windowAdaptation"] ?: Opinion(0, ""),
        navigationButtonOpinion = opinions["navigationButton"] ?: Opinion(0, ""),
        navigationBottomBarOpinion = opinions["navigationBottomBar"] ?: Opinion(0, ""),
        navigationGeneralOpinion = opinions["navigationGeneral"] ?: Opinion(0, ""),
        splashScreenDesignOpinion = opinions["splashScreenDesign"] ?: Opinion(0, ""),
        splashScreenGeneralOpinion = opinions["splashScreenGeneral"] ?: Opinion(0, ""),
        guideScreenDesignOpinion = opinions["guideScreenDesign"] ?: Opinion(0, ""),
        guideScreenContentOpinion = opinions["guideScreenContent"] ?: Opinion(0, ""),
        guideScreenGeneralOpinion = opinions["guideScreenGeneral"] ?: Opinion(0, ""),
        menuScreenDesignOpinion = opinions["menuScreenDesign"] ?: Opinion(0, ""),
        menuScreenContentOpinion = opinions["menuScreenContent"] ?: Opinion(0, ""),
        menuScreenGeneralOpinion = opinions["menuScreenGeneral"] ?: Opinion(0, ""),
        loginScreenDesignOpinion = opinions["loginScreenDesign"] ?: Opinion(0, ""),
        loginScreenContentOpinion = opinions["loginScreenContent"] ?: Opinion(0, ""),
        loginScreenGeneralOpinion = opinions["loginScreenGeneral"] ?: Opinion(0, ""),
        signScreenDesignOpinion = opinions["signScreenDesign"] ?: Opinion(0, ""),
        signScreenContentOpinion = opinions["signScreenContent"] ?: Opinion(0, ""),
        signScreenGeneralOpinion = opinions["signScreenGeneral"] ?: Opinion(0, ""),
        homeScreenDesignOpinion = opinions["homeScreenDesign"] ?: Opinion(0, ""),
        homeScreenContentOpinion = opinions["homeScreenContent"] ?: Opinion(0, ""),
        homeScreenGeneralOpinion = opinions["homeScreenGeneral"] ?: Opinion(0, ""),
        settingsScreenDesignOpinion = opinions["settingsScreenDesign"] ?: Opinion(0, ""),
        settingsScreenContentOpinion = opinions["settingsScreenContent"] ?: Opinion(0, ""),
        settingsScreenGeneralOpinion = opinions["settingsScreenGeneral"] ?: Opinion(0, ""),
        profileScreenDesignOpinion = opinions["profileScreenDesign"] ?: Opinion(0, ""),
        profileScreenContentOpinion = opinions["profileScreenContent"] ?: Opinion(0, ""),
        profileScreenGeneralOpinion = opinions["profileScreenGeneral"] ?: Opinion(0, ""),
        chatScreenDesignOpinion = opinions["chatScreenDesign"] ?: Opinion(0, ""),
        chatScreenContentOpinion = opinions["chatScreenContent"] ?: Opinion(0, ""),
        chatScreenGeneralOpinion = opinions["chatScreenGeneral"] ?: Opinion(0, ""),
        groupScreenDesignOpinion = opinions["groupScreenDesign"] ?: Opinion(0, ""),
        groupScreenContentOpinion = opinions["groupScreenContent"] ?: Opinion(0, ""),
        groupScreenGeneralOpinion = opinions["groupScreenGeneral"] ?: Opinion(0, ""),
        playScreenDesignOpinion = opinions["playScreenDesign"] ?: Opinion(0, ""),
        playScreenContentOpinion = opinions["playScreenContent"] ?: Opinion(0, ""),
        playScreenGeneralOpinion = opinions["playScreenGeneral"] ?: Opinion(0, ""),
        mysteryNumberScreenDesignOpinion = opinions["mysteryNumberScreenDesign"] ?: Opinion(0, ""),
        mysteryNumberScreenContentOpinion = opinions["mysteryNumberScreenContent"] ?: Opinion(0, ""),
        mysteryNumberScreenGeneralOpinion = opinions["mysteryNumberScreenGeneral"] ?: Opinion(0, ""),
        wordPassScreenDesignOpinion = opinions["wordPassScreenDesign"] ?: Opinion(0, ""),
        wordPassScreenContentOpinion = opinions["wordPassScreenContent"] ?: Opinion(0, ""),
        wordPassScreenGeneralOpinion = opinions["wordPassScreenGeneral"] ?: Opinion(0, ""),
        quizScreenDesignOpinion = opinions["quizScreenDesign"] ?: Opinion(0, ""),
        quizScreenContentOpinion = opinions["quizScreenContent"] ?: Opinion(0, ""),
        quizScreenGeneralOpinion = opinions["quizScreenGeneral"] ?: Opinion(0, ""),
        familiesScreenDesignOpinion = opinions["familiesScreenDesign"] ?: Opinion(0, ""),
        familiesScreenContentOpinion = opinions["familiesScreenContent"] ?: Opinion(0, ""),
        familiesScreenGeneralOpinion = opinions["familiesScreenGeneral"] ?: Opinion(0, ""),
        surveyScreenDesignOpinion = opinions["surveyScreenDesign"] ?: Opinion(0, ""),
        surveyScreenContentOpinion = opinions["surveyScreenContent"] ?: Opinion(0, ""),
        surveyScreenGeneralOpinion = opinions["surveyScreenGeneral"] ?: Opinion(0, ""),
    )
}

private const val SUBMIT_TIMEOUT_MS = 20_000L
