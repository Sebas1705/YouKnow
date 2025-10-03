package es.sebas1705.firestore.documents

/**
 * SurveyDocument is a data class that represents the survey document.
 *
 * @since 1.0.0
 * @author Sebas1705 22/09/2025
 */
data class SurveyDocument(

    /////User:
    val authorFirebaseId: String = "",
    val authorNickName: String = "",
    val authorAge: Int = 0,
    val profession: String = "",
    val language: Int = 0,
    val androidKnowing: Int = 0,
    val applicationsKnowing: Int = 0,
    val gamesKnowing: Int = 0,
    val socialNetworksKnowing: Int = 0,
    val otherKnowing: String = "",
    // End User

    /////Usability:
    //Learnability:
    val predictionPoints: Int = 0,
    val predictionOpinion: String = "",
    val synthesisPoints: Int = 0,
    val synthesisOpinion: String = "",
    val familiarityPoints: Int = 0,
    val familiarityOpinion: String = "",
    val generalityPoints: Int = 0,
    val generalityOpinion: String = "",
    val consistencyPoints: Int = 0,
    val consistencyOpinion: String = "",
    val learnabilityGeneralPoints: Int = 0,
    val learnabilityGeneralOpinion: String = "",
    //Flexibility:
    val dialogInitiativePoints: Int = 0,
    val dialogInitiativeOpinion: String = "",
    val multitaskingPoints: Int = 0,
    val multitaskingOpinion: String = "",
    val taskControlPoints: Int = 0,
    val taskControlOpinion: String = "",
    val adaptationPoints: Int = 0,
    val adaptationOpinion: String = "",
    val substitutionPoints: Int = 0,
    val substitutionOpinion: String = "",
    val flexibilityGeneralPoints: Int = 0,
    val flexibilityGeneralOpinion: String = "",
    //Robustness
    val observationCapacityPoints: Int = 0,
    val observationCapacityOpinion: String = "",
    val recuperationCapacityPoints: Int = 0,
    val recuperationCapacityOpinion: String = "",
    val responseCapacityPoints: Int = 0,
    val responseCapacityOpinion: String = "",
    val taskAdaptationPoints: Int = 0,
    val taskAdaptationOpinion: String = "",
    val robustnessGeneralPoints: Int = 0,
    val robustnessGeneralOpinion: String = "",
    val usabilityGeneralPoints: Int = 0,
    val usabilityGeneralOpinion: String = "",
    // End Usability

    /////Accessibility:
    //Universal design:
    val equitableUsePoints: Int = 0,
    val equitableUseOpinion: String = "",
    val flexibilityUsePoints: Int = 0,
    val flexibilityUseOpinion: String = "",
    val simpleAndIntuitiveUsePoints: Int = 0,
    val simpleAndIntuitiveUseOpinion: String = "",
    val perceptibleInformationPoints: Int = 0,
    val perceptibleInformationOpinion: String = "",
    val toleranceErrorPoints: Int = 0,
    val toleranceErrorOpinion: String = "",
    val lowPhysicalEffortPoints: Int = 0,
    val lowPhysicalEffortOpinion: String = "",
    val sizeAndSpacePoints: Int = 0,
    val sizeAndSpaceOpinion: String = "",
    //Disabilities:
    /*Colors*/
    val visualProtanopiaPoints: Int = 0,
    val visualProtanopiaOpinion: String = "",
    val visualDeuteranopiaPoints: Int = 0,
    val visualDeuteranopiaOpinion: String = "",
    val visualTritanopiaPoints: Int = 0,
    val visualTritanopiaOpinion: String = "",
    /*Reduced vision*/
    val reducedVisionZoomAdapterPoints: Int = 0,
    val reducedVisionZoomAdapterOpinion: String = "",
    /*Blindness*/
    val blindnessScreenReaderPoints: Int = 0,
    val blindnessScreenReaderOpinion: String = "",
    val blindnessElementsSoundPoints: Int = 0,
    val blindnessElementsSoundOpinion: String = "",
    /*Deafness*/
    val textInformationPoints: Int = 0,
    val textInformationOpinion: String = "",
    val simpleTextPoints: Int = 0,
    val simpleTextOpinion: String = "",
    /*Reduced mobility*/
    val reducedMobilityPoints: Int = 0,
    val reducedMobilityOpinion: String = "",
    /*Cognitive disabilities*/
    val cognitiveSimplePoints: Int = 0,
    val cognitiveSimpleOpinion: String = "",
    // End Accessibility

    /////UI/UX
    //Colors:
    val colorSchemeDarkPoints: Int = 0,
    val colorSchemeDarkOpinion: String = "",
    val colorSchemeLightPoints: Int = 0,
    val colorSchemeLightOpinion: String = "",
    val colorSchemeContrastPoints: Int = 0,
    val colorSchemeContrastOpinion: String = "",
    val colorSchemeGeneralPoints: Int = 0,
    val colorSchemeGeneralOpinion: String = "",
    //Fonts:
    val fontTypeTitlePoints: Int = 0,
    val fontTypeTitleOpinion: String = "",
    val fontTypeBodyPoints: Int = 0,
    val fontTypeBodyOpinion: String = "",
    val fontTypeSpecialPoints: Int = 0,
    val fontTypeSpecialOpinion: String = "",
    val fontTypeGeneralPoints: Int = 0,
    val fontTypeGeneralOpinion: String = "",
    //WindowAdaptation:
    val windowAdaptationPoints: Int = 0,
    val windowAdaptationOpinion: String = "",
    //Navigation:
    val navigationButtonPoints: Int = 0,
    val navigationButtonOpinion: String = "",
    val navigationBottomBarPoints: Int = 0,
    val navigationBottomBarOpinion: String = "",
    val navigationGeneralPoints: Int = 0,
    val navigationGeneralOpinion: String = "",
    // End UI/UX

    /////Features:
    //Screens:
    /*Splash Screen*/
    val splashScreenDesignPoints: Int = 0,
    val splashScreenDesignOpinion: String = "",
    val splashScreenGeneralPoints: Int = 0,
    val splashScreenGeneralOpinion: String = "",
    /*Guide Screen*/
    val guideScreenDesignPoints: Int = 0,
    val guideScreenDesignOpinion: String = "",
    val guideScreenContentPoints: Int = 0,
    val guideScreenContentOpinion: String = "",
    val guideScreenGeneralPoints: Int = 0,
    val guideScreenGeneralOpinion: String = "",
    /*Menu Screen*/
    val menuScreenDesignPoints: Int = 0,
    val menuScreenDesignOpinion: String = "",
    val menuScreenContentPoints: Int = 0,
    val menuScreenContentOpinion: String = "",
    val menuScreenGeneralPoints: Int = 0,
    val menuScreenGeneralOpinion: String = "",
    /*Login Screen*/
    val loginScreenDesignPoints: Int = 0,
    val loginScreenDesignOpinion: String = "",
    val loginScreenContentPoints: Int = 0,
    val loginScreenContentOpinion: String = "",
    val loginScreenGeneralPoints: Int = 0,
    val loginScreenGeneralOpinion: String = "",
    /*Sign Screen*/
    val signScreenDesignPoints: Int = 0,
    val signScreenDesignOpinion: String = "",
    val signScreenContentPoints: Int = 0,
    val signScreenContentOpinion: String = "",
    val signScreenGeneralPoints: Int = 0,
    val signScreenGeneralOpinion: String = "",
    /*Home Screen*/
    val homeScreenDesignPoints: Int = 0,
    val homeScreenDesignOpinion: String = "",
    val homeScreenContentPoints: Int = 0,
    val homeScreenContentOpinion: String = "",
    val homeScreenGeneralPoints: Int = 0,
    val homeScreenGeneralOpinion: String = "",
    /*Settings Screen*/
    val settingsScreenDesignPoints: Int = 0,
    val settingsScreenDesignOpinion: String = "",
    val settingsScreenContentPoints: Int = 0,
    val settingsScreenContentOpinion: String = "",
    val settingsScreenGeneralPoints: Int = 0,
    val settingsScreenGeneralOpinion: String = "",
    /*Profile Screen*/
    val profileScreenDesignPoints: Int = 0,
    val profileScreenDesignOpinion: String = "",
    val profileScreenContentPoints: Int = 0,
    val profileScreenContentOpinion: String = "",
    val profileScreenGeneralPoints: Int = 0,
    val profileScreenGeneralOpinion: String = "",
    /*Chat Screen*/
    val chatScreenDesignPoints: Int = 0,
    val chatScreenDesignOpinion: String = "",
    val chatScreenContentPoints: Int = 0,
    val chatScreenContentOpinion: String = "",
    val chatScreenGeneralPoints: Int = 0,
    val chatScreenGeneralOpinion: String = "",
    /*Group Screen*/
    val groupScreenDesignPoints: Int = 0,
    val groupScreenDesignOpinion: String = "",
    val groupScreenContentPoints: Int = 0,
    val groupScreenContentOpinion: String = "",
    val groupScreenGeneralPoints: Int = 0,
    val groupScreenGeneralOpinion: String = "",
    /*Play Screen*/
    val playScreenDesignPoints: Int = 0,
    val playScreenDesignOpinion: String = "",
    val playScreenContentPoints: Int = 0,
    val playScreenContentOpinion: String = "",
    val playScreenGeneralPoints: Int = 0,
    val playScreenGeneralOpinion: String = "",
    /*Mystery Number Screens*/
    val mysteryNumberScreenDesignPoints: Int = 0,
    val mysteryNumberScreenDesignOpinion: String = "",
    val mysteryNumberScreenContentPoints: Int = 0,
    val mysteryNumberScreenContentOpinion: String = "",
    val mysteryNumberScreenGeneralPoints: Int = 0,
    val mysteryNumberScreenGeneralOpinion: String = "",
    /*Word Pass Screens*/
    val wordPassScreenDesignPoints: Int = 0,
    val wordPassScreenDesignOpinion: String = "",
    val wordPassScreenContentPoints: Int = 0,
    val wordPassScreenContentOpinion: String = "",
    val wordPassScreenGeneralPoints: Int = 0,
    val wordPassScreenGeneralOpinion: String = "",
    /*Quiz Screens*/
    val quizScreenDesignPoints: Int = 0,
    val quizScreenDesignOpinion: String = "",
    val quizScreenContentPoints: Int = 0,
    val quizScreenContentOpinion: String = "",
    val quizScreenGeneralPoints: Int = 0,
    val quizScreenGeneralOpinion: String = "",
    /*Families Screens*/
    val familiesScreenDesignPoints: Int = 0,
    val familiesScreenDesignOpinion: String = "",
    val familiesScreenContentPoints: Int = 0,
    val familiesScreenContentOpinion: String = "",
    val familiesScreenGeneralPoints: Int = 0,
    val familiesScreenGeneralOpinion: String = "",
    /*Survey Screen*/
    val surveyScreenDesignPoints: Int = 0,
    val surveyScreenDesignOpinion: String = "",
    val surveyScreenContentPoints: Int = 0,
    val surveyScreenContentOpinion: String = "",
    val surveyScreenGeneralPoints: Int = 0,
    val surveyScreenGeneralOpinion: String = "",
    // End Features
)