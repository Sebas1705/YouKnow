package es.sebas1705.survey.schema

/**
 * One usability-survey question: [key] matches a `SurveyModel`/`SurveyDocument` `*Opinion`
 * property (without its "Opinion" suffix) and [label] is its human-readable title.
 *
 * @since 1.3.3
 * @author Sebas1705 30/09/2026
 */
data class SurveyQuestion(val key: String, val label: String)

/** A titled group of [SurveyQuestion]s shown together on a page. */
data class SurveySection(val title: String, val questions: List<SurveyQuestion>)

/**
 * The usability survey's ~94 opinion questions, grouped into [SurveyModel.PAGES_N] pages of
 * titled sections, in the exact order [es.sebas1705.mappers.toSurveyDocument] expects them.
 *
 * @since 1.3.3
 * @author Sebas1705 30/09/2026
 */
object SurveySchema {
    val pages: List<List<SurveySection>> = listOf(
        // Page 1: about you + Learnability
        listOf(
        SurveySection("Learnability", listOf(
            SurveyQuestion("prediction", "Prediction"),
            SurveyQuestion("synthesis", "Synthesis"),
            SurveyQuestion("familiarity", "Familiarity"),
            SurveyQuestion("generality", "Generality"),
            SurveyQuestion("consistency", "Consistency"),
            SurveyQuestion("learnabilityGeneral", "Learnability General"),
        )),
        ),
        // Page 2: Flexibility, Robustness, overall usability
        listOf(
        SurveySection("Flexibility", listOf(
            SurveyQuestion("dialogInitiative", "Dialog Initiative"),
            SurveyQuestion("multitasking", "Multitasking"),
            SurveyQuestion("taskControl", "Task Control"),
            SurveyQuestion("adaptation", "Adaptation"),
            SurveyQuestion("substitution", "Substitution"),
            SurveyQuestion("flexibilityGeneral", "Flexibility General"),
        )),
        SurveySection("Robustness", listOf(
            SurveyQuestion("observationCapacity", "Observation Capacity"),
            SurveyQuestion("recuperationCapacity", "Recuperation Capacity"),
            SurveyQuestion("responseCapacity", "Response Capacity"),
            SurveyQuestion("taskAdaptation", "Task Adaptation"),
            SurveyQuestion("robustnessGeneral", "Robustness General"),
        )),
        SurveySection("Overall Usability", listOf(
            SurveyQuestion("usabilityGeneral", "Usability General"),
        )),
        ),
        // Page 3: Accessibility (universal design + disabilities)
        listOf(
        SurveySection("Universal Design", listOf(
            SurveyQuestion("equitableUse", "Equitable Use"),
            SurveyQuestion("flexibilityUse", "Flexibility Use"),
            SurveyQuestion("simpleAndIntuitiveUse", "Simple And Intuitive Use"),
            SurveyQuestion("perceptibleInformation", "Perceptible Information"),
            SurveyQuestion("toleranceError", "Tolerance Error"),
            SurveyQuestion("lowPhysicalEffort", "Low Physical Effort"),
            SurveyQuestion("sizeAndSpace", "Size And Space"),
        )),
        SurveySection("Disabilities", listOf(
            SurveyQuestion("visualProtanopia", "Visual Protanopia"),
            SurveyQuestion("visualDeuteranopia", "Visual Deuteranopia"),
            SurveyQuestion("visualTritanopia", "Visual Tritanopia"),
            SurveyQuestion("reducedVisionZoomAdapter", "Reduced Vision Zoom Adapter"),
            SurveyQuestion("blindnessScreenReader", "Blindness Screen Reader"),
            SurveyQuestion("blindnessElementsSound", "Blindness Elements Sound"),
            SurveyQuestion("textInformation", "Text Information"),
            SurveyQuestion("simpleText", "Simple Text"),
            SurveyQuestion("reducedMobility", "Reduced Mobility"),
            SurveyQuestion("cognitiveSimple", "Cognitive Simple"),
        )),
        ),
        // Page 4: UI/UX (colors, fonts, window, navigation)
        listOf(
        SurveySection("Colors", listOf(
            SurveyQuestion("colorSchemeDark", "Color Scheme Dark"),
            SurveyQuestion("colorSchemeLight", "Color Scheme Light"),
            SurveyQuestion("colorSchemeContrast", "Color Scheme Contrast"),
            SurveyQuestion("colorSchemeGeneral", "Color Scheme General"),
        )),
        SurveySection("Fonts", listOf(
            SurveyQuestion("fontTypeTitle", "Font Type Title"),
            SurveyQuestion("fontTypeBody", "Font Type Body"),
            SurveyQuestion("fontTypeSpecial", "Font Type Special"),
            SurveyQuestion("fontTypeGeneral", "Font Type General"),
        )),
        SurveySection("Window Adaptation", listOf(
            SurveyQuestion("windowAdaptation", "Window Adaptation"),
        )),
        SurveySection("Navigation", listOf(
            SurveyQuestion("navigationButton", "Navigation Button"),
            SurveyQuestion("navigationBottomBar", "Navigation Bottom Bar"),
            SurveyQuestion("navigationGeneral", "Navigation General"),
        )),
        ),
        // Page 5: Features, one section per screen
        listOf(
        SurveySection("Splash Screen", listOf(
            SurveyQuestion("splashScreenDesign", "Splash Screen Design"),
            SurveyQuestion("splashScreenGeneral", "Splash Screen General"),
        )),
        SurveySection("Guide Screen", listOf(
            SurveyQuestion("guideScreenDesign", "Guide Screen Design"),
            SurveyQuestion("guideScreenContent", "Guide Screen Content"),
            SurveyQuestion("guideScreenGeneral", "Guide Screen General"),
        )),
        SurveySection("Menu Screen", listOf(
            SurveyQuestion("menuScreenDesign", "Menu Screen Design"),
            SurveyQuestion("menuScreenContent", "Menu Screen Content"),
            SurveyQuestion("menuScreenGeneral", "Menu Screen General"),
        )),
        SurveySection("Login Screen", listOf(
            SurveyQuestion("loginScreenDesign", "Login Screen Design"),
            SurveyQuestion("loginScreenContent", "Login Screen Content"),
            SurveyQuestion("loginScreenGeneral", "Login Screen General"),
        )),
        SurveySection("Sign Screen", listOf(
            SurveyQuestion("signScreenDesign", "Sign Screen Design"),
            SurveyQuestion("signScreenContent", "Sign Screen Content"),
            SurveyQuestion("signScreenGeneral", "Sign Screen General"),
        )),
        SurveySection("Home Screen", listOf(
            SurveyQuestion("homeScreenDesign", "Home Screen Design"),
            SurveyQuestion("homeScreenContent", "Home Screen Content"),
            SurveyQuestion("homeScreenGeneral", "Home Screen General"),
        )),
        SurveySection("Settings Screen", listOf(
            SurveyQuestion("settingsScreenDesign", "Settings Screen Design"),
            SurveyQuestion("settingsScreenContent", "Settings Screen Content"),
            SurveyQuestion("settingsScreenGeneral", "Settings Screen General"),
        )),
        SurveySection("Profile Screen", listOf(
            SurveyQuestion("profileScreenDesign", "Profile Screen Design"),
            SurveyQuestion("profileScreenContent", "Profile Screen Content"),
            SurveyQuestion("profileScreenGeneral", "Profile Screen General"),
        )),
        SurveySection("Chat Screen", listOf(
            SurveyQuestion("chatScreenDesign", "Chat Screen Design"),
            SurveyQuestion("chatScreenContent", "Chat Screen Content"),
            SurveyQuestion("chatScreenGeneral", "Chat Screen General"),
        )),
        SurveySection("Group Screen", listOf(
            SurveyQuestion("groupScreenDesign", "Group Screen Design"),
            SurveyQuestion("groupScreenContent", "Group Screen Content"),
            SurveyQuestion("groupScreenGeneral", "Group Screen General"),
        )),
        SurveySection("Play Screen", listOf(
            SurveyQuestion("playScreenDesign", "Play Screen Design"),
            SurveyQuestion("playScreenContent", "Play Screen Content"),
            SurveyQuestion("playScreenGeneral", "Play Screen General"),
        )),
        SurveySection("Mystery Number Screen", listOf(
            SurveyQuestion("mysteryNumberScreenDesign", "Mystery Number Screen Design"),
            SurveyQuestion("mysteryNumberScreenContent", "Mystery Number Screen Content"),
            SurveyQuestion("mysteryNumberScreenGeneral", "Mystery Number Screen General"),
        )),
        SurveySection("Word Pass Screen", listOf(
            SurveyQuestion("wordPassScreenDesign", "Word Pass Screen Design"),
            SurveyQuestion("wordPassScreenContent", "Word Pass Screen Content"),
            SurveyQuestion("wordPassScreenGeneral", "Word Pass Screen General"),
        )),
        SurveySection("Quiz Screen", listOf(
            SurveyQuestion("quizScreenDesign", "Quiz Screen Design"),
            SurveyQuestion("quizScreenContent", "Quiz Screen Content"),
            SurveyQuestion("quizScreenGeneral", "Quiz Screen General"),
        )),
        SurveySection("Families Screen", listOf(
            SurveyQuestion("familiesScreenDesign", "Families Screen Design"),
            SurveyQuestion("familiesScreenContent", "Families Screen Content"),
            SurveyQuestion("familiesScreenGeneral", "Families Screen General"),
        )),
        SurveySection("Survey Screen", listOf(
            SurveyQuestion("surveyScreenDesign", "Survey Screen Design"),
            SurveyQuestion("surveyScreenContent", "Survey Screen Content"),
            SurveyQuestion("surveyScreenGeneral", "Survey Screen General"),
        )),
        ),
    )
}
