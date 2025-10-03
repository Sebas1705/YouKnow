package es.sebas1705.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.sebas1705.room.config.SettingsDB

/**
 * SurveyEntity is a data class that represents the survey entity.
 *
 * @property authorFirebaseId [String]: Author Firebase ID
 * @property authorNickName [String]: Author Nick Name
 * @property authorAge [Int]: Author Age
 * @property profession [String]: Profession
 * @property language [Int]: Language
 * @property androidKnowing [Int]: Android Knowing
 * @property applicationsKnowing [Int]: Applications Knowing
 * @property gamesKnowing [Int]: Games Knowing
 * @property socialNetworksKnowing [Int]: Social Networks Knowing
 * @property otherKnowing [String]: Other Knowing
 * @property predictionPoints [Int]: Prediction Points
 * @property predictionOpinion [String]: Prediction Opinion
 * @property synthesisPoints [Int]: Synthesis Points
 * @property synthesisOpinion [String]: Synthesis Opinion
 * @property familiarityPoints [Int]: Familiarity Points
 * @property familiarityOpinion [String]: Familiarity Opinion
 * @property generalityPoints [Int]: Generality Points
 * @property generalityOpinion [String]: Generality Opinion
 * @property consistencyPoints [Int]: Consistency Points
 * @property consistencyOpinion [String]: Consistency Opinion
 * @property learnabilityGeneralPoints [Int]: Learnability General Points
 * @property learnabilityGeneralOpinion [String]: Learnability General Opinion
 * @property dialogInitiativePoints [Int]: Dialog Initiative Points
 * @property dialogInitiativeOpinion [String]: Dialog Initiative Opinion
 * @property multitaskingPoints [Int]: Multitasking Points
 * @property multitaskingOpinion [String]: Multitasking Opinion
 * @property taskControlPoints [Int]: Task Control Points
 * @property taskControlOpinion [String]: Task Control Opinion
 * @property adaptationPoints [Int]: Adaptation Points
 * @property adaptationOpinion [String]: Adaptation Opinion
 * @property substitutionPoints [Int]: Substitution Points
 * @property substitutionOpinion [String]: Substitution Opinion
 * @property flexibilityGeneralPoints [Int]: Flexibility General Points
 * @property flexibilityGeneralOpinion [String]: Flexibility General Opinion
 * @property observationCapacityPoints [Int]: Observation Capacity Points
 * @property observationCapacityOpinion [String]: Observation Capacity Opinion
 * @property recuperationCapacityPoints [Int]: Recuperation Capacity Points
 * @property recuperationCapacityOpinion [String]: Recuperation Capacity Opinion
 * @property responseCapacityPoints [Int]: Response Capacity Points
 * @property responseCapacityOpinion [String]: Response Capacity Opinion
 * @property taskAdaptationPoints [Int]: Task Adaptation Points
 * @property taskAdaptationOpinion [String]: Task Adaptation Opinion
 * @property robustnessGeneralPoints [Int]: Robustness General Points
 * @property robustnessGeneralOpinion [String]: Robustness General Opinion
 * @property usabilityGeneralPoints [Int]: Usability General Points
 * @property usabilityGeneralOpinion [String]: Usability General Opinion
 * @property equitableUsePoints [Int]: Equitable Use Points
 * @property equitableUseOpinion [String]: Equitable Use Opinion
 * @property flexibilityUsePoints [Int]: Flexibility Use Points
 * @property flexibilityUseOpinion [String]: Flexibility Use Opinion
 * @property simpleAndIntuitiveUsePoints [Int]: Simple And Intuitive Use Points
 * @property simpleAndIntuitiveUseOpinion [String]: Simple And Intuitive Use Opinion
 * @property perceptibleInformationPoints [Int]: Perceptible Information Points
 * @property perceptibleInformationOpinion [String]: Perceptible Information Opinion
 * @property toleranceErrorPoints [Int]: Tolerance Error Points
 * @property toleranceErrorOpinion [String]: Tolerance Error Opinion
 * @property lowPhysicalEffortPoints [Int]: Low Physical Effort Points
 * @property lowPhysicalEffortOpinion [String]: Low Physical Effort Opinion
 * @property sizeAndSpacePoints [Int]: Size And Space Points
 * @property sizeAndSpaceOpinion [String]: Size And Space Opinion
 * @property visualProtanopiaPoints [Int]: Visual Protanopia Points
 * @property visualProtanopiaOpinion [String]: Visual Protanopia Opinion
 * @property visualDeuteranopiaPoints [Int]: Visual Deuteranopia Points
 * @property visualDeuteranopiaOpinion [String]: Visual Deuteranopia Opinion
 * @property visualTritanopiaPoints [Int]: Visual Tritanopia Points
 * @property visualTritanopiaOpinion [String]: Visual Tritanopia Opinion
 * @property reducedVisionZoomAdapterPoints [Int]: Reduced Vision Zoom Adapter Points
 * @property reducedVisionZoomAdapterOpinion [String]: Reduced Vision Zoom Adapter Opinion
 * @property blindnessScreenReaderPoints [Int]: Blindness Screen Reader Points
 * @property blindnessScreenReaderOpinion [String]: Blindness Screen Reader Opinion
 * @property blindnessElementsSoundPoints [Int]: Blindness Elements Sound Points
 * @property blindnessElementsSoundOpinion [String]: Blindness Elements Sound Opinion
 * @property textInformationPoints [Int]: Text Information Points
 * @property textInformationOpinion [String]: Text Information Opinion
 * @property simpleTextPoints [Int]: Simple Text Points
 * @property simpleTextOpinion [String]: Simple Text Opinion
 * @property reducedMobilityPoints [Int]: Reduced Mobility Points
 * @property reducedMobilityOpinion [String]: Reduced Mobility Opinion
 * @property cognitiveSimplePoints [Int]: Cognitive Simple Points
 * @property cognitiveSimpleOpinion [String]: Cognitive Simple Opinion
 * @property colorSchemeDarkPoints [Int]: Color Scheme Dark Points
 * @property colorSchemeDarkOpinion [String]: Color Scheme Dark Opinion
 * @property colorSchemeLightPoints [Int]: Color Scheme Light Points
 * @property colorSchemeLightOpinion [String]: Color Scheme Light Opinion
 * @property colorSchemeContrastPoints [Int]: Color Scheme Contrast Points
 * @property colorSchemeContrastOpinion [String]: Color Scheme Contrast Opinion
 * @property colorSchemeGeneralPoints [Int]: Color Scheme General Points
 * @property colorSchemeGeneralOpinion [String]: Color Scheme General Opinion
 * @property fontTypeTitlePoints [Int]: Font Type Title Points
 * @property fontTypeTitleOpinion [String]: Font Type Title Opinion
 * @property fontTypeBodyPoints [Int]: Font Type Body Points
 * @property fontTypeBodyOpinion [String]: Font Type Body Opinion
 * @property fontTypeSpecialPoints [Int]: Font Type Special Points
 * @property fontTypeSpecialOpinion [String]: Font Type Special Opinion
 * @property fontTypeGeneralPoints [Int]: Font Type General Points
 * @property fontTypeGeneralOpinion [String]: Font Type General Opinion
 * @property windowAdaptationPoints [Int]: Window Adaptation Points
 * @property windowAdaptationOpinion [String]: Window Adaptation Opinion
 * @property navigationButtonPoints [Int]: Navigation Button Points
 * @property navigationButtonOpinion [String]: Navigation Button Opinion
 * @property navigationBottomBarPoints [Int]: Navigation Bottom Bar Points
 * @property navigationBottomBarOpinion [String]: Navigation Bottom Bar Opinion
 * @property navigationGeneralPoints [Int]: Navigation General Points
 * @property navigationGeneralOpinion [String]: Navigation General Opinion
 * @property splashScreenDesignPoints [Int]: Splash Screen Design Points
 * @property splashScreenDesignOpinion [String]: Splash Screen Design Opinion
 * @property splashScreenGeneralPoints [Int]: Splash Screen General Points
 * @property splashScreenGeneralOpinion [String]: Splash Screen General Opinion
 * @property guideScreenDesignPoints [Int]: Guide Screen Design Points
 * @property guideScreenDesignOpinion [String]: Guide Screen Design Opinion
 * @property guideScreenContentPoints [Int]: Guide Screen Content Points
 * @property guideScreenContentOpinion [String]: Guide Screen Content Opinion
 * @property guideScreenGeneralPoints [Int]: Guide Screen General Points
 * @property guideScreenGeneralOpinion [String]: Guide Screen General Opinion
 * @property menuScreenDesignPoints [Int]: Menu Screen Design Points
 * @property menuScreenDesignOpinion [String]: Menu Screen Design Opinion
 * @property menuScreenContentPoints [Int]: Menu Screen Content Points
 * @property menuScreenContentOpinion [String]: Menu Screen Content Opinion
 * @property menuScreenGeneralPoints [Int]: Menu Screen General Points
 * @property menuScreenGeneralOpinion [String]: Menu Screen General Opinion
 * @property loginScreenDesignPoints [Int]: Login Screen Design Points
 * @property loginScreenDesignOpinion [String]: Login Screen Design Opinion
 * @property loginScreenContentPoints [Int]: Login Screen Content Points
 * @property loginScreenContentOpinion [String]: Login Screen Content Opinion
 * @property loginScreenGeneralPoints [Int]: Login Screen General Points
 * @property loginScreenGeneralOpinion [String]: Login Screen General Opinion
 * @property signScreenDesignPoints [Int]: Sign Screen Design Points
 * @property signScreenDesignOpinion [String]: Sign Screen Design Opinion
 * @property signScreenContentPoints [Int]: Sign Screen Content Points
 * @property signScreenContentOpinion [String]: Sign Screen Content Opinion
 * @property signScreenGeneralPoints [Int]: Sign Screen General Points
 * @property signScreenGeneralOpinion [String]: Sign Screen General Opinion
 * @property homeScreenDesignPoints [Int]: Home Screen Design Points
 * @property homeScreenDesignOpinion [String]: Home Screen Design Opinion
 * @property homeScreenContentPoints [Int]: Home Screen Content Points
 * @property homeScreenContentOpinion [String]: Home Screen Content Opinion
 * @property homeScreenGeneralPoints [Int]: Home Screen General Points
 * @property homeScreenGeneralOpinion [String]: Home Screen General Opinion
 * @property settingsScreenDesignPoints [Int]: Settings Screen Design Points
 * @property settingsScreenDesignOpinion [String]: Settings Screen Design Opinion
 * @property settingsScreenContentPoints [Int]: Settings Screen Content Points
 * @property settingsScreenContentOpinion [String]: Settings Screen Content Opinion
 * @property settingsScreenGeneralPoints [Int]: Settings Screen General Points
 * @property settingsScreenGeneralOpinion [String]: Settings Screen General Opinion
 * @property profileScreenDesignPoints [Int]: Profile Screen Design Points
 * @property profileScreenDesignOpinion [String]: Profile Screen Design Opinion
 * @property profileScreenContentPoints [Int]: Profile Screen Content Points
 * @property profileScreenContentOpinion [String]: Profile Screen Content Opinion
 * @property profileScreenGeneralPoints [Int]: Profile Screen General Points
 * @property profileScreenGeneralOpinion [String]: Profile Screen General Opinion
 * @property chatScreenDesignPoints [Int]: Chat Screen Design Points
 * @property chatScreenDesignOpinion [String]: Chat Screen Design Opinion
 * @property chatScreenContentPoints [Int]: Chat Screen Content Points
 * @property chatScreenContentOpinion [String]: Chat Screen Content Opinion
 * @property chatScreenGeneralPoints [Int]: Chat Screen General Points
 * @property chatScreenGeneralOpinion [String]: Chat Screen General Opinion
 * @property groupScreenDesignPoints [Int]: Group Screen Design Points
 * @property groupScreenDesignOpinion [String]: Group Screen Design Opinion
 * @property groupScreenContentPoints [Int]: Group Screen Content Points
 * @property groupScreenContentOpinion [String]: Group Screen Content Opinion
 * @property groupScreenGeneralPoints [Int]: Group Screen General Points
 * @property groupScreenGeneralOpinion [String]: Group Screen General Opinion
 * @property playScreenDesignPoints [Int]: Play Screen Design Points
 * @property playScreenDesignOpinion [String]: Play Screen Design Opinion
 * @property playScreenContentPoints [Int]: Play Screen Content Points
 * @property playScreenContentOpinion [String]: Play Screen Content Opinion
 * @property playScreenGeneralPoints [Int]: Play Screen General Points
 * @property playScreenGeneralOpinion [String]: Play Screen General Opinion
 * @property mysteryNumberScreenDesignPoints [Int]: Mystery Number Screen Design Points
 * @property mysteryNumberScreenDesignOpinion [String]: Mystery Number Screen Design Opinion
 * @property mysteryNumberScreenContentPoints [Int]: Mystery Number Screen Content Points
 * @property mysteryNumberScreenContentOpinion [String]: Mystery Number Screen Content Opinion
 * @property mysteryNumberScreenGeneralPoints [Int]: Mystery Number Screen General Points
 * @property mysteryNumberScreenGeneralOpinion [String]: Mystery Number Screen General Opinion
 * @property wordPassScreenDesignPoints [Int]: Word Pass Screen Design Points
 * @property wordPassScreenDesignOpinion [String]: Word Pass Screen Design Opinion
 * @property wordPassScreenContentPoints [Int]: Word Pass Screen Content Points
 * @property wordPassScreenContentOpinion [String]: Word Pass Screen Content Opinion
 * @property wordPassScreenGeneralPoints [Int]: Word Pass Screen General Points
 * @property wordPassScreenGeneralOpinion [String]: Word Pass Screen General Opinion
 * @property quizScreenDesignPoints [Int]: Quiz Screen Design Points
 * @property quizScreenDesignOpinion [String]: Quiz Screen Design Opinion
 * @property quizScreenContentPoints [Int]: Quiz Screen Content Points
 * @property quizScreenContentOpinion [String]: Quiz Screen Content Opinion
 * @property quizScreenGeneralPoints [Int]: Quiz Screen General Points
 * @property quizScreenGeneralOpinion [String]: Quiz Screen General Opinion
 * @property familiesScreenDesignPoints [Int]: Families Screen Design Points
 * @property familiesScreenDesignOpinion [String]: Families Screen Design Opinion
 * @property familiesScreenContentPoints [Int]: Families Screen Content Points
 * @property familiesScreenContentOpinion [String]: Families Screen Content Opinion
 * @property familiesScreenGeneralPoints [Int]: Families Screen General Points
 * @property familiesScreenGeneralOpinion [String]: Families Screen General Opinion
 * @property surveyScreenDesignPoints [Int]: Survey Screen Design Points
 * @property surveyScreenDesignOpinion [String]: Survey Screen Design Opinion
 * @property surveyScreenContentPoints [Int]: Survey Screen Content Points
 * @property surveyScreenContentOpinion [String]: Survey Screen Content Opinion
 * @property surveyScreenGeneralPoints [Int]: Survey Screen General Points
 * @property surveyScreenGeneralOpinion [String]: Survey Screen General Opinion
 *
 * @since 1.0.0
 * @author Sebas1705 30/09/2025
 */
