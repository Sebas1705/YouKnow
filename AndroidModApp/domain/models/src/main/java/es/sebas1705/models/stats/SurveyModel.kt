package es.sebas1705.youknow.domain.model.stats
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

import es.sebas1705.youknow.core.classes.data.Opinion
import es.sebas1705.common.games.Languages

/**
 * SurveyModel is a data class that represents the survey model.
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
 */
data class SurveyModel(

    /////User:
    val authorFirebaseId: String,
    val authorNickName: String,
    val authorAge: Int,
    val profession: String,
    val language: Languages,
    val androidKnowing: Int,
    val applicationsKnowing: Int,
    val gamesKnowing: Int,
    val socialNetworksKnowing: Int,
    val otherKnowing: String,
    // End User

    /////Usability:
    //Learnability:
    val predictionOpinion: Opinion,
    val synthesisOpinion: Opinion,
    val familiarityOpinion: Opinion,
    val generalityOpinion: Opinion,
    val consistencyOpinion: Opinion,
    val learnabilityGeneralOpinion: Opinion,
    //Flexibility:
    val dialogInitiativeOpinion: Opinion,
    val multitaskingOpinion: Opinion,
    val taskControlOpinion: Opinion,
    val adaptationOpinion: Opinion,
    val substitutionOpinion: Opinion,
    val flexibilityGeneralOpinion: Opinion,
    //Robustness
    val observationCapacityOpinion: Opinion,
    val recuperationCapacityOpinion: Opinion,
    val responseCapacityOpinion: Opinion,
    val taskAdaptationOpinion: Opinion,
    val robustnessGeneralOpinion: Opinion,
    val usabilityGeneralOpinion: Opinion,
    // End Usability

    /////Accessibility:
    //Universal design:
    val equitableUseOpinion: Opinion,
    val flexibilityUseOpinion: Opinion,
    val simpleAndIntuitiveUseOpinion: Opinion,
    val perceptibleInformationOpinion: Opinion,
    val toleranceErrorOpinion: Opinion,
    val lowPhysicalEffortOpinion: Opinion,
    val sizeAndSpaceOpinion: Opinion,
    //Disabilities:
    /*Colors*/
    val visualProtanopiaOpinion: Opinion,
    val visualDeuteranopiaOpinion: Opinion,
    val visualTritanopiaOpinion: Opinion,
    /*Reduced vision*/
    val reducedVisionZoomAdapterOpinion: Opinion,
    /*Blindness*/
    val blindnessScreenReaderOpinion: Opinion,
    val blindnessElementsSoundOpinion: Opinion,
    /*Deafness*/
    val textInformationOpinion: Opinion,
    val simpleTextOpinion: Opinion,
    /*Reduced mobility*/
    val reducedMobilityOpinion: Opinion,
    /*Cognitive disabilities*/
    val cognitiveSimpleOpinion: Opinion,
    // End Accessibility

    /////UI/UX
    //Colors:
    val colorSchemeDarkOpinion: Opinion,
    val colorSchemeLightOpinion: Opinion,
    val colorSchemeContrastOpinion: Opinion,
    val colorSchemeGeneralOpinion: Opinion,
    //Fonts:
    val fontTypeTitleOpinion: Opinion,
    val fontTypeBodyOpinion: Opinion,
    val fontTypeSpecialOpinion: Opinion,
    val fontTypeGeneralOpinion: Opinion,
    //WindowAdaptation:
    val windowAdaptationOpinion: Opinion,
    //Navigation:
    val navigationButtonOpinion: Opinion,
    val navigationBottomBarOpinion: Opinion,
    val navigationGeneralOpinion: Opinion,
    // End UI/UX

    /////Features:
    //Screens:
    /*Splash Screen*/
    val splashScreenDesignOpinion: Opinion,
    val splashScreenGeneralOpinion: Opinion,
    /*Guide Screen*/
    val guideScreenDesignOpinion: Opinion,
    val guideScreenContentOpinion: Opinion,
    val guideScreenGeneralOpinion: Opinion,
    /*Menu Screen*/
    val menuScreenDesignOpinion: Opinion,
    val menuScreenContentOpinion: Opinion,
    val menuScreenGeneralOpinion: Opinion,
    /*Login Screen*/
    val loginScreenDesignOpinion: Opinion,
    val loginScreenContentOpinion: Opinion,
    val loginScreenGeneralOpinion: Opinion,
    /*Sign Screen*/
    val signScreenDesignOpinion: Opinion,
    val signScreenContentOpinion: Opinion,
    val signScreenGeneralOpinion: Opinion,
    /*Home Screen*/
    val homeScreenDesignOpinion: Opinion,
    val homeScreenContentOpinion: Opinion,
    val homeScreenGeneralOpinion: Opinion,
    /*Settings Screen*/
    val settingsScreenDesignOpinion: Opinion,
    val settingsScreenContentOpinion: Opinion,
    val settingsScreenGeneralOpinion: Opinion,
    /*Profile Screen*/
    val profileScreenDesignOpinion: Opinion,
    val profileScreenContentOpinion: Opinion,
    val profileScreenGeneralOpinion: Opinion,
    /*Chat Screen*/
    val chatScreenDesignOpinion: Opinion,
    val chatScreenContentOpinion: Opinion,
    val chatScreenGeneralOpinion: Opinion,
    /*Group Screen*/
    val groupScreenDesignOpinion: Opinion,
    val groupScreenContentOpinion: Opinion,
    val groupScreenGeneralOpinion: Opinion,
    /*Play Screen*/
    val playScreenDesignOpinion: Opinion,
    val playScreenContentOpinion: Opinion,
    val playScreenGeneralOpinion: Opinion,
    /*Mystery Number Screens*/
    val mysteryNumberScreenDesignOpinion: Opinion,
    val mysteryNumberScreenContentOpinion: Opinion,
    val mysteryNumberScreenGeneralOpinion: Opinion,
    /*Word Pass Screens*/
    val wordPassScreenDesignOpinion: Opinion,
    val wordPassScreenContentOpinion: Opinion,
    val wordPassScreenGeneralOpinion: Opinion,
    /*Quiz Screens*/
    val quizScreenDesignOpinion: Opinion,
    val quizScreenContentOpinion: Opinion,
    val quizScreenGeneralOpinion: Opinion,
    /*Families Screens*/
    val familiesScreenDesignOpinion: Opinion,
    val familiesScreenContentOpinion: Opinion,
    val familiesScreenGeneralOpinion: Opinion,
    /*Survey Screen*/
    val surveyScreenDesignOpinion: Opinion,
    val surveyScreenContentOpinion: Opinion,
    val surveyScreenGeneralOpinion: Opinion,
    // End Features
) {
    companion object {
        const val PAGES_N = 5
    }
}