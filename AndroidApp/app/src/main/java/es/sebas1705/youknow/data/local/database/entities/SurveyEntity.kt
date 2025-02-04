package es.sebas1705.youknow.data.local.database.entities
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

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.sebas1705.youknow.data.local.database.config.SettingsDB

/**
 * SurveyEntity is a data class that represents the survey entity.
 *
 * @since 1.0.0
 * @author Sebastián Ramiro Entrerrios García
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