@Entity(tableName = SettingsDB.SURVEY_TABLE)
data class SurveyEntity(

    /////User:
    @PrimaryKey val authorFirebaseId: String,
    val authorNickName: String,
    val authorAge: Int,
    val profession: String,
    val language: Int,
    val androidKnowing: Int,
    val applicationsKnowing: Int,
    val gamesKnowing: Int,
    val socialNetworksKnowing: Int,
    val otherKnowing: String,
    // End User

    /////Usability:
    //Learnability:
    val predictionPoints: Int,
    val predictionOpinion: String,
    val synthesisPoints: Int,
    val synthesisOpinion: String,
    val familiarityPoints: Int,
    val familiarityOpinion: String,
    val generalityPoints: Int,
    val generalityOpinion: String,
    val consistencyPoints: Int,
    val consistencyOpinion: String,
    val learnabilityGeneralPoints: Int,
    val learnabilityGeneralOpinion: String,
    //Flexibility:
    val dialogInitiativePoints: Int,
    val dialogInitiativeOpinion: String,
    val multitaskingPoints: Int,
    val multitaskingOpinion: String,
    val taskControlPoints: Int,
    val taskControlOpinion: String,
    val adaptationPoints: Int,
    val adaptationOpinion: String,
    val substitutionPoints: Int,
    val substitutionOpinion: String,
    val flexibilityGeneralPoints: Int,
    val flexibilityGeneralOpinion: String,
    //Robustness
    val observationCapacityPoints: Int,
    val observationCapacityOpinion: String,
    val recuperationCapacityPoints: Int,
    val recuperationCapacityOpinion: String,
    val responseCapacityPoints: Int,
    val responseCapacityOpinion: String,
    val taskAdaptationPoints: Int,
    val taskAdaptationOpinion: String,
    val robustnessGeneralPoints: Int,
    val robustnessGeneralOpinion: String,
    val usabilityGeneralPoints: Int,
    val usabilityGeneralOpinion: String,
    // End Usability

    /////Accessibility:
    //Universal design:
    val equitableUsePoints: Int,
    val equitableUseOpinion: String,
    val flexibilityUsePoints: Int,
    val flexibilityUseOpinion: String,
    val simpleAndIntuitiveUsePoints: Int,
    val simpleAndIntuitiveUseOpinion: String,
    val perceptibleInformationPoints: Int,
    val perceptibleInformationOpinion: String,
    val toleranceErrorPoints: Int,
    val toleranceErrorOpinion: String,
    val lowPhysicalEffortPoints: Int,
    val lowPhysicalEffortOpinion: String,
    val sizeAndSpacePoints: Int,
    val sizeAndSpaceOpinion: String,
    //Disabilities:
    /*Colors*/
    val visualProtanopiaPoints: Int,
    val visualProtanopiaOpinion: String,
    val visualDeuteranopiaPoints: Int,
    val visualDeuteranopiaOpinion: String,
    val visualTritanopiaPoints: Int,
    val visualTritanopiaOpinion: String,
    /*Reduced vision*/
    val reducedVisionZoomAdapterPoints: Int,
    val reducedVisionZoomAdapterOpinion: String,
    /*Blindness*/
    val blindnessScreenReaderPoints: Int,
    val blindnessScreenReaderOpinion: String,
    val blindnessElementsSoundPoints: Int,
    val blindnessElementsSoundOpinion: String,
    /*Deafness*/
    val textInformationPoints: Int,
    val textInformationOpinion: String,
    val simpleTextPoints: Int,
    val simpleTextOpinion: String,
    /*Reduced mobility*/
    val reducedMobilityPoints: Int,
    val reducedMobilityOpinion: String,
    /*Cognitive disabilities*/
    val cognitiveSimplePoints: Int,
    val cognitiveSimpleOpinion: String,
    // End Accessibility

    /////UI/UX
    //Colors:
    val colorSchemeDarkPoints: Int,
    val colorSchemeDarkOpinion: String,
    val colorSchemeLightPoints: Int,
    val colorSchemeLightOpinion: String,
    val colorSchemeContrastPoints: Int,
    val colorSchemeContrastOpinion: String,
    val colorSchemeGeneralPoints: Int,
    val colorSchemeGeneralOpinion: String,
    //Fonts:
    val fontTypeTitlePoints: Int,
    val fontTypeTitleOpinion: String,
    val fontTypeBodyPoints: Int,
    val fontTypeBodyOpinion: String,
    val fontTypeSpecialPoints: Int,
    val fontTypeSpecialOpinion: String,
    val fontTypeGeneralPoints: Int,
    val fontTypeGeneralOpinion: String,
    //WindowAdaptation:
    val windowAdaptationPoints: Int,
    val windowAdaptationOpinion: String,
    //Navigation:
    val navigationButtonPoints: Int,
    val navigationButtonOpinion: String,
    val navigationBottomBarPoints: Int,
    val navigationBottomBarOpinion: String,
    val navigationGeneralPoints: Int,
    val navigationGeneralOpinion: String,
    // End UI/UX

    /////Features:
    //Screens:
    /*Splash Screen*/
    val splashScreenDesignPoints: Int,
    val splashScreenDesignOpinion: String,
    val splashScreenGeneralPoints: Int,
    val splashScreenGeneralOpinion: String,
    /*Guide Screen*/
    val guideScreenDesignPoints: Int,
    val guideScreenDesignOpinion: String,
    val guideScreenContentPoints: Int,
    val guideScreenContentOpinion: String,
    val guideScreenGeneralPoints: Int,
    val guideScreenGeneralOpinion: String,
    /*Menu Screen*/
    val menuScreenDesignPoints: Int,
    val menuScreenDesignOpinion: String,
    val menuScreenContentPoints: Int,
    val menuScreenContentOpinion: String,
    val menuScreenGeneralPoints: Int,
    val menuScreenGeneralOpinion: String,
    /*Login Screen*/
    val loginScreenDesignPoints: Int,
    val loginScreenDesignOpinion: String,
    val loginScreenContentPoints: Int,
    val loginScreenContentOpinion: String,
    val loginScreenGeneralPoints: Int,
    val loginScreenGeneralOpinion: String,
    /*Sign Screen*/
    val signScreenDesignPoints: Int,
    val signScreenDesignOpinion: String,
    val signScreenContentPoints: Int,
    val signScreenContentOpinion: String,
    val signScreenGeneralPoints: Int,
    val signScreenGeneralOpinion: String,
    /*Home Screen*/
    val homeScreenDesignPoints: Int,
    val homeScreenDesignOpinion: String,
    val homeScreenContentPoints: Int,
    val homeScreenContentOpinion: String,
    val homeScreenGeneralPoints: Int,
    val homeScreenGeneralOpinion: String,
    /*Settings Screen*/
    val settingsScreenDesignPoints: Int,
    val settingsScreenDesignOpinion: String,
    val settingsScreenContentPoints: Int,
    val settingsScreenContentOpinion: String,
    val settingsScreenGeneralPoints: Int,
    val settingsScreenGeneralOpinion: String,
    /*Profile Screen*/
    val profileScreenDesignPoints: Int,
    val profileScreenDesignOpinion: String,
    val profileScreenContentPoints: Int,
    val profileScreenContentOpinion: String,
    val profileScreenGeneralPoints: Int,
    val profileScreenGeneralOpinion: String,
    /*Chat Screen*/
    val chatScreenDesignPoints: Int,
    val chatScreenDesignOpinion: String,
    val chatScreenContentPoints: Int,
    val chatScreenContentOpinion: String,
    val chatScreenGeneralPoints: Int,
    val chatScreenGeneralOpinion: String,
    /*Group Screen*/
    val groupScreenDesignPoints: Int,
    val groupScreenDesignOpinion: String,
    val groupScreenContentPoints: Int,
    val groupScreenContentOpinion: String,
    val groupScreenGeneralPoints: Int,
    val groupScreenGeneralOpinion: String,
    /*Play Screen*/
    val playScreenDesignPoints: Int,
    val playScreenDesignOpinion: String,
    val playScreenContentPoints: Int,
    val playScreenContentOpinion: String,
    val playScreenGeneralPoints: Int,
    val playScreenGeneralOpinion: String,
    /*Mystery Number Screens*/
    val mysteryNumberScreenDesignPoints: Int,
    val mysteryNumberScreenDesignOpinion: String,
    val mysteryNumberScreenContentPoints: Int,
    val mysteryNumberScreenContentOpinion: String,
    val mysteryNumberScreenGeneralPoints: Int,
    val mysteryNumberScreenGeneralOpinion: String,
    /*Word Pass Screens*/
    val wordPassScreenDesignPoints: Int,
    val wordPassScreenDesignOpinion: String,
    val wordPassScreenContentPoints: Int,
    val wordPassScreenContentOpinion: String,
    val wordPassScreenGeneralPoints: Int,
    val wordPassScreenGeneralOpinion: String,
    /*Quiz Screens*/
    val quizScreenDesignPoints: Int,
    val quizScreenDesignOpinion: String,
    val quizScreenContentPoints: Int,
    val quizScreenContentOpinion: String,
    val quizScreenGeneralPoints: Int,
    val quizScreenGeneralOpinion: String,
    /*Families Screens*/
    val familiesScreenDesignPoints: Int,
    val familiesScreenDesignOpinion: String,
    val familiesScreenContentPoints: Int,
    val familiesScreenContentOpinion: String,
    val familiesScreenGeneralPoints: Int,
    val familiesScreenGeneralOpinion: String,
    /*Survey Screen*/
    val surveyScreenDesignPoints: Int,
    val surveyScreenDesignOpinion: String,
    val surveyScreenContentPoints: Int,
    val surveyScreenContentOpinion: String,
    val surveyScreenGeneralPoints: Int,
    val surveyScreenGeneralOpinion: String,
    // End Features
)