package es.sebas1705.firestore.config

import es.sebas1705.firestore.config.SettingsFS.ERROR_GENERIC_MESSAGE_EX
import es.sebas1705.firestore.config.SettingsFS.ERROR_GENERIC_MESSAGE_FAIL

/**
 * Settings for Firestore
 *
 * @property USERS_COLLECTION_NAME [String]: Users collection reference
 * @property NEWS_COLLECTION_NAME [String]: News collection reference
 * @property SURVEYS_COLLECTION_NAME [String]: Surveys collection reference
 * @property USERS_LOGGED_FIELD [String]: Field to check if the user is logged
 * @property USERS_CREDITS_FIELD [String]: Field to store the user credits
 * @property USERS_POINTS_FIELD [String]: Field to store the user points
 * @property USERS_GROUP_FIELD [String]: Field to store the user group
 * @property USERS_PHOTO_FIELD [String]: Field to store the user photo
 * @property USERS_NICKNAME_FIELD [String]: Field to store the user nickname
 * @property ERROR_GENERIC_MESSAGE_EX [String]: Generic error message for exceptions
 * @property ERROR_GENERIC_MESSAGE_FAIL [String]: Generic error message for failure listeners
 * @property ERROR_CREDITS_NEGATIVE [String]: Error message for negative credits
 * @property USER_NOT_FOUND [String]: Error message for user not found
 * @property SURVEY_NOT_FOUND [String]: Error message for survey not found
 *
 * @author Sebas1705 22/09/2025
 * @since 1.0.0
 */
object SettingsFS {
    const val USERS_COLLECTION_NAME = "users"
    const val NEWS_COLLECTION_NAME = "news"
    const val SURVEYS_COLLECTION_NAME = "surveys"
    const val USERS_LOGGED_FIELD = "logged"
    const val USERS_CREDITS_FIELD = "credits"
    const val USERS_POINTS_FIELD = "points"
    const val USERS_GROUP_FIELD = "groupId"
    const val USERS_PHOTO_FIELD = "photoUrl"
    const val USERS_NICKNAME_FIELD = "nickName"

    const val ERROR_GENERIC_MESSAGE_EX = "An error occurred on firestore by an exception"
    const val ERROR_GENERIC_MESSAGE_FAIL = "An error occurred on firestore by failure listener"
    const val ERROR_CREDITS_NEGATIVE = "The credits can't be negative"
    const val USER_NOT_FOUND = "User not found"
    const val SURVEY_NOT_FOUND = "Survey not found"
}