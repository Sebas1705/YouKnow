package es.sebas1705.survey


import es.sebas1705.survey.usescases.GetActualSurvey
import es.sebas1705.survey.usescases.GetAllSurveys
import es.sebas1705.survey.usescases.PublicSurvey

/**
 * Survey use cases
 *
 * @param publicSurvey [PublicSurvey]: use case to public a survey
 * @param getActualSurvey [GetActualSurvey]: use case to get the actual survey
 * @param getAllSurveys [GetAllSurveys]: use case to get all surveys
 *
 * @since 1.0.0
 * @author Sebas1705 12/09/2025
 */
data class SurveyUsesCases(
    val publicSurvey: PublicSurvey,
    val getActualSurvey: GetActualSurvey,
    val getAllSurveys: GetAllSurveys
)