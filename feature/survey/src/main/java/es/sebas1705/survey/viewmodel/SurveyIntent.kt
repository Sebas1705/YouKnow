package es.sebas1705.survey.viewmodel


import es.sebas1705.common.classes.mvi.MVIBaseIntent

/**
 * Intent of the [SurveyViewModel].
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
sealed interface SurveyIntent : MVIBaseIntent {

    data object Load : SurveyIntent

    data class UpdateAge(val age: String) : SurveyIntent
    data class UpdateProfession(val profession: String) : SurveyIntent
    data class UpdateAndroidKnowing(val value: Int) : SurveyIntent
    data class UpdateApplicationsKnowing(val value: Int) : SurveyIntent
    data class UpdateGamesKnowing(val value: Int) : SurveyIntent
    data class UpdateSocialNetworksKnowing(val value: Int) : SurveyIntent
    data class UpdateOtherKnowing(val otherKnowing: String) : SurveyIntent

    data class UpdateOpinionPoints(val key: String, val points: Int) : SurveyIntent
    data class UpdateOpinionText(val key: String, val text: String) : SurveyIntent

    data object Submit : SurveyIntent
}
